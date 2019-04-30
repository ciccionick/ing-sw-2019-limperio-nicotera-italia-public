package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

public class RequestToRunByPlayer extends ViewEvent {
    public RequestToRunByPlayer(String message, String nickname) {
        super(message, nickname);
        setRequestToRunByPlayer(true);
    }
}
