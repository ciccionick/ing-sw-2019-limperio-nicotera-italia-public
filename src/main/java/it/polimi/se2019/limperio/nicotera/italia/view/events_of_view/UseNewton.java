package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class UseNewton extends UsePowerUp {

    private Player playerToMove;
    private Square arrivalSquare;

    public UseNewton(Player player, PowerUpCard powerUpCard, Player playerToMove) {
        super(player, powerUpCard);
        this.playerToMove = playerToMove;
        this.arrivalSquare=arrivalSquare;
    }

    public Player getPlayerToMove() {
        return playerToMove;
    }

    public Square getArrivalSquare() {
        return arrivalSquare;
    }
}
