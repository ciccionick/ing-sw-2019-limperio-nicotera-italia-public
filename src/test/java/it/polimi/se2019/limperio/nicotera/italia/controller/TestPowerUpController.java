package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test for PowerUpCardController
 *
 * @author Giuseppe Italia
 */

public class TestPowerUpController {


    Game game = Game.instanceOfGame();
    Controller controller = new Controller(game);
    PowerUpController powerUpController = new PowerUpController(this.game, this.controller);


    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.setGameOver(true);
        game.initializeGame(false, 2, false);
    }


    @Test
    public void findSpawnSquareWithThisColorTest(){
       assertEquals("player1", game.getPlayers().get(0).getNickname());
       Square square1 = powerUpController.findSpawnSquareWithThisColor(ColorOfCard_Ammo.BLUE);
       assertEquals(square1.getColor(), ColorOfFigure_Square.BLUE);
       assertEquals(0, square1.getRow());
       assertEquals(2, square1.getColumn());
       assertTrue(square1.isSpawn());
       assertTrue(square1.isHasDoor());
       Square square2 = powerUpController.findSpawnSquareWithThisColor(ColorOfCard_Ammo.RED);
       assertEquals(ColorOfFigure_Square.RED, square2.getColor());
       assertEquals(1, square2.getRow());
       assertEquals(0, square2.getColumn());
       assertTrue(square2.isSpawn());
       assertTrue(square2.isHasDoor());
    }



    @Test
    public void handleDiscardOfCardToSpawnTest()
    {
        DiscardPowerUpCardToSpawnEvent event= new DiscardPowerUpCardToSpawnEvent("", game.getPlayers().get(0).getNickname());
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().clear();
        PowerUpCard powerUpCard= PowerUpCard.createPowerUpCard(1);
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard);

        ServerEvent.AliasCard card= new ServerEvent.AliasCard(powerUpCard.getName(), "", powerUpCard.getColor());
        event.setPowerUpCard(card);
        powerUpController.handleDiscardOfCardToSpawn(event);
        assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[2][3]);
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().isEmpty());

    }
 /*   @Test
    public void handleDrawOfTwoCardsANDHandleDiscardOfCardToSpawnANDSpawnPlayerTest()
    {


        game.setPlayerOfTurn(1);
        powerUpController.handleDrawOfPowerUpCards(game.getPlayers().get(0).getNickname());
        assertEquals(game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().size(), 2);
        //powerUpCardChoice is the card that game.getPlayers().get(0) wants to discard
        PowerUpCard powerUpCardChoice= game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().get(0);
        DiscardPowerUpCardToSpawnEvent event = new DiscardPowerUpCardToSpawnEvent("", game.getPlayers().get(0).getNickname());
        event.setPowerUpCard(new ServerEvent.AliasCard(powerUpCardChoice.getName(),  powerUpCardChoice.getDescription(), powerUpCardChoice.getColor() ));
        powerUpController.handleDiscardOfCardToSpawn(event);
        assertTrue(!game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().contains(powerUpCardChoice));
    }*/




}