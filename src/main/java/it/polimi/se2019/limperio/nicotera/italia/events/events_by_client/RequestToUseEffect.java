package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated after that player decides to use an effect.
 * @author Pietro L'Imperio.
 */
public class RequestToUseEffect extends ClientEvent {
    /**
     * The number of the effect that player decides to use.
     */
    private int numOfEffect;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */

    public RequestToUseEffect(String message, String nickname) {
        super(message, nickname);
        setRequestToUseEffect();
    }

    public int getNumOfEffect() {
        return numOfEffect;
    }

    public void setNumOfEffect(int numOfEffect) {
        this.numOfEffect = numOfEffect;
    }
}
