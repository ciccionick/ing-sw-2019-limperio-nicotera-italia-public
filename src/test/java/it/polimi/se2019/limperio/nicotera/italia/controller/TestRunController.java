package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.Before;
import org.junit.Test;


/**
 *Test for RunController
 *
 * @author Giuseppe Italia
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunController {



    private Game game = Game.instanceOfGame();

    private Controller controller = new Controller(game);
    private RunController runController= new RunController(game, controller);



    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.setGameOver(false);
        game.initializeGame(false, 1, false);
    }

    @Test
    public void doRunActionTest()
    {
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        SelectionSquare event= new SelectionSquare("", game.getPlayers().get(0).getNickname(), 1,1);
        event.setRunEvent(true);
        //runController.doRunAction(event, false);
        //assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[1][1]);

    }




}
