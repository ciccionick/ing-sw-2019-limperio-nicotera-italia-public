package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that represents the killshot track.
 *
 * @author Giuseppe Italia.
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

    public ArrayList<ColorOfDeathToken> getTokenOfFrenzyMode() {
        return tokenOfFrenzyMode;
    }

    /**
     * Override of the clone method to make possible the serialization avoiding the shallow copy.
     * @return The cloned object.
     */
    public Object clone(){
        KillshotTrack killshotTrack;
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
        killshotTrack.tokenOfFrenzyMode = new ArrayList<>();
        for(ColorOfDeathToken token : this.tokenOfFrenzyMode){
            killshotTrack.tokenOfFrenzyMode.add(token);
        }
        return killshotTrack;
    }

    public void setInstanceOfKillShotTrackNullForTesting(){
        instanceOfKillShotTrack = null;
    }
}
