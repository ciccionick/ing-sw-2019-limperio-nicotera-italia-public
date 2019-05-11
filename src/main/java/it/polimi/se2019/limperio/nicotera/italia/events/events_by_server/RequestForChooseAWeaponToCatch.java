package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestForChooseAWeaponToCatch extends ServerEvent{
    private ArrayList<AliasCard> weaponsAvailableToCatch = new ArrayList<>();

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
}
