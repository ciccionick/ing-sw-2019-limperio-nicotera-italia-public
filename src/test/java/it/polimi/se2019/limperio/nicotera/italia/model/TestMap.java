package it.polimi.se2019.limperio.nicotera.italia.model;


import org.junit.*;

import static org.junit.Assert.fail;


public class TestMap {
     Map map;


    @After
    public void deleteMap(){
       if(map != null) map.setInstanceOfMapForTesting();
    }


    @Test
    public void TestMapType1 () {
        map = Map.instanceOfMap(1);
        Assert.assertEquals(map.getMatrixOfSquares()[0][0].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertTrue(map.getMatrixOfSquares()[0][0].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][1].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][2].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][1].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][0].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][2].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[1][3].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][3].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][1].getColor(), ColorOfFigure_Square.GREY);
        Assert.assertTrue(map.getMatrixOfSquares()[2][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][2].getColor(), ColorOfFigure_Square.GREY);
        Assert.assertTrue(map.getMatrixOfSquares()[2][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isSpawn());
        Assert.assertNull(map.getMatrixOfSquares()[0][3]);
        Assert.assertNull(map.getMatrixOfSquares()[2][0]);
    }

    @Test
    public void testMapType2(){
        map = Map.instanceOfMap(2);
        Assert.assertEquals(map.getMatrixOfSquares()[0][0].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertTrue(map.getMatrixOfSquares()[0][0].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][1].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][2].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][3].getColor(), ColorOfFigure_Square.GREEN);
        Assert.assertTrue(map.getMatrixOfSquares()[0][3].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][3].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][0].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][1].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][2].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[1][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[1][3].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][3].isSpawn());
        Assert.assertNull(map.getMatrixOfSquares()[2][0]);
        Assert.assertEquals(map.getMatrixOfSquares()[2][1].getColor(), ColorOfFigure_Square.GREY);
        Assert.assertTrue(map.getMatrixOfSquares()[2][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][2].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[2][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isSpawn());
    }

    @Test
    public void testMapType3(){
        map = Map.instanceOfMap(3);
        Assert.assertEquals(map.getMatrixOfSquares()[0][0].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[0][0].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][1].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][2].getColor(), ColorOfFigure_Square.BLUE);
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[0][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[0][3].getColor(), ColorOfFigure_Square.GREEN);
        Assert.assertTrue(map.getMatrixOfSquares()[0][3].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[0][3].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][0].getColor(), ColorOfFigure_Square.RED);
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[1][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][1].getColor(), ColorOfFigure_Square.PURPLE);
        Assert.assertTrue(map.getMatrixOfSquares()[1][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][2].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[1][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[1][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[1][3].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[1][3].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][0].getColor(), ColorOfFigure_Square.GREY);
        Assert.assertTrue(map.getMatrixOfSquares()[2][0].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][0].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][1].getColor(), ColorOfFigure_Square.GREY);
        Assert.assertTrue(map.getMatrixOfSquares()[2][1].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][1].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][2].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[2][2].isHasDoor());
        Assert.assertFalse(map.getMatrixOfSquares()[2][2].isSpawn());
        Assert.assertEquals(map.getMatrixOfSquares()[2][3].getColor(), ColorOfFigure_Square.YELLOW);
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isHasDoor());
        Assert.assertTrue(map.getMatrixOfSquares()[2][3].isSpawn());
    }

    @Test
    public void testWrongTypeMap (){
        try {
            map = Map.instanceOfMap(4);
        }
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
        }
    }

}