package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

public class AmmoTileDeck {

    private ArrayList<AmmoTile> ammoTiles = new ArrayList<>();
    private ArrayList<AmmoTile> usedAmmoTiles = new ArrayList<>();
    private int currentSize = 0;
    private static AmmoTileDeck instanceOfAmmoTileDeck;

    private AmmoTileDeck(){
        for(int i = 0 ; i < 2; i++){
            ammoTiles.add(new AmmoTile(1));
            ammoTiles.add(new AmmoTile(2));
        }
        for(int i = 0 ; i < 3; i++){
            ammoTiles.add(new AmmoTile(3));
        }
        for(int i = 0 ; i <4 ; i++){
            ammoTiles.add(new AmmoTile(4));
        }
        currentSize=ammoTiles.size();
        Collections.shuffle(ammoTiles);
    }


    public void drawAmmoTile (AmmoTile ammoTileToDraw){
        ammoTiles.remove(ammoTileToDraw);
        usedAmmoTiles.add(ammoTileToDraw);
    }

    public boolean areThereEnoughTiles (int numOfNeedTIles){
        return ammoTiles.size()<=numOfNeedTIles;
    }

    public void shuffleDeck(){
        ammoTiles.addAll(usedAmmoTiles);
        Collections.shuffle(ammoTiles);
    }

    static AmmoTileDeck instanceOfAmmoTileDeck(){
        if(instanceOfAmmoTileDeck==null) new AmmoTileDeck();
        return instanceOfAmmoTileDeck;
    }


    public ArrayList<AmmoTile> getAmmoTile() {
        return ammoTiles;
    }
}
