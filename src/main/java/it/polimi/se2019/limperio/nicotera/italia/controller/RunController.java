package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToRunByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquareForRun;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionSquareForRun;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

/**
 * This class handles the run action by a player and checks where it can move to.
 */
public class RunController {

    private final Game game;
    private Controller controller;

    public RunController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

    /**
     * Handles the run request of a player: the method sends an event in which there are the reachable squares from the player that want to run
     * @param event contains the player's nickname
     */
    void handleRunAction(RequestToRunByPlayer event){
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        Square square = player.getPositionOnTheMap();
        ArrayList<Square> listOfSquaresReachable = new ArrayList<>();
        RequestSelectionSquareForRun newEvent = new RequestSelectionSquareForRun("Scegli un quadrato tra quelli disponibili: ");
        newEvent.setNicknameInvolved(event.getNickname());
        if(!game.isInFrenzy()){
            controller.findSquaresReachableWithThisMovements(square, 3, listOfSquaresReachable);
            newEvent.setSquaresReachableWithRunAction(listOfSquaresReachable);
            game.notify(newEvent);
        }
        else{
            //l'azione la possono fare solo i players che hanno a disposizione due azioni in frenzy mode
            controller.findSquaresReachableWithThisMovements(square, 4, listOfSquaresReachable);
            newEvent.setSquaresReachableWithRunAction(listOfSquaresReachable);
            game.notify(newEvent);
        }
    }

    /**
     * Moves the player in the square it has chosen
     * @param event contains the coordinates of the square in which the player want to go and its nickname.
     */
    void doRunAction(SelectionSquareForRun event){
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        player.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()]);
    }


}
