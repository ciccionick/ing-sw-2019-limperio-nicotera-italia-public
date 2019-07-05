package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

/**
 * Controller that is dedicated to the actions of the terminator and is correctness.
 * @author Pietro L'Imperio.
 */
class TerminatorController {
    /**
     * Reference of the controller.
     */
    private Controller controller;
    /**
     * Reference of the game.
     */
    private Game game;

    /**
     * Constructor where controller and game references are initialized.
     */
     TerminatorController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }


    /**
     * Handles the spawn of the terminator putting it on the square chosen by the player of the turn.
     * @param message Event received by the player of the turn with row and column of the square where he decided to generate the terminator.
     */
     void handleSpawnOfTerminator(SelectionSquare message) {
         int row = message.getRow();
         int column = message.getColumn();
         Square square = game.getBoard().getMap().getMatrixOfSquares()[row][column];
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         terminator.setPositionOnTheMap(square);
         MapEvent mapEvent = new MapEvent();
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setNicknameInvolved("terminator");
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setMessageForOthers("Terminator has been generated in the spawn square of the " + square.getColor().toString() + " color!");
         game.notify(mapEvent);

         if(game.getRound()==1){
             controller.getPowerUpController().sendRequestToDiscardPowerUpCardToBeGenerate(message.getNickname());
         }
    }


    /**
     * Handles the request to do terminator action by the player.
     * @param message Event received by the player that decided to do terminator action.
     */
     void handleFirstRequestAction(ClientEvent message) {
        boolean terminatorCanShoot=false;
        boolean terminatorCanMove = true;

        Player terminator = controller.findPlayerWithThisNickname("terminator");
        if(!controller.getWeaponController().getVisiblePlayers(0,terminator, 0).isEmpty())
            terminatorCanShoot = true;

        RequestChooseActionForTerminator requestChooseActionForTerminator = new RequestChooseActionForTerminator();
        requestChooseActionForTerminator.setTerminatorCanShoot(terminatorCanShoot);
        requestChooseActionForTerminator.setTerminatorCanMove(terminatorCanMove);
        requestChooseActionForTerminator.setNicknameInvolved(message.getNickname());
        requestChooseActionForTerminator.setMessageForInvolved("Choose what action do you want the terminator does");
        message.getMyVirtualView().update(requestChooseActionForTerminator);



    }

    /**
     * Handles the request by the player to move terminator sending an event to choose a square.
     * @param message Event received by player with the choice to move terminator.
     */
     void handleRequestMove(ClientEvent message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Choose a square where you want to move the terminator!");
         requestSelectionSquareForAction.setNicknameInvolved(message.getNickname());
         requestSelectionSquareForAction.setSelectionForMoveTerminator();
         controller.findSquaresReachableWithThisMovements(terminator.getPositionOnTheMap(), 1, requestSelectionSquareForAction.getSquaresReachable());
         game.notify(requestSelectionSquareForAction);

    }


    /**
     * Handles the movement of the terminator in the square indicated by the row and column contained in the event received.
     * @param message Event received by client with the row and column of the square where the player wants to move the terminator.
     */
     void handleMove(SelectionSquare message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         int row = message.getRow();
         int column = message.getColumn();
         Square squareWhereMove = game.getBoard().getMap().getMatrixOfSquares()[row][column];
         terminator.setPositionOnTheMap(squareWhereMove);

         MapEvent mapEvent = new MapEvent();
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setNotifyAboutActionDone();
         mapEvent.setNumOfAction(game.getNumOfActionOfTheTurn());
         mapEvent.setNumOfMaxAction(game.getNumOfMaxActionForTurn());
         mapEvent.setMessageForOthers("terminator" + " has been moved in another square! \nLook the map to discover where.");
         mapEvent.setNicknames(game.getListOfNickname());
         mapEvent.setMessageForInvolved(mapEvent.getMessageForOthers());
         mapEvent.setNicknameInvolved(message.getNickname());
         game.notify(mapEvent);

         ArrayList<Player> attackedPlayers = controller.getWeaponController().getVisiblePlayers(0,terminator,0);
         if (attackedPlayers.isEmpty()) {
             game.setHasToDoTerminatorAction(false);
            controller.handleTheEndOfAnAction(false);
         }
         else{
             sendRequestToChoosePlayerToAttack(message);
         }
    }

    /**
     * Sends the request to the player to choose who the terminator has to attack.
     * @param message Event received by client.
     */
    void sendRequestToChoosePlayerToAttack(ClientEvent message){
        ArrayList<Player> attackedPlayers = controller.getWeaponController().getVisiblePlayers(0,controller.findPlayerWithThisNickname("terminator"),0);
        RequestToSelectionPlayerToAttackWithTerminator requestToSelectionPlayerToAttackWithTerminator = new RequestToSelectionPlayerToAttackWithTerminator();
        requestToSelectionPlayerToAttackWithTerminator.setNicknamesOfPlayersAttachable(attackedPlayers);
        requestToSelectionPlayerToAttackWithTerminator.setNicknameInvolved(message.getNickname());
        requestToSelectionPlayerToAttackWithTerminator.setMessageForInvolved("Choose who the terminator has to shoot: ");
        game.notify(requestToSelectionPlayerToAttackWithTerminator);
    }


    /**
     * Handles the terminator shoot to the player chosen by the player of the turn.
     * @param message Event received by client with the nickname of the player to choose to be attacked.
     */
     void handleTerminatorShootAction(ClientEvent message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         Player playerToAttack = controller.findPlayerWithThisNickname(((TerminatorShootEvent)message).getNicknamePlayerToAttack());
         playerToAttack.assignDamage(terminator.getColorOfFigure(), 1);
         if(terminator.isOverSixDamage()){
             playerToAttack.assignMarks(terminator.getColorOfFigure(), 1);
         }
             PlayerBoardEvent pbEvent = new PlayerBoardEvent();
             pbEvent.setNicknameInvolved(playerToAttack.getNickname());
             pbEvent.setNicknames(game.getListOfNickname());
             pbEvent.setMessageForInvolved("You have been attacked by the terminator!");
             pbEvent.setMessageForOthers(playerToAttack.getNickname() + " has been attacked by terminator");
             pbEvent.setPlayerBoard(playerToAttack.getPlayerBoard());
             pbEvent.setNotifyAboutActionDone();
             game.notify(pbEvent);
             game.setHasToDoTerminatorAction(false);
         ArrayList<Player> players = new ArrayList<>();
         players.add(playerToAttack);
         game.setHasToDoTerminatorAction(false);
         controller.getShootController().handleSendRequestAfterShoot(terminator, players, true);
     }


}
