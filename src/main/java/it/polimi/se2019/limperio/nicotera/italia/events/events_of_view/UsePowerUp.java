package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard;

public class UsePowerUp extends ViewEvent {


    public UsePowerUp(String message, String nickname) {
        super(message, nickname);
    }
}
