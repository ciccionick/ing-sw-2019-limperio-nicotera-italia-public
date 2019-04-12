package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

public class InvolvedPlayer {

    private Player player;
    private ArrayList<Integer>  effects;
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
