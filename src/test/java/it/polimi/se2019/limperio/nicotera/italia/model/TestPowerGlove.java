package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for PowerGlove of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestPowerGlove {
    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage and two marks with basicEffect
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives two damages with rocketFirstMode
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    /**
     * player who receives two damages with rocketFirstMode
     */
    private Player player4 = new Player("player4", false, 4, ColorOfFigure_Square.GREY);

    private Map map;
    private PowerGlove powerGlove;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;


    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[0][1]);


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();
        powerGlove = new PowerGlove();
        powerGlove.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(powerGlove);
        involvedPlayer2 = new InvolvedPlayer(player2, 0, null);
        involvedPlayer3 = new InvolvedPlayer(player3, 0, map.getMatrixOfSquares()[2][1]);
        involvedPlayer4 = new InvolvedPlayer(player4, 0, map.getMatrixOfSquares()[0][1]);

    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffect() {
        powerGlove.useWeapon(1, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});
        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[2][1]);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 2);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().get(1), ColorOfFigure_Square.BLUE);
    }

    @Test
    public void TestRocketFirstMode()
    {
        powerGlove.useWeapon(4, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer3);add(involvedPlayer4); }});
        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[0][1]);
        assertEquals(player3.getPlayerBoard().getDamages().size(),2);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);

        assertEquals(player4.getPlayerBoard().getDamages().size(),2);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);

    }
}
