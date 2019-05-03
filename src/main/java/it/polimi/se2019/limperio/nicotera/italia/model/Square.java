package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Square implements Serializable {

    private ColorOfFigure_Square color;
    private boolean hasDoor;
    private ArrayList<Player> playerOfThisSquare;
    private Square north, south, west, east;
    private ArrayList<Square> adjSquares;
    private int row;
    private int column;
    private boolean isSpawn=false;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isSpawn() {
        return isSpawn;
    }

     void setSpawn(boolean spawn) {
        isSpawn = spawn;
    }

    public Square (ColorOfFigure_Square color, boolean hasDoor, int row, int column){
        this.color=color;
        this.hasDoor=hasDoor;
        this.row=row;
        this.column = column;
    }


    public ColorOfFigure_Square getColor() {

        return color;
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
        if(this.north!=null) {
            adjSquares.add(this.north);
        }
        if(this.south!=null) {
            adjSquares.add(this.south);
        }
        if(this.east!=null){
        adjSquares.add(this.east);
        }
        if(this.west!=null) {
            adjSquares.add(this.west);
        }
    }

    public Square getNorth() {
        return north;
    }

    public Square getSouth() {
        return south;
    }

    public Square getWest() {
        return west;
    }

    public Square getEast() {
        return east;
    }


    public ArrayList<Square> getAdjSquares() {
        return adjSquares;
    }

    public boolean isHasDoor() {
        return hasDoor;
    }

}
