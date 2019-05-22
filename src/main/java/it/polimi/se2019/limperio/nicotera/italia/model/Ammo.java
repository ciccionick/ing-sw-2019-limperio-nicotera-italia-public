package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

/**
 * This class is used to represent the ammo
 * @author giuseppeitali
 */

public class Ammo implements Serializable, Cloneable {
    private final ColorOfCard_Ammo color;
    private boolean isUsable;

    public Ammo(ColorOfCard_Ammo color, boolean isUsable)
    {
        this.color = color;
        this.isUsable=isUsable;
    }

    public ColorOfCard_Ammo getColor() {
        return color;
    }

    /**
     * calculates if the ammo is usable
     *
     * <p>
     *     After the ammo has been used, we need to reload it to be able to use it
     * </p>
     * @return a boolean variable that says if the ammo is usable
     */

    public boolean isUsable() {
        return isUsable;
    }

    public void setIsUsable(boolean bool){ isUsable = bool;}

    public Object clone(){
        Ammo ammo = null;
        try {
            ammo = (Ammo) super.clone();
        } catch (CloneNotSupportedException e) {
            ammo = new Ammo(this.color, this.isUsable);
        }
        ammo.isUsable = isUsable;
        return ammo;
    }
}
