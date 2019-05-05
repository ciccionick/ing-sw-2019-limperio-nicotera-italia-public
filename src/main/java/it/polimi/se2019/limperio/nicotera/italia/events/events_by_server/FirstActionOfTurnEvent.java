package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class FirstActionOfTurnEvent extends ServerEvent {
    public FirstActionOfTurnEvent(String message) {
        super(message);
        setFirstActionOfTurnEvent(true);
    }
}
