package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.KillshotTrackEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.TokenOfDeath;

import java.util.ArrayList;

public class KillshotTrackView {

    private  ArrayList<TokenOfDeath> tokensOfDeath;
    private ArrayList<TokenOfDeath> tokenOfFrenzyMode;
    private ArrayList<TokenOfDeath> tokensOfDoubleKill;

    public void update(KillshotTrackEvent event){
        setTokensOfDeath(event.getKillShotTrack().getTokensOfDeath());
        setTokensOfDoubleKill(event.getKillShotTrack().getTokensOfDoubleKill());
        setTokenOfFranzyMode(event.getKillShotTrack().getTokenOfFranzyMode());
        System.out.println(event.getMessage());
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
