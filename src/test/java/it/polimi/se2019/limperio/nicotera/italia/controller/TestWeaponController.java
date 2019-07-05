package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWeaponController {

    Game game = Game.instanceOfGame();

    Controller controller = new Controller(game);

    WeaponController weaponController= new WeaponController(game, controller);


    @Before
    public void setUp(){
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "PURPLE");
        game.setGameOver(true);
        game.initializeGame(false, 2, false);
    }
    @After
    public void cleanUp(){
        game.setInstanceOfGameNullForTesting();

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


    @Test
    public  void isThisWeaponUsableTest()
    {

        Square[][] matrix = game.getBoard().getMap().getMatrixOfSquares();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[i].length; j++) {
                if(matrix[i][j]!=null)
                    game.getBoard().getMap().getMatrixOfSquares()[i][j].getPlayerOnThisSquare().clear();

            }
        }
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        WeaponCard card= new Cyberblade();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        assertTrue(weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][2]);
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Sledgehammer();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Zx2();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new MachineGun();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new GranadeLauncher();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        game.getPlayers().get(0).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        game.getPlayers().get(1).setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][1]);
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new PlasmaGun();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new VortexCannon();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        //assertTrue(!weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Hellion();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Thor();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));

        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Flamethrower();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        for(int i=0;i<9;i++)
            game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new RocketLauncher();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        for(int i=0;i<9;i++)
            game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Shotgun();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));


        for(int i=0;i<9;i++)
            game.getPlayers().get(0).getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().clear();
        card= new Furnace();
        game.getPlayers().get(0).getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(game.getPlayers().get(0));
        assertTrue(!weaponController.isThisWeaponUsable(card,0));




    }
}
