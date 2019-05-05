package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.IOException;
import java.util.Scanner;

public class NetworkHandler extends Observable<ServerEvent> implements Observer<ClientEvent> {

    private String provisoryNickname;
    private Client client;
    private Scanner stdin = new Scanner(System.in);
    private RemoteView remoteView;

    public Client getClient() {
        return client;
    }

     NetworkHandler(Client client) {
        this.client = client;
        this.remoteView = new RemoteView(client, this);
        register(this.remoteView);
    }


    private void showMessage(String message) {
        System.out.println(message + " ");

    }

    public void replyToRequestOfInitialization(AnswerInitializationEvent event){
        try {
            if(event.getNickname()!=null)
                provisoryNickname=event.getNickname();
            client.out.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     void handleEventInitialization(RequestInitializationEvent event){
        if(event.isColorRequest())
            client.setNickname(provisoryNickname);
        remoteView.getInitializationView().handleInitialization(event);
    }

     void handleEvent(ServerEvent event){
        notify(event);
    }


    @Override
    public void notify(ServerEvent message) {
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
    public void update(ClientEvent message) {
        try {
            client.out.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
