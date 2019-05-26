package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  This class is used to represent the AmmoTiles's deck
 *
 * @author giuseppeitalia
 */

public class AmmoTileDeck {

    private ArrayList<AmmoTile> ammoTilesAvailable = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesOnTheMap = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesDiscarded = new ArrayList<>();
    private static AmmoTileDeck instanceOfAmmoTileDeck;

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
     * <p>
     *     puts in the map the AmmoTile Available
     * </p>
     * <p>
     *     When a player's turn ends we need to insert a AmmoTile Available at the points on the map
     *     where they were taken by the previous player. At the end of the method shuffle AmmoTiles Available
     * </p>
     *
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
