package it.polimi.se2019.limperio.nicotera.italia.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestNewton {

    Player player1= new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    Player player2= new Player("player2", false, 2, ColorOfFigure_Square.YELLOW);

    Map map;
    Newton newton;

    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(1);
        newton = new Newton(ColorOfCard_Ammo.YELLOW, 7);
        player1.createPlayerBoard();
        player2.createPlayerBoard();

        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);

        newton.setOwnerOfCard(player1);



    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

    @Test
    public void Test()
    {
        newton.useAsPowerUp(player2, map.getMatrixOfSquares()[1][2]);

        Assert.assertEquals(player2.getPositionOnTheMap(), map.getMatrixOfSquares()[1][2]);

    }
}
