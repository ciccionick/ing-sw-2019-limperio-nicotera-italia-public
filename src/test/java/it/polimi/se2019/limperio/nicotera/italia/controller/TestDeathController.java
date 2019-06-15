package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import org.junit.Before;
import org.junit.Test;

public class TestDeathController {
    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);
    DeathController deathController= new DeathController(game, controller);



    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(false);
        game.initializeGame(false, 1, false);
    }




}
