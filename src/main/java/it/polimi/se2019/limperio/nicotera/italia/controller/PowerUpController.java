package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;

import java.util.ArrayList;

/**
 * This class handles the draught, the discard and the use of a power up card by a player
 * @author Pietro L'Imperio
 */

class PowerUpController {
     private  Game game = null;
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
         PowerUpCard powerUpCardToDraw;
         for (int i = 0; i < 2; i++) {
             powerUpCardToDraw = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0); //add to local array the first powerUp card of the deck
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0)); //add to used deck the first of normal deck and remove from this one
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().get(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size() - 1).setInTheDeckOfSomePlayer(true); //set boolean attribute to the card
             powerUpCardToDraw.setOwnerOfCard(controller.findPlayerWithThisNickname(nickname));
             controller.findPlayerWithThisNickname(nickname).drawPowerUpCard(powerUpCardToDraw);
         }
         PlayerBoardEvent requestDiscardPowerUpCardEvent = new PlayerBoardEvent();
         requestDiscardPowerUpCardEvent.setMessageForInvolved("Choose which powerUp card you want to discard. \nYou will be generated in the square of that color. \n(You can tap and hold on the card too see more info)");
         requestDiscardPowerUpCardEvent.setRequestToDiscardPowerUpCardToSpawnEvent(true);
         requestDiscardPowerUpCardEvent.setNicknameInvolved(nickname);
         requestDiscardPowerUpCardEvent.setHasToDiscardCard(true);
         requestDiscardPowerUpCardEvent.setPlayerBoard(controller.findPlayerWithThisNickname(nickname).getPlayerBoard());
         game.notify(requestDiscardPowerUpCardEvent);
     }


    /**
     * This method handles the draught of a power up by a player in order to be spawned in the square with the same color of the discarded card
     * @param event It is sent by the view and contains the references to the player that want to discard.
     */
    void handleDiscardOfCardToSpawn(DiscardPowerUpCardToSpawnEvent event){
        removePowerCardFromPlayerDeck(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard());
        spawnPlayer(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard().getColor());
     }

    /**
     * This method finds the spawn square with the color that is passed.
      * @param color the color of the square that is found.
     * @return the spawn square that has the same color of the parameter.
     */
     Square findSpawnSquareWithThisColor(ColorOfCard_Ammo color){
        for(int i=0 ; i< game.getBoard().getMap().getMatrixOfSquares().length; i++){
            for(int j = 0 ; j< game.getBoard().getMap().getMatrixOfSquares()[i].length; j++){
                if(game.getBoard().getMap().getMatrixOfSquares()[i][j]!=null && game.getBoard().getMap().getMatrixOfSquares()[i][j].isSpawn() && game.getBoard().getMap().getMatrixOfSquares()[i][j].getColor().toString().equals(color.toString()))
                    return game.getBoard().getMap().getMatrixOfSquares()[i][j];
            }

        }
        throw new IllegalArgumentException();
    }

    /**
     * This method remove the cards from player's deck because they are used or draught
     * @param playerWithThisNickname the player that has the deck that has to be modified
     * @param aliasPowerUpCard the card that has to be removed from player's deck
     */
    private void removePowerCardFromPlayerDeck(Player playerWithThisNickname, ServerEvent.AliasCard aliasPowerUpCard) {
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
    private void spawnPlayer(Player playerWithThisNickname, ColorOfCard_Ammo color) {
        Square square;
        square = findSpawnSquareWithThisColor(color);
        playerWithThisNickname.setPositionOnTheMap(controller.getGame().getBoard().getMap().getMatrixOfSquares()[square.getRow()][square.getColumn()]);
        MapEvent generationEvent;
        generationEvent = new MapEvent();
        generationEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        generationEvent.setMessageForInvolved("You have been generated in the " + square.getColor().toString() + " spawn square.");
        generationEvent.setMessageForOthers(playerWithThisNickname.getNickname() + " has been generated in the " + square.getColor().toString() + " spawn square.\nNow it begins his turn.");
        generationEvent.setGenerationEvent(true);
        generationEvent.setNicknames(game.getListOfNickname());
        generationEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname());
        game.notify(generationEvent);

        PlayerBoardEvent pbEvent = new PlayerBoardEvent();
        pbEvent.setPlayerBoard(playerWithThisNickname.getPlayerBoard());
        pbEvent.setNicknames(game.getListOfNickname());
        pbEvent.setNicknameInvolved(playerWithThisNickname.getNickname());
        game.notify(pbEvent);

        if(game.getRound()==1){
            PlayerBoardEvent firstActionOfTurnEvent = new PlayerBoardEvent();
            firstActionOfTurnEvent.setFirstActionOfTurnEvent(true);
            firstActionOfTurnEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
            firstActionOfTurnEvent.setMessageForInvolved(playerWithThisNickname.getNickname());
            firstActionOfTurnEvent.setMessageForInvolved("Choose one action you want to do");
            pbEvent.setCanUseTeleporter(true);
            if (game.getRound()>1 || game.getPlayerOfTurn() > 1)
                pbEvent.setCanUseNewton(true);
            firstActionOfTurnEvent.setPlayerBoard(game.getPlayers().get(game.getPlayerOfTurn()-1).getPlayerBoard());
            firstActionOfTurnEvent.setCanShoot(controller.hasWeaponLoaded(playerWithThisNickname));
            game.notify(firstActionOfTurnEvent);
        }
    }



 }