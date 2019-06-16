package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles the draught, the discard and the use of a power up card by a player
 * @author Pietro L'Imperio
 */

class PowerUpController {
     private  Game game;
     private final Controller controller;
     private int numOfCardToUse;
     private String nicknameOfPlayerHasToBeMovedByNewton;
     PowerUpController(Game game, Controller controller) {
         this.game = game;
         this.controller = controller;
     }

    /**
     * This method moves two power up cards from the deck to the player's deck and then it notifies to view part
     */
     void handleDrawOfPowerUpCards(DrawPowerUpCards event) {
         String nickname = event.getNickname();
         PowerUpCard powerUpCardToDraw;
         for (int i = 0; i < event.getNumOfCards(); i++) {
             powerUpCardToDraw = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0)); //add to used deck the first of normal deck and remove from this one
             game.getBoard().getPowerUpDeck().getUsedPowerUpCards().get(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().size() - 1).setInTheDeckOfSomePlayer(true); //set boolean attribute to the card
             powerUpCardToDraw.setOwnerOfCard(controller.findPlayerWithThisNickname(nickname));
             controller.findPlayerWithThisNickname(nickname).drawPowerUpCard(powerUpCardToDraw);

         }
         if(game.getPlayerOfTurn()==2 && game.getRound()==1 && game.isTerminatorModeActive() && controller.findPlayerWithThisNickname("terminator").getPositionOnTheMap()==null) {
             PlayerBoardEvent pBEvent = new PlayerBoardEvent();
             pBEvent.setPlayerBoard(game.getPlayers().get(game.getPlayerOfTurn() - 1).getPlayerBoard());
             pBEvent.setNicknames(game.getListOfNickname());
             pBEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname());
             game.notify(pBEvent);
             sendRequestToChooseSquareForSpawnOfTerminator();
         }
         else
             sendRequestToDiscardPowerUpCardToBeGenerate(nickname);
     }

     void sendRequestToChooseSquareForSpawnOfTerminator() {
         String message;
         if(game.getRound()==1)
             message = "Look up the cards you have drawn and choose a spawn square where generate the terminator!";
         else
             message = "Choose a square where respawn the terminator";

         RequestSelectionSquareForAction requestSelectionSquareForTerminator = new RequestSelectionSquareForAction(message);
         requestSelectionSquareForTerminator.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname());
         requestSelectionSquareForTerminator.setSelectionForSpawnTerminator(true);
         ArrayList<Square> squareReachable = new ArrayList<>();
         Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
         for (Square[] matrix1 : matrix) {
             for (Square square : matrix1) {
                 if (square != null && square.isSpawn())
                     squareReachable.add(square);
             }
         }
         requestSelectionSquareForTerminator.setSquaresReachable(squareReachable);
         game.notify(requestSelectionSquareForTerminator);
    }

    void sendRequestToDiscardPowerUpCardToBeGenerate(String nickname){
         PlayerBoardEvent requestDiscardPowerUpCardEvent = new PlayerBoardEvent();
         requestDiscardPowerUpCardEvent.setMessageForInvolved("Choose which powerUp card you want to discard. \nYou will be generated in the square of that color. \n(You can tap and hold on the card too see more info)");
         requestDiscardPowerUpCardEvent.setRequestToDiscardPowerUpCardToSpawnEvent(true);
         requestDiscardPowerUpCardEvent.setNicknameInvolved(nickname);
         requestDiscardPowerUpCardEvent.setPlayerBoard(controller.findPlayerWithThisNickname(nickname).getPlayerBoard());
         game.notify(requestDiscardPowerUpCardEvent);
     }


    /**
     * This method handles the draught of a power up by a player in order to be spawned in the square with the same color of the discarded card
     * @param event It is sent by the view and contains the references to the player that want to discard.
     */
    void handleDiscardOfCardToSpawn(DiscardPowerUpCardToSpawnEvent event) {
            removePowerCardFromPlayerDeck(controller.findPlayerWithThisNickname(event.getNickname()), event.getPowerUpCard());
            Square square;
            square = findSpawnSquareWithThisColor(event.getPowerUpCard().getColor());
            spawnPlayer(controller.findPlayerWithThisNickname(event.getNickname()), square);

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
     *
     */
     void spawnPlayer(Player playerWithThisNickname, Square square) {
        playerWithThisNickname.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[square.getRow()][square.getColumn()]);
        playerWithThisNickname.setDead(false);
        playerWithThisNickname.setHasToBeGenerated(false);
        MapEvent generationEvent;
        generationEvent = new MapEvent();
        generationEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        generationEvent.setMessageForInvolved("You have been generated in the " + square.getColor().toString() + " spawn square.");
        generationEvent.setMessageForOthers(playerWithThisNickname.getNickname() + " has been generated in the " + square.getColor().toString() + " spawn square.\nNow it begins his turn.");
        generationEvent.setGenerationEvent(true);
        generationEvent.setNicknames(game.getListOfNickname());
        generationEvent.setNicknameInvolved(playerWithThisNickname.getNickname());
        game.notify(generationEvent);
         PlayerBoardEvent pbEvent = new PlayerBoardEvent();
         pbEvent.setPlayerBoard(playerWithThisNickname.getPlayerBoard());
         pbEvent.setNicknames(game.getListOfNickname());
         pbEvent.setNicknameInvolved(playerWithThisNickname.getNickname());
         game.notify(pbEvent);

         ArrayList<Player> playerStillDead = new ArrayList<>();
         for(Player player : game.getPlayers()){
             if(player.isDead())
                 playerStillDead.add(player);
         }
         if(playerStillDead.isEmpty()) {
             controller.sendRequestForAction();
         }
    }


     void handleRequestToUseTeleporter(ClientEvent message) {
        RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Select the square in which you want to teleport");
        requestSelectionSquareForAction.setSelectionForTeleporter(true);
        requestSelectionSquareForAction.setSquaresReachable(getAllSquaresInTheMap());
        requestSelectionSquareForAction.setNicknameInvolved(message.getNickname());
        numOfCardToUse = ((RequestToUseTeleporter)message).getNumOfCard();
        game.notify(requestSelectionSquareForAction);

    }

    ArrayList<Square> getAllSquaresInTheMap(){
        ArrayList<Square> squaresReachable = new ArrayList<>();
        for(int i=0; i<game.getBoard().getMap().getMatrixOfSquares().length; i++){
            for(int j=0; j<game.getBoard().getMap().getMatrixOfSquares()[i].length; j++){
                if(game.getBoard().getMap().getMatrixOfSquares()[i][j]!=null){
                    squaresReachable.add(game.getBoard().getMap().getMatrixOfSquares()[i][j]);
                }
            }
        }
        return squaresReachable;
    }


     void useTeleporter(SelectionSquareToUseTeleporter message) {
         Player player = controller.findPlayerWithThisNickname(message.getNickname());
         PowerUpCard card = player.getPlayerBoard().getPowerUpCardsOwned().get(numOfCardToUse-1);
         card.useAsPowerUp(player, game.getBoard().getMap().getMatrixOfSquares()[message.getRow()][message.getColumn()]);
         card.setInTheDeckOfSomePlayer(false);
         card.setOwnerOfCard(null);
         player.getPlayerBoard().getPowerUpCardsOwned().remove(card);
         MapEvent mapEvent = new MapEvent();
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setNicknameInvolved(message.getNickname());
         mapEvent.setMessageForOthers(message.getNickname() + "has used teleporter and moves himself");
         mapEvent.setMessageForInvolved("You have been moved on the selected square");
         mapEvent.setTypeOfMap(game.getBoard().getMap().getTypeOfMap());
         game.notify(mapEvent);
         PlayerBoardEvent playerBoardEvent = new PlayerBoardEvent();
         playerBoardEvent.setPlayerBoard(player.getPlayerBoard());
         playerBoardEvent.setNicknameInvolved(message.getNickname());
         playerBoardEvent.setNicknames(game.getListOfNickname());
         game.notify(playerBoardEvent);
         controller.handleTheEndOfAnAction(true);
     }

     void handleRequestToUseNewton(ClientEvent message) {
        RequestToChooseAPlayer requestToChooseAPlayer = new RequestToChooseAPlayer();
        requestToChooseAPlayer.setChoosePlayerForNewton(true);
        requestToChooseAPlayer.setMessageForInvolved("Select the player you want to move");
        ArrayList<String> playerCanBeChosen = new ArrayList<>();
        for(Player player : game.getPlayers()){
            if(!player.isHasToBeGenerated()) {
                String nickname = player.getNickname();
                playerCanBeChosen.add(nickname);
            }
        }
        requestToChooseAPlayer.setNameOfPlayers(playerCanBeChosen);
        requestToChooseAPlayer.setNicknameInvolved(message.getNickname());
        numOfCardToUse = ((RequestToUseNewton) message).getNumOfCard();
        game.notify(requestToChooseAPlayer);
    }


     void handleChoosePlayerForNewton(ChoosePlayer choosePlayer) {
         nicknameOfPlayerHasToBeMovedByNewton = choosePlayer.getNameOfPlayer();
         RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Select the square in which you want to move the player you selected before");
         requestSelectionSquareForAction.setSelectionForNewton(true);
         ArrayList<Square> squaresReachable = new ArrayList<>();
         Square positionOfTheTarget = controller.findPlayerWithThisNickname(choosePlayer.getNameOfPlayer()).getPositionOnTheMap();
         if(positionOfTheTarget.getNorth() != null) {
             squaresReachable.add(positionOfTheTarget.getNorth());
             if(positionOfTheTarget.getNorth().getNorth()!=null)
                 squaresReachable.add(positionOfTheTarget.getNorth().getNorth());
         }
         if(positionOfTheTarget.getSouth() != null) {
             squaresReachable.add(positionOfTheTarget.getSouth());
             if(positionOfTheTarget.getSouth().getSouth()!=null)
                 squaresReachable.add(positionOfTheTarget.getSouth().getSouth());
         }
         if(positionOfTheTarget.getWest() != null) {
             squaresReachable.add(positionOfTheTarget.getWest());
             if(positionOfTheTarget.getWest().getWest()!=null)
                 squaresReachable.add(positionOfTheTarget.getWest().getWest());
         }
         if(positionOfTheTarget.getEast() != null) {
             squaresReachable.add(positionOfTheTarget.getEast());
             if(positionOfTheTarget.getEast().getEast()!=null)
                 squaresReachable.add(positionOfTheTarget.getEast().getEast());
         }
         requestSelectionSquareForAction.setSquaresReachable(squaresReachable);
         requestSelectionSquareForAction.setNicknameInvolved(choosePlayer.getNickname());
         game.notify(requestSelectionSquareForAction);
    }

    void useNewton(SelectionSquareToUseNewton event){
         Square newPositionOfThePlayer = game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()];
         Player player = controller.findPlayerWithThisNickname(event.getNickname());
         PowerUpCard cardToUse = player.getPlayerBoard().getPowerUpCardsOwned().get(numOfCardToUse-1);
         cardToUse.useAsPowerUp(controller.findPlayerWithThisNickname(nicknameOfPlayerHasToBeMovedByNewton), newPositionOfThePlayer);
         cardToUse.setOwnerOfCard(null);
         cardToUse.setInTheDeckOfSomePlayer(false);
         player.getPlayerBoard().getPowerUpCardsOwned().remove(cardToUse);
         MapEvent mapEvent = new MapEvent();
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setNicknameInvolved(event.getNickname());
         mapEvent.setTypeOfMap(game.getBoard().getMap().getTypeOfMap());
         mapEvent.setMessageForOthers(event.getNickname() + "has used newton and moves"+ nicknameOfPlayerHasToBeMovedByNewton);
         mapEvent.setMessageForInvolved("You have been moved on the selected square");
         game.notify(mapEvent);
         PlayerBoardEvent playerBoardEvent = new PlayerBoardEvent();
         playerBoardEvent.setPlayerBoard(player.getPlayerBoard());
         playerBoardEvent.setNicknameInvolved(event.getNickname());
         playerBoardEvent.setNicknames(game.getListOfNickname());
         game.notify(playerBoardEvent);
         controller.handleTheEndOfAnAction(true);
    }
}