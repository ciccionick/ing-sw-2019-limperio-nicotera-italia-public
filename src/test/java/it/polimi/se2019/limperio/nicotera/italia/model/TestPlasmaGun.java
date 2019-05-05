package it.polimi.se2019.limperio.nicotera.italia.model;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestPlasmaGun {

    private Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.BLUE);
    private Player player2= new Player("player2", false, 2, ColorOfFigure_Square.GREY);
    private PlasmaGun plasmaGun;
    private InvolvedPlayer involvedPlayer;
    private InvolvedPlayer involvedPlayer1;
    private Map map;

    @Before
    public void setUp()
    {
        player1.createPlayerBoard();
        player2.createPlayerBoard();
        map = Map.instanceOfMap(1);
        plasmaGun= new PlasmaGun();
        plasmaGun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(plasmaGun);
        player1.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        player2.setPositionOnTheMap(map.getMatrixOfSquares()[1][1]);
        involvedPlayer= new InvolvedPlayer(player2, new ArrayList<Integer>(){{add(1);add(3);}}, player2.getPositionOnTheMap());
        involvedPlayer1= new InvolvedPlayer(player1, new ArrayList<Integer>(){{add(2);}}, map.getMatrixOfSquares()[1][0]);

    }

    @Test
    public void TestBasicAndWithchargedshot()
    {
        plasmaGun.useWeapon(new ArrayList<Integer>(){{add(1);add(3);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer);}} );
        assertEquals(player2.getPlayerBoard().getDamages().size(), 3);
    }

    @Test
    public void TestWithPhaseGlide()
    {
        plasmaGun.useWeapon(new ArrayList<Integer>(){{add(2);}}, new ArrayList<InvolvedPlayer>(){{add(involvedPlayer1);}} );
        assertEquals(player1.getPositionOnTheMap(), map.getMatrixOfSquares()[1][0]);
    }





}

