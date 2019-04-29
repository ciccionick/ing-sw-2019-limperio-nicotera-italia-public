package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.FirstActionOfTurnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
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
            if(message.isDiscardPowerUpCardToSpawn()){
                powerUpController.handleDiscardOfCardToSpawn((DiscardPowerUpCardToSpawnEvent) message);
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

    private SpawnSquare findSpawnSquareWithThisColor(ColorOfCard_Ammo color){
        for(int i=0 ; i< game.getBoard().getMap().getMatrixOfSquares().length; i++){
            for(int j = 0 ; j< game.getBoard().getMap().getMatrixOfSquares()[i].length; j++){
               if(game.getBoard().getMap().getMatrixOfSquares()[i][j]!=null && game.getBoard().getMap().getMatrixOfSquares()[i][j].isSpawn() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().toString().equals(color.toString()))
                    return (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[i][j];
            }

        }
        throw new IllegalArgumentException();
    }

     void removePowerCardFromPlayerDeck(Player playerWithThisNickname, ModelEvent.AliasPowerUp aliasPowerUpCard) {
        playerWithThisNickname.getPlayerBoard().getPowerUpCardsOwned().remove(findPowerUpCardFromAliasInPlayerDeck(playerWithThisNickname,aliasPowerUpCard));
    }

    private PowerUpCard findPowerUpCardFromAliasInPlayerDeck(Player playerWithThisNickname, ModelEvent.AliasPowerUp aliasPowerUpCard) {
        for (PowerUpCard card : playerWithThisNickname.getPlayerBoard().getPowerUpCardsOwned()){
            if(card.getColor().equals(aliasPowerUpCard.getColor())&&card.getName().equals(aliasPowerUpCard.getName()))
            {
                return card;
            }
        }
        throw new IllegalArgumentException();
    }

    void spawnPlayer(Player playerWithThisNickname, ColorOfCard_Ammo color) {
        SpawnSquare square;
        square=findSpawnSquareWithThisColor(color);
        playerWithThisNickname.setPositionOnTheMap(square);
        FirstActionOfTurnEvent newEvent = new FirstActionOfTurnEvent("Comincia il tuo turno, decidi se vuoi raccogliere, muoverti o sparare");
        newEvent.getNickname().add(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        newEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        game.notify(newEvent);
    }
}



