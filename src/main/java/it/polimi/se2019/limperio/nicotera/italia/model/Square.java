package it.polimi.se2019.limperio.nicotera.italia.model;

public class Square {
    private ColorOfFigure_Square color;
    private AdjSquare[] adjSquares;
    private boolean hasDoor;


    public Square() {
    }

    public Square (boolean isSpawn, Direction[] directions, ColorOfFigure_Square color){}


    public void setColor(ColorOfFigure_Square color) {
        this.color = color;
    }


    public ColorOfFigure_Square getColor() {
        return color;
    }

    public boolean isHasDoor() {
        return hasDoor;
    }
}
