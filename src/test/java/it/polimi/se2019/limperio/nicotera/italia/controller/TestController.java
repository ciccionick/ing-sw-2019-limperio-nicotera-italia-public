package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.*;


/**
 * Test for Controller
 *
 * @author  Giuseppe Italia
 */


public class TestController {
    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);
    WeaponController weaponController= new WeaponController(game, controller);



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


      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j <matrix[i].length; j++) {
            if(matrix[i][j]!=null)
            {
               game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();
            }

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
      assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().size()==1);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      //assertFalse(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      card.setLoad(false);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));






      //Test for player with Heatseeker in his WeaponDeck


      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Heatseeker();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));




      //Test for player with Shockwave in his Weapon Deck

      for(int i=0;i<9;i++)
      {
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
      }


      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Shockwave();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][3]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).size()==1);
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      //Inserisco un ammo giallo al player per poter fargli usare anche la modalità extra nella carta
      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(2).setIsUsable(true);
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
//      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      //Test for player with "Furnace" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Furnace();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      /*assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));*/


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));





      //Test for player with Lock rifle in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new LockRifle();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==1));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      //Test for player with "Hellion" in his Weapon Deck

      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(0).setIsUsable(true);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Hellion();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==2));


      //Test for player with Whisper in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Whisper();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));

      // Test for player with Railgun in his Weapon Deck
      /*game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Railgun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));*/



      //Test for player with "Flamethrower" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Flamethrower();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      //assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      //Test for player with  "Cyberblade" in his Weapon Deck

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Cyberblade();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(2).setIsUsable(false);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==2));
      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(2).setIsUsable(true);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(3));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==3));



      //Test for player with  "Sledgehammer" in his Weapon Deck

     /* Da sempre la possobilità di utilizzare entrambi gli effetti anche se non ha i soldi il player per pagarlo
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Sledgehammer();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(4));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(0).setIsUsable(false);
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));*/

      //Test for player with "Shotgun" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Shotgun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(4));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

      //Test for player with "Zx-2" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Zx2();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(4));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));


      /* C'e sempr eil problema che anche se il giocatore non ha munizioni e powerUp card con cui pagaregli effetti essi risultano lo stesso utilizzabili
      //Test for player with "Machine gun" in his Weapon Deck
      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
      game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new MachineGun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));*/




      //Test
      game.setPlayerOfTurn(1);
      game.setRound(1);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

   }

   @Test
   public void isTheTurnOfThisPlayerTest()
   {
      game.setPlayerOfTurn(1);
      assertTrue(controller.isTheTurnOfThisPlayer(game.getPlayers().get(0).getNickname()));
   }

   @Test
   public void substituteWeaponsCardWithTheirAliasTest()
   {
      ArrayList<WeaponCard> weapons= new ArrayList(){{add(new Railgun());add(new Hellion());}};
      assertEquals(controller.substituteWeaponsCardWithTheirAlias(weapons).get(0).getName(), "Railgun");
      assertEquals(controller.substituteWeaponsCardWithTheirAlias(weapons).get(1).getName(), "Hellion");

   }

   /*@Test
   public void handleDisconnectionTest()
   {
      game.setPlayerOfTurn(1);
      String nickname=game.getPlayers().get(0).getNickname();
      controller.handleDisconnection(nickname);
      assertEquals(game.getPlayerOfTurn(), 2);
      assertTrue(!game.getListOfNickname().contains(nickname));


   }*/






}
