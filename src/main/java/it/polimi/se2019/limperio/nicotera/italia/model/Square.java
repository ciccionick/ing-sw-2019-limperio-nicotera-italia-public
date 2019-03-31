package it.polimi.se2019.limperio.nicotera.italia.model;

public class Square {
    private ColorOfFigure_Square color;
    private AdjSquare[] adjSquares;

    public Square() {
    }

    public void setColor(ColorOfFigure_Square color) {
        this.color = color;
    }

    public Square (boolean isSpawn, ColorOfFigure_Square color){}

    public ColorOfFigure_Square getColor() {
        return color;
    }
}
