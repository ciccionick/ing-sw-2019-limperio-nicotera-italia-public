package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class MoveTerminatorEvent extends ClientEvent {
    private int row;
    private int column;
    public MoveTerminatorEvent(String terminator, int row, int column) {
        super("", terminator);
        this.row=row;
        this.column=column;
        setMoveTerminatorEvent(true);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
