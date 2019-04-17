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

public class VirtualView extends Observable<ViewEvent> implements Observer<ModelEvent>,Runnable {

    Socket client;
    String nicknameOfClient;
    Server server;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    Controller controller;

    public VirtualView(Socket client, Server server, Controller controller) {
        this.client = client;
        this.server = server;
        this.controller = controller;
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
            return;
        }
    }

    @Override
    public void run() {
        try {
            out.writeObject(new RequestNicknameEvent("Il server sta cercando di recuperare il tuo nickname..", false, false));
            boolean nicknameNotValid = true;
            while (nicknameNotValid) {
                AnswerNicknameEvent answer = (AnswerNicknameEvent) in.readObject();
                if (!(server.getListOfNickname().contains(answer.getNickname()))) {
                    System.out.println("Nickname aggiunto con successo: " + answer.getNickname());
                    this.nicknameOfClient = answer.getNickname();
                    server.addNickname(nicknameOfClient);
                    out.writeObject(new RequestNicknameEvent("Il server sta cercando di recuperare il tuo nickname..", false, true));

                    nicknameNotValid = false;
                } else {
                    out.writeObject(new RequestNicknameEvent("Nickname già esistente, inseriscine un altro", true, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (true) {
            ViewEvent newEvent = null;
            try {
                newEvent = (ViewEvent) in.readObject();
                notify(newEvent);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            break;
        }
        server.getListOfNickname().remove(nicknameOfClient);
        server.deregister(this, client);
    }

    @Override
    public void notify(ViewEvent message) {
        controller.update(message);
    }

    @Override
    public void update(ModelEvent message) {
        try {
            message.setNickname(nicknameOfClient);
            out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register(Observer<ViewEvent> observer) {
    }

    @Override
    public void deregister(Observer<ViewEvent> observer) {
    }

}