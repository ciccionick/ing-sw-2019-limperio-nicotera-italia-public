package it.polimi.se2019.limperio.nicotera.italia.model;

public class NormalSquare extends Square {
    private AmmoTile ammoTile;

    public NormalSquare( ColorOfFigure_Square color, boolean hasDoor){
        super(color, hasDoor);
        ammoTile=null;

    }
}
