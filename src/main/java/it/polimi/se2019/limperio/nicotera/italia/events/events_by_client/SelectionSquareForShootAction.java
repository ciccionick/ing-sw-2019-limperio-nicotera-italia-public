package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionSquareForShootAction extends ClientEvent {

    private int row;
    private int column;

    public SelectionSquareForShootAction(String message, String nickname, int row, int column) {
        super(message, nickname);
        setSelectionSquareForShootAction(true);
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
