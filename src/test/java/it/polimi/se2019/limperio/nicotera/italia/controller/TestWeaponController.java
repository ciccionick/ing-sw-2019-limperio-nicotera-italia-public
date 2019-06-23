package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestWeaponController {

    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);

    WeaponController weaponController= new WeaponController(game, controller);


    @Before
    public void setUp(){
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(true);
        game.initializeGame(false, 2, false);
    }

    @Test
    public void addSquaresForCardinalDirectionsTest()
    {

        ArrayList<Square> squares= new ArrayList<>();
        weaponController.addSquaresForCardinalDirections(game.getBoard().getMap().getMatrixOfSquares()[1][1], squares, 1, true);
        squares.remove(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
        assertEquals(squares.size(), 5);
    }


  /*  @Test
    public void canReloadTest()
    {
        for(int i=0;i<9;i++)
        {
            game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        }
        WeaponCard card= new ElectroScythe();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!controller.getReloadController().isThisWeaponReloadable(card));

    }*/
}
