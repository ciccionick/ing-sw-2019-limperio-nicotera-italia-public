package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  This class is used to represent a deck of ammo tiles where the ammo tile are taken to put them on the normal square.
 *
 * @author Giuseppe Italia.
 */

public class AmmoTileDeck {

    /**
     * List of ammo tiles available to be put on the map.
     */
    private ArrayList<AmmoTile> ammoTilesAvailable = new ArrayList<>();
    /**
     * The list of the ammo tiles already on the map.
     */
    private ArrayList<AmmoTile> ammoTilesOnTheMap = new ArrayList<>();
    /**
     * The list of the ammo tiles already drawn.
     */
    private ArrayList<AmmoTile> ammoTilesDiscarded = new ArrayList<>();
    /**
     * The instance of the class that implements singleton pattern.
     */
    private static AmmoTileDeck instanceOfAmmoTileDeck;

    /**
     * The constructor that calls the constructor of the class ammo tile adding the new ammo tile to the list of ammo tiles available and then shuffle it.
     */
    private AmmoTileDeck(){
        for(int i = 0 ; i < 4; i++){
            ammoTilesAvailable.add(new AmmoTile(1));
            ammoTilesAvailable.add(new AmmoTile(2));
            ammoTilesAvailable.add(new AmmoTile(3));
        }

        for(int i = 0 ; i <3 ; i++){
            ammoTilesAvailable.add(new AmmoTile(4));
            ammoTilesAvailable.add(new AmmoTile(5));
            ammoTilesAvailable.add(new AmmoTile(6));
            ammoTilesAvailable.add(new AmmoTile(7));
            ammoTilesAvailable.add(new AmmoTile(8));
            ammoTilesAvailable.add(new AmmoTile(9));

        }

        for(int i = 0 ; i < 2; i++){
            ammoTilesAvailable.add(new AmmoTile(10));
            ammoTilesAvailable.add(new AmmoTile(11));
            ammoTilesAvailable.add(new AmmoTile(12));
        }
        Collections.shuffle(ammoTilesAvailable);
    }


    /**
     * Shuffle the deck of ammo tiles available when this has a size lower than 3 adding to it all of the ammo tiles already discarded.
     */
    public void shuffleDeck(){
        ammoTilesAvailable.addAll(ammoTilesDiscarded);
        Collections.shuffle(ammoTilesAvailable);
    }



    static AmmoTileDeck instanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null)
            instanceOfAmmoTileDeck =new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }



    public ArrayList<AmmoTile> getAmmoTile() {
        return ammoTilesAvailable;
    }



    public ArrayList<AmmoTile> getAmmoTilesOnTheMap() {
        return ammoTilesOnTheMap;
    }

    public ArrayList<AmmoTile> getAmmoTilesDiscarded() {
        return ammoTilesDiscarded;
    }
}
