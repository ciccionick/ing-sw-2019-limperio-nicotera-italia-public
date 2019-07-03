package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event that handles the request to a player to choose an action that terminator has to do.
 * @author Pietro L'Imperio.
 */
public class RequestChooseActionForTerminator extends ServerEvent {

    /**
     * It's true if the terminator can shoot, false otherwise.
     */
    private boolean terminatorCanShoot;
    /**
     * It's true if the terminator can move.
     */
    private boolean terminatorCanMove;

    /**
     * Constructor that sets true the boolean field relative of this event.
     */
    public RequestChooseActionForTerminator() {
        setRequestToChooseTerminatorAction();
    }

    public boolean isTerminatorCanShoot() {
        return terminatorCanShoot;
    }

    public void setTerminatorCanShoot(boolean terminatorCanShoot) {
        this.terminatorCanShoot = terminatorCanShoot;
    }

    public boolean isTerminatorCanMove() {
        return terminatorCanMove;
    }

    public void setTerminatorCanMove(boolean terminatorCanMove) {
        this.terminatorCanMove = terminatorCanMove;
    }
}
