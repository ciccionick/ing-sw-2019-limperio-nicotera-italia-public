package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.FrameForInitialization;



/**
 * This class handles the initial record of the players in which they choose nickname and color.
 * @author Francesco Nicotera
 */
public class InitializationView {
    /**
     * The frame utilized for the phase of initialization
     */
    private FrameForInitialization frameForInitialization;
    /**
     * reference to specific remote view of a client
     */
    private RemoteView remoteView;

     InitializationView(RemoteView remoteView) {
        this.remoteView = remoteView;
    }

    /**
     * Takes the nickname of a player that is recording to game.
     */
    private void setNicknameInitializingFrame(){
        frameForInitialization = new FrameForInitialization("Adrenaline - Setup", this);
    }


    /**
     *<p>
     *   Handles the initialization of a player in the game: it checks that there aren't two player with the same nickname or color.
     *   Generates a new event that it sends to remoteview, in order to ask to client the other information that it needs to register the player.
     *</p>
     *
     * @param event the boolean attributes in this parameter permit to recognize the phase of record in which the player is and whether to reply or ask to it.
     */
    public void handleInitialization(RequestInitializationEvent event){
        if(event.isNicknameRequest()){
            if(event.isRetake())
                frameForInitialization.handleRetakeForNickname();
            else
                setNicknameInitializingFrame();
        }
        if(event.isColorRequest()){
            if(event.isRetake())
                frameForInitialization.handleRetakeForColor();
            else
                frameForInitialization.setColor();
        }
        if(event.isFrenzyRequest()){
            frameForInitialization.setFrenzy();
        }
        if(event.isMapRequest()){
            frameForInitialization.setMap();
            }

        if (event.isTerminatorModeRequest()) {
            frameForInitialization.setTerminator();
        }
        if(event.isAck()){
            remoteView.getNetworkHandler().setNicknameOfClient(remoteView.getNetworkHandler().getTemporaryNickname());
            frameForInitialization.remainInListeningForTheStartGame();
        }


    }

    public RemoteView getRemoteView() {
        return remoteView;
    }

     FrameForInitialization getFrameForInitialization() {
        return frameForInitialization;
    }
}
