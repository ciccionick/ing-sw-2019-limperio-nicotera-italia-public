package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when the player decides to use teleporter.
 * @author Pietro L'Imperio.
 */
public class RequestToUseTeleporter extends ClientEvent {
    /**
     * The number of the power up card in the power up cards deck.
     */
    private int numOfCard;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public RequestToUseTeleporter(String message, String nickname) {
        super(message, nickname);
        setRequestToUseTeleporter();
    }

    public int getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }
}
