package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
    TerminatorController terminatorController= new TerminatorController(controller, game);
    ShootController shootController= new ShootController(game, controller);



   @Before
   public void setUp(){
      game.createPlayer("player1", true, 1, "BLUE");
      game.createPlayer("player2", false, 2, "YELLOW");
      game.createPlayer("player3", false, 3, "GREY");
      game.createPlayer("player4", false, 4, "PURPLE");
      game.setGameOver(false);
      game.initializeGame(false, 1, false);
   }
   @After
   public void cleanUp(){
      game.setInstanceOfGameNullForTesting();

   }

   @Test
   public void updateTest()
   {
      //Test for DrawPowerUpCard
      game.setRound(1);
      game.setPlayerOfTurn(1);
      PowerUpCard powerUpCard= game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
      DrawPowerUpCards event= new DrawPowerUpCards("",game.getPlayers().get(0).getNickname(), 1);
      controller.update(event);
      assertTrue(game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().contains(powerUpCard));

      //Test for DiscardPowerUpCardToSpawn
      DiscardPowerUpCardToSpawnEvent event1= new DiscardPowerUpCardToSpawnEvent("",game.getPlayers().get(0).getNickname());
      PowerUpCard card= PowerUpCard.createPowerUpCard(3);
      game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(card);
      event1.setPowerUpCard(new ServerEvent.AliasCard("Targeting scope","",card.getColor()));
      controller.update(event1);
      assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[0][2]);

      //Test for CatchEvent
      SelectionSquare event2= new SelectionSquare("",game.getPlayers().get(0).getNickname(), 0,1);
      event2.setCatchEvent(true);
      controller.update(event2);
      assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[0][1]);

      //Test for GenerationTerminatorEvent

      /*game.getPlayers().add(new Player("terminator", false, 5, ColorOfFigure_Square.GREEN));
      game.setPlayerOfTurn(5);
      GenerationTerminatorEvent event3= new GenerationTerminatorEvent("terminator",1,0);
      System.out.println(game.getPlayerOfTurn());
      System.out.println(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
      assertTrue(controller.isTheTurnOfThisPlayer("terminator"));
      controller.update(event3);*/

      //assertEquals(game.getPlayers().get(4).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[1][0]);

      //Test for SelectionWeaponToCatch event
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      SelectionWeaponToCatch event4= new SelectionWeaponToCatch("", game.getPlayers().get(0).getNickname());
      WeaponCard weaponCard= new Railgun();
      ((SpawnSquare)game.getBoard().getMap().getMatrixOfSquares()[1][0]).getWeaponCards().add(weaponCard);
      event4.setNameOfWeaponCard(weaponCard.getName());
      controller.update(event4);
//      assertTrue(!game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().isEmpty());


      /*//Test for MoveTerminatorEvent
      game.getPlayers().add(new Player("terminator", false, 5, ColorOfFigure_Square.GREEN));
      game.setPlayerOfTurn(5);
      MoveTerminatorEvent event5= new MoveTerminatorEvent("terminator", 1,1);
      controller.update(event5);
      assertEquals(game.getPlayers().get(0).getPositionOnTheMap(), game.getBoard().getMap().getMatrixOfSquares()[1][1]);*/

      //Test for SelectionMultiplePlayers
      SelectionMultiplePlayers event6= new SelectionMultiplePlayers("", game.getPlayers().get(0).getNickname());
      ArrayList<String> names= new ArrayList(){{add("player1");add("player2");}};
      event6.setNamesOfPlayers(names);
      controller.update(event6);
      for(int i=0;i<shootController.getInvolvedPlayers().size(); i++)
      {
         assertTrue(names.contains(shootController.getInvolvedPlayers().get(i).getPlayer().getNickname()));
      }

      //Test for SelectionSquareForShootAction
      SelectionSquare event7= new SelectionSquare("", game.getPlayers().get(0).getNickname(), 1,1);
      event7.setSelectionSquareForShootAction(true);
      controller.update(event7);
      assertTrue(shootController.getInvolvedPlayers().isEmpty());


      //L'ho bloccato perche andava con il terminatore nel inizialize game true
      /*//TEst for ChosePlayer
      game.setPlayerOfTurn(1);
      ChoosePlayer event8= new ChoosePlayer("", game.getPlayers().get(0).getNickname());
      event8.setForAttack(true);
      event8.setNameOfPlayer("player2");
      controller.getShootController().getTypeOfAttack().add(1);
      WeaponCard card1= new Heatseeker();
      controller.getShootController().setWeaponToUse(card1);
      card1.setOwnerOfCard(game.getPlayers().get(0));
      controller.update(event8);
      assertEquals(controller.getShootController().getInvolvedPlayers().get(0).getPlayer().getNickname(), "player2");*/








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
//      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      card.setLoad(false);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));






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
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));




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
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      //Test for player with "Furnace" in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Furnace();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));
      assertEquals(weaponController.getUsableEffectsForThisWeapon(card).size(), 2);


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));





      //Test for player with Lock rifle in his Weapon Deck

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new LockRifle();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==1));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));




      //Test for player with "Hellion" in his Weapon Deck

      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(0).setIsUsable(true);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Hellion();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(1)).equals(4));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==2));


      //Test for player with Whisper in his Weapon Deck
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Whisper();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).get(0)).equals(1));

      // Test for player with Railgun in his Weapon Deck
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Railgun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      //Test for player with "Flamethrower" in his Weapon Deck
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Flamethrower();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      //assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      //Test for player with  "Cyberblade" in his Weapon Deck

      for(int i=0;i<9;i++)
      {
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
      }

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Cyberblade();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));
      assertTrue(((Integer) weaponController.getUsableEffectsForThisWeapon(card).size()==2));



      //Test for player with "Sledgehammer" in his Weapon Deck

      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Sledgehammer();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(!weaponController.getUsableEffectsForThisWeapon(card).contains(4));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));

      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

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



      //Test for player with "Machine gun" in his Weapon Deck
      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);

      game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new MachineGun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));

      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));


      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(3));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));



      //Test for "Granade launcher"
      controller.getShootController().getTypeOfAttack().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new GranadeLauncher();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));


      //Test for "Plasma gun"
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new PlasmaGun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

      controller.getShootController().getTypeOfAttack().add(1);

      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(3));


      //Test for "Rocket launcher"
      controller.getShootController().getTypeOfAttack().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new RocketLauncher();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(3));

      //Test for THOR
      controller.getShootController().getTypeOfAttack().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Thor();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));

      controller.getShootController().getInvolvedPlayers().clear();
      controller.getShootController().getInvolvedPlayers().add(new InvolvedPlayer(game.getPlayers().get(1), 1, game.getPlayers().get(1).getPositionOnTheMap()));
      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));

      controller.getShootController().getInvolvedPlayers().add(new InvolvedPlayer(game.getPlayers().get(2), 2, game.getPlayers().get(1).getPositionOnTheMap()));
      controller.getShootController().getTypeOfAttack().add(2);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(3));


      //Test for Power glove
      controller.getShootController().getTypeOfAttack().clear();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new PowerGlove();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));

      //Test for Tractor beam
      /*game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(3).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new TractorBeam();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      //assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      //assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(4));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==2));*/

      //Test for Vortex cannon

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new VortexCannon();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));

      /*game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));*/




      /*//Test
      game.setPlayerOfTurn(1);
      game.setRound(1);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));*/

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
