package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

public class DiscardAmmoOrPowerUpToPayTargeting extends ClientEvent {
    private boolean blueAmmo = false;
    private boolean redAmmo = false;
    private boolean yellowAmmo = false;

    private ServerEvent.AliasCard powerUpCard = null;
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
