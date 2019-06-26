package it.polimi.se2019.limperio.nicotera.italia.network.client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles all the events received by the server and choose the right piece of remote view where
 * address the event to be managed
 * 
 * @author Pietro L'Imperio
 */
public class NetworkHandler implements Observer<ClientEvent> {

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
    private static Logger loggerNetworkHandler = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    private static Handler handlerLoggerNetworkHandler = new ConsoleHandler();



     NetworkHandler(Client client) {
         loggerNetworkHandler.addHandler(handlerLoggerNetworkHandler);
        this.client = client;
        this.remoteView = new RemoteView(client, this);
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
            loggerNetworkHandler.log(Level.ALL, "error");
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
            setNicknameOfClient(temporaryNickname);
        remoteView.getInitializationView().handleInitialization(event);
    }

    /**
     * Receives all of the event by the server and call updateStateOfRemoteView
     * @param event The event received
     */
     void handleEvent(ServerEvent event){
        updateStateOfRemoteView(event);
    }


    private void updateStateOfRemoteView(ServerEvent event) {

        if(event.isPlayerBoardEvent()){
            remoteView.handlePlayerBoardEvent((PlayerBoardEvent) event);
        }
        if(event.isMapEvent()){
            if(((MapEvent)event).isForNewton() || ((MapEvent)event).isForTeleporter())
                remoteView.getMainFrame().showMessage(event);
            remoteView.getMapView().update((MapEvent) event);
        }
        if(event.isKillshotTrackEvent()){
            if (remoteView.getMainFrame()==null)
                remoteView.setMyTurn(((KillshotTrackEvent)event).getNicknamePlayerOfTheTurn().equals(client.getNickname()));
            remoteView.getKillshotTrackView().update((KillshotTrackEvent) event);
            if(remoteView.getMainFrame()!=null)
                remoteView.getMainFrame().updateNorthPanel();
        }

        if(event.isUpdateScoreEvent()){
            remoteView.getMainFrame().showMessage(event);
            if(event.isFinalUpdate()){
                remoteView.getMyPlayerBoardView().disableEveryThingPlayerCanDo();
                remoteView.getMainFrame().getRightPanel().getPanelOfActions().updateStateOfButton();
                remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(remoteView.getMyPlayerBoardView().getNicknameOfPlayer());
            }
        }

        if(event.isRequestToChooseMultiplePlayers())
            remoteView.getMainFrame().handleRequestToChooseMultiplePlayers(event);

        if(event.isRequestToSelectionPlayerToAttackWithTerminator()){
            remoteView.getMainFrame().handleRequestToChooseAPlayer(event);

        }


        if (event.isTimerOverEvent()){
            remoteView.getMyPlayerBoardView().disableEveryThingPlayerCanDo();
            remoteView.getMapView().setHasToChooseASquare(false);
            remoteView.getMainFrame().updatePanelOfAction();
            remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(remoteView.getMyPlayerBoardView().getNicknameOfPlayer());
            remoteView.getMainFrame().updateEnableSquares(remoteView.getMapView().getListOfSquareAsArrayList());
            remoteView.getMainFrame().showMessage(event);
            remoteView.getMainFrame().hidePopup();
            remoteView.setMyTurn(false);
            remoteView.getMainFrame().updateNorthPanel();

        }

        if(event.isGenerationEvent()|| event.isRequestToChooseTerminatorAction()){
            remoteView.getMainFrame().showMessage(event);
        }

        if(event.isRequestForDrawTwoPowerUpCardsEvent()){
            remoteView.getMainFrame().showMessage(event);
            remoteView.setMyTurn(event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer()));
            remoteView.getMainFrame().updateNorthPanel();
            remoteView.getMainFrame().getRightPanel().getPanelOfActions().getButtonCancel().setEnabled(false);

        }
        if(event.isRequestForDrawOnePowerUpCardEvent()){
            remoteView.getMainFrame().showMessage(event);
            remoteView.setMyTurn(event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer()));
            remoteView.getMainFrame().updateNorthPanel();
        }
        if(event.isRequestToDiscardPowerUpCardToSpawnEvent()){
            remoteView.getMainFrame().handleRequestToDiscardPowerUpCard(event);
        }
        if(event.isRequestActionEvent()){
            if (event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer())) {
                remoteView.getMyPlayerBoardView().updateThingsPlayerCanDo((RequestActionEvent) event);
                remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(remoteView.getMyPlayerBoardView().getNicknameOfPlayer());
                remoteView.getMainFrame().updatePanelOfAction();
            }
            if(remoteView.isMyTurn() && !event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer())) {
                remoteView.setMyTurn(false);
                remoteView.getMyPlayerBoardView().disableEveryThingPlayerCanDo();
                remoteView.getMainFrame().getRightPanel().getPanelOfActions().updateStateOfButton();
                remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(remoteView.getMyPlayerBoardView().getNicknameOfPlayer());
                remoteView.getMainFrame().updateNorthPanel();
            }
            if(!remoteView.isMyTurn() && event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer())) {
                remoteView.setMyTurn(true);
                remoteView.getMainFrame().updateNorthPanel();
            }

            remoteView.getMainFrame().showMessage(event);

        }

        if(event.isRequestSelectionSquareForAction()){
            remoteView.getMapView().setHasToChooseASquare(true);
            remoteView.getMapView().setReachableSquares(((RequestSelectionSquareForAction) event).getSquaresReachable());
            remoteView.getMapView().setSelectionForCatch(((RequestSelectionSquareForAction) event).isSelectionForCatch());
            remoteView.getMapView().setSelectionForRun(((RequestSelectionSquareForAction) event).isSelectionForRun());
            remoteView.getMapView().setSelectionForGenerationOfTerminator(((RequestSelectionSquareForAction) event).isSelectionForSpawnTerminator());
            remoteView.getMapView().setSelectionForMoveTerminator(((RequestSelectionSquareForAction) event).isSelectionForMoveTerminator());
            remoteView.getMapView().setSelectionForTeleporter(((RequestSelectionSquareForAction) event).isSelectionForTeleporter());
            remoteView.getMapView().setSelectionForNewton(((RequestSelectionSquareForAction)event).isSelectionForNewton());
            remoteView.getMapView().setSelectionBeforeToShoot(((RequestSelectionSquareForAction) event).isBeforeToShoot());
            remoteView.getMapView().setSelectionForShootAction(((RequestSelectionSquareForAction)event).isForActionShoot());
            if(((RequestSelectionSquareForAction)event).isForActionShoot())
                remoteView.getMainFrame().getRightPanel().getPanelOfActions().getButtonCancel().setEnabled(false);
            remoteView.getMainFrame().updateEnableSquares(((RequestSelectionSquareForAction) event).getSquaresReachable());
            remoteView.getMainFrame().showMessage(event);
            remoteView.getMainFrame().updatePanelOfPlayers();
        }

        if(event.isRequestSelectionWeaponToReload())
            remoteView.getMainFrame().showPopupForChooseWeapon(event);


        if(event.isRequestToDiscardPowerUpCardToPay()){
            if (((RequestToDiscardPowerUpCard) event).isToTagback() && remoteView.isMyTurn())
                remoteView.getMainFrame().showMessage(event);
            else
                remoteView.getMainFrame().handleRequestToDiscardPowerUpCard(event);
        }

        if (event.isNotifyAboutActionDone()){
            if(event.getNicknameInvolved().equals(remoteView.getMyPlayerBoardView().getNicknameOfPlayer())){
                remoteView.getMyPlayerBoardView().disableEveryThingPlayerCanDo();
                remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(event.getNicknameInvolved());
                remoteView.getMainFrame().updatePanelOfAction();
            }
            remoteView.getMainFrame().showMessage(event);
        }

        if (event.isRequestForChooseAWeaponToCatch() || event.isRequestToDiscardWeaponCard()){
            remoteView.getMainFrame().showMessage(event);
            remoteView.getMainFrame().showPopupForChooseWeapon(event);
        }

        if(event.isRequestToChooseWeapon()){
            remoteView.getMainFrame().showMessage(event);
            remoteView.getMyPlayerBoardView().updateWeaponCanUse((RequestToChooseWeapon) event);
            remoteView.getMainFrame().updateLeftPanelForWhoIsViewing(event.getNicknameInvolved());
            remoteView.getMainFrame().getRightPanel().getPanelOfActions().getActionButtonListener().disablePowerUpCards();

        }

        if(event.isRequestToChooseAnEffect()){
            remoteView.getMainFrame().handleRequestToChooseAnEffect(event);
        }

        if(event.isRequestToPayWithAmmoOrPUCard())
            remoteView.getMainFrame().handleRequestToPayWithAmmoOrPUC(event);


        if(event.isRequestToChooseAPlayer())
            remoteView.getMainFrame().handleRequestToChooseAPlayer(event);




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
            loggerNetworkHandler.log(Level.ALL, "error");
        }
    }

    public String getTemporaryNickname() {
        return temporaryNickname;
    }

    public void setNicknameOfClient(String nickname){
        client.setNickname(nickname);
    }

}
