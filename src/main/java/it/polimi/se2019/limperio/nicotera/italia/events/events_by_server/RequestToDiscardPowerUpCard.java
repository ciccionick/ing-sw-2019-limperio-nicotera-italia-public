package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event to request to player to discard a power up card.
 * @author Pietro L'Imperio.
 */
public class RequestToDiscardPowerUpCard extends ServerEvent {

    /**
     * List of power up cards among them the player can choose which discard.
     */
    private ArrayList<AliasCard> powerUpCards = new ArrayList<>();
    /**
     * It's true if the discard is to pay the price of a weapon that player is catching, false otherwise.
     */
    private boolean isToCatch = false;
    /**
     * It's true if the discard is to pay an effect, false otherwise, false otherwise.
     */
    private boolean isToPayAnEffect = false;
    /**
     * It's true if the discard is to pay the price to reload a weapon, false otherwise.
     */
    private boolean isToReload = false;
    /**
     * It's true if the discard is to use the effect of targeting scope, false otherwise.
     */
    private boolean isToTargeting = false;
    /**
     * It's true if the discard is to use the effect of Tagback granade, false otherwise.
     */
    private boolean isToTagback = false;

    /**
     * Constructor of the class that calls the method that make true the boolean field relative of this kind of class.
     */

    public RequestToDiscardPowerUpCard() {
        setRequestToDiscardPowerUpCard();
    }

    public ArrayList<AliasCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<AliasCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public boolean isToCatch() {
        return isToCatch;
    }

    public void setToCatch() {
        isToCatch = true;
    }

    public boolean isToTargeting() {
        return isToTargeting;
    }

    public void setToTargeting() {
        isToTargeting = true;
    }

    public boolean isToPayAnEffect() {
        return isToPayAnEffect;
    }

    public void setToPayAnEffect() {
        isToPayAnEffect = true;
    }

    public boolean isToReload() {
        return isToReload;
    }

    public void setToReload() {
        isToReload = true;
    }

    public boolean isToTagback() {
        return isToTagback;
    }

    public void setToTagback() {
        isToTagback = true;
    }
}
