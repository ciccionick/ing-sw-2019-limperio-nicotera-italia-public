package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Whisper of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestWhisper {

    /**
     * attacking player
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives
     */
    private Player player2 = new Player("player2", false, 2, ColorOfFigure_Square.RED);

    private Map map;
    private Whisper whisper;
    private InvolvedPlayer involvedPlayer2;





    @Before
    public void setUp() {
        map = Map.instanceOfMap(2);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][2]);
        player1.createPlayerBoard();
        player2.createPlayerBoard();
        whisper = new Whisper();
        whisper.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(whisper);
        involvedPlayer2 = new InvolvedPlayer(player2, null , null);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void Test() {
        whisper.useWeapon(0, new ArrayList<InvolvedPlayer>() {{ add(involvedPlayer2); }});

        assertEquals(player2.getPlayerBoard().getDamages().size(), 3);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(1), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getDamages().get(2), ColorOfFigure_Square.BLUE);
        assertEquals(player2.getPlayerBoard().getMarks().size(), 1);
        assertEquals(player2.getPlayerBoard().getMarks().get(0), ColorOfFigure_Square.BLUE);


    }


}
