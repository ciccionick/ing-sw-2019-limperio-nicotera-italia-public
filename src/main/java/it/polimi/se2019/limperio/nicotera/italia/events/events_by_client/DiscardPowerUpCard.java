package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;

public class DiscardPowerUpCard extends ClientEvent {
    private String nameOfPowerUpCard;
    private ColorOfCard_Ammo colorOfCard;
    private boolean isToCatch = false;
    private boolean isToPayAnEffect = false;
    private boolean isToReload = false;
    private boolean isToTargeting = false;
    private boolean isToTagback = false;

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
