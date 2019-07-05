package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestReloadController {
    private Game game;
    private Controller controller;
    private Player player;

    @Before
    public void setUp(){
        game =Game.instanceOfGame();
        controller = new Controller(game);
        game.createPlayer("player", true, 1, "BLUE");
        player = controller.findPlayerWithThisNickname("player");
        game.initializeGame(false, 1, false);
    }

    @After
    public void cleanUp(){
        game.setInstanceOfGameNullForTesting();
        game.getBoard().getKillShotTrack().setInstanceOfKillShotTrackNullForTesting();

    }

    @Test
    public void handleRequestToReloadWeaponTest(){
        ElectroScythe electroScythe = new ElectroScythe();
        electroScythe.setOwnerOfCard(player);
        player.getPlayerBoard().getWeaponsOwned().add(electroScythe);
        electroScythe.setLoad(false);
        controller.getReloadController().handleRequestToReloadWeapon(player, null);
        for(int i=0; i<9; i++){
            if(player.getPlayerBoard().getAmmo().get(i).getColor().toString().equals("BLUE"))
                player.getPlayerBoard().getAmmo().get(i).setIsUsable(true);
            else
                player.getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        }
        controller.getReloadController().handleRequestToReloadWeapon(player, electroScythe);
        Assert.assertTrue(electroScythe.isLoad());
        electroScythe.setLoad(false);
        for(int i=0; i<9; i++) {
             player.getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        }
        PowerUpCard powerUpCard1 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
        for(PowerUpCard powerUpCard : game.getBoard().getPowerUpDeck().getPowerUpCards()){
            if (powerUpCard.getColor().toString().equals("BLUE")) {
                powerUpCard1 = powerUpCard;
                game.getBoard().getPowerUpDeck().getPowerUpCards().remove(powerUpCard);
                game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(powerUpCard);
                break;
            }
        }
        powerUpCard1.setInTheDeckOfSomePlayer(true);
        player.getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard1);
        powerUpCard1.setOwnerOfCard(player);
        controller.getReloadController().handleRequestToReloadWeapon(player, electroScythe);
        electroScythe.setLoad(false);
        PowerUpCard powerUpCard2 = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
        for(PowerUpCard powerUpCard : game.getBoard().getPowerUpDeck().getPowerUpCards()){
            if (powerUpCard.getColor().toString().equals("BLUE")) {
                powerUpCard2 = powerUpCard;
                game.getBoard().getPowerUpDeck().getPowerUpCards().remove(powerUpCard);
                game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(powerUpCard);
                break;
            }
        }
        powerUpCard2.setInTheDeckOfSomePlayer(true);
        player.getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard1);
        player.getPlayerBoard().getPowerUpCardsOwned().add(powerUpCard2);
        for(int i=0; i<9; i++) {
            player.getPlayerBoard().getAmmo().get(i).setIsUsable(false);
        }
        controller.getReloadController().handleRequestToReloadWeapon(player,electroScythe);

    }
}
