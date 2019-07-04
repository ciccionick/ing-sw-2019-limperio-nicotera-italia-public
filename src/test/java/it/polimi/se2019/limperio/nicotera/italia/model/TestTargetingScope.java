package it.polimi.se2019.limperio.nicotera.italia.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for TargetingScope PowerUp Card
 */

public class TestTargetingScope {


    Player player1= new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    Player player2= new Player("player2", false, 2, ColorOfFigure_Square.YELLOW);


    TargetingScope targetingScope;

    @Before
    public void setUp()
    {

        targetingScope = new TargetingScope(ColorOfCard_Ammo.YELLOW);
        player1.createPlayerBoard();
        player2.createPlayerBoard();

        targetingScope.setOwnerOfCard(player1);



    }



    @Test
    public void Test()
    {
        targetingScope.useAsPowerUp(player2, null);
        Assert.assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        Assert.assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);

    }
}
