package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * handles KillShotTrack of WeaponCard
 *
 * @author giuseppeitalia
 */

public class KillShotTrack implements Serializable {


    private static KillShotTrack instanceOfKillShotTrack;
    private ArrayList<TokenOfDeath> tokensOfDeath = new ArrayList<>();
    private ArrayList<TokenOfDeath> tokenOfFrenzyMode = new ArrayList<>();
    private ArrayList<TokenOfDeath> tokensOfDoubleKill = new ArrayList<>();

    private KillShotTrack(){

        for (int i=0;i<8;i++)
            tokensOfDeath.add(new TokenOfDeath(1, ColorOfDeathToken.SKULL));

    }

    static KillShotTrack instanceOfKillShotTrack()
    {
        if(instanceOfKillShotTrack==null) instanceOfKillShotTrack=new  KillShotTrack();
        return instanceOfKillShotTrack;
    }


    public ArrayList<TokenOfDeath> getTokensOfDeath() {
        return tokensOfDeath;
    }

    public ArrayList<TokenOfDeath> getTokenOfFranzyMode() {
        return tokenOfFrenzyMode;
    }

    public ArrayList<TokenOfDeath> getTokensOfDoubleKill() {
        return tokensOfDoubleKill;
    }
}
