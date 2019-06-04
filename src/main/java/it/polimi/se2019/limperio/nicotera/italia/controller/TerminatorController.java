package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.GenerationTerminatorEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.MoveTerminatorEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

class TerminatorController {
    private Controller controller;
    private Game game;

     TerminatorController(Controller controller, Game game) {
        this.controller = controller;
        this.game = game;
    }


     void handleSpawnOfTerminator(ClientEvent message) {
         int row = ((GenerationTerminatorEvent)message).getRow();
         int column = ((GenerationTerminatorEvent)message).getColumn();
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


     void handleRequestMove(ClientEvent message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Choose a square where you want to move the terminator!");
         requestSelectionSquareForAction.setNicknameInvolved(message.getNickname());
         requestSelectionSquareForAction.setSelectionForMoveTerminator(true);
         controller.findSquaresReachableWithThisMovements(terminator.getPositionOnTheMap(), 1, requestSelectionSquareForAction.getSquaresReachable());
         game.notify(requestSelectionSquareForAction);

    }


     void handleMove(ClientEvent message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         int row = ((MoveTerminatorEvent) message).getRow();
         int column = ((MoveTerminatorEvent) message).getColumn();
         Square squareWhereMove = game.getBoard().getMap().getMatrixOfSquares()[row][column];
         terminator.setPositionOnTheMap(squareWhereMove);

         MapEvent mapEvent = new MapEvent();
         mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
         mapEvent.setNotifyAboutActionDone(true);
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
            controller.handleTheEndOfAnAction();
         }
         else{
             sendRequestToChoosePlayerToAttack(message);
         }
    }

    void sendRequestToChoosePlayerToAttack(ClientEvent message){
        ArrayList<Player> attackedPlayers = controller.getWeaponController().getVisiblePlayers(0,controller.findPlayerWithThisNickname("terminator"),0);
        RequestToSelectionPlayerToAttackWithTerminator requestToSelectionPlayerToAttackWithTerminator = new RequestToSelectionPlayerToAttackWithTerminator();
        requestToSelectionPlayerToAttackWithTerminator.setNicknamesOfPlayersAttachable(attackedPlayers);
        requestToSelectionPlayerToAttackWithTerminator.setNicknameInvolved(message.getNickname());
        requestToSelectionPlayerToAttackWithTerminator.setMessageForInvolved("Choose who the terminator has to shoot: ");
        message.getMyVirtualView().update(requestToSelectionPlayerToAttackWithTerminator);

    }


     void handleTerminatorShootAction(ClientEvent message) {
         Player terminator = controller.findPlayerWithThisNickname("terminator");
         Player playerToAttack = controller.findPlayerWithThisNickname(((TerminatorShootEvent)message).getNicknamePlayerToAttack());
         playerToAttack.assignDamage(terminator.getColorOfFigure(), 1);
         if(terminator.isOverSixDamage()){
             playerToAttack.assignMarks(terminator.getColorOfFigure(), 1);
         }

         if(playerToAttack.getPlayerBoard().getDamages().size()>=11){
             controller.getDeathController().handleDeath(terminator, playerToAttack);
         }
         else {

             PlayerBoardEvent pbEvent = new PlayerBoardEvent();
             pbEvent.setNicknameInvolved(playerToAttack.getNickname());
             pbEvent.setNicknames(game.getListOfNickname());
             pbEvent.setMessageForInvolved("You have been attacked by the terminator!");
             pbEvent.setMessageForOthers(playerToAttack.getNickname() + " has been attacked by terminator");
             pbEvent.setPlayerBoard(playerToAttack.getPlayerBoard());
             pbEvent.setNotifyAboutActionDone(true);
             game.notify(pbEvent);
             game.setHasToDoTerminatorAction(false);
             controller.handleTheEndOfAnAction();
         }
    }


}
