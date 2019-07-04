package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionSquareForAction;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

/**
 * This class handles the run action by a player and checks where it can move to.
 * @author Francesco Nicotera.
 */
public class RunController {

    /**
     * The reference of the game.
     */
    private final Game game;
    /**
     * The reference of the controller.
     */
    private Controller controller;

    /**
     * Constructor of the class that initializes the reference of the game and the controller.
     */
    public RunController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

    /**
     * Handles the run request of a player: the method sends an event in which there are the reachable squares from the player that want to run
     * @param event contains the player's nickname
     */
    void handleRunActionRequest(ClientEvent event){
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        Square square = player.getPositionOnTheMap();
        ArrayList<Square> listOfSquaresReachable = new ArrayList<>();
        RequestSelectionSquareForAction newEvent = new RequestSelectionSquareForAction("Press on the square where you want arrive! \nYou can press CANCEL to choose another action");
        newEvent.setSelectionForRun();
        newEvent.setNicknameInvolved(event.getNickname());
        if(!game.isInFrenzy()){
            controller.findSquaresReachableWithThisMovements(square, 3, listOfSquaresReachable);
            newEvent.setSquaresReachable(listOfSquaresReachable);
            game.notify(newEvent);
        }
        else{
            if((game.getNumOfMaxActionForTurn()==3&&game.isTerminatorModeActive())||(game.getNumOfMaxActionForTurn()==2 && !game.isTerminatorModeActive())) {
                controller.findSquaresReachableWithThisMovements(square, 4, listOfSquaresReachable);
                newEvent.setSquaresReachable(listOfSquaresReachable);
                game.notify(newEvent);
            }
        }
    }

    /**
     * Moves the player in the square it has chosen
     * @param event contains the coordinates of the square in which the player want to go and its nickname.
     */
    void doRunAction(SelectionSquare event, boolean isBeforeToShoot){
        if(event.getNickname().equals(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname())) {
            Player player = controller.findPlayerWithThisNickname(event.getNickname());
            player.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()]);
            MapEvent newEvent = new MapEvent();
            newEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
            newEvent.setNotifyAboutActionDone();
            newEvent.setNumOfAction(game.getNumOfActionOfTheTurn());
            newEvent.setNumOfMaxAction(game.getNumOfMaxActionForTurn());
            newEvent.setMessageForInvolved("You have been moved in the square required!");
            newEvent.setMessageForOthers(event.getNickname() + " has ran in another square! \nLook the map to discover where.");
            newEvent.setNicknameInvolved(event.getNickname());
            newEvent.setNicknames(game.getListOfNickname());
            game.notify(newEvent);
            if(!isBeforeToShoot)
                controller.handleTheEndOfAnAction(false);
        }
    }


}
