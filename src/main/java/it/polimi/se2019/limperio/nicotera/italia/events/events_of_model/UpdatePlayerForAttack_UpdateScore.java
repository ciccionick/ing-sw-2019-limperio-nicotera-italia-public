package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class UpdatePlayerForAttack_UpdateScore extends ModelEvent{

    private ArrayList<Player> playersInvolved;
    public UpdatePlayerForAttack_UpdateScore(Board board, Player currentPlayer, int numOfLeftActions, ArrayList<Player> players) {
        super(board, currentPlayer, numOfLeftActions);
        playersInvolved = players;
    }

    public ArrayList<Player> getPlayersInvolved() {
        return playersInvolved;
    }
}
