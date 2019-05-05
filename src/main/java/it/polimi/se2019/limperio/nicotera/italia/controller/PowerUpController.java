package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForDiscardPowerUpCardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.FirstActionOfTurnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

/**
 * This class handles the draught, the discard and the use of a power up card by a player
 * @author Pietro L'Imperio
 */

class PowerUpController {
     private final Game game;
     private final Controller controller;

     PowerUpController(Game game, Controller controller) {
         this.game = game;
         this.controller = controller;
     }

    /**
     * This method moves two power up cards from the deck to the player's deck and then it notifies to view part
     * @param nickname the nickname of the player that want to draw the cards
     */
     void handleDrawOfTwoCards(String nickname) {
         ArrayList<PowerUpCard> powerUpCardsToDraw = new ArrayList<>();
         for (int i = 0; i < 2; i++) {
             powerUpCardsToDraw.add(game.getBoard().getPowerUpDeck().getPowerUpCards().get(0));
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0));
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().get(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size() - 1).setInTheDeckOfSomePlayer(true);
         }
         controller.findPlayerWithThisNickname(nickname).drawPowerUpCard(powerUpCardsToDraw);
         RequestForDiscardPowerUpCardEvent event = new RequestForDiscardPowerUpCardEvent("Hai pescato due carte potenziamento, ora scegline una da scartare per decidere dove essere generato");
         event.getNicknames().add(nickname);
         event.setPlayerBoard(controller.findPlayerWithThisNickname(nickname).getPlayerBoard());
         event.setPowerUpCards(subsitutePowerUpCards(event.getPlayerBoard().getPowerUpCardsOwned()));
         game.notify(event);
     }

    /**
     * This method substitutes the cards of model (power up and weapon card) with alias cards in order to send them through the socket to remote view.
     * @param arrayOfCard Contains the cards that have to be substituted
     * @return A list of alias card
     */
     private ArrayList<ServerEvent.AliasCard> subsitutePowerUpCards(ArrayList<PowerUpCard> arrayOfCard) {
         ArrayList<ServerEvent.AliasCard> newArray = new ArrayList<>();
        for(int i = 0 ; i<arrayOfCard.size(); i++){
            newArray.add(i, new ServerEvent.AliasCard(arrayOfCard.get(i).getName(), arrayOfCard.get(i).getDescription(), arrayOfCard.get(i).getColor()));
        }
        return newArray;
     }

    /**
     * This method handles the draught of a power up by a player in order to be spawned in the square with the same color of the discarded card
     * @param event It is sent by the view and contains the references to the player that want to discard.
     */
    void handleDiscardOfCardToSpawn(it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent event){
         System.out.println("Il player " + event.getNickname() + " ha deciso di scartare la powerUpCard " + event.getPowerUpCard().getName() + " di colore "+ event.getPowerUpCard().getColor() + " e di conseguenza sarà generato nel quadrato generazione di quel colore");
        removePowerCardFromPlayerDeck(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard());
        spawnPlayer(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard().getColor());
     }

    /**
     * This method finds the spawn square with the color that is passed.
      * @param color the color of the square that is found.
     * @return the spawn square that has the same color of the parameter.
     */
     SpawnSquare findSpawnSquareWithThisColor(ColorOfCard_Ammo color){
        for(int i=0 ; i< game.getBoard().getMap().getMatrixOfSquares().length; i++){
            for(int j = 0 ; j< game.getBoard().getMap().getMatrixOfSquares()[i].length; j++){
                if(game.getBoard().getMap().getMatrixOfSquares()[i][j]!=null && game.getBoard().getMap().getMatrixOfSquares()[i][j].isSpawn() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().toString().equals(color.toString()))
                    return (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[i][j];
            }

        }
        throw new IllegalArgumentException();
    }

    /**
     * This method remove the cards from player's deck because they are used or draught
     * @param playerWithThisNickname the player that has the deck that has to be modified
     * @param aliasPowerUpCard the card that has to be removed from player's deck
     */
    void removePowerCardFromPlayerDeck(Player playerWithThisNickname, ServerEvent.AliasCard aliasPowerUpCard) {
        playerWithThisNickname.getPlayerBoard().getPowerUpCardsOwned().remove(findPowerUpCardFromAliasInPlayerDeck(playerWithThisNickname,aliasPowerUpCard));
    }

    /**
     * This method finds the card of the model that matches with the alias card that the view has sent
     * @param playerWithThisNickname the player that is the owner of the card
     * @param aliasPowerUpCard the alias card that has to be matched with the card of the model
     * @return the card of the model that matches with the alias card
     * @throws IllegalArgumentException if the alias card isn't owned by the player, or if the player that is passed isn't in the game
     */
    private PowerUpCard findPowerUpCardFromAliasInPlayerDeck(Player playerWithThisNickname, ServerEvent.AliasCard aliasPowerUpCard) {
        for (PowerUpCard card : playerWithThisNickname.getPlayerBoard().getPowerUpCardsOwned()){
            if(card.getColor().equals(aliasPowerUpCard.getColor())&&card.getName().equals(aliasPowerUpCard.getName()))
            {
                return card;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * This method places the player in the right spawn square and then generates the event in order to start the turn of the player
     * @param playerWithThisNickname the player that has to be spawned
     * @param color the color of the square in which the player has to be spawned
     */
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
        newEvent.getNicknames().add(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        newEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        game.notify(newEvent);
    }



 }