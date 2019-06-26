package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class RequestChooseActionForTerminator extends ServerEvent {

    private boolean terminatorCanShoot;
    private boolean terminatorCanMove;

    public RequestChooseActionForTerminator() {
        setRequestToChooseTerminatorAction(true);
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
