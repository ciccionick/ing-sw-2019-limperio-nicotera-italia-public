package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class DeathOfPlayer extends ModelEvent {

    private ArrayList<Player> deadPlayers;

    public DeathOfPlayer(Board board, Player currentPlayer, int numOfLeftActions, ArrayList<Player> deadPlayers) {
        super(board, currentPlayer, numOfLeftActions);
        this.deadPlayers = deadPlayers;
    }

    public ArrayList<Player> getDeadPlayers() {
        return deadPlayers;
    }
}
