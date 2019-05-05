package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event to show to the controller the wish to do a shoot action.
 *
 * @author Pietro L'Imperio
 */
public class RequestToShootByPlayer extends ClientEvent {
    public RequestToShootByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToShootByPlayer(true);
    }
}
