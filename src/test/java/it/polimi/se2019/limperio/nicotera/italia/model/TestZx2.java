package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Zx2 or Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestZx2 {

    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives basic Effect or ScannerMode
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives ScannerMode
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.PURPLE);




    private Map map;
    private Zx2 zx2;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;




    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);




        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();


        zx2 = new Zx2();
        zx2.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(zx2);
        involvedPlayer2 = new InvolvedPlayer(player2, 1 , null);
        involvedPlayer3= new InvolvedPlayer(player3, 4 , null);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

    @Test
    public void TestBasicEffect() {
        zx2.useWeapon(1, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 2);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().get(1), ColorOfFigure_Square.BLUE);


    }

    @Test
    public void TestScannerMode()
    {
        zx2.useWeapon(4, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2);add(involvedPlayer3);}});

        assertEquals(player2.getPlayerBoard().getMarks().size(),1 );
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player3.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);


    }
}
