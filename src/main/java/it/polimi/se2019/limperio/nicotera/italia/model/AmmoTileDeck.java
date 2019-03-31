package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTileDeck {

    ColorOfCard_Ammo[] ammoTiles;
    private static AmmoTileDeck instanceOfAmmoTileDeck;


    public static AmmoTileDeck getInstanceOfAmmoTileDeck() {
        return instanceOfAmmoTileDeck;
    }

    private AmmoTileDeck(){
    };



    public static AmmoTileDeck istanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }






}
