package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

public class InvolvedPlayer {

    private Player player;
    private ArrayList<Integer>  effects;



    public InvolvedPlayer(Player players, ArrayList<Integer> effects) {
        this.player = players;
        this.effects = effects;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Integer> getEffects() {
        return effects;
    }
}
