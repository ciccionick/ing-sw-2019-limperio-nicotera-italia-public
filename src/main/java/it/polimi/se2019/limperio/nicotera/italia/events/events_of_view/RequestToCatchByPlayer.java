package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

public class RequestToCatchByPlayer extends ViewEvent {
    public RequestToCatchByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToCatchByPlayer(true);
    }
}
