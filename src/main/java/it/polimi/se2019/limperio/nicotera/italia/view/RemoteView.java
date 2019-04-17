package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.AnswerNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;

import java.io.IOException;
import java.util.Scanner;

public class RemoteView implements RemoteViewInterface {
    Client client;
    Scanner stdin = new Scanner(System.in);

    public RemoteView(Client client) {
        this.client = client;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message + " ");

    }

    @Override
    public void sendMessage() throws IOException {
        client.getOut().writeObject(new AnswerNicknameEvent(client.getNickname()));
    }

    @Override
    public void handleEvent(RequestNicknameEvent event) throws IOException {
        System.out.println("[Virtual view] : ");
        if (event.isSolved()) {
            System.out.println("Il nickname che hai scelto è valido");
            client.setInvalidNickname(false);
        }
        else {
            if (!(event.isNotFirstAttempt())) {
                showMessage(event.getMessage());
                sendMessage();
            } else {
                showMessage(event.getMessage());
                String newNickname = stdin.nextLine();
                client.changeNickname(newNickname);
                sendMessage();
            }
        }
    }

    public void handleEvent(ModelEvent event) throws IOException {
        showMessage(event.getMessage());
        showMessage(event.getNickname());
        client.getOut().writeObject(new ModelEvent());
    }






}
