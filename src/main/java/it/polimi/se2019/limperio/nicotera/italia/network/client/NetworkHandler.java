package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.IOException;

/**
 * Handles all the events received by the server and choose the right piece of remote view where
 * address the event to be managed
 * 
 * @author Pietro L'Imperio
 */
public class NetworkHandler extends Observable<ServerEvent> implements Observer<ClientEvent> {

    /**
     * The nickname that the client choose but the server has to check if has not already chosen by another player before
     */
    private String temporaryNickname;
    /**
     * The reference of the {@link Client} associated
     */
    private Client client;
    /**
     * The reference of the {@link RemoteView} it will communicate with
     */
    private RemoteView remoteView;



     NetworkHandler(Client client) {
        this.client = client;
        this.remoteView = new RemoteView(client, this);
        register(this.remoteView);
    }

    public Client getClient() {
        return client;
    }


    /**
     * Replies to the {@link it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView}
     * and saves the nickname chosen by client in temporaryNickname attribute
     * @param event The event containing the information inserted by the client that has to be sent
     */
    public void replyToRequestOfInitialization(AnswerInitializationEvent event){
        try {
            if(event.getNickname()!=null)
                temporaryNickname = event.getNickname();
            client.out.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives and handles event during the phase of initialization sending them to the part of {@link RemoteView}
     * that is charged for this ({@link it.polimi.se2019.limperio.nicotera.italia.view.InitializationView}
     * <p>
     *     Set the nickname of {@link Client} when receive the request for the color becuase it means that the nickname was available
     * </p>
     * @param event The event received by the server
     */
     void handleEventInitialization(RequestInitializationEvent event){
        if(event.isColorRequest())
            client.setNickname(temporaryNickname);
        remoteView.getInitializationView().handleInitialization(event);
    }

    /**
     * Receives all of the event by the server and call notify
     * @param event The event received
     */
     void handleEvent(ServerEvent event){
        notify(event);
    }

    /**
     * In accordance with the boolean attributes stored in {@link ServerEvent} calls the update of the
     * right part of {@link RemoteView}
     * @param event The event received by the server
     */
    @Override
    public void notify(ServerEvent event) {
        if(event.isPlayerBoardEvent()){
            System.out.println("L'evento è di tipo PlayerBoard event e di conseguenza chiamo l'update di PlayerBoardView");
            remoteView.handlePlayerBoardEvent(((PlayerBoardEvent) event));
        }
        if(event.isMapEvent()){
            System.out.println("L'evento arrivato è di tipo MapEvent e di conseguenza chiamo l'update di MapView");
            remoteView.getMapView().update((MapEvent)event);

        }
        if(event.isKillshotTrackEvent()){
            System.out.println(("L'evento arrivato è di tipo KillshotTrack e di conseguenza chiamo l'update di KillshotTrackView"));
            remoteView.getKillshotTrackView().update((KillshotTrackEvent) event);

        }
        if(event.isRequestForDrawTwoPowerUpCardsEvent()){
            System.out.println(("L'evento arrivato è di tipo DrawPowerUpCards e di conseguenza chiamo l'update di remote view"));
            remoteView.update(event);
            return;
        }
        if(event.isRequestToDiscardPowerUpCardToSpawnEvent()){
            System.out.println(("L'evento arrivato è di tipo DiscardPowerUpCard e di conseguenza chiamo l'update di remote view"));
            remoteView.update(event);
            return;
        }
        if(event.isFirstActionOfTurnEvent()){
            //if se player del turno chiama update, altrimenti chiamo remoteviwe che deve stamoare attendi il turno di ..
            System.out.println("L'evento è arrivato ed è di tipo FirstTurnAction di conseguenza lo mando al remote View e al mapView");
            remoteView.update(event);
            return;
        }
        if(event.isSelectionSquareForSquareWhereCatch()) {
            System.out.println("L'evento è arrivato ed è di tipo SelectionViewForSquareWhereCatch di conseguenza lo mando al remote View");
            remoteView.update(event);
        }
        if(event.isCatchActionDone()){
            if(((CatchActionDoneEvent) event).isCatchActionOfAmmoTile()){
                System.out.println("E' stata effettuata la raccolta di un ammotile");
                remoteView.update(event);
            }

        }


    }

    /**
     * Send through the object output stream of the client the event generated by the {@link RemoteView}
     * @param event The event that has to send to the server
     */
    @Override
    public void update(ClientEvent event) {
        try {
            client.out.writeObject(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
