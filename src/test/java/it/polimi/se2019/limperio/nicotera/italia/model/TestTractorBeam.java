package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for TractorBeam of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestTractorBeam {

    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damages and is moved for three squares with basicEffect
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives three damages is moved for two squares with InPunisherModeand
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.PURPLE);




    private Map map;
    private TractorBeam tractorBeam;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;




    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][3]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);




        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();


        tractorBeam = new TractorBeam();
        tractorBeam.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(tractorBeam);
        involvedPlayer2 = new InvolvedPlayer(player2, null , map.getMatrixOfSquares()[1][2]);
        involvedPlayer3= new InvolvedPlayer(player3, null , null);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicMode() {
        tractorBeam.useWeapon(new ArrayList<Integer>() {{ add(1);}}, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(map.getMatrixOfSquares()[1][2], player2.getPositionOnTheMap());
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);


    }

    @Test
    public void TestInPunisherMode()
    {
        tractorBeam.useWeapon(new ArrayList<Integer>() {{ add(4);}}, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer3);}});

        assertEquals(player3.getPlayerBoard().getDamages().size(), 3);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(2), ColorOfFigure_Square.BLUE);
    }

}
