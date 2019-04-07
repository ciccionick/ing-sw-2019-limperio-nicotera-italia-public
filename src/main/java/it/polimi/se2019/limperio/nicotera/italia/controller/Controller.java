package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.ViewEvent;

public class Controller implements Observer<ViewEvent> {

    private final Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public boolean check(ViewEvent message) throws  IllegalArgumentException {
        switch(message.getClass().getName()){
            case "DiscardPowerUpCard":
                return checkForDiscardPowerUpCard(message);
            case "DrawPowerUpCard":
                return checkForDrawPowerUpCard(message);
            case "EndTurn":
                return checkForEndTurn(message);
            case "ReloadWeapons":
                return checkForReload(message);
            case "RunCatchAction":
                return checkForRunCatchAction(message);
            case "ShootAction":
                return checkForShoot(message);
            case "UseNewton":
                return checkForUseNewton(message);
            case "UseTagBackGranade":
                return checkForUseTagBackGranade(message);
            case "UseTeleporter":
                return checkForUseTeleporter(message);
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean checkForDrawPowerUpCard(ViewEvent message){
    return true;
    }

    private boolean checkForDiscardPowerUpCard(ViewEvent message){
        return true;
    }

    private boolean checkForRunCatchAction(ViewEvent message){
        return true;
    }

    private boolean checkForReload(ViewEvent message){
        return true;
    }

    private boolean checkForEndTurn(ViewEvent message){
        return true;
    }

    private boolean checkForUseTeleporter(ViewEvent message){
        return true;
    }

    private boolean checkForUseTagBackGranade(ViewEvent message){
        return true;
    }

    private boolean checkForUseNewton(ViewEvent message){
        return true;
    }

    private boolean checkForShoot(ViewEvent message){
        return true;
    }

    private int distanceOfManhattan(int[] startCoordinates, int[] targetCoordinates){
        return startCoordinates[0]-targetCoordinates[0];
    }

    private int[] findSquareOnTheMap(Square targetSquare){
        return new int[]{0, 0};
    }

    @Override
    public void update(ViewEvent message) /*throws IllegalChooseByUser */{
        if(check(message)){
            switch (message.getClass().getName()){
                case "DiscardPowerUpCard":
                case "DrawPowerUpCard":
                case "EndTurn":
                case "ReloadWeapons":
                case "RunCatchAction":
                case "ShootAction":
                case "UseNewton":
                case "UseTagBackGranade":
                case "UseTeleporter":

            }

        }
        /*else
            throw new IllegaleChooseByUser();*/


    }
}
