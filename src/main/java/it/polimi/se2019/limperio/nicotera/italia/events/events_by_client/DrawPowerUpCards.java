package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to express the wish to draw two powerUp cards in the first turn.
 *
 * @author Pietro L'Imperio
 */
public class DrawPowerUpCards extends ClientEvent {
    /**
     * The number of the cards to draw.
     */
    private int numOfCards;
    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public DrawPowerUpCards(String message, String nickname, int num) {
        super(message, nickname);
        setDrawPowerUpCard(true);
        this.numOfCards = num;
    }

    public int getNumOfCards() {
        return numOfCards;
    }
}
