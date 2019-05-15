package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test of TestHellion of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestHellion {


    /**
     * attacking player
     */
    private Player player1= new Player("player1", true, 1,ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage and one mark with basicMode or one damage and two marks with InNanoTracerMode
     */
    private Player player2= new Player("player2", false, 2,ColorOfFigure_Square.RED);
    /**
     * player who receives one mark with basicMode or two marks with InNanoTracerMode
     */
    private Player player3= new Player("player3", false, 3,ColorOfFigure_Square.YELLOW);
    /**
     * player who receives one mark with basicMode or two marks with InNanoTracerMode
     */
    private Player player4= new Player("player4", false, 4,ColorOfFigure_Square.GREY);
    private Map map;
    private Hellion hellion;
    private InvolvedPlayer involvedPlayer1;





    @Before
    public void setUp()
    {
        map= Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();
        hellion = new Hellion();
        hellion.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(hellion);
        involvedPlayer1= new InvolvedPlayer(player2, null,  null);



    }
    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


   @Test
    public void TestBasicMode()
    {
        hellion.useWeapon(new ArrayList<Integer>(){{add(1);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player3.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player4.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player4.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 0);
    }

    @Test
    public void TestInNanoTracerMode()
    {
        hellion.useWeapon(new ArrayList<Integer>(){{add(4);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 2);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getMarks().size(), 2);
        assertEquals(player3.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getMarks().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player4.getPlayerBoard().getMarks().size(), 2);
        assertEquals(player4.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getMarks().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 0);

    }

}
