package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to show to the controller the wish to do a run action.
 *
 * @author Pietro L'Imperio
 */
public class RequestToRunByPlayer extends ClientEvent {

    public RequestToRunByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToRunByPlayer(true);
    }
}
