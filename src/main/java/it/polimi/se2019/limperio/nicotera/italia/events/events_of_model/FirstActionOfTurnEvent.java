package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class FirstActionOfTurnEvent extends ModelEvent {
    public FirstActionOfTurnEvent(String message) {
        super(message);
        setFirstActionOfTurnEvent(true);
    }
}
