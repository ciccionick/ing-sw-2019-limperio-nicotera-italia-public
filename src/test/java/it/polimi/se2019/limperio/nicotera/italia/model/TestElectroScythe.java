package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestElectroScythe {

    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    private Player player3= new Player("player3", false, 3, ColorOfFigure_Square.YELLOW);
    private ElectroScythe electroScythe;
    /*private InvolvedPlayer involvedPlayer;
    private InvolvedPlayer involvedPlayer3;
    private InvolvedPlayer involvedPlayer4;*/
    private Map map;


    @Before
    public void setUp()
    {


        player1.createPlayerBoard();
        player2.createPlayerBoard();
        player3.createPlayerBoard();
        map= Map.instanceOfMap(1);
        electroScythe= new ElectroScythe();
        electroScythe.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(electroScythe);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player3.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        /*involvedPlayer= new InvolvedPlayer(null, null , null);*/
        /*involvedPlayer3= new InvolvedPlayer(player3, new ArrayList<Integer>(){{add(1);}}, null);*/
    }

    @Test
    public void TestBasicMode()
    {



        electroScythe.useWeapon(new ArrayList<Integer>(){{add(1);}}, null);
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 1);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());

    }

    @Test
    public void TestInReaperMode()
    {



        electroScythe.useWeapon(new ArrayList<Integer>(){{add(4);}}, null);
        assertEquals(player1.getPlayerBoard().getDamages().size(), 0);
        assertEquals(player2.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player2.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());
        assertEquals(player3.getPlayerBoard().getDamages().size(), 2);
        assertEquals(player3.getPlayerBoard().getDamages().get(0), player1.getColorOfFigure());

    }


}