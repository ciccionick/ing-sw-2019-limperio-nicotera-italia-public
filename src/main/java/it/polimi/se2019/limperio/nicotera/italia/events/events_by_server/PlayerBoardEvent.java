package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class PlayerBoardEvent extends ServerEvent {

    public PlayerBoardEvent(String message) {
        super(message);
        setPlayerBoardEvent(true);
    }

}
