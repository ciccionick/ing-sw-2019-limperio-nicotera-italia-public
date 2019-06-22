package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestRoundController {

    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);
    RoundController roundController= new RoundController(controller, game);



    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(false);
        game.initializeGame(false, 1, false);
    }


   /* @Test
    public void updateTurnTest()
    {
        game.getPlayers().get(0).setDead(true);
        game.getPlayers().get(0).getPlayerBoard().getDamages().add(ColorOfFigure_Square.BLUE);
        game.setInFrenzy(true);
        game.setFirstInFrenzyMode(1);
        game.setPlayerOfTurn(4);
        roundController.updateTurn();
        assertTrue(game.isGameOver());


    }*/
}
