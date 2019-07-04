package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Furnace of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestFurnace {

    /**
     * player attacking
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage with BasicMode or one damage with InCozyFireMode
     */
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    /**
     * player who receives one damage with BasicMode
     */
    private Player player3= new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    private Furnace furnace;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;
    private Map map;



    @Before
    public void setUp()
    {


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        map= Map.instanceOfMap(3);
        furnace= new Furnace();
        furnace.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(furnace);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][3]);
        involvedPlayer2= new InvolvedPlayer(player2, 1, null);
        involvedPlayer3= new InvolvedPlayer(player3, 1 , null);
        involvedPlayer4= new InvolvedPlayer(null, 0 , map.getMatrixOfSquares()[1][2]);

    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }




    /*@Test
    public void TestBasicMode()
    {



        furnace.useWeapon(1, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);add(involvedPlayer3);}});
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());

    }*/

    @Test
    public void TestInCozyFireMode()
    {



        furnace.useWeapon(4, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer4);}});
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player3.getPlayerBoard().getMarks().size(), 0);


    }
}
