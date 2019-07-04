package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;


import org.junit.After;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for Cyberblade of Weapon Card
 *
 * @author Giuseppe Italia
 */

public class TestCyberblade {

    /**
     * attacking player and who movies by one square with ShadowstepEffect
     */
    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    /**
     * player who receives two damage with BasicEffect
     */
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    /**
     * player who receives two damage with SliceAndDice
     */
    private Player player3= new Player("player2", false, 2, ColorOfFigure_Square.YELLOW);
    private Cyberblade cyberblade;
    private InvolvedPlayer involvedPlayer1;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    int newdamagesBlue=0;

    private Map map;

    @Before
    public void setUp()
    {
        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        map = Map.instanceOfMap(1);
        cyberblade= new Cyberblade();
        cyberblade.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(cyberblade);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[0][0]);
        involvedPlayer1= new InvolvedPlayer(player2, 1, player2.getPositionOnTheMap());
        involvedPlayer2= new InvolvedPlayer(player3, 3, player2.getPositionOnTheMap());
        involvedPlayer3= new InvolvedPlayer(player1, 2, map.getMatrixOfSquares()[1][0]);


    }

    @After
    public void deleteMap(){
        map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestBasicEffect(){


        newdamagesBlue=0;
        cyberblade.useWeapon(1, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);

        for(ColorOfFigure_Square damagex: player2.getPlayerBoard().getDamages())
        {
            if(damagex==ColorOfFigure_Square.BLUE) newdamagesBlue++;
        }
        assertEquals(newdamagesBlue++, 2);


    }

    @Test
    public void TestWithSliceAndDice()
    {
        newdamagesBlue=0;
        cyberblade.useWeapon(3, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);}});
        assertEquals(player3.getPlayerBoard().getDamages().size(), 2);

        for(ColorOfFigure_Square damagex: player3.getPlayerBoard().getDamages())
        {
            if(damagex==ColorOfFigure_Square.BLUE) newdamagesBlue++;
        }
        assertEquals(newdamagesBlue++, 2);
    }


    @Test
    public void TestWithShadowstepEffect()
    {

        cyberblade.useWeapon(2, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer3);}});
        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[1][0]);

    }



}
