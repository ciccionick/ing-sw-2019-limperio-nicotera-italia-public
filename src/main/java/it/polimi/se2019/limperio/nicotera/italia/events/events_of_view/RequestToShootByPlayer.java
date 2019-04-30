package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

public class RequestToShootByPlayer extends ViewEvent {
    public RequestToShootByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToShootByPlayer(true);
    }
}
