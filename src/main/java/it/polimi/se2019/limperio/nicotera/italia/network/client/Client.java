package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Handles the client and his socket
 *
 * @author Pietro L'Imperio */
public class Client {
    /**
     * The reference of the network handler of the client
     */
   private NetworkHandler myNetworkHandler;
    /**
     * The nickname of the player linked with this client
     */
   private String nickname;
    /**
     * The object input stream linked with the socket of client
     */
   private ObjectInputStream in = null;
    /**
     * The object output stream linked with the socket of client
     */
   ObjectOutputStream out = null;
    /**
     * The socket of the client
     */
   private Socket csocket = null;

   private String ipAddress = null;

   private static Logger clientLogger =Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
   private static Handler handlerLoggerClient  = new ConsoleHandler();




    public Client(){
        this.myNetworkHandler=new NetworkHandler(this);
        clientLogger.addHandler(handlerLoggerClient);
    }



    void handleConnectionWithServer() throws IOException {

        try {
            this.csocket = new Socket(ipAddress, 4000);
        }
        catch (SocketException e){
            System.exit(0);
        }
        this.out = new ObjectOutputStream(this.csocket.getOutputStream());
        this.in = new ObjectInputStream(this.csocket.getInputStream());
        Timer timer;
        //timer = new Timer();
        //timer.schedule(new TaskForStart(), 1000);
        waitForMessagesOfInitialization();

    }

     private void waitForMessagesOfInitialization(){

            while(true) {
                RequestInitializationEvent req = null;
                    try {
                        req = (RequestInitializationEvent) this.in.readObject();
                    } catch (IOException | ClassNotFoundException ex) {
                        System.exit(0);
                    }
                this.myNetworkHandler.handleEventInitialization(req);
                    if (req!=null && req.isAck())
                        break;
            }
        waitForMessage();
    }


    private void waitForMessage(){
        while(true){
            try {
                ServerEvent eventFromModel = (ServerEvent) this.in.readObject();
                if(eventFromModel.isFinished())
                    break;
                this.myNetworkHandler.handleEvent(eventFromModel);
            }
            catch (SocketException se){
                System.exit(0);
            } catch (IOException | ClassNotFoundException e) {
                clientLogger.log(Level.ALL, "error");
            }

        }
    }


    public String getNickname() {
        return nickname;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public NetworkHandler getMyNetworkHandler()
    {
        return myNetworkHandler;
    }

     void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private class TaskForStart extends TimerTask {
        @Override
        public void run() {
            waitForMessagesOfInitialization();
        }
    }
}
