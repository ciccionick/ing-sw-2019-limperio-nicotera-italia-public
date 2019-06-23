package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Timer;
import java.util.TimerTask;

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

   private FrameForRequestIP frameForRequestIP;

   private Timer timer = null;

   private int delay = 1000;




    public Client(){
        this.myNetworkHandler=new NetworkHandler(this);
    }



    void handleConnectionWithServer() throws IOException {

        this.csocket = new Socket(ipAddress, 4000);
        this.out = new ObjectOutputStream(this.csocket.getOutputStream());
        this.in = new ObjectInputStream(this.csocket.getInputStream());
        timer = new Timer();
        timer.schedule(new TaskForStart(), delay);
        //waitForMessage();



    }

     void waitForMessage(){

            while(true) {
                RequestInitializationEvent req = null;
                    try {
                        req = (RequestInitializationEvent) this.in.readObject();
                    } catch (EOFException ex) {
                        System.exit(0);
                    } catch (IOException | ClassNotFoundException e) {
                        System.exit(0);
                    }
                    this.myNetworkHandler.handleEventInitialization(req);
                    if (req!=null && req.isAck())
                        break;
            }


        while(true){
            try {

                ServerEvent eventFromModel = (ServerEvent) this.in.readObject();
                if(eventFromModel.isFinished())
                    break;
                this.myNetworkHandler.handleEvent(eventFromModel);
            }
            catch (SocketException se){
                System.out.println("Disconnessione...");
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setFrameForRequestIP(FrameForRequestIP frameForRequestIP) {
        this.frameForRequestIP = frameForRequestIP;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private class TaskForStart extends TimerTask {
        @Override
        public void run() {
            waitForMessage();
        }
    }
}
