package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.RequestNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.AnswerNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.PlayerBoard;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.IOException;
import java.util.Scanner;

public class NetworkHandler extends Observable<ModelEvent> implements Observer<ViewEvent> {

    private Client client;
    private Scanner stdin = new Scanner(System.in);
    private RemoteView remoteView;

    public Client getClient() {
        return client;
    }

    public NetworkHandler(Client client) {
        this.client = client;
        register(new RemoteView(client, this));
    }


    public void showMessage(String message) {
        System.out.println(message + " ");

    }

    private String setNickname(){
        System.out.println("Scrivi nickname: ");
        String nickname = stdin.nextLine();
        return nickname;
    }

    private String setColor(){
        System.out.println("Scrivi che colore vuoi (YELLOW, BLUE, PURPLE, GREY, GREEN): ");
        String color = stdin.nextLine();
        while (!color.equalsIgnoreCase("YELLOW")&& !color.equalsIgnoreCase("BLUE") && !color.equalsIgnoreCase("PURPLE") && !color.equalsIgnoreCase("GREY") && !color.equalsIgnoreCase("GREEN")){
            System.out.println("Riprova, scrivi che colore vuoi (YELLOW, BLUE, <PURPLE, GREY, GREEN): ");
            color = stdin.nextLine();
        }
        return color;
    }

    private void sendInitializationAsFirst() {

        String frenzy=null;
        boolean wannaFrenzy;
        int typeOfMap=0;
        String nickname=setNickname();
        String color = setColor();
        System.out.println("Scrivi y se vuoi la frenzy, n altrimenti");
        frenzy = stdin.nextLine();
        while(!frenzy.equalsIgnoreCase("y") && !frenzy.equalsIgnoreCase("n")) {
            System.out.println("Riprova, scrivi y se vuoi la frenzy, n altrimenti");
            frenzy = stdin.nextLine();
        }
        if(frenzy.equalsIgnoreCase("y")) wannaFrenzy=true;
        else wannaFrenzy=false;
        System.out.println("Scegli la mappa (1,2 o 3): ");
        typeOfMap=stdin.nextInt();
        while(typeOfMap!=1 && typeOfMap!=2 && typeOfMap!=3){
            System.out.println("Riprova, scegli la mappa (1,2 o 3): ");
            typeOfMap=stdin.nextInt();
        }
        AnswerNicknameEvent ans = new AnswerNicknameEvent(nickname,color,typeOfMap,wannaFrenzy);
        try {
            stdin.nextLine();
            client.out.writeObject(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void sendInitialization(){
        String nickname = setNickname();
        String color = setColor();
        AnswerNicknameEvent ans = new AnswerNicknameEvent(nickname,color,0,false);
        try {
            client.out.writeObject(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleEventInitialization(RequestNicknameEvent event) throws IOException {

        if(event.isMessageOfRequest()){
            showMessage(event.getMessage());
            if(event.isFirst())
                sendInitializationAsFirst();
            else
                sendInitialization();
        }
        else
        {
            if (event.isAck()){
                showMessage(event.getMessage());
                if(event.isValid()){
                    client.setInvalidInitialization(false);
                    client.setNickname(event.getNickname());
                }
            }
        }
    }

    public void handleEvent(ModelEvent event) throws IOException {
        notify(event);
    }


    @Override
    public void register(Observer<ModelEvent> observer) {
        this.remoteView= (RemoteView) observer;
    }

    @Override
    public void deregister(Observer<ModelEvent> observer) {

    }

    @Override
    public void notify(ModelEvent message) {
        if(message.isPlayerBoardEvent()){
            System.out.println("L'evento è di tipo PlayerBoard event e di conseguenza chiamo l'update di PlayerBoardView");
            remoteView.getPlayerBoardView().update((PlayerBoardEvent) message);
            return;
        }
        if(message.isMapEvent()){
            System.out.println("L'evento arrivato è di tipo MapEvent e di conseguenza chiamo l'update di MapView");
            remoteView.getMapView().update((MapEvent) message);
        }


    }

    @Override
    public void update(ViewEvent message) {
        try {
            client.out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
