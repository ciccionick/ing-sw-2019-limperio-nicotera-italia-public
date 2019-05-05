package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event for ask to player to discard one of his powerUp card to be generated in one of spawn square.
 *
 * @author  Pietro L'Imperio
 */
public class RequestForDiscardPowerUpCardEvent extends ServerEvent {
    public RequestForDiscardPowerUpCardEvent(String message) {
        super(message);
        setDiscardPowerUpCardToSpawnEvent(true);
    }
}
