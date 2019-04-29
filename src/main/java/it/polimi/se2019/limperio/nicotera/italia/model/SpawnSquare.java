package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class SpawnSquare extends Square {

    private ArrayList<WeaponCard> weaponCards;


     SpawnSquare( ColorOfFigure_Square color, boolean hasDoor) {
        super(color, hasDoor);
        weaponCards=new ArrayList<>();
        setSpawn(true);
    }



    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }
}
