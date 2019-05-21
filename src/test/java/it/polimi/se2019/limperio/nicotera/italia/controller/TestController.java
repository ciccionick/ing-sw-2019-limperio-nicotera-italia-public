package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Railgun;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test for Controller
 *
 * @author  Giuseppe Italia
 */


public class TestController {
    Game game = Game.instanceOfGame();
    Controller controller = new Controller(game);

   @Before
   public void setUp(){
      game.createPlayer("player1", true, 1, "BLUE");
      game.createPlayer("player2", false, 2, "YELLOW");
      game.createPlayer("player3", false, 3, "GREY");
      game.setGameOver(true);
      game.initializeGame(false, 1, false);
   }

   @Test
    public void distanceOfManhattanTest() {
       int[] start = {0, 0};
       int[] end = {1, 2};
       assertEquals(3, controller.distanceOfManhattan(start, end));
   }
   @Test
   public void findPlayerWithThisNicknameTest(){
      assertEquals(controller.findPlayerWithThisNickname("player1").getNickname(), "player1");
      assertEquals(controller.findPlayerWithThisNickname("player2").getNickname(), "player2");
      assertEquals(controller.findPlayerWithThisNickname("player3").getNickname(), "player3");
   }

   @Test
   public void findSquaresReachableWithThisMovementsTest() {
      ArrayList<Square> listOfSquaresReachable = new ArrayList<>();
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      controller.findSquaresReachableWithThisMovements(game.getPlayers().get(0).getPositionOnTheMap(), 1, listOfSquaresReachable);
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
      listOfSquaresReachable.clear();
      controller.findSquaresReachableWithThisMovements(game.getPlayers().get(0).getPositionOnTheMap(), 2, listOfSquaresReachable);
      assertEquals(5, listOfSquaresReachable.size());
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[0][2]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
      assertTrue(listOfSquaresReachable.contains(game.getBoard().getMap().getMatrixOfSquares()[1][1]));
   }



}
