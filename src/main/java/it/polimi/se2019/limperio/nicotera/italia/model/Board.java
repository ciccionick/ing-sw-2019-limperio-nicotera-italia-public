package it.polimi.se2019.limperio.nicotera.italia.model;

import static it.polimi.se2019.limperio.nicotera.italia.model.AmmoTileDeck.istanceOfAmmoTileDeck;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;

public class Board {
    private static Board instanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammoTileDeck;
    private Board(){};

    public static Board instanceOfBoard() {
        if(instanceOfBoard==null) instanceOfBoard= new Board();
        return instanceOfBoard;
    }

    public Board clone(){};

    public Map createMap(int typeMap)
    {
       this.map = instanceOfMap(typeMap);
    }

    public PowerUpDeck createPowerUpDeck(){}

    public WheaponDeck CreateWheaponCardDeck(){}

    public AmmoTileDeck creatAmmoTileDeck(){
        this.ammoTileDeck = istanceOfAmmoTileDeck();
    }

    public KillShotTrack createKillShotTrack(){}
}
