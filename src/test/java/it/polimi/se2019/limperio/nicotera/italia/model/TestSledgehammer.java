package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Sledgehammer of Weapon Card
 */

public class TestSledgehammer {


    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives two damages with basicEffect
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives three damages with pulverizeMode and he moves two squares in the same direction
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.PURPLE);


    private Map map;
    private Sledgehammer sledgehammer;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;



    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);



        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();

        sledgehammer = new Sledgehammer();
        sledgehammer.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(sledgehammer);
        involvedPlayer2 = new InvolvedPlayer(player2, new ArrayList<Integer>(){{add(1);}} , null);
        involvedPlayer3= new InvolvedPlayer(player3, new ArrayList<Integer>(){{add(4);}}, map.getMatrixOfSquares()[1][2]);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffect() {
        sledgehammer.useWeapon(new ArrayList<Integer>() {{ add(1);}}, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);

    }

    @Test
    public void TestLongBarrelMode()
    {
        sledgehammer.useWeapon(new ArrayList<Integer>() {{ add(4);}}, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer3);}});

        assertEquals(player3.getPlayerBoard().getDamages().size(), 3);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(2), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPositionOnTheMap(), map.getMatrixOfSquares()[1][2]);

    }
}
