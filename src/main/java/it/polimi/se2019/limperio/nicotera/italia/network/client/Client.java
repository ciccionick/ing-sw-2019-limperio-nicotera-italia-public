package it.polimi.se2019.limperio.nicotera.italia.network.client;


import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteViewInterface;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    boolean invalidNickname=true;
    private RemoteView ownView;
    private String nickname;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Socket csocket = null;

    public Client(String nickname){
        this.nickname=nickname;
        this.ownView=new RemoteView(this);
    }

    public RemoteView getOwnView() {
        return ownView;
    }

    public void changeNickname(String newNickname){
        this.nickname=newNickname;
    }


    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        Client client;
        Scanner stdin = new Scanner(System.in);
        String nick;
        System.out.println("Scegli il tuo nickname:");
        nick=stdin.nextLine();
        client = new Client(nick);

        try
        {
            client.csocket = new Socket("localhost", 4000);
            client.out = new ObjectOutputStream(client.csocket.getOutputStream());
            client.in = new ObjectInputStream(client.csocket.getInputStream());
            System.out.println("In attesa del primo messaggio..");
            while(client.invalidNickname) {
                RequestNicknameEvent req = (RequestNicknameEvent) client.in.readObject();
                client.ownView.handleEvent(req);
            }
        }
        catch(Exception e) { System.err.println(e.getMessage());
        }
        while(true){
            System.out.println("In attesa di messaggi..");
            ModelEvent modelEvent = (ModelEvent) client.in.readObject();
            client.ownView.handleEvent(modelEvent);
        }

    }

    public boolean isInvalidNickname() {
        return invalidNickname;
    }

    public String getNickname() {
        return nickname;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public Socket getCsocket() {
        return csocket;
    }

    public void setInvalidNickname(boolean invalidNickname) {
        this.invalidNickname = invalidNickname;
    }
}
