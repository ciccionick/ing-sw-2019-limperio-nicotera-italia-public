package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

public class Ammo implements Serializable {
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

    public boolean isUsable() {
        return isUsable;
    }
}
