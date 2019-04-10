package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class Square {

    private ColorOfFigure_Square color;
    private boolean hasDoor;
    private ArrayList<Player> playerOfThisSquare;
    private Square nord, sud, west, east;




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
    }

}
