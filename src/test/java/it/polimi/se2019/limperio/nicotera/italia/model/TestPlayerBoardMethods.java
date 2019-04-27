package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.PlayerBoard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Collections;

import static org.junit.Assert.*;


public class TestPlayerBoardMethods {

    private  Player player1 = new Player("player1", true, 1, ColorOfFigure_Square.YELLOW );
    @BeforeEach
    public void creationBoard(){
        player1.createPlayerBoard();
    }

    @Test
    public void assignDamageTest(){
        player1.assignDamage(ColorOfFigure_Square.BLUE, 3);
        assertEquals("test primo danno", 3, player1.getPlayerBoard().getDamages().size());
        player1.assignDamage(ColorOfFigure_Square.YELLOW, 2);
        player1.assignDamage(ColorOfFigure_Square.RED, 1);
        assertEquals("test danni totali",6, player1.getPlayerBoard().getDamages().size());
        int blue = Collections.frequency(player1.getPlayerBoard().getDamages(), ColorOfFigure_Square.BLUE);
        int yellow = Collections.frequency(player1.getPlayerBoard().getDamages(), ColorOfFigure_Square.YELLOW);
        int red = Collections.frequency(player1.getPlayerBoard().getDamages(), ColorOfFigure_Square.RED);
        assertEquals(3, blue);
        assertEquals(2, yellow);
        assertEquals(1, red);
    }
    @Test
    public void assignMarksTest() {
        player1.assignMarks(ColorOfFigure_Square.BLUE, 1);
        player1.assignMarks(ColorOfFigure_Square.YELLOW, 1);
        player1.assignMarks(ColorOfFigure_Square.RED, 1);
        assertEquals(3, player1.getPlayerBoard().getMarks().size());
        int blue = Collections.frequency(player1.getPlayerBoard().getMarks(), ColorOfFigure_Square.BLUE);
        int yellow = Collections.frequency(player1.getPlayerBoard().getMarks(), ColorOfFigure_Square.YELLOW);
        int red = Collections.frequency(player1.getPlayerBoard().getMarks(), ColorOfFigure_Square.RED);
        assertEquals(1, red);
        assertEquals(1, yellow);
        assertEquals(1, blue);
    }
    @Test
    public void isDeathTest(){
        player1.assignDamage(ColorOfFigure_Square.YELLOW, 5);
        assertFalse(player1.getPlayerBoard().isDeath());
        player1.assignDamage(ColorOfFigure_Square.BLUE, 5);
        assertTrue(player1.getPlayerBoard().isDeath());
    }

}
