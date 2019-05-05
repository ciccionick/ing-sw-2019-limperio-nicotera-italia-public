package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to express the wish to draw two powerUp cards in the first turn.
 *
 * @author Pietro L'Imperio
 */
public class DrawTwoPowerUpCards extends ClientEvent {
    public DrawTwoPowerUpCards(String message, String nickname) {
        super(message, nickname);
        setDrawTwoPowerUpCards(true);
    }
}
