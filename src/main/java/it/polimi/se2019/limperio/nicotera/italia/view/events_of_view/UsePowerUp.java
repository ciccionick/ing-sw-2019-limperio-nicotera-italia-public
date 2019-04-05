package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class UsePowerUp extends ViewEvent {



    private PowerUpCard powerUpCard;

    public UsePowerUp(Player player, PowerUpCard powerUpCard) {
        super(player);
        this.powerUpCard = powerUpCard;
    }

    public PowerUpCard getPowerUpCard() {
        return powerUpCard;
    }
}
