package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class DiscardPowerUpCard extends ViewEvent {

    private PowerUpCard powerUpCard;

    public DiscardPowerUpCard(Player player, PowerUpCard powerUpCard) {
        super(player);
        this.powerUpCard = powerUpCard;
    }

    public PowerUpCard getPowerUpCard() {
        return powerUpCard;
    }
}