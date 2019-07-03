package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

/**
 * Event generated when the player decides how to pay the effect of targeting.
 * @author Pietro L'Imperio.
 */
public class DiscardAmmoOrPowerUpToPayTargeting extends ClientEvent {
    /**
     * It's true if the player decides to pay the effect with a blue ammo.
     */
    private boolean blueAmmo = false;
    /**
     * It's true if the player decides to pay the effect with a red ammo.
     */
    private boolean redAmmo = false;
    /**
     * It's true if the player decides to pay the effect with a yellow ammo.
     */
    private boolean yellowAmmo = false;

    /**
     * Alias card of the relative power up card that player decides to discard to pay the effect.
     */
    private ServerEvent.AliasCard powerUpCard = null;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public DiscardAmmoOrPowerUpToPayTargeting(String message, String nickname) {
        super(message, nickname);
        setDiscardAmmoOrPowerUpToPayTargeting();
    }

    public boolean isBlueAmmo() {
        return blueAmmo;
    }

    public void setBlueAmmo(boolean blueAmmo) {
        this.blueAmmo = blueAmmo;
    }

    public boolean isRedAmmo() {
        return redAmmo;
    }

    public void setRedAmmo(boolean redAmmo) {
        this.redAmmo = redAmmo;
    }

    public boolean isYellowAmmo() {
        return yellowAmmo;
    }

    public void setYellowAmmo(boolean yellowAmmo) {
        this.yellowAmmo = yellowAmmo;
    }

    public ServerEvent.AliasCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ServerEvent.AliasCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }
}
