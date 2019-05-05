package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class KillshotTrackEvent extends ServerEvent {
    public KillshotTrackEvent(String message) {
        super(message);
        setKillshotTrackEvent(true);
    }
}
