package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class MapEvent extends ModelEvent{
    public MapEvent(String message) {
        super(message);
        setMapEvent(true);
    }
}
