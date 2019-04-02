package it.polimi.se2019.limperio.nicotera.italia.model;

public class SpawnSquare extends Square {
    private WeaponCard[] wheaponCards;

    public SpawnSquare(Direction[] directions, ColorOfFigure_Square color) {
        super(directions, color);
        wheaponCards=null;
    }

    public WeaponCard[] getWeaponCards() {
        return wheaponCards;
    }
}
