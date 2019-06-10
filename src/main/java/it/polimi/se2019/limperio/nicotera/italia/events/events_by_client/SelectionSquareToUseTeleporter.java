package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionSquareToUseTeleporter extends  ClientEvent {
    int row;
    int column;


    public SelectionSquareToUseTeleporter(String message, String nickname, int row, int column) {
        super(message, nickname);
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
