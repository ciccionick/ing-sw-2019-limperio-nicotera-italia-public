package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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

   private String ipAddress;

   private boolean hasToInsertIP = true;

   private FrameForRequestIP frameForRequestIP;


    public Client(){
        this.myNetworkHandler=new NetworkHandler(this);
    }

    /**
     * Starts when the application run in the client side
     * @param argv Parameter of main
     * @throws IOException if there will be some problem with initialization of the streams
     * @throws ClassNotFoundException if there will be problems with the object received by the object input stream
     */
    public static void main(String[] argv) throws IOException, ClassNotFoundException {
        Client client;
        client = new Client();


        /*client.frameForRequestIP = new FrameForRequestIP(client);
        while(client.hasToInsertIP){
            num++;
        }*/


            client.csocket = new Socket("localhost", 4000);
            client.out = new ObjectOutputStream(client.csocket.getOutputStream());
            client.in = new ObjectInputStream(client.csocket.getInputStream());
            System.out.println("In attesa del primo messaggio..");

            while(true) {
                RequestInitializationEvent req = (RequestInitializationEvent) client.in.readObject();
                client.myNetworkHandler.handleEventInitialization(req);
                if(req.isAck())
                    break;
                }


        while(true){
            try {
                System.out.println("In attesa di messaggi..");
                ServerEvent eventFromModel = (ServerEvent) client.in.readObject();
                if(eventFromModel.isFinished())
                    break;
                client.myNetworkHandler.handleEvent(eventFromModel);
            }
            catch (SocketException se){
                System.out.println("Disconnessione...");
                break;
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

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isHasToInsertIP() {
        return hasToInsertIP;
    }

    public void setHasToInsertIP(boolean hasToInsertIP) {
        this.hasToInsertIP = hasToInsertIP;
    }
}
