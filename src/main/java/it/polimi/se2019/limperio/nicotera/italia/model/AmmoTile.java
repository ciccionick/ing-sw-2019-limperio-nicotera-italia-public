package it.polimi.se2019.limperio.nicotera.italia.model;


import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

public class AmmoTile {


    private boolean hasPowerUpCard;
    private ArrayList<ColorOfCard_Ammo> ammos = new ArrayList<>();

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
            case 8:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(YELLOW);
                ammos.add(BLUE);
                break;
            case 9:
                hasPowerUpCard = false;
                ammos.add(BLUE);
                ammos.add(RED);
                ammos.add(RED);
                break;
            case 10:
                hasPowerUpCard = false;
                ammos.add(RED);
                ammos.add(RED);
                ammos.add(YELLOW);
                break;
            case 11:
                hasPowerUpCard = true;
                ammos.add(RED);
                ammos.add(RED);
                break;
            case 12:
                hasPowerUpCard = true;
                ammos.add(BLUE);
                ammos.add(BLUE);
                break;
            case 13:
                hasPowerUpCard = true;
                ammos.add(YELLOW);
                ammos.add(YELLOW);
                break;
            default:
                throw  new IllegalArgumentException();
        }


    }


    public ArrayList<ColorOfCard_Ammo> getAmmos() {
        return ammos;
    }

    public boolean hasPowerUpCard() {
        return hasPowerUpCard;
    }




}
