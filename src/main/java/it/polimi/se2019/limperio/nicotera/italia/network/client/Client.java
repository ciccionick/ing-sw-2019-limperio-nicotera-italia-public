package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;
import java.io.*;
import java.net.Socket;


public class Client {

   private boolean invalidInizialization=true;
   private NetworkHandler myNetworkHandler;
   private String nickname;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
   private Socket csocket = null;


    public Client(){
        this.myNetworkHandler=new NetworkHandler(this);
    }

    public NetworkHandler getMyNetworkHandler() {
        return myNetworkHandler;
    }

    public void changeNickname(String newNickname){
        this.nickname=newNickname;
    }


    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        Client client;
        System.out.println("Client attivo:");
        client = new Client();

        try
        {
            client.csocket = new Socket("localhost", 4000);
            client.out = new ObjectOutputStream(client.csocket.getOutputStream());
            client.in = new ObjectInputStream(client.csocket.getInputStream());
            System.out.println("In attesa del primo messaggio..");
            while(client.invalidInizialization) {
                RequestNicknameEvent req = (RequestNicknameEvent) client.in.readObject();
                client.myNetworkHandler.handleEventInitialization(req);
            }
        }
        catch(Exception e) { System.err.println(e.getMessage());
        }
        while(true){
            System.out.println("In attesa di messaggi..");
            ModelEvent eventFromModel = (ModelEvent) client.in.readObject();
            client.myNetworkHandler.handleEvent(eventFromModel);
        }

    }


    public String getNickname() {
        return nickname;
    }

    public ObjectInputStream getIn() {
        return in;
    }


    public void setInvalidInizialization(boolean invalidInizialization) {
        this.invalidInizialization = invalidInizialization;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
