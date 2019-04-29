package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Square implements Serializable {

    private ColorOfFigure_Square color;
    private boolean hasDoor;
    private ArrayList<Player> playerOfThisSquare;
    private Square north, south, west, east;
    private ArrayList<Square> adjSquares;
    private boolean isSpawn=false;




    public boolean isSpawn() {
        return isSpawn;
    }

     void setSpawn(boolean spawn) {
        isSpawn = spawn;
    }

    public Square (ColorOfFigure_Square color, boolean hasDoor){
        this.color=color;
        this.hasDoor=hasDoor;
    }


    public ColorOfFigure_Square getColor() {

        return color;
    }


    public boolean hasDoor() {
        return hasDoor;
    }


    public ArrayList<Player> getPlayerOfThisSquare() {
        return playerOfThisSquare;
    }

    public void setPlayerOfThisSquare(Player player) {
        if (playerOfThisSquare==null)
            playerOfThisSquare = new ArrayList<>();
        playerOfThisSquare.add(player);

    }

    public void setCardinalSquare(Square north, Square south, Square west, Square east){
        this.north=north;
        this.south=south;
        this.east=east;
        this.west=west;
        adjSquares = new ArrayList<Square>();
        adjSquares.add(this.north);
        adjSquares.add(this.south);
        adjSquares.add(this.east);
        adjSquares.add(this.west);
    }

    public Square getNord() {
        return north;
    }

    public Square getSud() {
        return south;
    }

    public Square getWest() {
        return west;
    }

    public Square getEast() {
        return east;
    }

     ArrayList<Square> getAdjSquares() {
        return adjSquares;
    }

    public boolean isHasDoor() {
        return hasDoor;
    }

}
