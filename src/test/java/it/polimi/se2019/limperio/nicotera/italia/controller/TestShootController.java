package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.ElectroScythe;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.WeaponCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShootController {
    private Game game;
    private Controller controller;
    @Before
    public void setUp(){
        game = Game.instanceOfGame();
        controller = new Controller(game);
        game.setController(this.controller);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "GREEN");
        game.initializeGame(true, 1, false);
    }

    @After
    public void cleanUp(){
        game.setInstanceOfGameNullForTesting();
    }

    @Test
    public void replyToRequestToShootTest(){
        ClientEvent message = new ClientEvent("", "player1");
        game.setPlayerOfTurn(1);
        controller.findPlayerWithThisNickname("player1").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("player2").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("player3").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.findPlayerWithThisNickname("player4").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        ElectroScythe card = new ElectroScythe();
        controller.findPlayerWithThisNickname("player1").getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(controller.findPlayerWithThisNickname("player1"));
        controller.getShootController().replyToRequestToShoot(message);
        controller.findPlayerWithThisNickname("player1").assignDamage(ColorOfFigure_Square.YELLOW, 7);
        controller.findPlayerWithThisNickname("player2").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.findPlayerWithThisNickname("player3").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.findPlayerWithThisNickname("player4").setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.getShootController().replyToRequestToShoot(message);
        //controller.getShootController().replyWithUsableEffectsOfThisWeapon("ElectroScyte", controller.findPlayerWithThisNickname("player1"));

    }
}
