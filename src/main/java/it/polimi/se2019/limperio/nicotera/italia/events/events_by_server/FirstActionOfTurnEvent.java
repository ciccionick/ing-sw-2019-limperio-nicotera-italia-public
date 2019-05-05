package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event for notify a player about the start of his first turn
 *
 * @author Pietro L'Imperio
 */
public class FirstActionOfTurnEvent extends ServerEvent {
    public FirstActionOfTurnEvent(String message) {
        super(message);
        setFirstActionOfTurnEvent(true);
    }
}
