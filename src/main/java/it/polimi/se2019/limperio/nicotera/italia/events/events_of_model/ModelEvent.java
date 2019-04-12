package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

public class ModelEvent {

    private Board board;
    private Player currentPlayer;
    private int numOfLeftActions;


    public ModelEvent(Board board, Player currentPlayer, int numOfLeftActions) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.numOfLeftActions = numOfLeftActions;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumOfLeftActions() {
        return numOfLeftActions;
    }
}
