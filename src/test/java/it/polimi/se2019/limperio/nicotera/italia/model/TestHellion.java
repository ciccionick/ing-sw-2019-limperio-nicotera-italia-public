package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DA RIFARE; NON VIENE e da rifare anche la documentazione dei player
 */

public class TestHellion {


    /**
     * attacking player
     */
    private Player player1= new Player("player1", true, 1,ColorOfFigure_Square.BLUE);
    /**
     * player who receives the damage and the mark with basic attack
     */
    private Player player2= new Player("player2", false, 2,ColorOfFigure_Square.RED);
    /**
     * player who receives the mark with basic attack because he stays in the same square of player 2
     */
    private Player player3= new Player("player3", false, 3,ColorOfFigure_Square.YELLOW);
    /**
     * player who receives the mark with basic attack because he stays in the same square of player 2
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
