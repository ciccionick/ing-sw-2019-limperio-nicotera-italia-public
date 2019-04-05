package it.polimi.se2019.limperio.nicotera.italia.model;

public class Board {
    private static Board instanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammotiledeck;
    private Board()
    {

    };

    public static Board istanceOfBoard() {
        if(instanceOfBoard==null) instanceOfBoard= new Board();
        return istanceOfBoard;
    }

    public Board clone(){};

    public Map createMap(int typeMap)
    {
       this.map = instanceOfMap(typeMap);
    };

    public PowerUpDeck createPowerUpDeck(){};

    public WheaponCardDeck CreateWheaponCardDeck(){};

    public AmmoTileDeck creatAmmoTileDeck(){
        this.ammotiledeck = instanceOfAmmoTileDeck();
    };

    public KillShotTrack createKillShotTrack()
    {

    }
}
