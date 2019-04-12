package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.util.ArrayList;

public class EndGame extends ModelEvent {

    private ArrayList<Player> ranking;
    public EndGame(Board board, Player currentPlayer, int numOfLeftActions, ArrayList<Player> ranking) {
        super(board, currentPlayer, numOfLeftActions);
        this.ranking = ranking;
    }

    public ArrayList<Player> getRanking() {
        return ranking;
    }
}
