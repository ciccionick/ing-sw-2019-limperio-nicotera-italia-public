package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;


public class SelectionWeaponToCatch extends ClientEvent {
    private String  nameOfWeaponCard;
    private int row;
    private int column;

    public SelectionWeaponToCatch(String message, String nickname){
        super(message,nickname);
        setSelectionWeaponToCatch(true);
    }

    public String getNameOfWeaponCard() {
        return nameOfWeaponCard;
    }

    public void setNameOfWeaponCard(String nameOfWeaponCard) {
        this.nameOfWeaponCard = nameOfWeaponCard;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getCard() {
        return nameOfWeaponCard;
    }
}
