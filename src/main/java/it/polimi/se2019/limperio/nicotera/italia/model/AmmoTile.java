package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the AmmoTile
 *
 * @author giuseppeitalia
 */

public class AmmoTile implements Serializable, Cloneable {
    static final long serialVersionUID = 420000010;

    private boolean hasPowerUpCard;
    private ArrayList<ColorOfCard_Ammo> listOfAmmo = new ArrayList<>();
    private int typeOfAmmoTile;



    public  AmmoTile(int typeAmmoTile)
    {
        switch(typeAmmoTile) {
            case 1:
                hasPowerUpCard = true;
                listOfAmmo.add(RED);
                listOfAmmo.add(BLUE);
                break;
            case 2:
                hasPowerUpCard = true;
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(BLUE);
                break;
            case 3:
                hasPowerUpCard = true;
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(RED);
                break;
            case 4:
                hasPowerUpCard = false;
                listOfAmmo.add(BLUE);
                listOfAmmo.add(BLUE);
                listOfAmmo.add(RED);
                break;
            case 5:
                hasPowerUpCard = false;
                listOfAmmo.add(RED);
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(YELLOW);
                break;
            case 6:
                hasPowerUpCard = false;
                listOfAmmo.add(BLUE);
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(YELLOW);
                break;
            case 7:
                hasPowerUpCard = false;
                listOfAmmo.add(BLUE);
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(BLUE);
                break;
            case 8:
                hasPowerUpCard = false;
                listOfAmmo.add(BLUE);
                listOfAmmo.add(RED);
                listOfAmmo.add(RED);
                break;
            case 9:
                hasPowerUpCard = false;
                listOfAmmo.add(RED);
                listOfAmmo.add(RED);
                listOfAmmo.add(YELLOW);
                break;
            case 10:
                hasPowerUpCard = true;
                listOfAmmo.add(RED);
                listOfAmmo.add(RED);
                break;
            case 11:
                hasPowerUpCard = true;
                listOfAmmo.add(BLUE);
                listOfAmmo.add(BLUE);
                break;
            case 12:
                hasPowerUpCard = true;
                listOfAmmo.add(YELLOW);
                listOfAmmo.add(YELLOW);
                break;
            default:
                throw  new IllegalArgumentException();
        }
        this.typeOfAmmoTile=typeAmmoTile;


    }

    public Object clone(){
        AmmoTile ammoTile = null;
        try{
            ammoTile = (AmmoTile) super.clone();

        } catch (CloneNotSupportedException e) {
            ammoTile = new AmmoTile(this.typeOfAmmoTile);
        }
        return ammoTile;
    }


    public ArrayList<ColorOfCard_Ammo> getListOfAmmo() {
        return listOfAmmo;
    }


    public boolean hasPowerUpCard() {
        return hasPowerUpCard;
    }

    public int getTypeOfAmmoTile() {
        return typeOfAmmoTile;
    }
}
