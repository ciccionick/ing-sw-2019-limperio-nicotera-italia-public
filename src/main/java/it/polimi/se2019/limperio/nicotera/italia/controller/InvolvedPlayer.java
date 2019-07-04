package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

/**
 * Structure that store a player and square involved in an action like shoot or use of some powerUp cards
 * @author  Pietro L'Imperio
 */
public class InvolvedPlayer {
    /**
     * The player involved in the action, it could be null in the case only the square is significant for the action
     */
    private Player player;
    /**
     * The list of the effect in which the player is involved, it could be null in the case only the player is significant for the action
     */
    private int effect;
    /**
     * The list of the type effect in which the player is involved.
     */
    private Square square;


    /**
     * Constructor that initialize the player, the square and the effect concerning.
     */
    public InvolvedPlayer(Player players,int effect, Square square) {
        this.player = players;
        this.effect = effect;
        this.square = square;
    }

    public Player getPlayer() {
        return player;
    }

    public int getEffect() {
        return effect;
    }

    public Square getSquare() {
        return square;
    }

    public void setSquare(Square square) {
        this.square = square;
    }
}
