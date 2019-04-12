package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;

public abstract class Controller implements Observer<ViewEvent> {

    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void check(ViewEvent message) /*throws IllegalChooseByUser */ {
    }

    private int distanceOfManhattan(int[] startCoordinates, int[] targetCoordinates) {
        return startCoordinates[0] - targetCoordinates[0];
    }

    private int[] findSquareOnTheMap(Square targetSquare) {
        return new int[]{0, 0};
    }
}



