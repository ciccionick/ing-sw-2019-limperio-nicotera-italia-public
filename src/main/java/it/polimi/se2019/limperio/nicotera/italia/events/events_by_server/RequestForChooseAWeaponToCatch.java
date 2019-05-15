package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestForChooseAWeaponToCatch extends ServerEvent{
    private ArrayList<AliasCard> weaponsAvailableToCatch = new ArrayList<>();
    private int row;
    private int column;

    public RequestForChooseAWeaponToCatch(String message) {
        super(message);
        setRequestForChooseAWeaponToCatch(true);
    }

    public ArrayList<AliasCard> getWeaponsAvailableToCatch() {
        return weaponsAvailableToCatch;
    }

    public void setWeaponsAvailableToCatch(ArrayList<AliasCard> weaponsAvailableToCatch) {
        this.weaponsAvailableToCatch = weaponsAvailableToCatch;
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
}
