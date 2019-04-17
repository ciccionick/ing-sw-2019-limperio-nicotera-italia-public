package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

public class UseNewton extends UsePowerUp {

    private Player playerToMove;
    private Square arrivalSquare;

    public UseNewton(Player player, RemoteView view, PowerUpCard powerUpToUse, Player playerToMove, Square arrivalSquare) {
        super(player, powerUpToUse);
        this.playerToMove = playerToMove;
        this.arrivalSquare = arrivalSquare;
    }

    public Player getPlayerToMove() {
        return playerToMove;
    }

    public Square getArrivalSquare() {
        return arrivalSquare;
    }
}
