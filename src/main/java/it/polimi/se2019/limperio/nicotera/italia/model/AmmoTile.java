package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the ammo tile of the game.
 * It can be composed only by ammo or by ammo and one power up card according with the rules of the game.
 *
 * @author Giuseppe Italia
 */

public class AmmoTile implements Serializable, Cloneable {
    static final long serialVersionUID = 420000010;

    /**
     * It's true if the ammo tile is composed also by a power up card.
     */
    private boolean hasPowerUpCard;
    /**
     * The list of the ammo that composes the ammo tile.
     */
    private ArrayList<ColorOfCard_Ammo> listOfAmmo = new ArrayList<>();
    /**
     * The number of ammo tile used to recognize them especially in the gui for the relative icon.
     */
    private int typeOfAmmoTile;


    /**
     * Constructor of ammo tile. According with the number of the type of ammo tile passed by parameter creates the right one.
     */
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

    /**
     * Override of the clone method to make possible the serialization avoiding the shallow copy.
     * @return The cloned object.
     */
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
