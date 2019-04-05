package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class UseTeleporter extends UsePowerUp {

    private Square arrivalSquare;


    public UseTeleporter(Player player, PowerUpCard powerUpCard, Square arrivalSquare) {
        super(player, powerUpCard);
        this.arrivalSquare = arrivalSquare;
    }

    public Square getArrivalSquare() {
        return arrivalSquare;
    }
}


