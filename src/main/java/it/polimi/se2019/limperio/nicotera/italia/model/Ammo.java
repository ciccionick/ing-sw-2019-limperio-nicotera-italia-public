package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

/**
 * This class is used to represent the ammo
 * @author Giuseppe Italia
 */

public class Ammo implements Serializable, Cloneable {
    static final long serialVersionUID = 420000002;
    /**
     * The color of the ammo.
     */
    private final ColorOfCard_Ammo color;
    /**
     * It's true if the ammo is usable, false otherwise.
     */
    private boolean isUsable;

    /**
     * Constructor the initialize the color of ammo and his boolean field that show if it is usable or not.
     */
    public Ammo(ColorOfCard_Ammo color, boolean isUsable)
    {
        this.color = color;
        this.isUsable=isUsable;
    }

    public ColorOfCard_Ammo getColor() {
        return color;
    }


    public boolean isUsable() {
        return isUsable;
    }

    public void setIsUsable(boolean bool){ isUsable = bool;}

    /**
     * Override of the clone method to make possible the serialization avoiding the shallow copy.
     * @return The cloned object.
     */
    public Object clone(){
        Ammo ammo;
        try {
            ammo = (Ammo) super.clone();
        } catch (CloneNotSupportedException e) {
            ammo = new Ammo(this.color, this.isUsable);
        }
        ammo.isUsable = isUsable;
        return ammo;
    }
}
