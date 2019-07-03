package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTerminatorController{
    private Game game;
    private Controller controller;
    private TerminatorController terminatorController;
    @Before
    public void setUp(){
        game = Game.instanceOfGame();
        controller = new Controller(game);
        terminatorController = new TerminatorController(controller, game);
        game.setController(controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "GREY");
        game.createPlayer("player3", false, 3, "GREEN");
        game.createPlayer("terminator", false, 4, "YELLOW");
        game.initializeGame(false, 1, false);
    }
    @After
    public void cleanUp(){
        game.setInstanceOfGameNullForTesting();
    }

    @Test
    public void handleSpawnOfTerminatorTest(){
        SelectionSquare selectionSquare = new SelectionSquare("", "player1", 1, 1);
        terminatorController.handleSpawnOfTerminator(selectionSquare);
        Assert.assertEquals(1, controller.findPlayerWithThisNickname("terminator").getPositionOnTheMap().getRow());
        Assert.assertEquals(1, controller.findPlayerWithThisNickname("terminator").getPositionOnTheMap().getColumn());
    }
    @Test
    public void handleTerminatorShootAction(){
        TerminatorShootEvent terminatorShootEvent = new TerminatorShootEvent("", "player1");
        terminatorShootEvent.setNicknamePlayerToAttack("player2");
        controller.findPlayerWithThisNickname("player1").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("player2").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("player3").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("terminator").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        terminatorController.handleTerminatorShootAction(terminatorShootEvent);
        Assert.assertEquals(1, controller.findPlayerWithThisNickname("player2").getPlayerBoard().getDamages().size());
        Assert.assertFalse(game.isHasToDoTerminatorAction());
    }
    @Test
    public void handleMoveTest(){
        ClientEvent message = new ClientEvent("", "player1");
        controller.findPlayerWithThisNickname("terminator").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
        terminatorController.handleRequestMove(message);
        SelectionSquare selectionSquare = new SelectionSquare("", "player1", 2, 3);
        terminatorController.handleMove(selectionSquare);
        Assert.assertEquals(2, controller.findPlayerWithThisNickname("terminator").getPositionOnTheMap().getRow());
        Assert.assertEquals(3, controller.findPlayerWithThisNickname("terminator").getPositionOnTheMap().getColumn());

    }

}
