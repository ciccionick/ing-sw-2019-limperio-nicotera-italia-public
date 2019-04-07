package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard;

public class UsePowerUp extends ViewEvent {
    private PowerUpCard powerUpToUse;

    public UsePowerUp(Player player , PowerUpCard powerUpToUse) {
        super(player);
        this.powerUpToUse = powerUpToUse;
    }
}
