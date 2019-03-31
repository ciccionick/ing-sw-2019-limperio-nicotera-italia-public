package it.polimi.se2019.limperio.nicotera.italia.model;

public class Board {
    private static Board istanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammotiledeck;
    private Board(){};

    public static Board istanceOfBoard() {
        if(istanceOfBoard==null) istanceOfBoard= new Board();
        return istanceOfBoard;
    }

    public Board clone(){};

    public Map createMap(int typeMap)
    {
       this.map = istanceOfMap(typeMap);
    };

    public PowerUpDeck createPowerUpDeck(){};

    public WheaponCardDeck CreateWheaponCardDeck(){};

    public AmmoTileDeck creatAmmoTileDeck(){
        this.ammotiledeck = istanceOfAmmoTileDeck();
    };

    public KillShotTrack createKillShotTrack(){};
}
