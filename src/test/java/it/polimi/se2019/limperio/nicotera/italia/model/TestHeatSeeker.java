package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Heatseeker of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestHeatSeeker {

    /**
     * Attacking player
     */
    private Player player1= new Player("player1", true, 1,ColorOfFigure_Square.BLUE);
    /**
     * player who receives three damages
     */
    private Player player2= new Player("player2", false, 2,ColorOfFigure_Square.RED);
    private Map map;
    private Heatseeker heatSeeker;
    private InvolvedPlayer involvedPlayer1;





    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(1);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][2]);

        player1.createPlayerBoard();
        player2.createPlayerBoard();

        heatSeeker= new Heatseeker();
        heatSeeker.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(heatSeeker);
        involvedPlayer1= new InvolvedPlayer(player2, null, null);



    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

    @Test
    public void TestBasicEffect()
    {
        heatSeeker.useWeapon(0, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 3);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
    }



}
