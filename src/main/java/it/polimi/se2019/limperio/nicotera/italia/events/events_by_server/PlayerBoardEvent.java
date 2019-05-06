package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.PlayerBoard;

/**
 * Event for notify a player about creation or update of his player board.
 *
 * @author Pietro L'Imperio
 */
public class PlayerBoardEvent extends ServerEvent {

    /**
     * The player board updated in {@link PlayerBoardEvent}
     */
    private PlayerBoard playerBoard = null;

    public PlayerBoardEvent(String message) {
        super(message);
        setPlayerBoardEvent(true);
    }


    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }
}
