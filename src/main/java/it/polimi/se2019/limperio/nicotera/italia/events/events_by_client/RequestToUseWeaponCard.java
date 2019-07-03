package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

/**
 * Event generated when the player decides to use a weapon card.
 * @author Pietro L'Imperio.
 */
public class RequestToUseWeaponCard extends ClientEvent {
    /**
     * Alias card that represent the weapon card that player wants to use.
     */
    private ServerEvent.AliasCard weaponWantUse;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public RequestToUseWeaponCard(String message, String nickname) {
        super(message, nickname);
        setRequestToUseWeaponCard();
    }

    public ServerEvent.AliasCard getWeaponWantUse() {
        return weaponWantUse;
    }

    public void setWeaponWantUse(ServerEvent.AliasCard weaponWantUse) {
        this.weaponWantUse = weaponWantUse;
    }
}
