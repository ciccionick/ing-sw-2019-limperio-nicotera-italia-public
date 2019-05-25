package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
      game.setController(this.controller);
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

   @Test
   public void checkIfPlayerCanShootTest()
   {


      WeaponCard card;
      Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();
      for(int i=0;i<matrix.length;i++)
      {
         for(int j=0;j<matrix[i].length;j++)
         {
            if(matrix[i][j]!=null)
            game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();

         }
      }



      //Test for player with ElectroScythe in his WeaponDeck
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      card= new ElectroScythe();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.setPlayerOfTurn(1);
      game.setRound(2);
      card.setLoad(true);
      assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().size()==1);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[0][0].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[0][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      //Test for player with Heatseeker in his WeaponDeck


      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Heatseeker();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[2][3].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      /*game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[1][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));*/



      //Test for player with Shockwave in his Weapon Deck

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Shockwave();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][3]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[1][3].getPlayerOnThisSquare().remove( game.getPlayers().get(1));


      //Test for player with "Furnace" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Furnace();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[1][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[2][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));



      //Test for player with Lock rifle in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new LockRifle();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[1][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      game.getBoard().getMap().getMatrixOfSquares()[0][1].getPlayerOnThisSquare().remove( game.getPlayers().get(1));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));




   }




}
