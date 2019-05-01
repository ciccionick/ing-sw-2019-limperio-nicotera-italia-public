package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.FirstActionOfTurnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.Collections;


class PowerUpController {
     private final Game game;
     private final Controller controller;

     PowerUpController(Game game, Controller controller) {
         this.game = game;
         this.controller = controller;
     }


     void handleDrawOfTwoCards(String nickname) {
         ArrayList<PowerUpCard> powerUpCardsToDraw = new ArrayList<>();
         for (int i = 0; i < 2; i++) {
             powerUpCardsToDraw.add(game.getBoard().getPowerUpDeck().getPowerUpCards().get(0));
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0));
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().get(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size() - 1).setInTheDeckOfSomePlayer(true);
         }
         controller.findPlayerWithThisNickname(nickname).drawPowerUpCard(powerUpCardsToDraw);
         DiscardPowerUpCardToSpawnEvent event = new DiscardPowerUpCardToSpawnEvent("Hai pescato due carte potenziamento, ora scegline una da scartare per decidere dove essere generato");
         event.getNickname().add(nickname);
         event.setPlayerBoard(controller.findPlayerWithThisNickname(nickname).getPlayerBoard());
         event.setPowerUpCards(subsitutePowerUpCards(event.getPlayerBoard().getPowerUpCardsOwned()));
         game.notify(event);
     }


     private ArrayList<ModelEvent.AliasCard> subsitutePowerUpCards(ArrayList<PowerUpCard> arrayOfCard) {
         ArrayList<ModelEvent.AliasCard> newArray = new ArrayList<>();
        for(int i = 0 ; i<arrayOfCard.size(); i++){
            newArray.add(i, new ModelEvent.AliasCard(arrayOfCard.get(i).getName(), arrayOfCard.get(i).getDescription(), arrayOfCard.get(i).getColor()));
        }
        return newArray;
     }

    void handleDiscardOfCardToSpawn(it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DiscardPowerUpCardToSpawnEvent event){
         System.out.println("Il player " + event.getNickname() + " ha deciso di scartare la powerUpCard " + event.getPowerUpCard().getName() + " di colore "+ event.getPowerUpCard().getColor() + " e di conseguenza sarà generato nel quadrato generazione di quel colore");
        removePowerCardFromPlayerDeck(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard());
        spawnPlayer(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard().getColor());
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

    void removePowerCardFromPlayerDeck(Player playerWithThisNickname, ModelEvent.AliasCard aliasPowerUpCard) {
        playerWithThisNickname.getPlayerBoard().getPowerUpCardsOwned().remove(findPowerUpCardFromAliasInPlayerDeck(playerWithThisNickname,aliasPowerUpCard));
    }

    private PowerUpCard findPowerUpCardFromAliasInPlayerDeck(Player playerWithThisNickname, ModelEvent.AliasCard aliasPowerUpCard) {
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
        FirstActionOfTurnEvent newEvent;
        if(game.getRound()==1 || game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname().equals(playerWithThisNickname.getNickname())) {
             newEvent = new FirstActionOfTurnEvent("Sei stato generato e comincia il tuo turno, decidi se vuoi raccogliere, muoverti o sparare");
        }
        else
            newEvent = new FirstActionOfTurnEvent("Sei stato generato nel quadrato generazione del colore che hai scelto ma non è il tuo turno");
        newEvent.getNickname().add(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        newEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        game.notify(newEvent);
    }

 }