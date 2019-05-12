package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the AmmoTile
 *
 * @author giuseppeitalia
 */

public class AmmoTile implements Serializable {


    private boolean hasPowerUpCard;
    private ArrayList<ColorOfCard_Ammo> ammos = new ArrayList<>();
    private int typeOfAmmoTile;



     AmmoTile(int typeAmmoTile)
    {
        switch(typeAmmoTile) {
            case 1:
                hasPowerUpCard = true;
                ammos.add(RED);
                ammos.add(BLUE);
                break;
            case 2:
                hasPowerUpCard = true;
                ammos.add(YELLOW);
                ammos.add(BLUE);
                break;
            case 3:
                hasPowerUpCard = true;
                ammos.add(YELLOW);
                ammos.add(RED);
                break;
            case 4:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(BLUE);
                ammos.add(RED);
                break;
            case 5:
                hasPowerUpCard = false;
                ammos.add(RED);
                ammos.add(YELLOW);
                ammos.add(YELLOW);
                break;
            case 6:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(YELLOW);
                ammos.add(YELLOW);
                break;
            case 7:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(YELLOW);
                ammos.add(BLUE);
                break;
            case 8:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(RED);
                ammos.add(RED);
                break;
            case 9:
                hasPowerUpCard = false;
                ammos.add(RED);
                ammos.add(RED);
                ammos.add(YELLOW);
                break;
            case 10:
                hasPowerUpCard = true;
                ammos.add(RED);
                ammos.add(RED);
                break;
            case 11:
                hasPowerUpCard = true;
                ammos.add(BLUE);
                ammos.add(BLUE);
                break;
            case 12:
                hasPowerUpCard = true;
                ammos.add(YELLOW);
                ammos.add(YELLOW);
                break;
            default:
                throw  new IllegalArgumentException();
        }
        this.typeOfAmmoTile=typeAmmoTile;


    }



    public ArrayList<ColorOfCard_Ammo> getAmmos() {
        return ammos;
    }


    public boolean hasPowerUpCard() {
        return hasPowerUpCard;
    }



    @Override
    public String toString() {
        String string;
        string = "Ammo: ";
        for (ColorOfCard_Ammo color : getAmmos()){
            string = string.concat(color.toString() + ",");
        }
        if(hasPowerUpCard){
            string = string.concat(" e puoi pescare powerUp Card");
        }
        return string;
    }
}
