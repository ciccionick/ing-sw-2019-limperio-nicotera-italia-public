package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToRunByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *Test for RunController
 *
 * @author Giuseppe Italia
 */



public class TestRunController {



    private Game game;

    private Controller controller;


    @Before
    public void setUp(){
        game = Game.instanceOfGame();
        controller = new Controller(game);
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.setGameOver(false);
        game.initializeGame(false, 1, false);
    }

    @After
    public void afterClean(){
        game.setInstanceOfGameNullForTesting();
    }
    @Test
    public void doRunActionTest()
    {
        RequestToRunByPlayer requestToRunByPlayer = new RequestToRunByPlayer("", "player1");
        controller.findPlayerWithThisNickname("player1").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        game.setInFrenzy(true);
        controller.getRunController().handleRunActionRequest(requestToRunByPlayer);
        game.setInFrenzy(false);
        controller.getRunController().handleRunActionRequest(requestToRunByPlayer);
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        SelectionSquare event= new SelectionSquare("", game.getPlayers().get(0).getNickname(), 1,1);
        event.setRunEvent(true);
        controller.getRunController().doRunAction(event, true);
        assertEquals(1, game.getPlayers().get(0).getPositionOnTheMap().getRow());
        assertEquals(1, game.getPlayers().get(0).getPositionOnTheMap().getColumn());
    }




}
