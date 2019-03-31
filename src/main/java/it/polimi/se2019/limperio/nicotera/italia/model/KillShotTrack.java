package it.polimi.se2019.limperio.nicotera.italia.model;

public class KillShotTrack {


    private static KillShotTrack istanceOfKillShotTrack;
    private  TokenOfDeath[] tokensOfDeath;

    private KillShotTrack(){
        tokensOfDeath = new TokenOfDeath[8];
        int i=0;
        for(i=0;i<8;i++)
        {
            tokensOfDeath[i].color= ColorOfDeathToken.SKULL;
            tokensOfDeath[1].numOfToken = 1;
        }
    };

    public static KillShotTrack istanceOfKillShotTrack()
    {
        if(istanceOfKillShotTrack==null) istanceOfKillShotTrack=new  KillShotTrack();
        return istanceOfKillShotTrack;
    }




}
