package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.view.View;

public class UseTeleporter extends UsePowerUp {

    private Square arrivalSquare;


    public UseTeleporter(Player player, View view, PowerUpCard powerUpToUse, Square arrivalSquare) {
        super(player,  powerUpToUse);
        this.arrivalSquare = arrivalSquare;
    }

    public Square getArrivalSquare() {
        return arrivalSquare;
    }
}


