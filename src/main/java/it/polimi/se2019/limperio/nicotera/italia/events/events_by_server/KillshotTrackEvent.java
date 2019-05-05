package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event for notify a player about the creation or update of Killshot track.
 *
 * @author Pietro L'Imperio
 */
public class KillshotTrackEvent extends ServerEvent {
    public KillshotTrackEvent(String message) {
        super(message);
        setKillshotTrackEvent(true);
    }
}
