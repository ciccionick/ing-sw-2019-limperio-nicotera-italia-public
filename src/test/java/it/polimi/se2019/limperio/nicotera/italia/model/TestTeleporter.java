package it.polimi.se2019.limperio.nicotera.italia.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Teleporter of PowerUp Card
 */

public class TestTeleporter {

    Player player1= new Player("player1", true, 1, ColorOfFigure_Square.BLUE);

    Map map;
    Teleporter teleporter;

    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(1);
        teleporter = new Teleporter(ColorOfCard_Ammo.YELLOW, 4);
        player1.createPlayerBoard();

        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        teleporter.setOwnerOfCard(player1);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

    @Test
    public void Test()
    {
        teleporter.useAsPowerUp(null, map.getMatrixOfSquares()[2][2]);
        Assert.assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[2][2]);

    }
}
