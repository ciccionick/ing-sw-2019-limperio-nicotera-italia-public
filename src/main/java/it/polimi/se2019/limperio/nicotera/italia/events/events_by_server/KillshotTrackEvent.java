package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.KillshotTrack;

/**
 * Event for update state of remote view of a player about the creation or update of killshot track.
 * @author Pietro L'Imperio
 */
public class KillshotTrackEvent extends ServerEvent {
    /**
     * The killshot track updated in {@link KillshotTrackEvent}
     */
    private KillshotTrack killShotTrack = null;

    /**
     * The nickname of the player of the turn. This information is useful especially during the first turn.
     */
    private String nicknamePlayerOfTheTurn;

    /**
     * Constructor of the classe where the message and the reference of killshot track are initialized.
     */
    public KillshotTrackEvent(String message, KillshotTrack killShotTrack) {
        super(message);
        setKillshotTrackEvent();
        setKillShotTrack(killShotTrack);
    }

    public KillshotTrack getKillShotTrack() {
        return killShotTrack;
    }

    private void setKillShotTrack(KillshotTrack killShotTrack) {

        this.killShotTrack = (KillshotTrack) killShotTrack.clone();
    }


    public String getNicknamePlayerOfTheTurn() {
        return nicknamePlayerOfTheTurn;
    }

    public void setNicknamePlayerOfTheTurn(String nicknamePlayerOfTheTurn) {
        this.nicknamePlayerOfTheTurn = nicknamePlayerOfTheTurn;
    }
}
