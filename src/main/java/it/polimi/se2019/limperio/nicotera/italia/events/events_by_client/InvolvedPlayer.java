package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * Structure that store a player and square involved in an action like shoot or use of some powerUp cards
 *
 * @author  Pietro L'Imperio
 */
public class InvolvedPlayer {
    /**
     * The player involved in the action, it could be null in the case only the square is significant for the action
     */
    private Player player;
    /**
     * The list of the effects in which the player is involved, it could be null in the case only the player is significant for the action
     */
    private ArrayList<Integer>  effects;
    /**
     * The list of the type effects in which the player is involved.
     */
    private Square square;



    public InvolvedPlayer(Player players, ArrayList<Integer> effects, Square square) {
        this.player = players;
        this.effects = effects;
        this.square = square;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Integer> getEffects() {
        return effects;
    }

    public Square getSquare() {
        return square;
    }
}
