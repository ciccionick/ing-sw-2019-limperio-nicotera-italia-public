package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardAsAmmo;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCardToPay;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

class ReloadController {
    Game game;
    Controller controller;
    private ArrayList<ColorOfCard_Ammo> colorsNotEnough;
    private ArrayList<PowerUpCard> powerUpToDiscardToPay = new ArrayList<>();
    private boolean isReloadingAtTheEndOfAction = false;
    private WeaponCard weaponCardToReload;


     ReloadController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


    boolean isThisWeaponReloadable(WeaponCard weaponCard) {
        Player player = weaponCard.getOwnerOfCard();
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED, true) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE, true) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW, true)>=numOfYellowAmmoRequired;

    }

    boolean playerCanReload (Player player){
         for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
             if(!weaponCard.isLoad() && isThisWeaponReloadable(weaponCard)) {
                 weaponCardToReload = null;
                 return true;
             }
         }
         return false;
    }

     void sendRequestToReload(Player player, boolean isReloadingAtTheEndOfAction) {
         this.isReloadingAtTheEndOfAction = isReloadingAtTheEndOfAction;
         ArrayList<ServerEvent.AliasCard> weaponCards = new ArrayList<>();
         for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
             if(!weaponCard.isLoad() && isThisWeaponReloadable(weaponCard))
                 weaponCards.add(new ServerEvent.AliasCard(weaponCard.getName(), weaponCard.getDescription(), weaponCard.getColor()));
         }

         RequestSelectionWeaponToReload requestSelectionOfWeapon = new RequestSelectionWeaponToReload("", weaponCards);
         requestSelectionOfWeapon.setNicknameInvolved(player.getNickname());
         requestSelectionOfWeapon.setMessageForInvolved("Choose a weapon you want to reload");
         game.notify(requestSelectionOfWeapon);
    }

    void handleRequestToReloadWeapon(Player player, WeaponCard card){
         weaponCardToReload = card;
         if(controller.getCatchController().isAffordableOnlyWithAmmo(player, weaponCardToReload.getPriceToReload())){
             player.reload(weaponCardToReload, null );
             handleTheEndOfReaload(player);
         }
         else{
             ArrayList<ColorOfCard_Ammo> colorsToRemove = new ArrayList<>();
             colorsNotEnough = controller.getCatchController().getColorsOfAmmoNotEnough(player,weaponCardToReload.getPriceToReload());
             for(ColorOfCard_Ammo color : colorsNotEnough){
                 if(controller.getCatchController().frequencyOfPowerUpUsableByPlayer(player, color)==1) {
                     powerUpToDiscardToPay.add(controller.getCatchController().findPowerUpOfThisColor(player, color));
                     colorsToRemove.add(color);
                 }
             }

             for(ColorOfCard_Ammo colorToRemove : colorsToRemove){
                 colorsNotEnough.remove(colorToRemove);
             }

             if(!colorsNotEnough.isEmpty()){
                 sendRequestToDiscardPowerUpCard(player, colorsNotEnough);
             }
             else{
                 player.reload(weaponCardToReload, powerUpToDiscardToPay);
                handleTheEndOfReaload(player);
             }
         }

    }

    private void handleTheEndOfReaload(Player player) {
        sendPlayerBoardEventAfterRealod(player);
        if(playerCanReload(player))
            sendRequestToReload(player, isReloadingAtTheEndOfAction);
        else{
            if(isReloadingAtTheEndOfAction){
                controller.handleTheEndOfAnAction();
            }
            else{
                controller.getShootController().sendRequestToChooseAWeapon(player);
            }
        }
    }

    private void sendRequestToDiscardPowerUpCard(Player player, ArrayList<ColorOfCard_Ammo> colorsNotEnough) {
        RequestToDiscardPowerUpCardToPay requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCardToPay();
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose a powerUp to discard to reload:");
        requestToDiscardPowerUpCardToPay.setToReload(true);
        if(!colorsNotEnough.isEmpty()) {
            requestToDiscardPowerUpCardToPay.setPowerUpCards(controller.getCatchController().getPowerUpCardToChooseForDiscard(player, colorsNotEnough.remove(0)));
            game.notify(requestToDiscardPowerUpCardToPay);
        }
        else{
            player.reload(weaponCardToReload,powerUpToDiscardToPay);
            handleTheEndOfReaload(player);
        }
    }

    private void sendPlayerBoardEventAfterRealod(Player player){
        PlayerBoardEvent playerBoardEvent = new PlayerBoardEvent();
        playerBoardEvent.setNicknameInvolved(player.getNickname());
        playerBoardEvent.setNicknames(game.getListOfNickname());
        playerBoardEvent.setMessageForInvolved("You have reloaded " + weaponCardToReload.getName());
        playerBoardEvent.setMessageForOthers(player.getNickname() + " has reloaded "+ weaponCardToReload.getName() );
        playerBoardEvent.setPlayerBoard(player.getPlayerBoard());
        game.notify(playerBoardEvent);
    }

    void handleDiscardOfPowerUpCard(DiscardPowerUpCardAsAmmo event){
         powerUpToDiscardToPay.add(controller.getCatchController().findPowerUpCard(event.getNameOfPowerUpCard(), event.getColorOfCard(), weaponCardToReload.getOwnerOfCard()));
         sendRequestToDiscardPowerUpCard(weaponCardToReload.getOwnerOfCard(), colorsNotEnough);
    }
}
