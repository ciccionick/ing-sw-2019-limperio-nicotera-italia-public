package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPowerUpController extends TestController {
    PowerUpController powerUpController = new PowerUpController(this.game, this.controller);

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
}