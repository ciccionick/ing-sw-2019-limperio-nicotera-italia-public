package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

/**
<<<<<<< HEAD
 * handles KillShotTrack of WeaponCard
 *
 * @author giuseppeitalia
 */


public class KillShotTrack implements Serializable {

    /**
     * Contains the unique instance of Killshot track according to singleton pattern
     */
    private static KillShotTrack instanceOfKillShotTrack;
    /**
     * Records the killings of the players during the game
     */
    private ArrayList<TokenOfDeath> tokensOfDeath = new ArrayList<>();
    /**
     * Records the killings of the players during the frenzy turn
     */
    private ArrayList<TokenOfDeath> tokenOfFrenzyMode = new ArrayList<>();
    /**
     * Records the players that have done a double kill
     */
    private ArrayList<TokenOfDeath> tokensOfDoubleKill = new ArrayList<>();

    /**
     * Creates the killshot track and at the beginning it has filled only with skull, according to the rules
     */
    private KillShotTrack(){

        for (int i=0;i<8;i++)
            tokensOfDeath.add(new TokenOfDeath(1, ColorOfDeathToken.SKULL));

    }

    /**
     * Creates the unique instance of the killshot track, according to singleton pattern
     * @return the instance of this class
     */
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
