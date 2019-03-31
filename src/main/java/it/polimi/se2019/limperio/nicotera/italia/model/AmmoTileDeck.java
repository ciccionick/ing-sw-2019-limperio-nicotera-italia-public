package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTileDeck {

    ColorOfCard_Ammo[] ammoTile;
    private static AmmoTileDeck istanceOfAmmoTileDeck;

    private AmmoTileDeck(){

    }



    public static AmmoTileDeck istanceOfAmmoTileDeck(){
        if(istanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return istanceOfAmmoTileDeck;
    }


    public ColorOfCard_Ammo[] getAmmoTile() {
        return ammoTile;
    }
}
