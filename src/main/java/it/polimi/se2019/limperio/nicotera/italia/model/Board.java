package it.polimi.se2019.limperio.nicotera.italia.model;

import static it.polimi.se2019.limperio.nicotera.italia.model.AmmoTileDeck.instanceOfAmmoTileDeck;
import static it.polimi.se2019.limperio.nicotera.italia.model.Map.instanceOfMap;
import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpDeck.instanceOfPowerUpDeck;

public class Board {
    private static Board instanceOfBoard = null;

    private Map map;
    private AmmoTileDeck ammotiledeck;
    private WeaponsDeck weaponsDeck;
    private PowerUpDeck powerUpDeck;
    private KillShotTrack killShotTrack;

    private Board()
    { }

     static Board instanceOfBoard() {
        if(instanceOfBoard==null) instanceOfBoard= new Board();
        return instanceOfBoard;
    }

     void createMap(int typeMap)
    {
       this.map = instanceOfMap(typeMap);
    }

     void createPowerUpDeck(){
        this.powerUpDeck= instanceOfPowerUpDeck();
    }

     void createWeaponsDeck(){
        this.weaponsDeck = WeaponsDeck.instanceOfWeaponsDeck();
     }

     void createAmmoTileDeck(){
        this.ammotiledeck = instanceOfAmmoTileDeck();
    }

     void createKillShotTrack()
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

     KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }
}
