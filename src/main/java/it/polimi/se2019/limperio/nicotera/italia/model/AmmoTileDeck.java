package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTileDeck {

    ColorOfCard_Ammo[36]= ammoTiles;
    private static AmmoTileDeck istanceOfAmmoTileDeck = null;

    private AmmoTileDeck(){
        AmmoTile();
    };



    public static AmmoTileDeck istanceOfAmmoTileDeck(){
        if(istanceOfAmmoTileDeck==null) AmmoTileDeck();
        return istanceOfAmmoTileDeck;
    }


    public get AmmoTile() {

    }




}
