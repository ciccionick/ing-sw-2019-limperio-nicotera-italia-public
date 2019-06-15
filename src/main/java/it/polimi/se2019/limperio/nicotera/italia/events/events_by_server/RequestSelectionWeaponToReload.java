package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

/**
 * Event to ask to player the weaponCard the player wants to use to attack
 *
 * @author Giuseppe Italia
 */
public class RequestSelectionWeaponToReload extends ServerEvent{

    private ArrayList<AliasCard> weaponCardsAffordable;

    public RequestSelectionWeaponToReload(String message, ArrayList<AliasCard> weaponCardsAffordable)
    {
        super(message);
        this.weaponCardsAffordable= weaponCardsAffordable;
        setRequestSelectionWeaponToReload(true);
    }

    public ArrayList<AliasCard> getWeaponCardsAffordable() {
        return weaponCardsAffordable;
    }

    public void setWeaponCardsAffordable(ArrayList<AliasCard> weaponCardsAffordable) {
        this.weaponCardsAffordable = weaponCardsAffordable;
    }
}
