package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

/**
 * Event to ask to player the weaponCard the player wants to use to attack
 *
 * @author Giuseppe Italia
 */
public class RequestSelectionOfWeapon extends ServerEvent{

    ArrayList<ServerEvent.AliasCard> weaponCardsAffordable;

    public RequestSelectionOfWeapon(String message, ArrayList<ServerEvent.AliasCard> weaponCardsAffordable)
    {
        super(message);
        this.weaponCardsAffordable= weaponCardsAffordable;
    }
}
