package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class Square {

    private ColorOfFigure_Square color;
    private boolean hasDoor;
    private ArrayList<Player> playerOfThisSquare;
    private Square nord, sud, west, east;
    private ArrayList<Square> adjSquares;




    public Square ( ColorOfFigure_Square color, boolean hasDoor){
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
            playerOfThisSquare = new ArrayList<Player>();
        playerOfThisSquare.add(player);

    }

    public void setCardinalSquare(Square nord, Square sud, Square west, Square east){
        this.nord=nord;
        this.sud=sud;
        this.east=east;
        this.west=west;
        adjSquares = new ArrayList<Square>();
        adjSquares.add(this.nord);
        adjSquares.add(this.sud);
        adjSquares.add(this.east);
        adjSquares.add(this.west);
    }

    public Square getNord() {
        return nord;
    }

    public Square getSud() {
        return sud;
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
}
