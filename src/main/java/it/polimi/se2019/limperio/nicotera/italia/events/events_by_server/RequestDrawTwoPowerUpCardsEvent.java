package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


/**
 * Event for ask to player to draw tow PowerUp cards in his first turn.
 *
 * @author Pietro L'Imperio
 */
public class RequestDrawTwoPowerUpCardsEvent extends ServerEvent {
    public RequestDrawTwoPowerUpCardsEvent(String message) {
        super(message);
        setRequestForDiscardPowerUpCardEvent(true);
    }
}
