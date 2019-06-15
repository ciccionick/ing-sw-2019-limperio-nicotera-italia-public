package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RunEvent extends ClientEvent{

    private int row;
    private int column;
    private boolean isBeforeToShoot = false;

    public RunEvent(String message, String nickname, int row, int column){
        super(message, nickname);
        setSelectionSquareForRun(true);
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isBeforeToShoot() {
        return isBeforeToShoot;
    }

    public void setBeforeToShoot(boolean beforeToShoot) {
        isBeforeToShoot = beforeToShoot;
    }
}
