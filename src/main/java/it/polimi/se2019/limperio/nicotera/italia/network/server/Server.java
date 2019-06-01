package it.polimi.se2019.limperio.nicotera.italia.network.server;


import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Handles all of the operation that the server does and his socket
 *
 * @author Pietro L'Imperio
 */
public class Server  {
    /**
     * The server socket used to accept connection by clients
     */
    private ServerSocket serverSocket;
    /**
     * The reference of the game
     */
    private Game game;
    /**
     * The reference of the controller
     */
    private Controller controller;
    /**
     * The list of the client connected
     */
    private ArrayList<Socket> listOfClient = new ArrayList<>();
    /**
     * The delay after which start the game
     */
    private long delay;
    /**
     * The timer that starts when 3 client will connect to play
     */
    private Timer timer = null;
    /**
     * The task that starts after the delay
     */
    private MyTask task;
    /**
     * The list of the nicknames of the clients that participate to the game, in order of their connection
     */
    private ArrayList<String> listOfNickname = new ArrayList<>();
    /**
     * The list of the colors of the clients that participate to the game, in order of their connection
     */
    private ArrayList<String> listOfColor = new ArrayList<>();
    /**
     * It's true if the first player decides that at the end there will be the last turn in frenzy mode, false otherwise. True for default
     */
    private boolean anticipatedFrenzy=true;
    /**
     * The type of map chosen by the player to play with. 0 for default to be modified later automatically
     */
    private int typeMap=0;
    /**
     * It's true if the game started, false otherwise
     */
    private boolean gameIsStarted = false;
    /**
     * It's true if the first player decides to play in terminator mode, false otherwise. False for default
     */
    private boolean terminatorMode = false;

    private ArrayList<VirtualView> listOfVirtualView = new ArrayList<>();


    /**
     * Starts when the application running on the server side
     * @param argv Classic main parameter
     * @throws Exception if there will be some problem of creation of the server
     */
    public static void main(String[] argv) throws Exception {
        new Server();
    }

    /**
     * Constructor that creates an instance of the game, controller. Creates a server socket to receive connections
     * and read from file the duration of delay, setting the timer with his task
     * @throws IOException if there would be problems with the file where reading the delay for the timer
     */
    private Server() {
        try {
            serverSocket = new ServerSocket(4000);
            String ip;
            ip = InetAddress.getLocalHost().getHostAddress();
            System.out.println(ip);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Il Server è in attesa sulla porta 4000.");
        game=Game.instanceOfGame();
        controller=new Controller(game);
        game.setController(controller);
        File file;
        FileReader inFile = null;
        BufferedReader bin=null;
        file = new File("resources/timer/timerForStartOfGame.txt");

        try {
            inFile = new FileReader(file);
            bin = new BufferedReader(inFile);
            delay = Long.parseLong(bin.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }
        finally {
            try {
                if(bin!=null)
                    bin.close();
                if(inFile!=null)
                    inFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        task = new MyTask();
        acceptConnection();
    }

    /**
     * Handles the connections of the clients creating for each one a {@link VirtualView} in a separated thread
     */
    private void acceptConnection() {

        while (true) {
            try {

                if (listOfColor.size()==5){
                    if(timer!=null)
                        timer.cancel();
                    timer=null;
                    startGame();
                    break;
                }

                System.out.println("In attesa di Connessione.");
                Socket client = serverSocket.accept();
                listOfClient.add(client);
                System.out.println("Connessione accettata da: " + client.getInetAddress());
                VirtualView virtualView = new VirtualView(client, this, controller);
                Thread thread = new Thread(virtualView);
                thread.start();
                listOfVirtualView.add(virtualView);
                game.register(virtualView);

            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Removes in synchronized way a {@link VirtualView} from the list of virtual view in the game
     * and a client from the list of his clients connected
     * @param view The view to remove
     * @param client The client to remove
     */
     void deregister(VirtualView view, Socket client) {
        synchronized (this) {
            game.deregister(view);
            listOfClient.remove(client);
        }

        if(listOfClient.size()==2 && timer!=null && !gameIsStarted){
            timer.cancel();
            timer = null;
        }
        if(listOfClient.size()==2 && gameIsStarted && !game.isGameOver()){
            controller.getRoundController().handleEndOfGame();
        }

    }


    /**
     * Starts the game passing to it all of the information that it needs. Remain in listening for
     * reconnection of players.
     * @throws IOException if there will be problems with the reconnection.
     */
    private void startGame() throws IOException {
        gameIsStarted =true;
        for (int i = 0; i < listOfNickname.size(); i++) {
            game.createPlayer(listOfNickname.get(i), i == 0, i + 1, listOfColor.get(i).toUpperCase());
        }
        if (typeMap == 0) {
            setTypeMap(typeMap);
        }
        game.initializeGame(anticipatedFrenzy, typeMap, terminatorMode);
        gameIsStarted = true;
        while (serverSocket.isBound()) {
            Socket client = serverSocket.accept();
            handleReconnectionClient(client);
        }
    }

    private void handleReconnectionClient(Socket client){
        for(VirtualView virtualView : listOfVirtualView){
            if(client.getLocalAddress().getHostAddress().equals(virtualView.getIPAddress())){
                virtualView.handleReconnection();
                break;
            }
        }

    }

     void setTerminatorMode(boolean terminatoreMode) {
        this.terminatorMode = terminatoreMode;
    }


    synchronized void addNickname(String newNickname){
        listOfNickname.add(newNickname);
    }

     ArrayList<Socket> getListOfClient() {
        return listOfClient;
    }

     ArrayList<String> getListOfNickname() {
        return listOfNickname;
    }

     ArrayList<String> getListOfColor() {
        return listOfColor;
    }

     void setAnticipatedFrenzy(boolean anticipatedFrenzy) {
        this.anticipatedFrenzy = anticipatedFrenzy;
    }

    public boolean isGameIsStarted() {
        return gameIsStarted;
    }

    /**
     * Sets the type of map when the first player could not do that in accordance with the number of players.
     * @param typeMap The current type of map
     *
     */
    void setTypeMap(int typeMap) {
        if(typeMap!=0)
            this.typeMap=typeMap;
        else
        {
            if(listOfNickname.size()==3)
                this.typeMap=1;
            if(listOfNickname.size()==4)
                this.typeMap=2;
            if(listOfNickname.size()==5)
                this.typeMap=3;
        }
    }

    void startTimer() {
        System.out.println("Il timer è partito!");
        timer = new Timer();
        task = new MyTask();
        try{
            timer.schedule(task,delay);
        }
        catch (IllegalStateException er){
            er.printStackTrace();
        }
    }


    /**
     * Calls the method that starts the game at the end of the timer
     */

    class MyTask extends TimerTask{

        @Override
        public void run() {
            try {
                if(listOfColor.size() == listOfClient.size())
                    startGame();
                else {
                    int numOfClient = listOfClient.size();
                    while(numOfClient > listOfColor.size()){
                        listOfClient.get(numOfClient-1).close();
                        deregister(listOfVirtualView.get(numOfClient-1), listOfClient.get(numOfClient-1));
                        if(listOfNickname.size()>listOfColor.size())
                            listOfNickname.remove(numOfClient-1);
                        numOfClient--;
                    }
                    startGame();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}










