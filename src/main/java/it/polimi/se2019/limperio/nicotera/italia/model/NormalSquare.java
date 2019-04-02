package it.polimi.se2019.limperio.nicotera.italia.model;

public class NormalSquare extends Square {
    private AmmoTile ammoTile;

    public NormalSquare(Direction[] directions, ColorOfFigure_Square color){
        super(directions, color);
        ammoTile=null;

    }
}
