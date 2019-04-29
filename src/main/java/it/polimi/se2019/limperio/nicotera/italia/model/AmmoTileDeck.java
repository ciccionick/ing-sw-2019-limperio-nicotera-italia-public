package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

public class AmmoTileDeck {

    private ArrayList<AmmoTile> ammoTilesAvailable = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesOnTheMap = new ArrayList<>();
    private ArrayList<AmmoTile> ammoTilesDiscarded = new ArrayList<>();
    private int currentSize = 0;
    private static AmmoTileDeck instanceOfAmmoTileDeck;

    private AmmoTileDeck(){
        for(int i = 0 ; i < 2; i++){
            ammoTilesAvailable.add(new AmmoTile(1));
            ammoTilesAvailable.add(new AmmoTile(2));
        }
        for(int i = 0 ; i < 3; i++){
            ammoTilesAvailable.add(new AmmoTile(3));
        }
        for(int i = 0 ; i <4 ; i++){
            ammoTilesAvailable.add(new AmmoTile(4));
        }
        currentSize= ammoTilesAvailable.size();
        Collections.shuffle(ammoTilesAvailable);
    }


    public void drawAmmoTile (AmmoTile ammoTileToDraw){
        ammoTilesAvailable.remove(ammoTileToDraw);
        ammoTilesOnTheMap.add(ammoTileToDraw);
    }

    public boolean areThereEnoughTiles (int numOfNeedTIles){
        return ammoTilesAvailable.size()<=numOfNeedTIles;
    }

    public void shuffleDeck(){
        ammoTilesAvailable.addAll(ammoTilesOnTheMap);
        Collections.shuffle(ammoTilesAvailable);
    }

    static AmmoTileDeck instanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }


     ArrayList<AmmoTile> getAmmoTile() {
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
}
