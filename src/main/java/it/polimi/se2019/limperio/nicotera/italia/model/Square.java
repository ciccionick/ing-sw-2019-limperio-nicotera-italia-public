package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is used to represent the square in the map
 * @author Francesco Nicotera
 */
public class Square implements Serializable{
    static final long serialVersionUID = 420000004;

    /**
     * The color of the square.
     */
    private ColorOfFigure_Square color;
    /**
     * It's true if the square has door towards other rooms, false otherwise.
     */
    private boolean hasDoor;

    /**
     * The list of nicknames of the players on the square.
     */
     ArrayList<String> nicknamesOfPlayersOnThisSquare = new ArrayList<>();
    /**
     * Contains a reference to all the player that are in the square during the game
     */
    private transient ArrayList<Player> playerOnThisSquare = new ArrayList<>();
    /**
     * The references to the north adjacency for the square.
     */
    private Square north;
    /**
     * The references to the south adjacency for the square.
     */
    private Square south;
    /**
     * The references to the west adjacency for the square.
     */
    private Square west;
    /**
     * The references to the east adjacency for the square.
     */
    private Square east;
    /**
     * Contains all the squares that are adjacent
     */
    private ArrayList<Square> adjSquares;
    /**
     * The row of the square in the matrix.
     */
    private int row;
    /**
     * The column of the square in the matrix.
     */
    private int column;
    /**
     * It's true if the square is a spawn square, false otherwise.
     */
    private boolean isSpawn=false;


    /**
     * Constructor that initializes color, row, column of the square and if it has door or not.
     */
    public Square (ColorOfFigure_Square color, boolean hasDoor, int row, int column){
        this.color=color;
        this.hasDoor=hasDoor;
        this.row=row;
        this.column = column;
    }

    /**
     * Sets on the square the player passed by parameter and add his nickname to the list of nicknames.
     */
     void setPlayerOnThisSquare(Player player) {
        if (playerOnThisSquare ==null)
            playerOnThisSquare = new ArrayList<>();
        playerOnThisSquare.add(player);
        nicknamesOfPlayersOnThisSquare.add(player.getNickname());

    }

    /**
     * Sets the reference of the squares located on its adjacency.
     */
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

    public ColorOfFigure_Square getColor() {
        return color;
    }

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


    public ArrayList<Player> getPlayerOnThisSquare() {
        return playerOnThisSquare;
    }

}
