package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestController {
   private Game game = Game.instanceOfGame();
   private Controller controller = new Controller(game);

   @Test
    public void DistanceOfManhattanTest() {
       int[] start = {0, 0};
       int[] end = {1, 2};
       assertEquals(3, controller.distanceOfManhattan(start, end));
   }

}
