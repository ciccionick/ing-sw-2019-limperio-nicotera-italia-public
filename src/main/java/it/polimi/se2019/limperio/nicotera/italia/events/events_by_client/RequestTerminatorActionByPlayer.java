package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestTerminatorActionByPlayer extends ClientEvent {
    public RequestTerminatorActionByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestTerminatorActionByPlayer();
    }
}
