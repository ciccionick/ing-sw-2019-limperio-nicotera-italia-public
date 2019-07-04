package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Shockwave of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestShockwave {


    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives one damage with baseEffect or one damage with TsunamiMode
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);
    /**
     * player who receives one damage with baseEffect or one damage with TsunamiMode
     */
    private Player player3 = new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    /**
     * player who receives one damage with baseEffect or one damage with TsunamiMode
     */

    private Player player4 = new Player("player4", false, 4, ColorOfFigure_Square.GREY);
    /**
     * player who receives one damage with TsunamiMode
     */

    private Player player5 = new Player("player5", false, 5, ColorOfFigure_Square.PURPLE);


    private Map map;
    private Shockwave shockwave;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;




    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[2][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[2][2]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[2][2]);
        player4.setPositionOnTheMap(map.getMatrixOfSquares()[2][3]);
        player5.setPositionOnTheMap(map.getMatrixOfSquares()[2][3]);
        player1.getPositionOnTheMap().setCardinalSquare(map.getMatrixOfSquares()[2][2],map.getMatrixOfSquares()[2][3],null,null);

        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        player4.createPlayerBoard();
        player5.createPlayerBoard();
        shockwave = new Shockwave();
        shockwave.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(shockwave);
        involvedPlayer2 = new InvolvedPlayer(player2, 1 ,null);
        involvedPlayer3 = new InvolvedPlayer(player3, 1 ,null);
        involvedPlayer4 = new InvolvedPlayer(player4, 1 ,null);



    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }

    @Test
    public void TestBasicEffect() {
        shockwave.useWeapon(1, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2);add(involvedPlayer3);add(involvedPlayer4); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);

    }

    @Test
    public void TestTsunamiMode()
    {
        shockwave.useWeapon(4, null);

        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player4.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player4.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player5.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player5.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
    }
}
