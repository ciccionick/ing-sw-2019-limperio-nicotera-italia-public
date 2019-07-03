package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

/**
 * Event to request to player the weapon card he wants to use to reload.
 *
 * @author Giuseppe Italia
 */
public class RequestSelectionWeaponToReload extends ServerEvent{

    /**
     * List of weapon cards that player can reload.
     */
    private ArrayList<AliasCard> weaponCardsAffordable;

    /**
     * Constructor of the class that initialize the message for the involved player, the boolean field relative of this kind of event and the list of weapons reloadable.
     */
    public RequestSelectionWeaponToReload(String message, ArrayList<AliasCard> weaponCardsAffordable)
    {
        super(message);
        this.weaponCardsAffordable= weaponCardsAffordable;
        setRequestSelectionWeaponToReload();
    }

    public ArrayList<AliasCard> getWeaponCardsAffordable() {
        return weaponCardsAffordable;
    }

}
