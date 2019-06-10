package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

public class RequestToDiscardPowerUpCardToPay extends ServerEvent {

    private ArrayList<AliasCard> powerUpCards = new ArrayList<>();
    public RequestToDiscardPowerUpCardToPay() {
        setRequestToDiscardPowerUpCardToPay(true);
    }

    public ArrayList<AliasCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<AliasCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }
}
