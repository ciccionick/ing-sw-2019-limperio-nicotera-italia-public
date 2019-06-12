package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestToPayWithAmmoOrPUCard extends ServerEvent{

    private boolean blueAmmo = false;
    private boolean redAmmo = false;
    private boolean yellowAmmo = false;
    private ArrayList<AliasCard> powerUpCards = new ArrayList<>();

    public RequestToPayWithAmmoOrPUCard() {
        setRequestToPayWithAmmoOrPUCard(true);
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

    public ArrayList<AliasCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<AliasCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }
}
