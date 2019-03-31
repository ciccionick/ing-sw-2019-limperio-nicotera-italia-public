package it.polimi.se2019.limperio.nicotera.italia.model;

public class Ammo {
    private final ColorOfCard_Ammo color;
    private boolean isUsable;

    public Ammo(ColorOfCard_Ammo color) {
        this.color = color;
    }

    public ColorOfCard_Ammo getColor() {
        return color;
    }

    public boolean isUsable() {
        return isUsable;
    }
}
