package it.polimi.se2019.limperio.nicotera.italia.model;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBoard {
    private Player player = new Player("player", true, 1, ColorOfFigure_Square.BLUE);
    private Board board;
    @Before
    public void setUp(){
        board = Board.instanceOfBoard();
        board.createMap(1);
    }
    @After
    public void cleanUp(){
        board.getMap().setInstanceOfMapForTesting();
        player = null;
    }

    @Test
     public void TestSetAndGetPositionOnTheMap(){
        player.setPositionOnTheMap(board.getMap().getMatrixOfSquares()[0][0]);
        assertEquals(player.getPositionOnTheMap(), board.getMap().getMatrixOfSquares()[0][0]);
        player.setPositionOnTheMap(board.getMap().getMatrixOfSquares()[2][2]);
        assertEquals(player.getPositionOnTheMap(),board.getMap().getMatrixOfSquares()[2][2]);

    }






}