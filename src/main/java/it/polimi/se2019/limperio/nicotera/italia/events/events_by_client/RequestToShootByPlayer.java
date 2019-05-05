package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToShootByPlayer extends ClientEvent {
    public RequestToShootByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToShootByPlayer(true);
    }
}
