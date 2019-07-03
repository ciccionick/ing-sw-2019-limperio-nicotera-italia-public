package it.polimi.se2019.limperio.nicotera.italia.network.server;

import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.KillshotTrack;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.PlayerBoard;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * View of the client in the server side. Handle the communication with the client and the exchange of event.
 * <p>
 *     Each client has her virtual view.
 * </p>
 * <p>
 *     Run in a thread.
 * </p>
 *
 * @author Pietro L'Imperio
 */

public class VirtualView extends Observable<ClientEvent> implements Observer<ServerEvent>,Runnable {
    /**
     * The reference of the client associated
     */
    private Socket client;
    /**
     * It's true until the client is online, false later.
     */
    private boolean isClientCurrentlyOnline=true;
    /**
     * The nickname of the client associated
     */
    private String nicknameOfClient;
    /**
     * The color of the client associated
     */
    private String colorOfClient;
    /**
     * The reference of the server
     */
    private Server server;
    /**
     * The object input stream
     */
    private ObjectInputStream in = null;
    /**
     * The object output stream
     */
    private ObjectOutputStream out = null;
    /**
     * The reference of the controller
     */
    private Controller controller;
    /**
     * It's true if the client associated represents the first player, false otherwise
     */
    private boolean firstPlayer;

    /**
     * The list of all of player boards of all players in the game.
     */
    private ArrayList<PlayerBoard> listOfPlayerBoards = new ArrayList<>();

    /**
     * The player board of the player of the client linked with the current virtual view.
     */
    private PlayerBoard myPlayerBoard;

    /**
     * Matrix of square that represents the map always updated.
     */
    private Square[][] map;

    /**
     *  The killshot track always updated through the event received by the model.
     */
    private KillshotTrack killshotTrack;

    /**
     * It's true if the game has the terminator mode active.
     */
    private boolean terminatorMode = false;
    /**
     * The number to recognize what kind of map is playing with.
     */
    private int typeOfMap;
    /**
     * It's true if the stream with the client has been closed, false otherwise. True for default.
     */
    private boolean streamClosed = false;
    /**
     * The logger to catch and track possibly exception in the class.
     */
    private static Logger loggerVirtualView = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    /**
     * The handler of the logger.
     */
    private static Handler handlerLoggerVirtualView = new ConsoleHandler();

    /**
     * It's true if the color has been added to the list of color in the server class. Otherwise false.
     */
    private boolean colorsAdded = false;

    /**
     * Sets first player attribute, initialize the object streams
     * @param client The client associated
     * @param server The server that created it
     * @param controller The controller which he will send events by client with
     */
     public VirtualView(Socket client, Server server, Controller controller) {
         loggerVirtualView.addHandler(handlerLoggerVirtualView);
        this.client = client;
        this.server = server;
        this.controller = controller;
        register(controller);
        this.firstPlayer = server.getListOfClient().size() ==1;

        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            loggerVirtualView.log(Level.ALL, "error");
            try {
                client.close();
            } catch (Exception er) {
                loggerVirtualView.log(Level.ALL, "error");
            }
        }
    }

    /**
     * Manages the first phase of initialization of the client and then will remain in listening for
     * the handle of the event by client
     */
    @Override
    public void run() {
        try {
            boolean invalidInitialization = true;
            RequestInitializationEvent req;
            if(!server.isGameStarted()) {
                while (invalidInitialization) {
                    out.writeObject(new RequestInitializationEvent("Digit your nickname", true, false, false, false, false));
                    AnswerInitializationEvent ans = (AnswerInitializationEvent) in.readObject();
                    while (isNotValidNickname(ans.getNickname())) {
                        req = new RequestInitializationEvent("Digit your nickname: ", true, false, false, false, false);
                        req.setRetake(true);
                        out.writeObject(req);
                        ans = (AnswerInitializationEvent) in.readObject();
                    }
                    server.addNickname(ans.getNickname());
                    nicknameOfClient = ans.getNickname();
                    out.writeObject(new RequestInitializationEvent("Digit your color:", false, true, false, false, false));
                    ans = (AnswerInitializationEvent) in.readObject();
                    while (server.getListOfColor().contains(ans.getColor().toUpperCase()) || (server.getColorOfFirstPlayer()!=null && ans.getColor().equalsIgnoreCase(server.getColorOfFirstPlayer()))) {
                        req = new RequestInitializationEvent("Digit your color: ", false, true, false, false, false);
                        req.setRetake(true);
                        out.writeObject(req);
                        ans = (AnswerInitializationEvent) in.readObject();
                    }

                    if(!firstPlayer) {
                        server.getListOfColor().add(ans.getColor().toUpperCase());
                        colorsAdded=true;
                    }
                    else
                        server.setColorOfFirstPlayer(ans.getColor().toUpperCase());

                    if (server.getListOfColor().size() == 3)
                        server.startTimer();
                    colorOfClient = ans.getColor().toUpperCase();
                    if (firstPlayer) {
                        out.writeObject(new RequestInitializationEvent("Choose if u want frenzy:", false, false, true, false, false));
                        ans = (AnswerInitializationEvent) in.readObject();
                        server.setAnticipatedFrenzy(ans.isFrenzy());
                        out.writeObject(new RequestInitializationEvent("Choose which map do you want to play with:", false, false, false, false, true));
                        ans = (AnswerInitializationEvent) in.readObject();
                        if (ans.getMap() != 0)
                            server.setTypeMap(ans.getMap());
                        out.writeObject(new RequestInitializationEvent("Choose if u want terminator mode:", false, false, false, true, false));
                        ans = (AnswerInitializationEvent) in.readObject();
                        server.setTerminatorMode(ans.isTerminator());
                        if(!server.isGameStarted() && server.getListOfColor().size()<6) {
                            server.getListOfColor().add(colorOfClient);
                            colorsAdded=true;
                            if(server.getListOfColor().size()==3)
                                server.startTimer();
                        }
                    }
                    req = new RequestInitializationEvent("", false, false, false, false, false);
                    req.setAck(true);
                    out.writeObject(req);
                    invalidInitialization = false;
                }

            }

        } catch (IOException se) {
            try {
                client.close();
            } catch (IOException e) {
                loggerVirtualView.log(Level.ALL, "error");
            }

        }catch (ClassNotFoundException e) {
           loggerVirtualView.log(Level.ALL, "error");
        }

        receiveEvents();


    }

    /**
     * Waits for event receiving by the client.
     */
    private void receiveEvents() {
        while (isClientCurrentlyOnline) {
            try{
                ClientEvent newEvent;
                newEvent = (ClientEvent) in.readObject();
                newEvent.setMyVirtualView(this);
                notify(newEvent);
            }
            catch (SocketException ex){
                handleDisconnection();
                break;
            }
            catch(IOException | ClassNotFoundException e){
               loggerVirtualView.log(Level.ALL, "error");
            }
        }

    }

    /**
     * Handles the reconnection of the client after his disconnection.
     * @return The nickname of the client that is trying to reconnect or "Failed reconnection" if didnt' exists a client with that nickname at the beginning of the game.
     */
     String handleReconnection() {
        RequestInitializationEvent req = new RequestInitializationEvent("The game is already started: if you were disconnected, reinsert your previous nickname and you will be readmitted to the game", true, false, false, false, false );
         try {
             out.writeObject(req);
             AnswerInitializationEvent answer = (AnswerInitializationEvent) in.readObject();
             if(server.getListOfNickname().contains(answer.getNickname())){
                 for(Player player  : server.getGame().getPlayers()) {
                     if(player.getNickname().equals(answer.getNickname()) && !player.isConnected())
                        return answer.getNickname();
                 }
                 return "Failed reconnection";
             }
             else
                 return "Failed reconnection";
         } catch (IOException e) {
             System.exit(0);
         } catch (ClassNotFoundException e) {
            loggerVirtualView.log(Level.ALL, "error");
         }
         return "Failed reconnection";
     }

    /**
     * Handles the following phase after the reconnection  restoring the situation in the remote view sending all of the information stored during his absence.
     * @throws IOException If there will be problems with writeObject with output object stream of client socket.
     */
     void updateStatusAfterReconnection() throws IOException {
        for(PlayerBoard playerBoard : listOfPlayerBoards) {
            PlayerBoardEvent otherPlayerBoardEvent = new PlayerBoardEvent();
            otherPlayerBoardEvent.setNicknameInvolved(nicknameOfClient);
            otherPlayerBoardEvent.setPlayerBoard(playerBoard);
            otherPlayerBoardEvent.setMessageForInvolved("You are reconnected correctly");
            out.writeObject(otherPlayerBoardEvent);
        }
        KillshotTrackEvent killshotTrackEvent = new KillshotTrackEvent("", killshotTrack);
        killshotTrackEvent.setNicknameInvolved(nicknameOfClient);
        killshotTrackEvent.setNicknamePlayerOfTheTurn(server.getGame().getPlayers().get(server.getGame().getPlayerOfTurn()-1).getNickname());
        out.writeObject(killshotTrackEvent);
        MapEvent mapEvent = new MapEvent();
        mapEvent.setMap(map);
        mapEvent.setNicknameInvolved(nicknameOfClient);
        mapEvent.setTerminatorMode(terminatorMode);
        mapEvent.setTypeOfMap(typeOfMap);
        out.writeObject(mapEvent);
        Timer timer = new Timer();
        timer.schedule(new MyTask(), 1000);
    }

    /**
     * Checks if the nickname chosen by the client is valid or not. It's not valid if is equal to "termintor", "me" or is smaller than 2 character.
     * @param nickname The nickname to check is validation.
     * @return True if the nickname is valid, false otherwise.
     */
    private boolean isNotValidNickname(String nickname) {
        if(server.getListOfNickname().contains(nickname))
            return true;
        if(nickname.equalsIgnoreCase("me"))
            return true;
        if(nickname.equalsIgnoreCase("terminator"))
            return true;
        return nickname.length()<2;
    }

    /**
     * Sends events received by Model and Controller to the client through socket stream only if its client is involved
     * @param event The event received
     */
    @Override
    public void update(ServerEvent event) {
        if(isClientCurrentlyOnline && (event.getNicknames().contains(nicknameOfClient) || event.getNicknameInvolved().equals(nicknameOfClient))) {
            try {
                out.writeObject(event);
            } catch (IOException e) {
               handleDisconnection();
            }
        }

    }

    /**
     * It is called by Server during the initialization of the game; It fills the list of player board that contains the information of all player board in order to allow the right reconnection
     * @param playerBoard that has to be added to the list
     */
     void updateListOfPlayerBoard(PlayerBoard playerBoard) {
        if(playerBoard.getNicknameOfPlayer().equals(nicknameOfClient))
            myPlayerBoard = playerBoard;
        listOfPlayerBoards.add(playerBoard);
    }

    /**
     * Updates the status of the map through the events received by model.
     * @param map Map to set in the right field.
     */
     void updateMap(Square[][] map) {
        this.map = map;
    }

    /**
     * Updates the status of the killshot track through the events received by model.
     * @param killshotTrack Killshot track to set in the right field.
     */
     void updateKillshotTrack(KillshotTrack killshotTrack) {
        this.killshotTrack = killshotTrack;
    }


    /**
     * Handles the disconnection of the client sending information to the server and the controller
     */
     void handleDisconnection(){
        isClientCurrentlyOnline=false;
         closeStream();
        if(!server.isGameStarted()) {
            server.getListOfNickname().remove(nicknameOfClient);
            server.getListOfColor().remove(colorOfClient);
            if(firstPlayer)
                server.setColorOfFirstPlayer(null);
            server.deregister(this, client);
        }
        else{
            server.getListOfClient().remove(client);
            if(server.getListOfClient().size()==3 && !server.getGame().isGameOver())
                controller.getRoundController().handleEndOfGame(true);
            else if(server.getGame().isGameOver() && server.getListOfClient().size()==1)
                server.closeProcess();
            else
                controller.handleDisconnection(nicknameOfClient);
        }
    }

    /**
     * Sets the socket of the client and the related object input/output streams.
     * @param client Socket of the client.
     * @param in Object input stream of the client socket.
     * @param out Object output stream of the client socker.
     */
    void setClient(Socket client, ObjectInputStream in, ObjectOutputStream out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }

    /**
     * Sends an ack message to the client when the phase of reconnection is over.
     */
    void sendAckAfterReconnection() {
        RequestInitializationEvent ackEvent = new RequestInitializationEvent("", false, false, false, false, false);
        ackEvent.setAck(true);
        try {
            out.writeObject(ackEvent);
        } catch (IOException e) {
            loggerVirtualView.log(Level.ALL, "error");
        }
    }

    /**
     * Closes the stream related with the socket of the client after a disconnection or at the end of the game.
     */
    private void closeStream() {
        if(!streamClosed) {
            try {
                client.close();
                in.close();
                out.close();
                streamClosed = true;
            } catch (IOException e) {
                loggerVirtualView.log(Level.ALL, "error");
            }
        }
    }


     PlayerBoard getMyPlayerBoard() {
        return myPlayerBoard;
    }

    public Square[][] getMap() {
        return map;
    }

     void setClientCurrentlyOnline(boolean clientCurrentlyOnline) {
        isClientCurrentlyOnline = clientCurrentlyOnline;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream objectOutputStream){
         this.out= objectOutputStream;};

     void setTerminatorMode(boolean terminatorMode) {
        this.terminatorMode = terminatorMode;
    }

     void setTypeOfMap(int typeOfMap) {
        this.typeOfMap = typeOfMap;
    }

     boolean isColorsAdded() {
        return colorsAdded;
    }

    class MyTask extends TimerTask{
        @Override
        public void run() {
            receiveEvents();
        }
    }
}