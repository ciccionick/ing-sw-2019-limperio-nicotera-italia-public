package it.polimi.se2019.limperio.nicotera.italia.model;

import static it.polimi.se2019.limperio.nicotera.italia.model.AmmoTileDeck.instanceOfAmmoTileDeck;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;

public class Board {
    private static Board instanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammotiledeck;
    private WeaponsDeck weaponsDeck;
    private PowerUpDeck powerUpDeck;
    private KillShotTrack killShotTrack;

    private Board()
    { }

    public static Board instanceOfBoard() {
        if(instanceOfBoard==null) instanceOfBoard= new Board();
        return instanceOfBoard;
    }

    public void createMap(int typeMap)
    {
       this.map = instanceOfMap(typeMap);
    }

    public PowerUpDeck createPowerUpDeck(){
        return null;
    }

    public void createWeaponsDeck(){}

    public void createAmmoTileDeck(){
        this.ammotiledeck = instanceOfAmmoTileDeck();
    }

    public void createKillShotTrack()
    {
        killShotTrack = KillShotTrack.instanceOfKillShotTrack();
    }

    public Map getMap() {
        return map;
    }

    public AmmoTileDeck getAmmotiledeck() {
        return ammotiledeck;
    }

    public WeaponsDeck getWeaponsDeck() {
        return weaponsDeck;
    }

    public PowerUpDeck getPowerUpDeck() {
        return powerUpDeck;
    }

    public KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }
}
