package it.polimi.se2019.limperio.nicotera.italia.model;


import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Thor of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestThor {

    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives two damages with basicEffect
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives one damages with ChainReaction
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.PURPLE);
    /**
     * player who receives two damages with HighVoltage
     */
    private Player player4 = new Player("player4", false, 4, ColorOfFigure_Square.YELLOW);


    private Map map;
    private Thor thor;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;



    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[1][3]);



        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();

        thor = new Thor();
        thor.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(thor);
        involvedPlayer2 = new InvolvedPlayer(player2, 1 , null);
        involvedPlayer3= new InvolvedPlayer(player3, 2, null);
        involvedPlayer4= new InvolvedPlayer(player4, 3, null);

    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffect() {
        thor.useWeapon(1, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);

    }

    @Test
    public void TestWithChainReaction()
    {
        thor.useWeapon(2, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer3);}});

        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);


    }

    @Test
    public void TestWithHighVoltage()
    {

        thor.useWeapon(3, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer4);}});

        assertEquals(player4.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);

    }
}
