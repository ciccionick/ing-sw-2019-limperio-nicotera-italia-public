package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event for notify a player about creation or update of his player board.
 *
 * @author Pietro L'Imperio
 */
public class PlayerBoardEvent extends ServerEvent {

    public PlayerBoardEvent(String message) {
        super(message);
        setPlayerBoardEvent(true);
    }

}
