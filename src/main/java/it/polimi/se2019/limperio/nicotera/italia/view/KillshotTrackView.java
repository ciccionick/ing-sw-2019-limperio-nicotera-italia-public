package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.KillshotTrackEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken;

import java.util.ArrayList;

/**
 * This class handles the view that shows the killshot track to the player
 * @author Giuseppe Italia
 */
public class KillshotTrackView {

    /**
     * Tokens of death on killshot track, in order to keep a record of who has killed someone
     */
    private  ArrayList<ArrayList<ColorOfDeathToken>> tokensOfDeath;
    /**
     * Tokens of the frenzy mode
     */
    private ArrayList<ColorOfDeathToken> tokenOfFrenzyMode;

    /**
     * Updates the different parts of this part of view through the information that are in the event parameter.
     * @param event Contains the information to update the view
     */
    public void update(KillshotTrackEvent event){
        setTokensOfDeath(event.getKillShotTrack().getTokensOfDeath());
        setTokenOfFrenzyMode(event.getKillShotTrack().getTokenOfFrenzyMode());
    }

    public ArrayList<ArrayList<ColorOfDeathToken>> getTokensOfDeath() {
        return tokensOfDeath;
    }

    private void setTokensOfDeath(ArrayList<ArrayList<ColorOfDeathToken>> tokensOfDeath) {
        this.tokensOfDeath = tokensOfDeath;
    }

    public ArrayList<ColorOfDeathToken> getTokenOfFrenzyMode() {
        return tokenOfFrenzyMode;
    }

    private void setTokenOfFrenzyMode(ArrayList<ColorOfDeathToken> tokenOfFrenzyMode) {
        this.tokenOfFrenzyMode = tokenOfFrenzyMode;
    }
}
