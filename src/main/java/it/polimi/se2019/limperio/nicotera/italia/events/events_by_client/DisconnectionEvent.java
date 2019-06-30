package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class DisconnectionEvent extends ClientEvent {
    public DisconnectionEvent(String message, String nickname) {
        super(message, nickname);
        setDisconnectionEvent();
    }
}
