package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;

/**
 * Event generated when the player decide to discard a power up card to pay an effect, a weapon, the reload of a weapon or when he wants to use targeting or tagback.
 * @author Pietro L'Imperio.
 */
public class DiscardPowerUpCard extends ClientEvent {
    /**
     * Name of the power up card that player wants to discard.
     */
    private String nameOfPowerUpCard;
    /**
     * Color of the power up card that player wants to discard.
     */
    private ColorOfCard_Ammo colorOfCard;
    /**
     * It's true if the power up card to discard is used to pay the price of a weapon to catch, false otherwise.
     */
    private boolean isToCatch = false;
    /**
     * It's true if the power up card to discard is used to pay an effect, false otherwise.
     */
    private boolean isToPayAnEffect = false;
    /**
     * It's true if the power up card to discard is used to pay the price of reload of a weapon, false otherwise.
     */
    private boolean isToReload = false;
    /**
     * It's true if the power up card to discard is used for the effect of targeting.
     */
    private boolean isToTargeting = false;
    /**
     * It's true if the power up card to discard is used for the effect of tagback.
     */
    private boolean isToTagback = false;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public DiscardPowerUpCard(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCard();
    }

    public String getNameOfPowerUpCard() {
        return nameOfPowerUpCard;
    }

    public void setNameOfPowerUpCard(String nameOfPowerUpCard) {
        this.nameOfPowerUpCard = nameOfPowerUpCard;
    }

    public ColorOfCard_Ammo getColorOfCard() {
        return colorOfCard;
    }

    public void setColorOfCard(ColorOfCard_Ammo colorOfCard) {
        this.colorOfCard = colorOfCard;
    }

    public boolean isToCatch() {
        return isToCatch;
    }

    public boolean isToTargeting() {
        return isToTargeting;
    }



    public boolean isToTagback() {
        return isToTagback;
    }

    public void setToTagback(boolean toTagback) {
        isToTagback = toTagback;
    }

    public void setToTargeting(boolean toTargeting) {
        isToTargeting = toTargeting;
    }

    public void setToCatch(boolean toCatch) {
        isToCatch = toCatch;
    }

    public boolean isToPayAnEffect() {
        return isToPayAnEffect;
    }

    public void setToPayAnEffect(boolean toPayAnEffect) {
        isToPayAnEffect = toPayAnEffect;
    }

    public boolean isToReload() {
        return isToReload;
    }

    public void setToReload(boolean toReload) {
        isToReload = toReload;
    }
}
