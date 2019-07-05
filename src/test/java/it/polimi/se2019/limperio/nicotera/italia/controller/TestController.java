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

    private Game game = Game.instanceOfGame();
    private Controller controller = new Controller(game);
    private WeaponController weaponController= new WeaponController(game, controller);
    private ShootController shootController= new ShootController(game, controller);



   @Before
   public void setUp(){
      game.createPlayer("player1", true, 1, "BLUE");
      game.createPlayer("player2", false, 2, "YELLOW");
      game.createPlayer("player3", false, 3, "GREY");
      game.createPlayer("player4", false, 4, "PURPLE");

      game.setGameOver(false);
      game.initializeGame(true, 1, true);
   }
   @After
   public void cleanUp(){
      game.setInstanceOfGameNullForTesting();
      game.getBoard().getKillShotTrack().setInstanceOfKillShotTrackNullForTesting();


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



      //Test for SelectionWeaponToCatch event
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      SelectionWeaponToCatch event4= new SelectionWeaponToCatch("", game.getPlayers().get(0).getNickname());
      WeaponCard weaponCard= new Railgun();
      ((SpawnSquare)game.getBoard().getMap().getMatrixOfSquares()[1][0]).getWeaponCards().add(weaponCard);
      event4.setNameOfWeaponCard(weaponCard.getName());
      controller.update(event4);
//


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





      SelectionSquare event5= new SelectionSquare("",game.getPlayers().get(0).getNickname(), 0, 0);
      game.setPlayerOfTurn(1);
      event5.setGenerationTerminatorEvent(true);
      controller.update(event5);
      event5.setGenerationTerminatorEvent(false);
      event5.setMoveTerminatorEvent(true);
      controller.update(event5);
      event5.setMoveTerminatorEvent(false);
      event5.setRunEvent(true);
      controller.update(event5);
      event5.setRunEvent(false);
      event5.setBeforeToShoot(true);
      controller.update(event5);
      event5.setBeforeToShoot(false);
      event5.setSelectionSquareForShootAction(true);
      event5.setSelectionSquareForShootAction(false);
      event5.setSelectionSquareToUseTeleporter(true);
      game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(PowerUpCard.createPowerUpCard(1));
      controller.getPowerUpController().setNumOfCardToUse(1);



      //Test for RequestToUseNewton
      game.setPlayerOfTurn(1);
      RequestToUseNewton event8= new RequestToUseNewton("", game.getPlayers().get(0).getNickname());
      controller.update(event8);

      //Test for RequestToUseTeleporter
      RequestToUseTeleporter event9= new RequestToUseTeleporter("",game.getPlayers().get(0).getNickname());
      controller.update(event9);



      //Test for DiscardAmmoOrPowerUpToPayTargeting
      DiscardAmmoOrPowerUpToPayTargeting event12= new DiscardAmmoOrPowerUpToPayTargeting("", game.getPlayers().get(0).getNickname());
      controller.update(event12);

      //Test for DrawPowerUpCards

      DrawPowerUpCards event13= new  DrawPowerUpCards("", game.getPlayers().get(0).getNickname(), 0);
      controller.update(event13);



      //Test for RequestToUseWeaponCard
      RequestToUseWeaponCard event15= new RequestToUseWeaponCard("",game.getPlayers().get(0).getNickname());
      ServerEvent.AliasCard aliasCard= new ServerEvent.AliasCard("Flamethrower", "",ColorOfCard_Ammo.RED);
      event15.setWeaponWantUse(aliasCard);
      WeaponCard card2= new Flamethrower();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card2);
      card2.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      controller.update(event15);




      //Test
      ClientEvent event18= new ClientEvent("", game.getPlayers().get(0).getNickname());
      event18.setRequestToGoOn(true);
      controller.update(event18);
      event18.setRequestToGoOn(false);
      event18.setRequestToMoveTerminator(true);
      controller.update(event18);
      event18.setRequestToMoveTerminator(false);
      event18.setRequestToShootWithTerminator(true);
      controller.update(event18);
      event18.setRequestToShootWithTerminator(false);
      event18.setRequestToRunByPlayer(true);
      controller.update(event18);
      event18.setRequestToRunByPlayer(false);
      event18.setRequestToShootByPlayer(true);
      controller.update(event18);
      event18.setRequestToShootByPlayer(false);
      event18.setRequestToCatchByPlayer(true);

      controller.update(event18);
      event18.setRequestToCatchByPlayer(false);
      event18.setRequestTerminatorActionByPlayer();


      //test for TerminatorShootEvent
      TerminatorShootEvent event19= new TerminatorShootEvent("", game.getPlayers().get(0).getNickname());
      event19.setNicknamePlayerToAttack(game.getPlayers().get(0).getNickname());
      controller.update(event19);

      //Test for SelectionWeaponToReload
      SelectionWeaponToReload event20= new SelectionWeaponToReload("", game.getPlayers().get(0).getNickname());
      controller.update(event20);


   }

   @Test
   public void findPlayerWithThisNicknameTest(){
      assertEquals("player1", controller.findPlayerWithThisNickname("player1").getNickname() );
      assertEquals("player2", controller.findPlayerWithThisNickname("player2").getNickname());
      assertEquals("player3",controller.findPlayerWithThisNickname("player3").getNickname() );

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
      assertEquals(1,game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().size());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertEquals(1, (int) ( weaponController.getUsableEffectsForThisWeapon(card).get(0)));
      assertEquals(4, (int) ( weaponController.getUsableEffectsForThisWeapon(card).get(1)));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      card.setLoad(false);







      //Test for player with Heatseeker in his WeaponDeck


      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Heatseeker();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertEquals(1, (int) ( weaponController.getUsableEffectsForThisWeapon(card).get(0)));

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
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertEquals(1,  weaponController.getUsableEffectsForThisWeapon(card).size());
      assertEquals(1, (int) weaponController.getUsableEffectsForThisWeapon(card).get(0));
      //Inserisco un ammo giallo al player per poter fargli usare anche la modalità extra nella carta
      game.getPlayers().get(0).getPlayerBoard().getAmmo().get(2).setIsUsable(true);
      assertEquals(1, (int) weaponController.getUsableEffectsForThisWeapon(card).get(0));
      assertEquals(4, (int) weaponController.getUsableEffectsForThisWeapon(card).get(1));

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
      assertEquals(1, (int) ( weaponController.getUsableEffectsForThisWeapon(card).get(0)));
      assertEquals(4, (int) ( weaponController.getUsableEffectsForThisWeapon(card).get(1)));
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
      assertEquals((Integer) 1, (weaponController.getUsableEffectsForThisWeapon(card).get(0)));
      assertEquals(1, (weaponController.getUsableEffectsForThisWeapon(card).size()));


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
      assertEquals((weaponController.getUsableEffectsForThisWeapon(card).get(0)), (Integer) 1);
      assertEquals(weaponController.getUsableEffectsForThisWeapon(card).get(1), (Integer) 4 );
      assertEquals(2, weaponController.getUsableEffectsForThisWeapon(card).size());


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
      assertEquals((weaponController.getUsableEffectsForThisWeapon(card).get(0)), (Integer)1);

      // Test for player with Railgun in his Weapon Deck

      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j <matrix[i].length; j++) {
            if(matrix[i][j]!=null)
               game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();
         }
      }

      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);



      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Railgun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertEquals(2, weaponController.getUsableEffectsForThisWeapon(card).size());

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
      assertTrue(!controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));



      //Test for player with "Flamethrower" in his Weapon Deck
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j <matrix[i].length; j++) {
            if(matrix[i][j]!=null)
            {
               game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();
            }

         }
      }

      for(int i=0;i<9;i++)
      {
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
      }


      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Flamethrower();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertEquals(weaponController.getUsableEffectsForThisWeapon(card).size(), 1);

      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);

      assertEquals(2,weaponController.getUsableEffectsForThisWeapon(card).size());


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);




      //Test for player with  "Cyberblade" in his Weapon Deck

      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);


      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new Cyberblade();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(1));



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

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));


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
       game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
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
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(3).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new TractorBeam();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));




      //Test for Vortex cannon
      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);

      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new VortexCannon();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      controller.getShootController().getInvolvedPlayers().clear();
      controller.getShootController().getInvolvedPlayers().add(new InvolvedPlayer(game.getPlayers().get(1), 1,game.getPlayers().get(1).getPositionOnTheMap()));
      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue(weaponController.getUsableEffectsForThisWeapon(card).contains(2));


      //Test for player with Lock rifle in his Weapon Deck

      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j <matrix[i].length; j++) {
            if(matrix[i][j]!=null)
               game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();
         }
      }

      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);

      controller.getShootController().getTypeOfAttack().clear();
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new LockRifle();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
      game.getPlayers().get(2).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);

      assertEquals(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().get(0).getName(), card.getName());

      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).contains(1)));
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).size()==1));



      controller.getShootController().getTypeOfAttack().add(1);
      assertTrue((weaponController.getUsableEffectsForThisWeapon(card).contains(2)));



      //Test for Power glove in frenzy
      game.setInFrenzy(true);
      game.setFirstInFrenzyMode(1);

      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j <matrix[i].length; j++) {
            if(matrix[i][j]!=null)
               game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();
         }
      }
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
      game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
      game.getPlayers().get(1).getPlayerBoard().getWeaponsOwned().clear();
      card= new PowerGlove();
      game.getPlayers().get(1).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(1));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(1).getPlayerBoard().getWeaponsOwned()));

      game.setFirstInFrenzyMode(2);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
      card= new PowerGlove();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      card.setOwnerOfCard(game.getPlayers().get(0));
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));


      game.setInFrenzy(false);
      game.getPlayers().get(0).setOverSixDamage(true);
      WeaponCard card1= new Railgun();
      WeaponCard card2= new Flamethrower();
      WeaponCard card3= new GranadeLauncher();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card1);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card2);
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card3);
      assertTrue(controller.checkIfPlayerCanShoot(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned()));

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

   @Test
   public void handleDisconnectionTest()
   {
      game.setPlayerOfTurn(1);
      String nickname=game.getPlayers().get(0).getNickname();
      controller.handleDisconnection(nickname);
      assertEquals(game.getPlayerOfTurn(), 2);
      assertTrue(!game.getListOfNickname().contains(nickname));
      assertTrue(!game.getPlayers().get(0).isConnected());


   }

   @Test
   public void handleReconnectionTest()
   {
      game.getPlayers().get(0).setConnected(false);
      game.getPlayers().get(1).setConnected(false);
      String namePlayer= game.getPlayers().get(0).getNickname();
      controller.handleReconnection(namePlayer);
      assertTrue(game.getPlayers().get(0).isConnected());
      //assertTrue(!game.getListOfNickname().contains(game.getPlayers().get(1).getNickname()));
   }

   @Test
   public void sendRequestToDrawPowerUpCardTest()
   {
      game.getPlayers().get(0).setHasToBeGenerated(false);
      controller.sendRequestToDrawPowerUpCard(game.getPlayers().get(0), 1);
      assertTrue(game.getPlayers().get(0).getHasToBeGenerated());

   }


   @Test
   public void checkIfThisWeaponIsUsableTest()
   {

      game.setInFrenzy(false);
      WeaponCard card= new Railgun();
      game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
      for(int i=0;i<9;i++)
         game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(true);
      card.setOwnerOfCard(game.getPlayers().get(0));
      card.setLoad(false);
      assertTrue(!controller.checkIfThisWeaponIsUsable(card, 0));

   }

   @Test
   public void sendRequestForAction()
   {
      game.setRound(3);
      game.setNumOfActionOfTheTurn(0);
      controller.sendRequestForAction();
   }






}
