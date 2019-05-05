package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToCatchByPlayer extends ClientEvent {
    public RequestToCatchByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToCatchByPlayer(true);
    }
}
