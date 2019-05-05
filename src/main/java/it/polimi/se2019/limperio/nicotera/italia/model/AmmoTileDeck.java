package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  implements The AmmoTiles's deck
 *
 * @author giuseppeitalia
 */

public class AmmoTileDeck {

    private ArrayList<AmmoTile> ammoTilesAvailable = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesOnTheMap = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesDiscarded = new ArrayList<>();
    private int currentSize = 0;
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

        currentSize = ammoTilesAvailable.size();
        Collections.shuffle(ammoTilesAvailable);
    }


    /**
     *  <p>
     *      inserts new AmmoTile into the map
     *  </p>
     *
     * @param ammoTileToDraw The AmmoTile to be included in the map
     */

    public void drawAmmoTile (AmmoTile ammoTileToDraw){
        ammoTilesAvailable.remove(ammoTileToDraw);
        ammoTilesOnTheMap.add(ammoTileToDraw);
    }

    /**
     *
     * <p>
     *     checks if the deck of AmmoTile Available satisfies the request
     * </p>
     * @param numOfNeedTiles number of AmmoTile available requested
     * @return boolean: true if the number of AmmoTile Available is less than or equal to the request number
     */

    public boolean areThereEnoughTiles (int numOfNeedTiles){
        return ammoTilesAvailable.size()<=numOfNeedTiles;
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
        ammoTilesAvailable.addAll(ammoTilesOnTheMap);
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

    public int getCurrentSize() {
        return currentSize;
    }

    /* public ArrayList<AmmoTile> getAmmoTilesAvailable() {
        return ammoTilesAvailable;
    }*/
}
