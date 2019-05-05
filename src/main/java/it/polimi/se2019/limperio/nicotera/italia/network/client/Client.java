package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/** Class for the handle of the socket client side
 * @author Pietro L'Imperio */
public class Client {

   private boolean invalidInitialization =true;
   private NetworkHandler myNetworkHandler;
   private String nickname;
   ObjectInputStream in = null;
   ObjectOutputStream out = null;
   private Socket csocket = null;


    public Client(){
        this.myNetworkHandler=new NetworkHandler(this);
    }

    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        Client client;
        System.out.println("Client attivo:");
        client = new Client();


            client.csocket = new Socket("localhost", 4000);
            client.out = new ObjectOutputStream(client.csocket.getOutputStream());
            client.in = new ObjectInputStream(client.csocket.getInputStream());
            System.out.println("In attesa del primo messaggio..");

            while(client.invalidInitialization) {
                RequestInitializationEvent req = (RequestInitializationEvent) client.in.readObject();
                if(req.isAck())
                    break;
                client.myNetworkHandler.handleEventInitialization(req);
            }


        while(true){
            try {
                System.out.println("In attesa di messaggi..");
                ServerEvent eventFromModel = (ServerEvent) client.in.readObject();
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


    /**
     *
     * @param invalidInitialization means that the process of initialization is not complete
     */
    void setInvalidInitialization(boolean invalidInitialization) {
        this.invalidInitialization = invalidInitialization;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
