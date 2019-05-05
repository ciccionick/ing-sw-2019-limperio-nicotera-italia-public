package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestCatchController extends TestController {
    private CatchController catchController = new CatchController(game, controller);

    @Test
    public void findSquareWherePlayerCanCatchTest(){
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        ArrayList<ServerEvent.AliasCard> wp = new ArrayList<>();
        ArrayList<Square> squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(0), wp);
        assertEquals(squares.get(0).getRow(), 0);
        assertEquals(squares.get(0).getColumn(), 0);
        assertEquals(squares.get(1).getRow(), 1);
        assertEquals(squares.get(1).getColumn(), 0);
        assertEquals(squares.get(2).getRow(), 0);
        assertEquals(squares.get(2).getColumn(), 1);
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        squares = catchController.findSquareWherePlayerCanCatch(game.getPlayers().get(0), wp);
        assertEquals(squares.get(0).getRow(), 1);
        assertEquals(squares.get(0).getColumn(), 0);
        assertTrue(squares.get(0).isSpawn());

    }

    @Test
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
        assertEquals(1, catchController.frequencyOfAmmosUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.RED));
        assertEquals(1, catchController.frequencyOfAmmosUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.YELLOW));
        assertEquals(0, catchController.frequencyOfAmmosUsableByPlayer(game.getPlayers().get(0).getPlayerBoard().getAmmo(), ColorOfCard_Ammo.BLUE));
    }

    private void addWeaponNotAffordableTest(SpawnSquare square, Player player){
        ArrayList<ServerEvent.AliasCard> weapons = new ArrayList<>();
        catchController.addWeaponNotAffordable(square, player, weapons);
        assertEquals("Lock Rifle", weapons.get(0).getName());
    }

}