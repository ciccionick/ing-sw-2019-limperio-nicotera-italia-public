package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTileDeck {

    ColorOfCard_Ammo[] ammoTile;
    private static AmmoTileDeck instanceOfAmmoTileDeck;

    private AmmoTileDeck(){

    }



    public static AmmoTileDeck instanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }


    public ColorOfCard_Ammo[] getAmmoTile() {
        return ammoTile;
    }
}
