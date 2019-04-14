package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTileDeck {

    AmmoTile[] ammoTile;
    private static AmmoTileDeck instanceOfAmmoTileDeck;

    private AmmoTileDeck(){

    }



    public static AmmoTileDeck instanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }


    public AmmoTile[] getAmmoTile() {
        return ammoTile;
    }
}
