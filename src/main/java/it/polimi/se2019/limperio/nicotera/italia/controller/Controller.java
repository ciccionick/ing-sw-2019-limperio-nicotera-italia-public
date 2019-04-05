package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class Controller implements Observer<ViewEvent> {

    private Game game;

    public void check() {

    }

    private void checkForDrawPowerUpCard(){

    }

    private void checkForDiscardPowerUpCard(){

    }

    private void checkForRunCatchAction(){

    }

    private void checkForReload(){

    }

    private void checkForEndTurn(){

    }

    private void checkForShoot(){

    }

    private void checkForUsePowerUpCard(){

    }

    private int distanceOfManhattan(int[] startCoordinates, int[] targetCoordinates){
        return startCoordinates[0]-targetCoordinates[0];
    }

    private int[] findSquareOnTheMap(Square targetSquare){
        return new int[]{0, 0};
    }

    @Override
    public void update(ViewEvent message) {

    }
}
