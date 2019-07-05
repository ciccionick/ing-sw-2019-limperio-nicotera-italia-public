package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class TestRoundController {

    private Game game;
    private Controller controller;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;



    @Before
    public void setUp(){
        this.game = Game.instanceOfGame();
        this.controller = new Controller(game);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(false);
        game.initializeGame(true, 1, true);
         player1 = controller.findPlayerWithThisNickname("player1");
         player2 = controller.findPlayerWithThisNickname("player2");
         player3 = controller.findPlayerWithThisNickname("player3");
         player4 = controller.findPlayerWithThisNickname("player4");
    }

    @After
    public void afterClean(){
        game.setInstanceOfGameNullForTesting();
    }


   @Test
    public void updateTurnTest()
    {
        game.getPlayers().get(0).assignDamage(ColorOfFigure_Square.BLUE, 11);
        game.setPlayerOfTurn(1);
        game.getPlayers().get(1).setConnected(false);
        game.setNumOfMaxActionForTurn(2);
        controller.getRoundController().updateTurn();
        Assert.assertEquals(3, game.getPlayerOfTurn());
        Assert.assertTrue(game.isHasToDoTerminatorAction());
        Assert.assertNull(game.getPlayerHasToRespawn());

    }

    @Test
    public void randomSpawnTest(){
        game.setRound(1);
        player1.getPlayerBoard().getPowerUpCardsOwned().clear();
        controller.getRoundController().randomSpawn(player1);
        Assert.assertEquals(1, player1.getPlayerBoard().getPowerUpCardsOwned().size());
        player1.getPlayerBoard().getPowerUpCardsOwned().clear();
        game.getBoard().getPowerUpDeck().getPowerUpCards().get(0).setInTheDeckOfSomePlayer(true);
        player1.drawPowerUpCard(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0));
        game.getBoard().getPowerUpDeck().getPowerUpCards().get(0).setInTheDeckOfSomePlayer(true);
        player1.drawPowerUpCard(game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0));
        controller.getRoundController().randomSpawn(player1);
        Assert.assertEquals(1, player1.getPlayerBoard().getPowerUpCardsOwned().size());
        game.setRound(2);
        controller.getRoundController().randomSpawn(player1);
        Assert.assertEquals(1, player1.getPositionOnTheMap().getRow());
        Assert.assertEquals(0, player1.getPositionOnTheMap().getColumn());
    }

    @Test
    public void countScoreForDeathsTest(){
        ArrayList<Player> deadPlayer = new ArrayList<>();
        player1.assignDamage(player2.getColorOfFigure(), 6);
        player1.assignDamage(player3.getColorOfFigure(), 6);
        deadPlayer.add(player1);
        controller.getRoundController().countScoreForDeaths(deadPlayer);
        Assert.assertEquals(9, player2.getScore());
        Assert.assertEquals(6, player3.getScore());
        player1.getPlayerBoard().getDamages().clear();
        player1.setDead(false);
        player2.updateScore(0);
        player3.updateScore(0);
    }
    @Test
    public void handleEndGameTest(){
        game.getBoard().getKillShotTrack().getTokensOfDeath().clear();
        game.getBoard().getKillShotTrack().getTokensOfDeath().add(new ArrayList<>());
        game.getBoard().getKillShotTrack().getTokensOfDeath().add(new ArrayList<>());
        game.getBoard().getKillShotTrack().getTokensOfDeath().add(new ArrayList<>());
        game.getBoard().getKillShotTrack().getTokensOfDeath().add(new ArrayList<>());
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(0).add(ColorOfDeathToken.YELLOW);
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(0).add(ColorOfDeathToken.YELLOW);
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(1).add(ColorOfDeathToken.GREY);
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(2).add(ColorOfDeathToken.BLUE);
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(3).add(ColorOfDeathToken.PURPLE);
        game.getBoard().getKillShotTrack().getTokensOfDeath().get(3).add(ColorOfDeathToken.PURPLE);
        controller.getRoundController().handleEndOfGame(false);
        Assert.assertEquals(8, player2.getScore());
        Assert.assertEquals(6, player4.getScore());
        Assert.assertEquals(4, player3.getScore());
        Assert.assertEquals(2, player1.getScore());




    }

}
