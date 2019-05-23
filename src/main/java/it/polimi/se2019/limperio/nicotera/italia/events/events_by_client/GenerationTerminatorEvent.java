package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class GenerationTerminatorEvent extends ClientEvent {
    private int row;
    private int column;

    public GenerationTerminatorEvent(String nickname, int row, int column) {
        super("", nickname);
        this.row = row;
        this.column = column;
        setGenerationTerminatorEvent(true);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
