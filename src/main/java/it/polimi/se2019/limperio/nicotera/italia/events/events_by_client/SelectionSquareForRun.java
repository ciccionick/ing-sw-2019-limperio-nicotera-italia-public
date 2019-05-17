package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionSquareForRun extends ClientEvent{

    private int row;
    private int column;

    public SelectionSquareForRun(String message,String nickname,  int row, int column){
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
}
