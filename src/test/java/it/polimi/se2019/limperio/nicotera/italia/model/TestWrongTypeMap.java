package it.polimi.se2019.limperio.nicotera.italia.model;

import org.junit.Assert;
import org.junit.Test;

public class TestWrongTypeMap {

    Map map;

    @Test
    public void testWrongTypeMap (){
        try {
            map = Map.instanceOfMap(5);
        }
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
        }
    }

}
