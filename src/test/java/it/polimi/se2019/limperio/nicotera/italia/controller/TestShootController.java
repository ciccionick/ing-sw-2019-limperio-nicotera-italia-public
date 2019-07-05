package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestShootController {
    private Game game;
    private Controller controller;
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    @Before
    public void setUp(){
        game = Game.instanceOfGame();
        controller = new Controller(game);
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.createPlayer("player4", false, 4, "GREEN");
        //game.createPlayer("terminator", false, 5, "PURPLE");
        player1 = controller.findPlayerWithThisNickname("player1");
        player2 = controller.findPlayerWithThisNickname("player2");
        player3 = controller.findPlayerWithThisNickname("player3");
        player4 = controller.findPlayerWithThisNickname("player4");

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
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player4.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        ElectroScythe card = new ElectroScythe();
        player1.getPlayerBoard().getWeaponsOwned().add(card);
        card.setOwnerOfCard(player1);
        controller.getShootController().replyToRequestToShoot(message);
        player1.assignDamage(ColorOfFigure_Square.YELLOW, 7);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player4.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.getShootController().replyToRequestToShoot(message);
        controller.getShootController().replyWithUsableEffectsOfThisWeapon("Electroscythe", player1);
        PlasmaGun plasmaGun = new PlasmaGun();
        Player player1 = controller.findPlayerWithThisNickname("player1");
        player1.getPlayerBoard().getWeaponsOwned().clear();
        player1.getPlayerBoard().getWeaponsOwned().add(plasmaGun);
        plasmaGun.setOwnerOfCard(player1);
        controller.getShootController().replyWithUsableEffectsOfThisWeapon("Plasma gun", player1);
        controller.getShootController().sendPlayerBoardEventAfterTargetingOrTagback(player1, player2, true);

    }

    @Test
    public void handleRequestToUseEffectTest(){
        RequestToUseEffect request = new RequestToUseEffect("", "player1");
        player1.getPlayerBoard().getWeaponsOwned().clear();
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(player1.getPositionOnTheMap());
        player3.setPositionOnTheMap(player1.getPositionOnTheMap());
        player4.setPositionOnTheMap(player1.getPositionOnTheMap());
        ElectroScythe card = new ElectroScythe();
        card.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(card);
        controller.getShootController().setWeaponToUse(card);
        request.setNumOfEffect(0);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        Assert.assertEquals(1, player2.getPlayerBoard().getDamages().size());
        Assert.assertEquals(1, player3.getPlayerBoard().getDamages().size());
        Assert.assertEquals(1, player4.getPlayerBoard().getDamages().size());
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        Assert.assertEquals(3, player2.getPlayerBoard().getDamages().size());
        Assert.assertEquals(3, player3.getPlayerBoard().getDamages().size());
        Assert.assertEquals(3, player4.getPlayerBoard().getDamages().size());
        cleanAll(card);
        Cyberblade cyberblade = new Cyberblade();
        player1.getPlayerBoard().getWeaponsOwned().add(cyberblade);
        cyberblade.setOwnerOfCard(player1);
        request.setNumOfEffect(1);
        controller.getShootController().setWeaponToUse(cyberblade);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(3);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(cyberblade);
        Sledgehammer sledgehammer = new Sledgehammer();
        player1.getPlayerBoard().getWeaponsOwned().add(sledgehammer);
        sledgehammer.setOwnerOfCard(player1);
        request.setNumOfEffect(1);
        controller.getShootController().setWeaponToUse(sledgehammer);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(sledgehammer);
        Shotgun shotgun = new Shotgun();
        shotgun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(shotgun);
        request.setNumOfEffect(1);
        controller.getShootController().setWeaponToUse(shotgun);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(shotgun);
        Shockwave shockwave = new Shockwave();
        shockwave.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(shockwave);
        controller.getShootController().setWeaponToUse(shockwave);
        request.setNumOfEffect(1);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        Assert.assertEquals(1, player3.getPlayerBoard().getDamages().size());
        Assert.assertEquals(1, player2.getPlayerBoard().getDamages().size());
        cleanAll(shockwave);
        Furnace furnace = new Furnace();
        furnace.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(furnace);
        controller.getShootController().setWeaponToUse(furnace);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][1]);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(furnace);
        LockRifle lockRifle = new LockRifle();
        lockRifle.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(lockRifle);
        controller.getShootController().setWeaponToUse(lockRifle);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(lockRifle);
        Zx2 zx2 = new Zx2();
        zx2.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(zx2);
        controller.getShootController().setWeaponToUse(zx2);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(zx2);
        MachineGun machineGun = new MachineGun();
        machineGun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(machineGun);
        controller.getShootController().setWeaponToUse(machineGun);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        controller.getShootController().getTypeOfAttack().add(3);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(3);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(machineGun);
        GranadeLauncher granadeLauncher = new GranadeLauncher();
        granadeLauncher.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(granadeLauncher);
        controller.getShootController().setWeaponToUse(granadeLauncher);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(granadeLauncher);
        PlasmaGun plasmaGun = new PlasmaGun();
        plasmaGun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(plasmaGun);
        controller.getShootController().setWeaponToUse(plasmaGun);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(3);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(plasmaGun);
        Railgun railgun = new Railgun();
        railgun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(railgun);
        controller.getShootController().setWeaponToUse(railgun);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(railgun);
        Heatseeker heatseeker = new Heatseeker();
        heatseeker.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(heatseeker);
        controller.getShootController().setWeaponToUse(heatseeker);
        player4.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[2][3]);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(heatseeker);
        RocketLauncher rocketLauncher = new RocketLauncher();
        rocketLauncher.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(rocketLauncher);
        controller.getShootController().setWeaponToUse(rocketLauncher);
        request.setNumOfEffect(1);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        controller.getShootController().getPlayersAttacked().add(player2);
        controller.getShootController().getInvolvedPlayers().add(new InvolvedPlayer(player2, 1, null));
        request.setNumOfEffect(3);
        controller.getShootController().setOriginalSquareOfTheTarget(player2.getPositionOnTheMap());
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(rocketLauncher);
        Hellion hellion = new Hellion();
        hellion.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(hellion);
        controller.getShootController().setWeaponToUse(hellion);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(8);
        try{
            controller.getShootController().handleRequestToUseEffect(request);
        }
        catch (IllegalArgumentException e){
            Assert.assertTrue(true);
        }
        cleanAll(hellion);
        Whisper whisper = new Whisper();
        whisper.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(whisper);
        controller.getShootController().setWeaponToUse(whisper);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(whisper);
        Thor thor = new Thor();
        thor.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(thor);
        controller.getShootController().setWeaponToUse(thor);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(3);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(thor);
        Flamethrower flamethrower = new Flamethrower();
        flamethrower.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(flamethrower);
        controller.getShootController().setWeaponToUse(flamethrower);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(flamethrower);
        PowerGlove powerGlove = new PowerGlove();
        powerGlove.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(powerGlove);
        controller.getShootController().setWeaponToUse(powerGlove);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(powerGlove);
        TractorBeam tractorBeam = new TractorBeam();
        tractorBeam.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(tractorBeam);
        controller.getShootController().setWeaponToUse(tractorBeam);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(4);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(tractorBeam);
        VortexCannon vortexCannon = new VortexCannon();
        vortexCannon.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(vortexCannon);
        controller.getShootController().setWeaponToUse(vortexCannon);
        request.setNumOfEffect(1);
        controller.getShootController().handleRequestToUseEffect(request);
        request.setNumOfEffect(2);
        controller.getShootController().handleRequestToUseEffect(request);
        cleanAll(vortexCannon);





    }

    @Test
    public void setPlayersInInvolvedPlayersTest(){
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        controller.getShootController().getTypeOfAttack().add(1);
        controller.getShootController().setNeedToStoreOriginalSquare(true);
        controller.getShootController().setNeedToChooseASquare(true);
        TractorBeam tractorBeam = new TractorBeam();
        tractorBeam.setOwnerOfCard(player3);
        player3.getPlayerBoard().getWeaponsOwned().add(tractorBeam);
        controller.getShootController().setWeaponToUse(tractorBeam);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player3.getPlayerBoard().getWeaponsOwned().clear();
        RocketLauncher rocketLauncher = new RocketLauncher();
        rocketLauncher.setOwnerOfCard(player3);
        player3.getPlayerBoard().getWeaponsOwned().add(rocketLauncher);
        controller.getShootController().setWeaponToUse(rocketLauncher);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player3.getPlayerBoard().getWeaponsOwned().clear();
        Shotgun shotgun = new Shotgun();
        shotgun.setOwnerOfCard(player3);
        player3.getPlayerBoard().getWeaponsOwned().add(shotgun);
        controller.getShootController().setWeaponToUse(shotgun);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        players.remove(player2);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player3.getPlayerBoard().getWeaponsOwned().clear();
        GranadeLauncher granadeLauncher = new GranadeLauncher();
        granadeLauncher.setOwnerOfCard(player3);
        controller.getShootController().setWeaponToUse(granadeLauncher);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player3.getPlayerBoard().getWeaponsOwned().clear();
        players.clear();
        players.add(player3);
        players.add(player4);
        controller.getShootController().setNeedToChooseASquare(false);
        controller.getShootController().setNeedToChooseAPlayer(true);
        controller.getShootController().getPlayersAttacked().add(player3);
        controller.getShootController().getPlayersAttacked().add(player4);
        Shockwave shockwave = new Shockwave();
        shockwave.setOwnerOfCard(player1);
        controller.getShootController().setWeaponToUse(shockwave);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        player4.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player1.getPlayerBoard().getWeaponsOwned().add(shockwave);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player1.getPlayerBoard().getWeaponsOwned().clear();
        Railgun railgun = new Railgun();
        controller.getShootController().setNeedToChooseAPlayer(true);
        railgun.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(railgun);
        controller.getShootController().setWeaponToUse(railgun);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player1.getPlayerBoard().getWeaponsOwned().clear();
        Flamethrower flamethrower = new Flamethrower();
        controller.getShootController().setNeedToChooseAPlayer(true);
        flamethrower.setOwnerOfCard(player1);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player4.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        controller.getShootController().setWeaponToUse(flamethrower);
        controller.getShootController().setPlayersInInvolvedPlayers(players);
        player1.getPlayerBoard().getWeaponsOwned().clear();
        PowerGlove powerGlove = new PowerGlove();
        controller.getShootController().setNeedToChooseAPlayer(true);
        powerGlove.setOwnerOfCard(player1);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        player1.getPlayerBoard().getWeaponsOwned().add(powerGlove);
        controller.getShootController().setWeaponToUse(powerGlove);
        controller.getShootController().setPlayersInInvolvedPlayers(players);

    }

    @Test
    public void handleDiscardPowerUpToPayAnEffectTest(){
        player1.drawPowerUpCard(game.getBoard().getPowerUpDeck().getPowerUpCards().get(0));
        String name = player1.getPlayerBoard().getPowerUpCardsOwned().get(0).getName();
        ColorOfCard_Ammo color = player1.getPlayerBoard().getPowerUpCardsOwned().get(0).getColor();
        DiscardPowerUpCard event = new DiscardPowerUpCard("", "player1");
        event.setNameOfPowerUpCard(name);
        event.setColorOfCard(color);
        controller.getShootController().getColorsNotEnough().add(player1.getPlayerBoard().getPowerUpCardsOwned().get(0).getColor());
        controller.getShootController().handleDiscardPowerUpToPayAnEffect(event);

    }

    @Test
    public void setSquareInInvolvedPlayersTest(){
        cleanAll(new ElectroScythe());
        SelectionSquare selectionSquare = new SelectionSquare("", "player1", 0,0);
        Furnace furnace = new Furnace();
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[1][0]);
        furnace.setOwnerOfCard(player1);
        player1.getPlayerBoard().getWeaponsOwned().add(furnace);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        controller.getShootController().setWeaponToUse(furnace);
        controller.getShootController().getTypeOfAttack().add(1);
        controller.getShootController().setSquareInInvolvedPlayers(selectionSquare);
        Assert.assertEquals(1, player2.getPlayerBoard().getDamages().size());
        Assert.assertEquals(1, player2.getPlayerBoard().getDamages().size());
        cleanAll(furnace);
        controller.getShootController().setNeedToChooseASquare(true);
        Flamethrower flamethrower = new Flamethrower();
        flamethrower.setOwnerOfCard(player1);
        controller.getShootController().getInvolvedPlayers().clear();
        player1.getPlayerBoard().getWeaponsOwned().add(flamethrower);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.getShootController().getTypeOfAttack().add(4);
        for(int i=0; i<9;i++){
            if(player1.getPlayerBoard().getAmmo().get(i).getColor().toString().equals("YELLOW"))
                player1.getPlayerBoard().getAmmo().get(i).setIsUsable(true);
        }
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][1]);
        player3.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][2]);
        SelectionSquare selectionSquare1 = new SelectionSquare("", "player1", 0, 1);
        selectionSquare1.setSelectionSquareForShootAction(true);
        controller.getShootController().setWeaponToUse(flamethrower);
        controller.getShootController().setNeedToChooseAPlayer(false);
        player1.getPlayerBoard().getDamages().clear();
        //controller.getShootController().setSquareInInvolvedPlayers(selectionSquare1);
        controller.update(selectionSquare1);
        Assert.assertEquals(0, player1.getPlayerBoard().getDamages().size());
        Assert.assertEquals(1, player3.getPlayerBoard().getDamages().size());
        Assert.assertEquals(2, player2.getPlayerBoard().getDamages().size());
        cleanAll(furnace);
        controller.getShootController().getInvolvedPlayers().clear();
        controller.getShootController().getTypeOfAttack().add(1);
        controller.getShootController().getInvolvedPlayers().add(new InvolvedPlayer(null, controller.getShootController().getTypeOfAttack().get(0), game.getBoard().getMap().getMatrixOfSquares()[0][1]));
        VortexCannon vortexCannon = new VortexCannon();
        vortexCannon.setOwnerOfCard(player1);
        SelectionSquare selectionSquare2 = new SelectionSquare("", "player1", 0, 1);
        player1.getPlayerBoard().getWeaponsOwned().add(vortexCannon);
        controller.getShootController().setNeedToChooseAPlayer(true);
        controller.getShootController().setSquareInInvolvedPlayers(selectionSquare2);
    }

    @Test
    public void useTargetingTest(){
        for(PowerUpCard card : game.getBoard().getPowerUpDeck().getPowerUpCards()){
            if(card.getName().equals("Targeting scope") && card.getColor().toString().equals("RED")) {
                player2.drawPowerUpCard(card);
                break;
            }
        }
        game.setPlayerOfTurn(2);
        for(int i=0; i<player2.getPlayerBoard().getAmmo().size();i++){
            if(player2.getPlayerBoard().getAmmo().get(i).getColor().toString().equals("RED"))
                player2.getPlayerBoard().getAmmo().get(i).setIsUsable(true);
        }
        game.getBoard().getPowerUpDeck().getPowerUpCards().remove(player2.getPlayerBoard().getPowerUpCardsOwned().get(0));
        game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(player2.getPlayerBoard().getPowerUpCardsOwned().get(0));
        DiscardPowerUpCard discardPowerUpCard = new DiscardPowerUpCard("", "player2");
        discardPowerUpCard.setToReload(false);
        discardPowerUpCard.setToCatch(false);
        discardPowerUpCard.setToPayAnEffect(false);
        discardPowerUpCard.setNameOfPowerUpCard("Targeting scope");
        discardPowerUpCard.setColorOfCard(player2.getPlayerBoard().getPowerUpCardsOwned().get(0).getColor());
        discardPowerUpCard.setToTagback(true);
        player1.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        player2.setPositionOnTheMap(game.getBoard().getMap().getMatrixOfSquares()[0][0]);
        controller.getShootController().getPlayersAttacked().add(player1);
        controller.getShootController().handleDiscardPowerUpToUseTargeting(discardPowerUpCard);
        DiscardAmmoOrPowerUpToPayTargeting discardAmmoOrPowerUpToPayTargeting = new DiscardAmmoOrPowerUpToPayTargeting("", "player2");
        discardAmmoOrPowerUpToPayTargeting.setRedAmmo(true);
        ServerEvent.AliasCard aliasCard = new ServerEvent.AliasCard("Targeting scope", "", ColorOfCard_Ammo.RED);
        discardAmmoOrPowerUpToPayTargeting.setPowerUpCard(aliasCard);
        controller.getShootController().handlePaymentForTargeting(discardAmmoOrPowerUpToPayTargeting);
        Assert.assertEquals(1, player1.getPlayerBoard().getDamages().size());
        for(PowerUpCard card : game.getBoard().getPowerUpDeck().getPowerUpCards()){
            if(card.getName().equals("Targeting scope") && card.getColor().toString().equals("RED")) {
                player2.drawPowerUpCard(card);
                break;
            }
        }
        game.getBoard().getPowerUpDeck().getPowerUpCards().remove(player2.getPlayerBoard().getPowerUpCardsOwned().get(0));
        game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(player2.getPlayerBoard().getPowerUpCardsOwned().get(0));
        ChoosePlayer choosePlayer = new ChoosePlayer("", "player2");
        choosePlayer.setNameOfPlayer("player1");
        choosePlayer.setToTargeting(true);
        choosePlayer.setForAttack(false);
        choosePlayer.setToNewton(false);
        controller.getShootController().handleUseOfTargeting(choosePlayer);
        Assert.assertEquals(2, player1.getPlayerBoard().getDamages().size());

    }


    private void cleanAll(WeaponCard card){
        player2.getPlayerBoard().getDamages().clear();
        player3.getPlayerBoard().getDamages().clear();
        player4.getPlayerBoard().getDamages().clear();
        player1.getPlayerBoard().getWeaponsOwned().clear();
        card = null;
        controller.getShootController().setTypeOfAttack();
    }
}
