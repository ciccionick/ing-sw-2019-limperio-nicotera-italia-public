package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToRunByPlayer extends ClientEvent {
    public RequestToRunByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToRunByPlayer(true);
    }
}
