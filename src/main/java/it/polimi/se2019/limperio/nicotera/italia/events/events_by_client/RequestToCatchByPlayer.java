package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to show to the controller the wish to do a catch action.
 *
 * @author Pietro L'Imperio
 */
public class RequestToCatchByPlayer extends ClientEvent {
    public RequestToCatchByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToCatchByPlayer(true);
    }
}
