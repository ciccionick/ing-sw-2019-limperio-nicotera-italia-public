package it.polimi.se2019.limperio.nicotera.italia.network.server;

import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import java.io.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     * The list of the clients connected
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
     * It's true if the game is started, false otherwise
     */
    private boolean gameStarted = false;
    /**
     * It's true if the first player decides to play in terminator mode, false otherwise. False for default
     */
    private boolean terminatorMode = false;
    /**
     * The list of virtual views of all the client in game.
     */
    private ArrayList<VirtualView> listOfVirtualView = new ArrayList<>();
    /**
     * The logger that track and store possibly exception.
     */
    private static Logger loggerServer = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    /**
     * The handler of the logger.
     */
    private static Handler handlerLoggerServer = new ConsoleHandler();

    /**
     * The color chosen by the first client connected.
     */
    private String colorOfFirstPlayer;




    /**
     * Constructor that creates an instance of the game, controller. Creates a server socket to receive connections
     * and read from file the duration of delay, setting the timer with his task
     */
    public Server()  {
        loggerServer.addHandler(handlerLoggerServer);
        try {
            serverSocket = new ServerSocket(4000);
            String ip;
            ip = InetAddress.getLocalHost().getHostAddress();
            new FrameForShowIP(ip);

        } catch (IOException e) {
           loggerServer.log(Level.ALL, "error");
        }
        game=Game.instanceOfGame();
        controller=new Controller(game);
        BufferedReader timerReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/timer/timerForStartOfGame.txt")));


        try {
            delay = Long.parseLong(timerReader.readLine());
            } catch (IOException e) {
                loggerServer.log(Level.ALL, "error");
            }
        finally {
            try {
                 timerReader.close();
            } catch (IOException e) {
                loggerServer.log(Level.ALL, "error");
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
                if (listOfColor.size() == 5) {
                    if (timer != null)
                        timer.cancel();
                    timer = null;
                    startGame();
                    break;
                }
                if (gameStarted)
                    break;
                Socket client = serverSocket.accept();
                if(!gameStarted) {
                    listOfClient.add(client);
                    VirtualView virtualView = new VirtualView(client, this, controller);
                    Thread thread = new Thread(virtualView);
                    thread.start();
                    listOfVirtualView.add(virtualView);
                    game.register(virtualView);
                }
                else
                    handleReconnectionClient(client);
            }
            catch (Exception e) {
                loggerServer.log(Level.ALL, "error");
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

        if(listOfClient.size()==2 && timer!=null && !gameStarted){
            timer.cancel();
            timer = null;
        }

    }


    /**
     * Starts the game passing to it all of the information that it needs. Remain in listening for
     * reconnection of players.
     * @throws IOException if there will be problems with the reconnection.
     */
    private void startGame() throws IOException {
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        gameStarted = true;
        for (int i = 0; i < listOfNickname.size(); i++) {
            game.createPlayer(listOfNickname.get(i), i == 0, i + 1, listOfColor.get(i).toUpperCase());
        }
        if (typeMap == 0) {
            setTypeMap(typeMap);
        }
        game.initializeGame(anticipatedFrenzy, typeMap, terminatorMode);
        controller.sendRequestToDrawPowerUpCard(game.getPlayers().get(game.getPlayerOfTurn()-1),2);
        for(VirtualView virtualView : listOfVirtualView){
            for(Player player : game.getPlayers()){
                virtualView.updateListOfPlayerBoard(player.getPlayerBoard());
            }
            virtualView.updateMap(game.getBoard().getMap().getMatrixOfSquares());
            virtualView.updateKillshotTrack(game.getBoard().getKillShotTrack());
            virtualView.setTerminatorMode(game.isTerminatorModeActive());
            virtualView.setTypeOfMap(game.getBoard().getMap().getTypeOfMap());
        }
        gameStarted = true;
        while (serverSocket.isBound()) {
            Socket client = serverSocket.accept();
             handleReconnectionClient(client);
        }
    }

    /**
     * Handles the reconnection of a client, rejected it if the client was not in the original list of client when the game was started.
     * @param client Socket of the client that wants to reconnect.
     */
    private void handleReconnectionClient(Socket client){
        VirtualView virtualView = new VirtualView(client, this, controller);
        String nicknameReconnected = virtualView.handleReconnection();
            if (!nicknameReconnected.equals("Failed reconnection")) {
                virtualView.sendAckAfterReconnection();
                for (VirtualView virtualVW : listOfVirtualView) {
                    if (virtualVW.getMyPlayerBoard().getNicknameOfPlayer().equals(nicknameReconnected)) {
                        virtualVW.setClient(client, virtualView.getIn(), virtualView.getOut());
                        virtualVW.setClientCurrentlyOnline(true);

                        try {
                            virtualVW.updateStatusAfterReconnection();
                            controller.handleReconnection(virtualVW.getMyPlayerBoard().getNicknameOfPlayer());
                        } catch (IOException e) {
                            loggerServer.log(Level.ALL, "error");
                        }
                        break;
                    }
                }
            }
            else {
                try {
                    client.close();
                } catch (IOException e) {
                    loggerServer.log(Level.ALL, "error");
                }
            }

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

    /**
     * Start the timer, scheduling the task has to start after the delay.
     */
    void startTimer() {
        timer = new Timer();
        task = new MyTask();
        try{
            timer.schedule(task,delay);
        }
        catch (IllegalStateException er){
            loggerServer.log(Level.ALL, "error");
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

    boolean isGameStarted() {
        return gameStarted;
    }

    public Game getGame() {
        return game;
    }

    public String getColorOfFirstPlayer() {
        return colorOfFirstPlayer;
    }

    public void setColorOfFirstPlayer(String colorOfFirstPlayer) {
        this.colorOfFirstPlayer = colorOfFirstPlayer;
    }

    /**
     * Calls the method that starts the game at the end of the timer
     */
    class MyTask extends TimerTask{
        /**
         * Logger that handle the possibly exception in the run method.
         */
        private  Logger loggerForTimerTaskInServer = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
        /**
         * The handler of the logger of the class.
         */
        private Handler handlerLoggerTimerTaskServer = new ConsoleHandler();

        /**
         * Calls the start of the game after the delay.
         */
        @Override
        public void run() {
            loggerForTimerTaskInServer.addHandler(handlerLoggerTimerTaskServer);
            try {
                if(listOfColor.size() == listOfClient.size())
                    startGame();
                else {
                    int numOfClient = listOfClient.size();
                    for(int i = 0; i<numOfClient; i++) {
                        if(!listOfVirtualView.get(i).isColorsAdded())
                            listOfVirtualView.get(i).handleDisconnection();
                        }
                    startGame();
                }
            } catch (IOException e) {
                loggerForTimerTaskInServer.log(Level.ALL, "error");
            }
        }

    }

}










