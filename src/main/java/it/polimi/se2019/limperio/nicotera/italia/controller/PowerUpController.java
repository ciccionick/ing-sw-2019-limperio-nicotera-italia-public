package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard;
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


     private ArrayList<ModelEvent.AliasPowerUp> subsitutePowerUpCards(ArrayList<PowerUpCard> arrayOfCard) {
         ArrayList<ModelEvent.AliasPowerUp> newArray = new ArrayList<>();
        for(int i = 0 ; i<arrayOfCard.size(); i++){
            newArray.add(i, new ModelEvent.AliasPowerUp(arrayOfCard.get(i).getName(), arrayOfCard.get(i).getDescription(), arrayOfCard.get(i).getColor()));
        }
        return newArray;
     }

 }