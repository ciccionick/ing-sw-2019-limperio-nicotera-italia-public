package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for LockRifle of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestLockRifle {


    /**
     * attacking player
     */
    private Player player1= new Player("player1", true, 1,ColorOfFigure_Square.BLUE);
    /**
     * player who receives two damages and the mark with basic attack
     */
    private Player player2= new Player("player2", false, 2,ColorOfFigure_Square.RED);
    /**
     * player who receives the mark with SecondLock Attack
     */
    private Player player3= new Player("player3", false, 3,ColorOfFigure_Square.YELLOW);

    private Map map;
    private LockRifle lockRifle;
    private InvolvedPlayer involvedPlayer;
    private InvolvedPlayer involvedPlayer1;





    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);



        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();

        lockRifle = new LockRifle();
        lockRifle.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(lockRifle);
        involvedPlayer= new InvolvedPlayer(player2, 1,  null);
        involvedPlayer1= new InvolvedPlayer(player3, 2,  null);


    }
    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffec()
    {
        lockRifle.useWeapon(1, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);

    }

    @Test
    public void TestInNanoTracerMode()
    {
        lockRifle.useWeapon(2, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});

        assertEquals(player3.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player3.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);

        assertEquals(player3.getPlayerBoard().getDamages().size(), 0);


    }
}
