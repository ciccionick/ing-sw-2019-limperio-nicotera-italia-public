package it.polimi.se2019.limperio.nicotera.italia.network.server;

import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.AnswerNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class VirtualView extends Observable<ViewEvent> implements Observer<ModelEvent>,Runnable {
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
            while (invalidInizialization) {
                out.writeObject(new RequestNicknameEvent("Chiedo informazioni al mio player", null, true, false, false, firstPlayer));

                AnswerNicknameEvent ans = (AnswerNicknameEvent) in.readObject();
                if (server.getListOfNickname().contains(ans.getNickname()) && server.getListOfColor().contains(ans.getColor())) {
                    out.writeObject(new RequestNicknameEvent("nickname e colori gia scelti, riprova al prossimo tentativo", null, false, true, false, firstPlayer));
                } else {
                    if (server.getListOfColor().contains(ans.getColor())) {
                        out.writeObject(new RequestNicknameEvent("colore gia scelto, riprova al prossimo tentativo", null, false, true, false, firstPlayer));
                    }
                    if (server.getListOfNickname().contains(ans.getNickname())) {
                        out.writeObject(new RequestNicknameEvent("nickname gia scelto, riprova al prossimo tentativo", null, false, true, false, firstPlayer));
                    }
                    if (!(server.getListOfNickname().contains(ans.getNickname())) && !(server.getListOfColor().contains(ans.getColor()))) {
                        out.writeObject(new RequestNicknameEvent("Tutto ok " + ans.getNickname(), ans.getNickname(), false, true, true, firstPlayer));
                        server.addNickname(ans.getNickname(), ans.getColor());
                        nicknameOfClient = ans.getNickname();
                        colorOfClient = ans.getColor();
                        invalidInizialization = false;
                        if (firstPlayer) {
                            server.setAnticipatedFrenzy(ans.isFrenzy());
                            System.out.println("Aggiornato frenzy a " + server.isAnticipatedFrenzy());
                            server.setTypeMap(ans.getMap(),false);
                            System.out.println("Aggiornata mappa a " + server.getTypeMap());
                            server.setTerminatorMode(ans.isTerminator());
                            System.out.println("Aggiornato terminatore a " + server.isTerminatorMode());
                        }
                    }
                }
            }
        } catch (SocketException se) {
            handleDisconnection();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (isClientCurrentlyOnline) {
            ViewEvent newEvent = null;
            try {
                System.out.println("La virtual view di " + nicknameOfClient + " e in attesa di messaggi provenienti dalla remote view..");
                newEvent = (ViewEvent) in.readObject();
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
    public void update(ModelEvent message) {
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