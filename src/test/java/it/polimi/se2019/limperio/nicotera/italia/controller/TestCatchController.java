package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.CatchEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCatchController extends TestController {
    private CatchController catchController = new CatchController(game, controller);



    @Test
    public void findSquareWherePlayerCanCatchTest(){

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




    }

    @Test
    public void addWeaponToPlayerTest()
    {
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        SpawnSquare spawnSquare= (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[1][0];
        WeaponCard card= new Railgun();
        spawnSquare.getWeaponCards().add(card);
        SelectionWeaponToCatch event= new SelectionWeaponToCatch("", game.getPlayers().get(0).getNickname());
        event.setNameOfWeaponCard("Railgun");
        event.setColumn(0);
        event.setRow(1);
        catchController.addWeaponToPlayer(event);
        assertTrue(!((SpawnSquare)game.getBoard().getMap().getMatrixOfSquares()[1][0]).getWeaponCards().contains(card));
        assertTrue(game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().contains(card));
        /**
         * check number of ammunition which are isUsable
         */
        int i=0;
        for(Ammo ammox: game.getPlayers().get(0).getPlayerBoard().getAmmo())
        {
            if(ammox.isUsable()) i++;
        }
        assertEquals(i, 1);
    }


    @Test
    public void handleCatchingTest()
    {
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


    }

   /*@Test
    public void weaponIsAffordableByPlayerTest(){
        SpawnSquare square = (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[1][0];
        square.getWeaponCards().add(0, WeaponCard.createWeaponCard(1));
        assertTrue(catchController.weaponIsAffordableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), square.getWeaponCards().get(0)));
        game.getPlayers().get(0).getPlayerBoard().removeAmmoOfThisColor(ColorOfCard_Ammo.BLUE);
        assertFalse(catchController.weaponIsAffordableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), square.getWeaponCards().get(0)));
        frequencyOfAmmosUsableByPlayerTest();
        addWeaponNotAffordableTest(square, game.getPlayers().get(0));
    }

    private void frequencyOfAmmosUsableByPlayerTest(){
        assertEquals(1, catchController.frequencyOfAmmoUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.RED));
        assertEquals(1, catchController.frequencyOfAmmoUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.YELLOW));
        assertEquals(0, catchController.frequencyOfAmmoUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.BLUE));
    }

    private void addWeaponNotAffordableTest(SpawnSquare square, Player player){
        ArrayList<ServerEvent.AliasCard> weapons = new ArrayList<>();
        catchController.addWeaponNotAffordable(square, player, weapons);
        assertEquals("Lockrifle", weapons.get(0).getName());
    }*/

}