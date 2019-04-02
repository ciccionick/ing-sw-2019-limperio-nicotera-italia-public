package it.polimi.se2019.limperio.nicotera.italia.model;

public class SpawnSquare extends Square {
    private WheaponCard[] wheaponCards;

    public SpawnSquare(Direction[] directions, ColorOfFigure_Square color) {
        super(directions, color);
        wheaponCards=null;
    }

    public WheaponCard[] getWeaponCards() {
        return wheaponCards;
    }
}
