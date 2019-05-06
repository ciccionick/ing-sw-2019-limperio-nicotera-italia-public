package it.polimi.se2019.limperio.nicotera.italia.network.server;


import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


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
    private final long delay;
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
    private Server() throws  IOException {
        serverSocket = new ServerSocket(4000);
        System.out.println("Il Server è in attesa sulla porta 4000.");
        game=Game.instanceOfGame();
        controller=new Controller(game);
        File file = new File("resources/timer/timerForStartOfGame.txt");
        FileReader inFile = new FileReader(file);
        BufferedReader bin = new BufferedReader(inFile);
        delay = Long.parseLong(bin.readLine());
        System.out.println("Il timer è a: " + delay/1000 + " secondi");
        task = new MyTask();
        run();
    }

    /**
     * Handles the connections of the clients creating for each one a {@link VirtualView} in a separated thread
     */
    private void run() {

        while (true) {
            try {
                if(listOfClient.size()==3 && timer==null){
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
                if (listOfClient.size()==5){
                    timer.cancel();
                    timer=null;
                    System.out.println("Il timer e' stato fermato perchè sta per cominciare il gioco!");
                    TimeUnit.SECONDS.sleep(10);
                    startGame();
                }

                System.out.println("In attesa di Connessione.");
                Socket client = serverSocket.accept();
                listOfClient.add(client);
                System.out.println("Connessione accettata da: " + client.getInetAddress());
                VirtualView virtualView = new VirtualView(client, this, controller);
                game.register(virtualView);
                Thread thread = new Thread(virtualView);
                thread.start();
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
        if(!listOfNickname.isEmpty()) {
            System.out.println("Sono rimasti in gioco: ");
            for (String name : listOfNickname) {
                System.out.println(name);
            }
        }
        if(listOfClient.size()==2 && timer!=null && !gameIsStarted){
            timer.cancel();
            timer = null;
            System.out.println("Il timer è stato fermato perchè siamo scesi a due giocatori");
        }
        if(listOfClient.size()==2 && gameIsStarted){
            System.out.println("Il controller conta i punti");
        }
    }


    /**
     * Starts the game passing to it all of the information that it needs. Remain in listening for
     * reconnection of players.
     * @throws IOException if there will be problems with the reconnection.
     */
    private void startGame() throws IOException {

        System.out.println("I players i gioco sono: ");
        for (int i = 0; i < listOfNickname.size(); i++) {
            game.createPlayer(listOfNickname.get(i), i == 0, i + 1, listOfColor.get(i).toUpperCase());
        }
        if (typeMap == 0) {
            setTypeMap(1, true);
        }
        game.startGame(anticipatedFrenzy, typeMap, terminatorMode);
        gameIsStarted = true;
        while (serverSocket.isBound()) {
            Socket client = serverSocket.accept();
            handleReconnectionClient(client);
        }
    }

    private void handleReconnectionClient(Socket client){

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

     boolean isAnticipatedFrenzy() {
        return anticipatedFrenzy;
    }

    /**
     * Sets the type of map when the first player could not do that in accordance with the number of players.
     * @param typeMap The current type of map
     * @param defaultValue It's true if the value was not modified by the first client, false otherwise
     */
    void setTypeMap(int typeMap, boolean defaultValue) {
        if(!defaultValue) {
            this.typeMap = typeMap;
        }
        else{
            switch (listOfClient.size()){
                case 4:
                    setTypeMap(2, false);
                    break;
                case 5:
                    setTypeMap(3, false);
                    break;
                default:
                    setTypeMap(1, false);
            }
        }
    }


    /**
     * Calls the method that starts the game at the end of the timer
     */

    class MyTask extends TimerTask{

        @Override
        public void run() {
            try {
                startGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}










