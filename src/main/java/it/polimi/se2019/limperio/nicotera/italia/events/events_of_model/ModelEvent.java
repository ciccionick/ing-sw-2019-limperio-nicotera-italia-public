package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.io.Serializable;

public class ModelEvent implements Serializable {
    private String message;
    private Board board;
    private Player currentPlayer;
    private int numOfLeftActions;
    private String nickname;

    public ModelEvent() {

    }


    public ModelEvent(Board board, Player currentPlayer, int numOfLeftActions) {
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.numOfLeftActions = numOfLeftActions;
    }

    public String getMessage() {
        return message;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


}
