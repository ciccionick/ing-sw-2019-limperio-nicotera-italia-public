package it.polimi.se2019.limperio.nicotera.italia.controller;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * Handles the reload action of a player, checking if he can reload and the way to pay the price to reload his weapons.
 * @author Francesco Nicotera.
 */
class ReloadController {
    /**
     * The reference of the game.
     */
    Game game;
    /**
     * The reference of the controller.
     */
    Controller controller;
    /**
     * The list of the colors of which the player has not enough ammo.
     */
    private ArrayList<ColorOfCard_Ammo> colorsNotEnough;
    /**
     * The list of power up cards usable to pay discarding them.
     */
    private ArrayList<PowerUpCard> powerUpToDiscardToPay = new ArrayList<>();
    /**
     * It's true if the reload is at the end of the turn like in the normal mode, false if is before to shoot as happens during the frenzy mode.
     */
    private boolean isReloadingAtTheEndOfAction = false;
    /**
     * The weapon card to reload.
     */
    private WeaponCard weaponCardToReload;
    /**
     * It's true if the player has chosen that he doesn't want to reload anymore, false otherwise.
     */
    private boolean doesntWantToReloadAnymore = false;


    /**
     * Constructor that initializes the game and controller references.
     */
     ReloadController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


    /**
     * Check if a weapon is reloadable with the ammo and power up cards that is owner owns.
     * @param weaponCard Weapon card that player could want to reload.
     * @return True if the weapon is reloadable, false otherwise.
     */
    boolean isThisWeaponReloadable(WeaponCard weaponCard) {
        Player player = weaponCard.getOwnerOfCard();
        int numOfRedAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), RED);
        int numOfBlueAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), BLUE);
        int numOfYellowAmmoRequired = controller.getCatchController().frequencyAmmoInPrice(weaponCard.getPriceToReload(), YELLOW);
        return controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, RED, true) >= numOfRedAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, BLUE, true) >= numOfBlueAmmoRequired && controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, YELLOW, true)>=numOfYellowAmmoRequired;

    }

    /**
     * Check if a player can reload checking if there is at least one weapon reloadable.
     * @param player Player that could want to reload.
     * @return True if the player can reload, false otherwise.
     */
    boolean playerCanReload (Player player){
         for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
             if(!weaponCard.isLoad() && isThisWeaponReloadable(weaponCard)) {
                 weaponCardToReload = null;
                 return true;
             }
         }
         return false;
    }

    /**
     * Sends the request to reload after to have checked that the player really can.
     * @param player Player that could want to reload.
     * @param isReloadingAtTheEndOfAction True if the reload is at the end of the action, false otherwise.
     */
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

    /**
     * Handles the request by the client to reload a weapon.
     * @param player Player owner of the weapon to reload.
     * @param card Weapon card to reload.
     */
    void handleRequestToReloadWeapon(Player player, WeaponCard card){
         if(card==null){
             doesntWantToReloadAnymore = true;
             handleTheEndOfReload(player);
             return;
         }
         weaponCardToReload = card;
         if(controller.getCatchController().isAffordableOnlyWithAmmo(player, weaponCardToReload.getPriceToReload())){
             player.reload(weaponCardToReload, null );
             handleTheEndOfReload(player);
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
                handleTheEndOfReload(player);
             }
         }

    }

    /**
     * Handles the end of the reload phase when there are no more weapon reloadable or the player has decided to not reload anymore.
     * @param player Player that has completed the reload action.
     */
    private void handleTheEndOfReload(Player player) {
        if (weaponCardToReload != null)
            sendPlayerBoardEventAfterReload(player);
        if (playerCanReload(player) && !doesntWantToReloadAnymore)
            sendRequestToReload(player, isReloadingAtTheEndOfAction);
        else {
            doesntWantToReloadAnymore = false;
            if (isReloadingAtTheEndOfAction) {
                controller.handleTheEndOfAnAction(false);
            } else {
                controller.getShootController().sendRequestToChooseAWeapon(player);
            }
        }
    }

    /**
     * Sends the request to discard a power up card to pay the price of reload of a weapon.
     * @param player Player that is trying to reload a weapon.
     * @param colorsNotEnough List of colors of which there are not enough ammo.
     */
    private void sendRequestToDiscardPowerUpCard(Player player, ArrayList<ColorOfCard_Ammo> colorsNotEnough) {
        RequestToDiscardPowerUpCard requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCard();
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose a powerUp to discard to reload:");
        requestToDiscardPowerUpCardToPay.setToReload();
        if(!colorsNotEnough.isEmpty()) {
            requestToDiscardPowerUpCardToPay.setPowerUpCards(controller.getCatchController().getPowerUpCardToChooseForDiscard(player, colorsNotEnough.remove(0)));
            game.notify(requestToDiscardPowerUpCardToPay);
        }
        else{
            player.reload(weaponCardToReload,powerUpToDiscardToPay);
            handleTheEndOfReload(player);
        }
    }

    /**
     * Sends the player board event after a successfully reload.
     * @param player Player that has done the reload.
     */
    private void sendPlayerBoardEventAfterReload(Player player){
        PlayerBoardEvent playerBoardEvent = new PlayerBoardEvent();
        playerBoardEvent.setNicknameInvolved(player.getNickname());
        playerBoardEvent.setNicknames(game.getListOfNickname());
        playerBoardEvent.setMessageForInvolved("You have reloaded " + weaponCardToReload.getName());
        playerBoardEvent.setMessageForOthers(player.getNickname() + " has reloaded "+ weaponCardToReload.getName() );
        playerBoardEvent.setPlayerBoard(player.getPlayerBoard());
        game.notify(playerBoardEvent);
    }

    /**
     * Handles the discard of the power up card chosen by the player to pay the price of the weapon card.
     * @param event Event generated by the client.
     */
    void handleDiscardOfPowerUpCard(DiscardPowerUpCard event){
         powerUpToDiscardToPay.add(controller.getCatchController().findPowerUpCard(event.getNameOfPowerUpCard(), event.getColorOfCard(), weaponCardToReload.getOwnerOfCard()));
         sendRequestToDiscardPowerUpCard(weaponCardToReload.getOwnerOfCard(), colorsNotEnough);
    }
}
