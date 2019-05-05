package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCyberblade {

    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    private Player player3= new Player("player2", false, 2, ColorOfFigure_Square.YELLOW);
    private Cyberblade cyberblade;
    private InvolvedPlayer involvedPlayer1;
    private InvolvedPlayer involvedPlayer2;
    private InvolvedPlayer involvedPlayer3;
    int newdemagesBlue=0;

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
        involvedPlayer1= new InvolvedPlayer(player2, new ArrayList<Integer>(){{add(1);}}, player2.getPositionOnTheMap());
        involvedPlayer2= new InvolvedPlayer(player3, new ArrayList<Integer>(){{add(3);}}, player2.getPositionOnTheMap());
        involvedPlayer3= new InvolvedPlayer(player1, new ArrayList<Integer>(){{add(2);}}, map.getMatrixOfSquares()[1][0]);


    }


    @Test
    public void TestBasicEffect(){


        newdemagesBlue=0;
        cyberblade.useWeapon(new ArrayList<Integer>(){{add(1);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}});
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);

        for(ColorOfFigure_Square demagex: player2.getPlayerBoard().getDamages())
        {
            if(demagex==ColorOfFigure_Square.BLUE) newdemagesBlue++;
        }
        assertEquals(newdemagesBlue++, 2);


    }

    @Test
    public void TestWithSliceAndDice()
    {
        newdemagesBlue=0;
        cyberblade.useWeapon(new ArrayList<Integer>(){{add(3);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer2);}});
        assertEquals(player3.getPlayerBoard().getDamages().size(), 2);

        for(ColorOfFigure_Square demagex: player3.getPlayerBoard().getDamages())
        {
            if(demagex==ColorOfFigure_Square.BLUE) newdemagesBlue++;
        }
        assertEquals(newdemagesBlue++, 2);
    }


    @Test
    public void TestWithShadowstepEffect()
    {

        cyberblade.useWeapon(new ArrayList<Integer>(){{add(2);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer3);}});
        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[1][0]);

    }



}
