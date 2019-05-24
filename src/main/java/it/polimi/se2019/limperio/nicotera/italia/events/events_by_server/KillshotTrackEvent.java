package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.KillshotTrack;

/**
 * Event for notify a player about the creation or update of Killshot track.
 *
 * @author Pietro L'Imperio
 */
public class KillshotTrackEvent extends ServerEvent {
    /**
     * The killshot track updated in {@link KillshotTrackEvent}
     */
    private String nicknamePlayerOfTheTurn;
    private KillshotTrack killShotTrack = null;

    public KillshotTrackEvent(String message, KillshotTrack killShotTrack) {
        super(message);
        setKillshotTrackEvent(true);
        setKillShotTrack(killShotTrack);
    }

    public KillshotTrack getKillShotTrack() {
        return killShotTrack;
    }

    private void setKillShotTrack(KillshotTrack killShotTrack) {
        this.killShotTrack = killShotTrack;
    }

    public String getNicknamePlayerOfTheTurn() {
        return nicknamePlayerOfTheTurn;
    }

    public void setNicknamePlayerOfTheTurn(String nicknamePlayerOfTheTurn) {
        this.nicknamePlayerOfTheTurn = nicknamePlayerOfTheTurn;
    }
}
