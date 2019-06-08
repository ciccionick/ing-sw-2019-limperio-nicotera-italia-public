package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.KillshotTrack;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
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
            remoteView.handlePlayerBoardEvent((PlayerBoardEvent) event);
        }
        if(event.isMapEvent()){
            remoteView.getMapView().update((MapEvent) event);
        }
        if(event.isKillshotTrackEvent()){
            if (remoteView.getMainFrame()==null)
                remoteView.setMyTurn(((KillshotTrackEvent)event).getNicknamePlayerOfTheTurn().equals(client.getNickname()));
            remoteView.getKillshotTrackView().update((KillshotTrackEvent) event);

        }

        if(event.isUpdateScoreEvent()){
            remoteView.update(event);
        }

        if(event.isRequestToSelectionPlayerToAttackWithTerminator()){
            remoteView.update(event);
        }

        if(event.isRequestToChooseTerminatorAction()){
            remoteView.update(event);
        }

        if (event.isTimerOverEvent()){
            remoteView.update(event);
        }
        if(event.isGenerationEvent()){
            remoteView.update(event);
        }
        if(event.isRequestForDrawTwoPowerUpCardsEvent()){
            remoteView.update(event);
            return;
        }
        if(event.isRequestForDrawOnePowerUpCardEvent()){
            remoteView.update(event);
        }
        if(event.isRequestToDiscardPowerUpCardToSpawnEvent()){
            remoteView.update(event);
        }
        if(event.isRequestActionEvent()){
            remoteView.update(event);
            return;
        }

        if(event.isRequestSelectionSquareForAction()){
            remoteView.update(event);
            return;
        }

        if (event.isNotifyAboutActionDone()){
            remoteView.update(event);
        }

        if (event.isRequestForChooseAWeaponToCatch()){
            remoteView.update(event);
        }
        if(event.isRequestToDiscardWeaponCard()){
            remoteView.update(event);
        }
        if(event.isRequestToChooseWeapon()){
            remoteView.update(event);
        }

        if(event.isRequestToChooseAnEffect()){
            remoteView.update(event);
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
