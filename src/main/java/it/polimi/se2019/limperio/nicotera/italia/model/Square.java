package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to represent the square in the map
 * @author Francesco Nicotera
 */
public class Square implements Serializable{

    private ColorOfFigure_Square color;
    private boolean hasDoor;

    protected ArrayList<String> nicknamesOfPlayersOnThisSquare = new ArrayList<>();
    /**
     * Contains a reference to all the player that are in the square during the game
     */
    private transient ArrayList<Player> playerOnThisSquare = new ArrayList<>();
    /**
     * The references to the adjacency for each cardinal direction
     */
    private Square north, south, west, east;
    /**
     * Contains all the squares that are adjacent
     */
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



    public ArrayList<Player> getPlayerOnThisSquare() {
        return playerOnThisSquare;
    }

     void setPlayerOnThisSquare(Player player) {
        if (playerOnThisSquare ==null)
            playerOnThisSquare = new ArrayList<>();
        playerOnThisSquare.add(player);
        nicknamesOfPlayersOnThisSquare.add(player.getNickname());

    }

     void setCardinalSquare(Square north, Square south, Square west, Square east){
        this.north=north;
        this.south=south;
        this.east=east;
        this.west=west;
        adjSquares = new ArrayList<>();
        if(this.north!=null) {
            adjSquares.add(this.north);
        }
        if(this.south!=null) {
            adjSquares.add(this.south);
        }
        if(this.west!=null) {
            adjSquares.add(this.west);
        }
        if(this.east!=null){
            adjSquares.add(this.east);
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

    public ArrayList<String> getNicknamesOfPlayersOnThisSquare() {
        return nicknamesOfPlayersOnThisSquare;
    }

    public void setNicknamesOfPlayersOnThisSquare(ArrayList<String> nicknamesOfPlayersOnThisSquare) {
        this.nicknamesOfPlayersOnThisSquare = nicknamesOfPlayersOnThisSquare;
    }
}
