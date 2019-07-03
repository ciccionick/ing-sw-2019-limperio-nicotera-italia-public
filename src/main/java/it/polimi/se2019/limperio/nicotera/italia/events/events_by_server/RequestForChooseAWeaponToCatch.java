package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event to request to choose a weapon to catch
 * @author Pietro L'Imperio.
 */
public class RequestForChooseAWeaponToCatch extends ServerEvent{
    /**
     * List of alias cards containing all of the weapon that player can catch.
     */
    private ArrayList<AliasCard> weaponsAvailableToCatch = new ArrayList<>();

    /**
     * Constructor that initialize message and call the method to make true the boolean field relative to this event.
     * @param message Message for the involved player.
     */
    public RequestForChooseAWeaponToCatch(String message) {
        super(message);
        setRequestForChooseAWeaponToCatch();
    }

    public ArrayList<AliasCard> getWeaponsAvailableToCatch() {
        return weaponsAvailableToCatch;
    }

    public void setWeaponsAvailableToCatch(ArrayList<AliasCard> weaponsAvailableToCatch) {
        this.weaponsAvailableToCatch = weaponsAvailableToCatch;
    }


}
