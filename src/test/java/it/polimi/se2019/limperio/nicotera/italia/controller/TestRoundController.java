package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class TestRoundController {

    private Game game;
    private Controller controller;



    @Before
    public void setUp(){
        this.game = Game.instanceOfGame();
        this.controller = new Controller(game);
        game.setController(controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(false);
        game.initializeGame(true, 1, true);
    }

    @After
    public void afterClean(){
        game.setInstanceOfGameNullForTesting();
    }


   /*@Test
    public void updateTurnTest()
    {
        //game.getPlayers().get(0).setDead(true);
        System.out.println(controller.getRoundController().getPlayersDeadInThisTurn().get(0).getNickname());
        game.getPlayers().get(0).getPlayerBoard().getDamages().add(ColorOfFigure_Square.YELLOW);
        game.getPlayers().get(0).setDead(true);
        game.setPlayerOfTurn(1);
        game.getPlayers().get(1).setConnected(false);
        game.setNumOfMaxActionForTurn(2);
        controller.getRoundController().updateTurn();
        Assert.assertEquals(3, game.getPlayerOfTurn());
        game.setPlayerOfTurn(1);
        game.getPlayers().get(1).setConnected(true);
        game.getPlayers().get(2).setNumOfKillDoneInTheTurn(2);
        game.setInFrenzy(true);
        game.setFirstInFrenzyMode(3);
        game.getPlayers().get(0).assignDamage(ColorOfFigure_Square.YELLOW, 11);
        game.getPlayers().get(0).setDead(true);
        game.getPlayers().get(1).getPlayerBoard().setFrenzyBoardPlayer(true);
        game.getPlayers().get(1).assignDamage(ColorOfFigure_Square.YELLOW, 4);
        controller.getRoundController().updateTurn();
        Assert.assertEquals(2, game.getPlayerOfTurn());

    }*/
}
