package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

public class RequestToDiscardPowerUpCardToPay extends ServerEvent {

    private ArrayList<AliasCard> powerUpCards = new ArrayList<>();
    private boolean isToCatch = false;
    private boolean isToPayAnEffect = false;
    private boolean isToReload = false;
    private boolean isToTargeting = false;
    private boolean isToTagback = false;

    public RequestToDiscardPowerUpCardToPay() {
        setRequestToDiscardPowerUpCardToPay(true);
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

    public void setToCatch(boolean toCatch) {
        isToCatch = toCatch;
    }

    public boolean isToTargeting() {
        return isToTargeting;
    }

    public void setToTargeting(boolean toTargeting) {
        isToTargeting = toTargeting;
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

    public boolean isToTagback() {
        return isToTagback;
    }

    public void setToTagback(boolean toTagback) {
        isToTagback = toTagback;
    }
}
