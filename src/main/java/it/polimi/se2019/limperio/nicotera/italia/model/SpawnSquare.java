package it.polimi.se2019.limperio.nicotera.italia.model;

public class SpawnSquare extends Square {
    private WeaponCard[] wheaponCards;

    public SpawnSquare( ColorOfFigure_Square color, boolean hasDoor) {
        super(color, hasDoor);
        wheaponCards=null;
    }

    public WeaponCard[] getWeaponCards() {
        return wheaponCards;
    }
}
