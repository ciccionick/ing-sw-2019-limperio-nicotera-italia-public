package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToTerminatorActionByPlayer extends ClientEvent {
    public RequestToTerminatorActionByPlayer(String message, String nickname) {
        super(message, nickname);
    }
}
