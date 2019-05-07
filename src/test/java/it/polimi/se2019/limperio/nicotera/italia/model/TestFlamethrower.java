package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Weapon Card Flamethrower
 *
 * @author Giuseppe Italia
 */

public class TestFlamethrower {

    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    private Player player3= new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    private Flamethrower flamethrower;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;
    private Map map;


    /**
     * The test puts player2 in a square 1 move away from player1 and player3 in a second square 1 more move away in the same direction
     */

    @Before
    public void setUp()
    {


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        map= Map.instanceOfMap(1);
        flamethrower = new Flamethrower();
        flamethrower.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(flamethrower);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][3]);
        involvedPlayer2= new InvolvedPlayer(player2, null , null);
        involvedPlayer3= new InvolvedPlayer(player3, null , null);

    }



    @Test
    public void TestBasicMode()
    {



        flamethrower.useWeapon(new ArrayList<Integer>(){{add(1);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);add(involvedPlayer3);}});
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());

    }

    @Test
    public void TestBarbecueMode()
    {



        flamethrower.useWeapon(new ArrayList<Integer>(){{add(4);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);add(involvedPlayer3);}});
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());

    }
}
