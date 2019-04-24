package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class StartTurnEvent extends ModelEvent {

    public StartTurnEvent(String message) {
        super(message);
        setStartTurnEvent(true);
    }
}
