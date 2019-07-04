package it.polimi.se2019.limperio.nicotera.italia.model;



import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTagbackGranade {

    Player player1= new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    Player player2= new Player("player2", false, 2, ColorOfFigure_Square.YELLOW);


    TagbackGranade tagbackGranade;

    @Before
    public void setUp()
    {

        tagbackGranade = new TagbackGranade(ColorOfCard_Ammo.YELLOW);
        player1.createPlayerBoard();
        player2.createPlayerBoard();

        tagbackGranade.setOwnerOfCard(player1);



    }



    @Test
    public void Test()
    {
        tagbackGranade.useAsPowerUp(player2, null);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);

    }
}
