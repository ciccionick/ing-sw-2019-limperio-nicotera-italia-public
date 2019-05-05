package it.polimi.se2019.limperio.nicotera.italia.network.server;

import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class VirtualView extends Observable<ClientEvent> implements Observer<ServerEvent>,Runnable {
    private Socket client;
    private boolean isClientCurrentlyOnline=true;
    private String nicknameOfClient;
    private String colorOfClient;
    private Server server;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Controller controller;
    private boolean firstPlayer;

     VirtualView(Socket client, Server server, Controller controller) {
        this.client = client;
        this.server = server;
        this.controller = controller;
        register(controller);
        if (server.getListOfClient().size() == 1)
            this.firstPlayer = true;
        else
            this.firstPlayer = false;
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                client.close();
            } catch (Exception er) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void run() {
        try {
            boolean invalidInizialization = true;
            RequestInitializationEvent req;
            while (invalidInizialization) {
                out.writeObject(new RequestInitializationEvent("Digit your nickname", true, false, false, false, false));
                AnswerInitializationEvent ans = (AnswerInitializationEvent) in.readObject();
                while(server.getListOfNickname().contains(ans.getNickname())){
                    req = new RequestInitializationEvent("Digit your nickname: ", true, false, false, false, false);
                    req.setRetake(true);
                    out.writeObject(req);
                    ans = (AnswerInitializationEvent) in.readObject();
                }
                server.addNickname(ans.getNickname());
                nicknameOfClient = ans.getNickname();
                out.writeObject(new RequestInitializationEvent("Digit your color:", false, true, false, false, false));
                ans = (AnswerInitializationEvent) in.readObject();
                while(server.getListOfColor().contains(ans.getColor().toUpperCase())){
                    req = new RequestInitializationEvent("Digit your color: ", false, true, false, false, false);
                    req.setRetake(true);
                    out.writeObject(req);
                    ans = (AnswerInitializationEvent) in.readObject();
                }
                server.getListOfColor().add(ans.getColor().toUpperCase());
                colorOfClient = ans.getColor();
                if(firstPlayer){
                    out.writeObject(new RequestInitializationEvent("Choose if u want frenzy:", false, false, true, false, false));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setAnticipatedFrenzy(ans.isFrenzy());
                    out.writeObject(new RequestInitializationEvent("Choose which map do you want to play with:", false, false, false, false, true));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setTypeMap(ans.getMap(), false);
                    out.writeObject(new RequestInitializationEvent("Choose if u want terminator mode:", false, false, false, true, false));
                    ans = (AnswerInitializationEvent) in.readObject();
                    server.setTerminatorMode(ans.isTerminator());
                }
                req = new RequestInitializationEvent("ack: ", false, false, false, false, false);
                req.setAck(true);
                out.writeObject(req);
                invalidInizialization=false;
            }
        } catch (SocketException se) {
            handleDisconnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (isClientCurrentlyOnline) {
            ClientEvent newEvent;
            try {
                System.out.println("La virtual view di " + nicknameOfClient + " e in attesa di messaggi provenienti dalla remote view..");
                newEvent = (ClientEvent) in.readObject();
                newEvent.setMyVirtualView(this);
                notify(newEvent);
            } catch (SocketException se) {
                handleDisconnection();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void update(ServerEvent message) {
        if(message.getNickname().contains(nicknameOfClient)) {
            try {
                out.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleDisconnection(){
        System.out.println("Il client " + nicknameOfClient + " si è disonnesso!");
        server.getListOfNickname().remove(nicknameOfClient);
        server.getListOfColor().remove(colorOfClient);
        server.deregister(this, client);
        isClientCurrentlyOnline=false;
        //QUI BISOGNA INVIARE UN MESSAGGIO AL CONTROLLER DICENDO CHE IL PLAYER CON IL NICKNAME nicknameOfClient SI E' DISCONNESSO IN MODO CHE IL MODEL LO SAPPIA E POSSA EVENTUALMENTE SALTARE IL SUO TURNO
        try {
            in.close();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}