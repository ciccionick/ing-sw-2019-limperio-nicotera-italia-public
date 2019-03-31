package it.polimi.se2019.limperio.nicotera.italia.model;

public class SpawnSquare extends Square {
    private WheaponCard[] wheaponCards;

    public SpawnSquare(ColorOfFigure_Square color) {
        setColor(color);
    }

    public WheaponCard[] getWeaponCards() {
        return wheaponCards;
    }
}
