package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class Square {
    private ColorOfFigure_Square color;
    private ArrayList<AdjSquare> adjSquares;
    private boolean hasDoor;
    private ArrayList<Player> playerOfThisSquare;



    Direction[] directions;


    public Square() {
    }

    public Square (Direction[] directions, ColorOfFigure_Square color){
        setColor(color);
        if (directions!=null)
            setHasDoor(true);
        else
            setHasDoor(false);
        setDirections(directions);
        adjSquares= new ArrayList<AdjSquare>();
    }


    public void setColor(ColorOfFigure_Square color) {
        this.color = color;
    }


    public ColorOfFigure_Square getColor() {
        return color;
    }

    public void setHasDoor(boolean hasDoor) {
        this.hasDoor = hasDoor;
    }
    public boolean isHasDoor() {
        return hasDoor;
    }


    public Direction[] getDirections() {
        return directions;
    }

    public void setDirections(Direction[] directions) {
        this.directions = directions;

    }

    public void setAdjSquares(Direction direction, Square square){
        adjSquares.add(new AdjSquare(direction, square));
    }

    public ArrayList<Player> getPlayerOfThisSquare() {
        return playerOfThisSquare;
    }

    public void setPlayerOfThisSquare(Player player) {
        if (playerOfThisSquare==null)
            playerOfThisSquare = new ArrayList<Player>();
        playerOfThisSquare.add(player);

    }
}
