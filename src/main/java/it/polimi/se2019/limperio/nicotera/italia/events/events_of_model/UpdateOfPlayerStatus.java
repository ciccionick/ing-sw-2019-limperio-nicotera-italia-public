package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

public class UpdateOfPlayerStatus extends ModelEvent {

    private Player playerInvolved;

    public UpdateOfPlayerStatus(Board board, Player currentPlayer, int numOfLeftActions, Player playerInvolved) {
        super(board,currentPlayer,numOfLeftActions);
        this.playerInvolved = playerInvolved;
    }

    public Player getPlayerInvolvedInTheUpdate() {
        return playerInvolved;
    }
}
