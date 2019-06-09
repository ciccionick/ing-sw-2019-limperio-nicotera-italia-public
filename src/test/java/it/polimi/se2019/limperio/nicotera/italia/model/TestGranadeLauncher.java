package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of GranadeLauncher of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestGranadeLauncher {

    /**
     * player attacking
     */
    private Player player1= new Player("player1", true, 1,ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage and is moved by a square with BasicEffect
     */
    private Player player2= new Player("player2", false, 2,ColorOfFigure_Square.RED);
    /**
     * player who receives one damage with ExtraGranade
     */
    private Player player3= new Player("player3", false, 3,ColorOfFigure_Square.YELLOW);
    /**
     * player who receives one damage with ExtraGranade
     */
    private Player player4= new Player("player4", false, 4,ColorOfFigure_Square.GREY);
    private Map map;
    private GranadeLauncher granadeLauncher;
    private InvolvedPlayer involvedPlayer1;
    private InvolvedPlayer involvedPlayer2;




    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(1);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();
        granadeLauncher= new GranadeLauncher();
        granadeLauncher.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(granadeLauncher);
        involvedPlayer1= new InvolvedPlayer(player2, new ArrayList<Integer>(){{add(1);}},  map.getMatrixOfSquares()[1][0]);
        involvedPlayer2= new InvolvedPlayer(null, new ArrayList<Integer>(){{add(2);}}, map.getMatrixOfSquares()[1][1]);


    }
    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffect()
    {
        granadeLauncher.useWeapon(1, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPositionOnTheMap(), map.getMatrixOfSquares()[1][0]);
    }

    @Test
    public void TestWithExtraGranade()
    {
        granadeLauncher.useWeapon(2, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);

    }



}
