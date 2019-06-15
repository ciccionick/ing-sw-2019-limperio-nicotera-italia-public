package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RunEvent;
import org.junit.Test;


/**
 *Test for RunController
 *
 * @author Giuseppe Italia
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunController extends TestController{

    RunController runController= new RunController(this.game, this.controller);

    @Test
    public void doRunActionTest()
    {
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        RunEvent event= new RunEvent("", game.getPlayers().get(0).getNickname(), 1,1);
        runController.doRunAction(event, false);

        assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[1][1]);

    }




}
