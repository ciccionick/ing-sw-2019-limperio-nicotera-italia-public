package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;

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
        catchController = new CatchController(game);
        powerUpController = new PowerUpController(game, this);
        roundController = new RoundController(game);
        runController = new RunController(game);
        shootController = new ShootController(game);
        turnController = new TurnController(game);
        weaponController = new WeaponController(game);
    }

    public void update(ViewEvent message){
        System.out.println(message.getMessage() + " " + message.getNickname());
        if(message.getNickname().equals(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname())) {
            if (message.isDrawTwoPowerUpCards()) {
                powerUpController.handleDrawOfTwoCards(message.getNickname());
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
        return startCoordinates[0] - targetCoordinates[0];
    }

    private int[] findSquareOnTheMap(Square targetSquare) {
        return new int[]{0, 0};
    }
}



