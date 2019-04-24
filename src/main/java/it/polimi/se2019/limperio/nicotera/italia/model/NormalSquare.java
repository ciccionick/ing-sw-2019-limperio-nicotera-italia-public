package it.polimi.se2019.limperio.nicotera.italia.model;

 class NormalSquare extends Square {
    private AmmoTile ammoTile;

     NormalSquare( ColorOfFigure_Square color, boolean hasDoor){
        super(color, hasDoor);
        ammoTile=null;
    }

}
