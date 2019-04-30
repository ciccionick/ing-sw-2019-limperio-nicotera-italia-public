package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.FirstActionOfTurnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;

import java.util.ArrayList;

public class Controller implements Observer<ViewEvent> {

    private final Game game;
    private ActionController actionController;
    private CatchController catchController;
    private PowerUpController powerUpController;
    private RoundController roundController;
    private RunController runController;
    private ShootController shootController;
    private TurnController turnController;
    private WeaponController weaponController;

    public Controller(Game game) {
        this.game = game;
        actionController = new ActionController(game);
        catchController = new CatchController(game, this);
        powerUpController = new PowerUpController(game, this);
        roundController = new RoundController(game);
        runController = new RunController(game);
        shootController = new ShootController(game);
        turnController = new TurnController(game);
        weaponController = new WeaponController(game);
    }

    public void update(ViewEvent message) {
        System.out.println(message.getMessage() + " " + message.getNickname());
        if (isTheTurnOfThisPlayer(message.getNickname())) {
            if (message.isDrawTwoPowerUpCards()) {
                powerUpController.handleDrawOfTwoCards(message.getNickname());
            }
            if (message.isDiscardPowerUpCardToSpawn()) {
                powerUpController.handleDiscardOfCardToSpawn((DiscardPowerUpCardToSpawnEvent) message);
            }
            if (message.isRequestToCatchByPlayer()) {
                catchController.replyToRequestToCatch((RequestToCatchByPlayer) message);
            }
            if (message.isRequestToRunByPlayer()) {
                //runController.replyToReqeustToCatch(message);
            }
            if (message.isRequestToShootByPlayer()) {
                //shootController.replyToRequestToShoot(message);
            }
        }
    }

    Player findPlayerWithThisNickname (String nickname){
        for(Player player : game.getPlayers()){
            if(player.getNickname().equals(nickname)){
                return player;
            }
        }
        throw new IllegalArgumentException();
    }

    int distanceOfManhattan(int[] startCoordinates, int[] targetCoordinates) {
        return Math.abs(startCoordinates[0] - targetCoordinates[0]) + Math.abs(startCoordinates[1]- targetCoordinates[1]);
    }

     boolean isTheTurnOfThisPlayer(String nickname){
        return nickname.equals(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
    }




}



