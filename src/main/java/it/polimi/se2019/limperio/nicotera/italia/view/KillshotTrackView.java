package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.KillshotTrackEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.TokenOfDeath;

import java.util.ArrayList;

/**
 * This class handles the view that shows the killshot track to the player
 * @author Giuseppe Italia
 */
public class KillshotTrackView {

    /**
     * Tokens of death on killshot track, in order to keep a record of who has killed someone
     */
    private  ArrayList<TokenOfDeath> tokensOfDeath;
    /**
     * Tokens of the frenzy mode
     */
    private ArrayList<TokenOfDeath> tokenOfFrenzyMode;
    /**
     * It allows to keep a record of who has done a double kill
     */
    private ArrayList<TokenOfDeath> tokensOfDoubleKill;

    /**
     * Updates the different parts of this part of view through the information that are in the event parameter.
     * @param event Contains the information to update the view
     */
    public void update(KillshotTrackEvent event){
        setTokensOfDeath(event.getKillShotTrack().getTokensOfDeath());
        setTokensOfDoubleKill(event.getKillShotTrack().getTokensOfDoubleKill());
        setTokenOfFranzyMode(event.getKillShotTrack().getTokenOfFranzyMode());
        //System.out.println(event.getMessage());
    }

    public ArrayList<TokenOfDeath> getTokensOfDeath() {
        return tokensOfDeath;
    }

    public void setTokensOfDeath(ArrayList<TokenOfDeath> tokensOfDeath) {
        this.tokensOfDeath = tokensOfDeath;
    }

    public ArrayList<TokenOfDeath> getTokenOfFranzyMode() {
        return tokenOfFrenzyMode;
    }

    public void setTokenOfFranzyMode(ArrayList<TokenOfDeath> tokenOfFranzyMode) {
        this.tokenOfFrenzyMode = tokenOfFranzyMode;
    }

    public ArrayList<TokenOfDeath> getTokensOfDoubleKill() {
        return tokensOfDoubleKill;
    }

    public void setTokensOfDoubleKill(ArrayList<TokenOfDeath> tokensOfDoubleKill) {
        this.tokensOfDoubleKill = tokensOfDoubleKill;
    }
}
