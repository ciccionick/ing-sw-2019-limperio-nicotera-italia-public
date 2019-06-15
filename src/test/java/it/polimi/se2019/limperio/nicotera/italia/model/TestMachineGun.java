package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for MachineGun of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestMachineGun {
    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage with basicEffect and another damage with withFocusShot attack
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives one damage with basicEffect and another damage with TurretTripod attack
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    /**
     * player player who receives one damage with TurretTripod attack
     */
    private Player player4 = new Player("player4", false, 4, ColorOfFigure_Square.GREY);

    private Map map;
    private MachineGun machineGun;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;


    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[0][1]);


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();
        machineGun = new MachineGun();
        machineGun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(machineGun);
        involvedPlayer2 = new InvolvedPlayer(player2, 2, null);
        involvedPlayer3 = new InvolvedPlayer(player3, 1, null);
        involvedPlayer4 = new InvolvedPlayer(player4, 3, null);

    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

   /* @Test
    public void Test() {
        machineGun.useWeapon(new ArrayList<Integer>() {{ add(1);add(2); add(3);}}, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2);add(involvedPlayer3);add(involvedPlayer4); }});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
    }*/


}