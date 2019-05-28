package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * handles KillshotTrack of WeaponCard
 *
 * @author giuseppeitalia
 */


public class KillshotTrack implements Serializable, Cloneable {
    static final long serialVersionUID = 420000005;

    /**
     * Contains the unique instance of Killshot track according to singleton pattern
     */
    private static KillshotTrack instanceOfKillShotTrack;
    /**
     * Records the killings of the players during the game
     */
    private ArrayList<ArrayList<ColorOfDeathToken>> tokensOfDeath = new ArrayList<>();
    /**
     * Records the killings of the players during the frenzy turn
     */
    private ArrayList<ColorOfDeathToken> tokenOfFrenzyMode = new ArrayList<>();

    /**
     * Creates the killshot track and at the beginning it has filled only with skull, according to the rules
     */
    private KillshotTrack(){

        for (int i=0;i<8;i++) {
            tokensOfDeath.add(new ArrayList<>());
            if(i<7)
                tokensOfDeath.get(i).add(ColorOfDeathToken.YELLOW);
            else
                tokensOfDeath.get(i).add(ColorOfDeathToken.SKULL);
        }
    }

    /**
     * Creates the unique instance of the killshot track, according to singleton pattern
     * @return the instance of this class
     */
    static KillshotTrack instanceOfKillShotTrack()
    {
        if(instanceOfKillShotTrack==null) instanceOfKillShotTrack=new KillshotTrack();
        return instanceOfKillShotTrack;
    }


    public ArrayList<ArrayList<ColorOfDeathToken>> getTokensOfDeath() {
        return tokensOfDeath;
    }

    public void setTokensOfDeath(ArrayList<ArrayList<ColorOfDeathToken>> tokensOfDeath) {
        this.tokensOfDeath = tokensOfDeath;
    }

    public ArrayList<ColorOfDeathToken> getTokenOfFrenzyMode() {
        return tokenOfFrenzyMode;
    }

    public void setTokenOfFrenzyMode(ArrayList<ColorOfDeathToken> tokenOfFrenzyMode) {
        this.tokenOfFrenzyMode = tokenOfFrenzyMode;
    }

    public Object clone(){
        KillshotTrack killshotTrack = null;
        try{
            killshotTrack = (KillshotTrack) super.clone();
        } catch (CloneNotSupportedException e) {
            killshotTrack = new KillshotTrack();
        }
        killshotTrack.tokensOfDeath = new ArrayList<>();
        int i=0;
        for(ArrayList<ColorOfDeathToken> listOfToken : this.tokensOfDeath){
            killshotTrack.tokensOfDeath.add(new ArrayList<>());
            for(ColorOfDeathToken token : listOfToken){
                killshotTrack.tokensOfDeath.get(i).add(token);
            }
            i++;
        }
        killshotTrack.tokenOfFrenzyMode = this.tokenOfFrenzyMode;
        return killshotTrack;
    }
}
