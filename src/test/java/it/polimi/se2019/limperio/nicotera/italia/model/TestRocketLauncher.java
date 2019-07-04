package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for RocketLauncher of Weapon card
 *
 * @author Giuseppe Italia
 */

public class TestRocketLauncher {


    /**
     * attacking player and with rocket jump effect he can move 1 or 2 squares
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives two damages and is moved to a neighboring square with basicEffect. With third effect he receives another damage
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives one damage because he is in the square where player2 was after the base Effect
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);


    private Map map;
    private RocketLauncher rocketLauncher;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer1;



    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);



        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();

        rocketLauncher = new RocketLauncher();
        rocketLauncher.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(rocketLauncher);
        involvedPlayer2 = new InvolvedPlayer(player2, 1 ,map.getMatrixOfSquares()[2][2]);
        involvedPlayer1= new InvolvedPlayer(player1, 2, map.getMatrixOfSquares()[1][1]);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


   /* @Test
    public void TestBasicAndThirdEffect() {
        rocketLauncher.useWeapon(1, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 3);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPositionOnTheMap(), map.getMatrixOfSquares()[2][2]);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);

    }*/

    @Test
    public void TestRocketJump()
    {
        rocketLauncher.useWeapon(2, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer1);}});

        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[1][1]);

    }
}
