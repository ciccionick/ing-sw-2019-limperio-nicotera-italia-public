package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

public class CatchEvent extends ClientEvent {

    private int row;
    private int column;
    private ServerEvent.AliasCard card;

    public CatchEvent(String message, String nickname, int row, int column){
        super(message, nickname);
        setCatchEvent(true);
        this.row =row;
        this.column = column;
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
