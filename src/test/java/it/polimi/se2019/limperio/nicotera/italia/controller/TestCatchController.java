package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.CatchEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardAsAmmo;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToDiscard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for CatchController
 *
 * @author Giuseppe Italia
 */

public class TestCatchController{




    Game game = Game.instanceOfGame();
    Controller controller = new Controller(game);
    private CatchController catchController = new CatchController(game, controller);



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
    public void findSquareWherePlayerCanCatchTest(){

        game.setInFrenzy(false);
       //Test for player 1 situated in [0][0]. The game is not in FrenzyMode
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        ArrayList<ServerEvent.AliasCard> wp = new ArrayList<>();
        ArrayList<Square> squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(0), wp);

        for(Square squarex: squares)
        {
            if(!squarex.isSpawn())
            {

                assertNotEquals(((NormalSquare) squarex).getAmmoTile(), null);
            }
            else
            {

                assertNotEquals(((SpawnSquare) squarex).getWeaponCards(), null);
            }
        }


        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));


        //Test for player situated in [1][0]. The game is not in FrenzyMode
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);

        squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(0), wp);

        for(Square squarex: squares)
        {
            if(!squarex.isSpawn())
            {

                assertNotEquals(((NormalSquare) squarex).getAmmoTile(), null);
            }
            else
            {

                assertNotEquals(((SpawnSquare) squarex).getWeaponCards(), null);
            }
        }


        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][1]));

        //Test for player situated in [0][0]. The game is not in FrenzyMode, but the player is Over Three Damage

        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);

        game.getPlayers().get(0).setIsUnderThreeDamage(false);
        squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(0), wp);

        for(Square squarex: squares)
        {
            if(!squarex.isSpawn())
                assertNotEquals(((NormalSquare) squarex).getAmmoTile(), null);
            else assertNotEquals(((SpawnSquare) squarex).getWeaponCards(), null);
        }



        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][2]));




        //Test for player 2 situated in [0][0]. The game in FrenzyMode

        game.setInFrenzy(true);
        game.setFirstInFrenzyMode(1);

        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        wp.clear();
        squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(1), wp);

        for(Square squarex: squares)
        {
            if(!squarex.isSpawn())
            {

                assertNotEquals(((NormalSquare) squarex).getAmmoTile(), null);
            }
            else
            {

                assertNotEquals(((SpawnSquare) squarex).getWeaponCards(), null);
            }
        }


        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][2]));

        //Test for player 2 situated in [0][0]. The game in FrenzyMode

        game.setInFrenzy(true);
        game.setFirstInFrenzyMode(3);

        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        wp.clear();
        squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(1), wp);

        for(Square squarex: squares)
        {
            if(!squarex.isSpawn())
            {

                assertNotEquals(((NormalSquare) squarex).getAmmoTile(), null);
            }
            else
            {

                assertNotEquals(((SpawnSquare) squarex).getWeaponCards(), null);
            }
        }


        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][0]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][1]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[0][2]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[1][2]));
        assertTrue(squares.contains(game.getBoard().getMap().getMatrixOfSquares()[2][1]));


    }

    @Test
    public void handleSelectionWeaponToCatch() {

        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        SpawnSquare spawnSquare = (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[1][0];
        WeaponCard card = spawnSquare.getWeaponCards().get(0);
        SelectionWeaponToCatch event = new SelectionWeaponToCatch("", game.getPlayers().get(0).getNickname());
        event.setNameOfWeaponCard(card.getName());
        catchController.handleSelectionWeaponToCatch(event);
        assertTrue(!spawnSquare.getWeaponCards().contains(card));
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().contains(card));

        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        spawnSquare = (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[1][0];
        spawnSquare.getWeaponCards().clear();
        card = new LockRifle();
        spawnSquare.getWeaponCards().add(card);
        for(int i=0;i<9;i++)
        {
            game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        }
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().clear();
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(PowerUpCard.createPowerUpCard(3));
        event = new SelectionWeaponToCatch("", game.getPlayers().get(0).getNickname());
        event.setNameOfWeaponCard(card.getName());
        catchController.handleSelectionWeaponToCatch(event);
        assertTrue(!spawnSquare.getWeaponCards().contains(card));
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().isEmpty());
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().contains(card));

    }



    @Test
    public void handleCatchingTest()
    {

       // To catch the Ammotile in a Normal Square
        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
        NormalSquare normalSquare= (NormalSquare) game.getBoard().getMap().getMatrixOfSquares()[1][1];
        normalSquare.setAmmoTile(new AmmoTile(1));
        CatchEvent event= new CatchEvent("",game.getPlayers().get(1).getNickname(),1,1);
        PowerUpCard powerUpCard = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
        catchController.handleCatching(event);
        assertTrue(game.getPlayers().get(1).getPlayerBoard().getPowerUpCardsOwned().contains(powerUpCard));
        assertTrue(!game.getBoard().getPowerUpDeck().getPowerUpCards().contains(powerUpCard));
        assertTrue(game.getBoard().getPowerUpDeck().getUsedPowerUpCards().contains(powerUpCard));


        int j=0;
        for(Ammo ammox: game.getPlayers().get(1).getPlayerBoard().getAmmo())
        {
            if(ammox.isUsable()) j++;

        }

        assertEquals(j, 5);

       /*
        //To catch the Ammotile in a Spoon Square
        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        SpawnSquare spawnSquare= (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[1][0];
        CatchEvent event= new CatchEvent("",game.getPlayers().get(1).getNickname(),1,0);
        RequestForChooseAWeaponToCatch requestForChooseAWeaponToCatch= new RequestForChooseAWeaponToCatch("");
        event.setMyVirtualView(new VirtualView(null, null, controller));
        event.getMyVirtualView().update(requestForChooseAWeaponToCatch);
        catchController.handleCatching(event);
        assertNotEquals(event.getMyVirtualView().getOut(), requestForChooseAWeaponToCatch);*/

    }

    @Test
    public void addWeaponNotAffordableTest()
    {
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        PlasmaGun plasmaGun= new PlasmaGun();
        ((SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[0][2]).getWeaponCards().add(plasmaGun);
        int i;
        for(i=0;i<9;i++)
        {
            if(game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).getColor().equals(ColorOfCard_Ammo.YELLOW))
            {
                game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
            }
        }
        ArrayList<ServerEvent.AliasCard> weaponNotAffordable= new ArrayList<>();


        catchController.addWeaponNotAffordable(((SpawnSquare)game.getBoard().getMap().getMatrixOfSquares()[0][2]), game.getPlayers().get(0), weaponNotAffordable);

        boolean trovato=false;
        for(ServerEvent.AliasCard weaponNotAffordablex: weaponNotAffordable)
        {
            if(weaponNotAffordablex.getName().equals(plasmaGun.getName())) trovato=true;
        }
        assertTrue(trovato);

    }

    @Test
    public void findPowerUpCardTest()
    {
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(PowerUpCard.createPowerUpCard(3));
        assertTrue(catchController.findPowerUpCard("Targeting scope", ColorOfCard_Ammo.BLUE, game.getPlayers().get(0))!=null);

    }

    /*@Test
    public void handleRequestToDiscardPowerUpCardTest()
    {
        PowerUpCard powerUpCard= PowerUpCard.createPowerUpCard(3);
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard);
        DiscardPowerUpCardAsAmmo event= new DiscardPowerUpCardAsAmmo(" ", game.getPlayers().get(0).getNickname());
        event.setColorOfCard(ColorOfCard_Ammo.BLUE);
        event.setNameOfPowerUpCard("Targeting scope");
        catchController.handleRequestToDiscardPowerUpCardAsAmmo(event);
        assertTrue(!game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().contains(powerUpCard));

    }*/

    @Test
    public void getPowerUpCardToChooseForDiscardTest()
    {

        PowerUpCard powerUpCard= PowerUpCard.createPowerUpCard(3);
        game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard);
        assertEquals((catchController.getPowerUpCardToChooseForDiscard(game.getPlayers().get(0), ColorOfCard_Ammo.BLUE)).get(0).getName(), powerUpCard.getName());
        assertEquals((catchController.getPowerUpCardToChooseForDiscard(game.getPlayers().get(0), ColorOfCard_Ammo.BLUE)).get(0).getColor(), powerUpCard.getColor());

    }

   /* @Test
    public void handleSelectionWeaponToCatchAfterDiscardTest()
    {
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        WeaponCard cardToAdd= new Heatseeker(); //Sarebbe la carta del giocatore che vado a scartare
        WeaponCard cardToRemove= new Railgun(); // rimuovere non dal deck del giocatore ma dallo spoon Square
        Square spawnSquare= game.getPlayers().get(0).getPositionOnTheMap();

        ((SpawnSquare) spawnSquare).setWeaponCards(new ArrayList<WeaponCard>(){{add(cardToRemove);}});
        SelectionWeaponToDiscard event = new SelectionWeaponToDiscard("", game.getPlayers().get(0).getNickname(), cardToAdd.getName(), cardToRemove.getName());

        catchController.handleSelectionWeaponToCatchAfterDiscard(event);
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().contains(cardToRemove));
        assertTrue(!game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().contains(cardToAdd));
    }*/

   /*@Test
    public void handleRequestToDiscardPowerUpCardAsAmmoTest()
   {
       catchController.getColorsNotEnough().clear();
       game.getPlayers().get(0).getPlayerBoard().getPowerUpCardsOwned().add(PowerUpCard.createPowerUpCard(4));
       ArrayList<PowerUpCard> getpowerUpCardsToDiscard= catchController.getpowerUpCardsToDiscard();
       int x= getpowerUpCardsToDiscard.size();
       DiscardPowerUpCardAsAmmo event= new DiscardPowerUpCardAsAmmo("", game.getPlayers().get(0).getNickname());
       event.setNameOfPowerUpCard("Teleporter");
       event.setColorOfCard(ColorOfCard_Ammo.YELLOW);
       catchController.handleRequestToDiscardPowerUpCardAsAmmo(event);
       assertEquals(getpowerUpCardsToDiscard.size(), x+1);
   }*/

   @Test
    public void getWeaponCardFromNameTest()
   {
       WeaponCard weaponCard= new TractorBeam();
       game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(weaponCard);
       assertEquals(catchController.getWeaponCardFromName("Tractor beam").getName(), weaponCard.getName() );
   }

}