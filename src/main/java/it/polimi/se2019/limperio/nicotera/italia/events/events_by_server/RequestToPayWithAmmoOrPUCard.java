package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event to request to player with what he wants to pay the  price for targeting scope.
 * @author Pietro L'Imperio.
 */
public class RequestToPayWithAmmoOrPUCard extends ServerEvent{

    /**
     * It's true if there are blue ammo usable to pay.
     */
    private boolean blueAmmo = false;
    /**
     * It's true if there are red ammo usable to pay.
     */
    private boolean redAmmo = false;
    /**
     * It's true if there are yellow ammo usable to pay.
     */
    private boolean yellowAmmo = false;
    /**
     * The list of power up cards among them the player can choose which discard to pay.
     */
    private ArrayList<AliasCard> powerUpCards = new ArrayList<>();

    /**
     * Constructor of the class that calls the method that make true the boolean field relative of this kind of class.
     */

    public RequestToPayWithAmmoOrPUCard() {
        setRequestToPayWithAmmoOrPUCard();
    }

    public boolean isBlueAmmo() {
        return blueAmmo;
    }

    public void setBlueAmmo() {
        this.blueAmmo = true;
    }

    public boolean isRedAmmo() {
        return redAmmo;
    }

    public void setRedAmmo() {
        this.redAmmo = true;
    }

    public boolean isYellowAmmo() {
        return yellowAmmo;
    }

    public void setYellowAmmo() {
        this.yellowAmmo = true;
    }

    public ArrayList<AliasCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<AliasCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }
}
