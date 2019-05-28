package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to express the wish to draw two powerUp cards in the first turn.
 *
 * @author Pietro L'Imperio
 */
public class DrawPowerUpCards extends ClientEvent {
    private int numOfCards;
    public DrawPowerUpCards(String message, String nickname, int num) {
        super(message, nickname);
        setDrawPowerUpCard(true);
        this.numOfCards = num;
    }

    public int getNumOfCards() {
        return numOfCards;
    }
}
