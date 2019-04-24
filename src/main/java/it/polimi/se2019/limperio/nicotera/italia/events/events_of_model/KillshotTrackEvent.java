package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class KillshotTrackEvent extends ModelEvent {
    public KillshotTrackEvent(String message) {
        super(message);
        setKillshotTrackEvent(true);
    }
}
