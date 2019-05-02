package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.AnswerNicknameEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;
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

     NetworkHandler(Client client) {
        this.client = client;
        register(new RemoteView(client, this));
    }


    private void showMessage(String message) {
        System.out.println(message + " ");

    }

    private String setNickname(){
        System.out.println("Scrivi nickname: ");
        return stdin.nextLine();
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
        String terminator = null;

        int typeOfMap=0;
        String nickname=setNickname();
        String color = setColor();
        System.out.println("Scrivi y se vuoi la frenzy, n altrimenti");
        frenzy = stdin.nextLine();
        while(!frenzy.equalsIgnoreCase("y") && !frenzy.equalsIgnoreCase("n")) {
            System.out.println("Riprova, scrivi y se vuoi la frenzy, n altrimenti");
            frenzy = stdin.nextLine();
        }
        System.out.println("Scrivi y se vuoi la terminator mode nel caso in cui la partita cominci con 3 giocatori, n altrimenti");
        terminator = stdin.nextLine();
        while(!terminator.equalsIgnoreCase("y") && !terminator.equalsIgnoreCase("n")) {
            System.out.println("Riprova, scrivi y se vuoi la terminator, n altrimenti");
            terminator = stdin.nextLine();
        }
        System.out.println("Scegli la mappa (1,2, 3 o 4): ");
        typeOfMap=stdin.nextInt();
        while(typeOfMap!=1 && typeOfMap!=2 && typeOfMap!=3 && typeOfMap!=4){
            System.out.println("Riprova, scegli la mappa (1,2, 3 o 4): ");
            typeOfMap=stdin.nextInt();
        }
        AnswerNicknameEvent ans = new AnswerNicknameEvent(nickname,color,typeOfMap,frenzy.equalsIgnoreCase("y"),terminator.equalsIgnoreCase("y"));
        try {
            stdin.nextLine();
            client.out.writeObject(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void sendInitialization(){
        String nickname = setNickname();
        String color = setColor();
        AnswerNicknameEvent ans = new AnswerNicknameEvent(nickname,color,0,false,false);
        try {
            client.out.writeObject(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void handleEventInitialization(RequestNicknameEvent event){

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

     void handleEvent(ModelEvent event){
        notify(event);
    }


    @Override
    public void register(Observer<ModelEvent> observer) {
        this.remoteView= (RemoteView) observer;
    }

    @Override
    public void deregister(Observer<ModelEvent> observer) {
        //this method is empty since that the only observer of Model Event is the RemoteView of the client
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
            remoteView.getMapView().update(message);
            return;
        }
        if(message.isKillshotTrackEvent()){
            System.out.println(("L'evento arrivato è di tipo KillshotTrack e di conseguenza chiamo l'update di KillshotTrackView"));
            remoteView.getKillshotTrackView().update((KillshotTrackEvent) message);
            return;
        }
        if(message.isDrawTwoPowerUpCardEvent()){
            System.out.println(("L'evento arrivato è di tipo DrawPowerUpCards e di conseguenza chiamo l'update di remote view"));
            remoteView.update(message);
            return;
        }
        if(message.isDiscardPowerUpCardToSpawnEvent()){
            System.out.println(("L'evento arrivato è di tipo DiscardPowerUpCard e di conseguenza chiamo l'update di remote view"));
            remoteView.getPlayerBoardView().update(message);
            remoteView.update(message);
        }
        if(message.isFirstActionOfTurnEvent()){
            System.out.println("L'evento è arrivato ed è di tipo FirstTurnAction di conseguenza lo mando al remote View e al mapView");
            remoteView.getMapView().update(message);
            remoteView.update(message);
        }
        if(message.isSelectionSquareForSquareWhereCatch()){
            System.out.println("L'evento è arrivato ed è di tipo SelectionViewForSquareWhereCatch di conseguenza lo mando al remote View");
            remoteView.update(message);

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
