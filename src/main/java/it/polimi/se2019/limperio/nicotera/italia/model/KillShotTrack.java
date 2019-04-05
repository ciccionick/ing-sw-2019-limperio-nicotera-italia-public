package it.polimi.se2019.limperio.nicotera.italia.model;


import java.util.ArrayList;

public class KillShotTrack {


    private static KillShotTrack instanceOfKillShotTrack;
    private  TokenOfDeath[] tokensOfDeath;
    private ArrayList<TokenOfDeath> tokenOfFranzyMode;

    private KillShotTrack(){
        int i=0;
        for(i=0;i<8;i++)
        {
            tokensOfDeath[i].color= ColorOfDeathToken.SKULL;
            tokensOfDeath[1].numOfToken = 1;
        }
    };

    public static KillShotTrack istanceOfKillShotTrack()
    {
        if(instanceOfKillShotTrack==null) instanceOfKillShotTrack=new  KillShotTrack();
        return instanceOfKillShotTrack;
    }




}
