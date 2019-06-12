package it.polimi.se2019.limperio.nicotera.italia.controller;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * This class handles the request of shoot by a player to another one.
 */
public class ShootController {

    private final Game game;
    private Controller controller;
    private ArrayList<InvolvedPlayer> involvedPlayers = new ArrayList<>();
    private ArrayList<Integer> typeOfAttack = new ArrayList<>();
    private WeaponCard weaponToUse;
    private ColorOfCard_Ammo[] priceToPay;
    private ArrayList<ColorOfCard_Ammo> colorsNotEnough;
    private ArrayList<PowerUpCard> powerUpCardToDiscardToPay = new ArrayList<>();
    private ColorOfCard_Ammo ammoForPayTargeting;
    private ArrayList<Player> playersAttacked = new ArrayList<>();
    private boolean alreadyAskedToUseTargeting = false;
    private PowerUpCard targetingScopeToUse;
    private int numberOfPlayerInWaitingForTagback = 0;

    public ShootController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


     void replyToRequestToShoot(ClientEvent message) {
        typeOfAttack = new ArrayList<>();
        involvedPlayers = new ArrayList<>();
        weaponToUse = null;
        priceToPay = null;
        powerUpCardToDiscardToPay = new ArrayList<>();
        playersAttacked = new ArrayList<>();
        alreadyAskedToUseTargeting = false;
        ammoForPayTargeting = null;
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        if(game.isInFrenzy() && player.getPosition()>=game.getFirstInFrenzyMode() || !game.isInFrenzy() && player.isOverSixDamage()){
            //seleziona quadrati a 1 distanza in cui si puo spostare e in cui puo utilizzare almeno un arma
            return;
        }
        if(game.isInFrenzy() && player.getPosition()<game.getFirstInFrenzyMode()){
            //seleziona quadrati a 2 distanza in cui si puo spostare e in cui puo utilizzare almeno un arma
        }

        else
            sendRequestToChooseAWeapon(player);
    }

    private void sendRequestToChooseAWeapon(Player player){
        boolean[] canUseWeapon = new boolean[3];
        canUseWeapon[0] = false;
        canUseWeapon[1] = false;
        canUseWeapon[2] = false;
        int i = 0;
        for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
            canUseWeapon[i] = controller.checkIfThisWeaponIsUsable(weaponCard, 0);
            i++;
        }

        RequestToChooseWeapon requestToChooseWeapon = new RequestToChooseWeapon();
        requestToChooseWeapon.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn()-1).getNickname());
        requestToChooseWeapon.setMessageForInvolved("Choose one weapon enable to shoot");
        requestToChooseWeapon.setCanUseWeapon1(canUseWeapon[0]);
        requestToChooseWeapon.setCanUseWeapon2(canUseWeapon[1]);
        requestToChooseWeapon.setCanUseWeapon3(canUseWeapon[2]);

        game.notify(requestToChooseWeapon);

    }

    private WeaponCard findWeaponCardWithThisName(String name, Player player){
        for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()){
            if(weaponCard.getName().equals(name))
                return weaponCard;
        }
        throw new IllegalArgumentException();
    }

     void replyWithUsableEffectsOfThisWeapon(ClientEvent message) {
        String nameOfCard = ((RequestToUseWeaponCard)message).getWeaponWantUse().getName();
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        WeaponCard weaponCard = findWeaponCardWithThisName(nameOfCard, player);
        weaponToUse = weaponCard;
         ArrayList<Integer> usableEffectsForThisWeapon = controller.getWeaponController().getUsableEffectsForThisWeapon(weaponCard);
         RequestToChooseAnEffect requestToChooseAnEffect = new RequestToChooseAnEffect();
         if(typeOfAttack.isEmpty())
            requestToChooseAnEffect.setMessageForInvolved("Choose, to start, one of these effects:");
         else{
             for(Integer effect : typeOfAttack){
                     usableEffectsForThisWeapon.remove(effect);
             }
             requestToChooseAnEffect.setOneEffectAlreadyChoosen(true);
             requestToChooseAnEffect.setMessageForInvolved("Choose another effect or press on 'END ACTION'");
             requestToChooseAnEffect.setCanTerminateAction(true);
         }
         requestToChooseAnEffect.setNameOfCard(weaponCard.getName());
         requestToChooseAnEffect.setNicknameInvolved(player.getNickname());
         requestToChooseAnEffect.setUsableEffects(usableEffectsForThisWeapon);
         message.getMyVirtualView().update(requestToChooseAnEffect);
    }

    public ArrayList<InvolvedPlayer> getInvolvedPlayers() {
        return involvedPlayers;
    }

    public ArrayList<Integer> getTypeOfAttack() {
        return typeOfAttack;
    }

     void handleRequestToUseEffect(RequestToUseEffect message) {
         Player player = controller.findPlayerWithThisNickname(message.getNickname());
         Square squareOfPlayer = player.getPositionOnTheMap();
         switch (weaponToUse.getName()){
             case "Electroscythe":
                 typeOfAttack.add(message.getNumOfEffect());
                 involvedPlayers.add(new InvolvedPlayer(null, message.getNumOfEffect(), squareOfPlayer));
                 playersAttacked = squareOfPlayer.getPlayerOnThisSquare();
                 playersAttacked.remove(player);
                if (message.getNumOfEffect() == 1) {
                    player.shoot(1, weaponToUse, involvedPlayers, null, null);
                    weaponToUse.setLoad(false);
                    sendPlayerBoardEvent(playersAttacked);
                    handleSendRequestAfterShoot(player, playersAttacked);
                }
                else {
                    priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                    handlePaymentForEffect(player, playersAttacked);
                }
                break;
             case "Cyberblade":
             case "Sledgehammer":
             case "Shotgun":
             case "Shockwave":
             case "Furnace":
             case "Lock rifle":
             case "Zx-2":
             case "Machine gun":
             case "Granade launcher":
             case "Plasma gun":
             case "Railgun":
             case "Heatseeker":
             case "Rocket launcher":
             case "Hellion":
             case "Whisper":
             case "THOR":
             case "Flamethrower":
             case "Power glove":
             case "Tractor beam":
             case "Vortex cannon":

             default:
                 throw  new IllegalArgumentException();
         }
    }

    private void sendPlayerBoardEvent(ArrayList<Player> playersAttacked) {

        String messageForAttacked = weaponToUse.getOwnerOfCard().getNickname() + " has attacked: ";
        String messageForAttacker = "You have attacked ";
        for(Player player : playersAttacked){
            if(playersAttacked.indexOf(player)==playersAttacked.size()-1) {
                messageForAttacked = messageForAttacked.concat(player.getNickname());
                messageForAttacker = messageForAttacker.concat(player.getNickname());
            }
            else {
                messageForAttacked = messageForAttacked.concat(player.getNickname() + ", ");
                messageForAttacker = messageForAttacker.concat(player.getNickname() + ", ");
            }
        }
        messageForAttacked = messageForAttacked.concat("\nCheck player boards to see their new situation!");

        for(Player player : game.getPlayers()){
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            if(player.equals(weaponToUse.getOwnerOfCard()))
                pbEvent.setMessageForInvolved(messageForAttacker);
            else
                pbEvent.setMessageForInvolved(messageForAttacked);
            pbEvent.setNicknames(game.getListOfNickname());
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            pbEvent.setNicknameInvolved(player.getNickname());
            game.notify(pbEvent);
        }

    }

    private void handlePaymentForEffect(Player player, ArrayList<Player> playersAttacked) {
        ArrayList<ColorOfCard_Ammo> colorsToRemove = new ArrayList<>();
        if(controller.getCatchController().isAffordableOnlyWithAmmo(player, priceToPay)){
            player.shoot(typeOfAttack.get(typeOfAttack.size()-1), weaponToUse, getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size()-1)), priceToPay, null);
            sendPlayerBoardEvent(playersAttacked);
            handleSendRequestAfterShoot(player, playersAttacked);
        }
        else{
            colorsNotEnough = controller.getCatchController().getColorsOfAmmoNotEnough(player,priceToPay);
            for(ColorOfCard_Ammo color : colorsNotEnough){
                if(controller.getCatchController().frequencyOfPowerUpUsableByPlayer(player, color)==1) {
                    powerUpCardToDiscardToPay.add(controller.getCatchController().findPowerUpOfThisColor(player, color));
                    colorsToRemove.add(color);
                }
            }

            for(ColorOfCard_Ammo colorToRemove : colorsToRemove){
                colorsNotEnough.remove(colorToRemove);
            }

            if(!colorsNotEnough.isEmpty()){
                sendRequestToDiscardPowerUpCard(player, colorsNotEnough);
                return;
            }
            player.shoot(typeOfAttack.get(typeOfAttack.size()-1), weaponToUse, getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size()-1)), priceToPay, powerUpCardToDiscardToPay);
            sendPlayerBoardEvent(playersAttacked);
            handleSendRequestAfterShoot(player, playersAttacked);
        }

    }

    private void sendRequestToDiscardPowerUpCard(Player player, ArrayList<ColorOfCard_Ammo> colorsNotEnough) {
        RequestToDiscardPowerUpCardToPay requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCardToPay();
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which power up card you want to discard to pay for the effect");
        requestToDiscardPowerUpCardToPay.setToPayAnEffect(true);
        if(!colorsNotEnough.isEmpty()) {
            requestToDiscardPowerUpCardToPay.setPowerUpCards(controller.getCatchController().getPowerUpCardToChooseForDiscard(player, colorsNotEnough.remove(0)));
            game.notify(requestToDiscardPowerUpCardToPay);
        }
        else{
            player.shoot(typeOfAttack.get(typeOfAttack.size()-1), weaponToUse, getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size()-1)), priceToPay, powerUpCardToDiscardToPay);
            sendPlayerBoardEvent(playersAttacked);
            handleSendRequestAfterShoot(player, playersAttacked);
        }
    }

    void handleDiscardPowerUpToPayAnEffect(ClientEvent message){
        DiscardPowerUpCardAsAmmo event = (DiscardPowerUpCardAsAmmo) message;
        powerUpCardToDiscardToPay.add(controller.getCatchController().findPowerUpCard(event.getNameOfPowerUpCard(), event.getColorOfCard()));
        sendRequestToDiscardPowerUpCard(controller.findPlayerWithThisNickname(event.getNickname()), colorsNotEnough);
    }



    private ArrayList<InvolvedPlayer> getInvolvedPlayersForThisEffect(int effect) {
        ArrayList<InvolvedPlayer> involvedPlayersForThisEffect = new ArrayList<>();
        for(InvolvedPlayer involvedPlayer : getInvolvedPlayers()){
            if(involvedPlayer.getEffect()==effect)
                involvedPlayersForThisEffect.add(involvedPlayer);
        }
        return involvedPlayersForThisEffect;
    }

    private void handleSendRequestAfterShoot(Player player, ArrayList<Player> playersAttacked){
        weaponToUse.setLoad(false);
        PlayerBoardEvent pbEvent = new PlayerBoardEvent();
        pbEvent.setNicknames(game.getListOfNickname());
        pbEvent.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
        pbEvent.setPlayerBoard(weaponToUse.getOwnerOfCard().getPlayerBoard());
        game.notify(pbEvent);

        ArrayList<Player> playersCouldUseTagback = new ArrayList<>();
        for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getName().equals("Targeting scope") && !alreadyAskedToUseTargeting){
                sendRequestToUseTargeting(player);
                return;
            }
        }
        for(Player playerAttacked : playersAttacked){
            if(hasTagBack(playerAttacked) && controller.getWeaponController().getVisiblePlayers(0, playerAttacked, 0).contains(player))
                playersCouldUseTagback.add(playerAttacked);
        }

        if(playersCouldUseTagback.isEmpty()){
            controller.handleTheEndOfAnAction();
        }
        else{
            sendRequestToUseTagbackGranade(playersCouldUseTagback);
        }
    }

    private boolean hasTagBack(Player playerAttacked) {
        for(PowerUpCard powerUpCard : playerAttacked.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getName().equals("Tagback granade"))
                return true;
        }
        return false;
    }

    private void sendRequestToUseTargeting(Player player) {
        RequestToDiscardPowerUpCardToPay requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCardToPay();
        requestToDiscardPowerUpCardToPay.setToTargeting(true);
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which Targeting scope you want to use. \nIf you don't want you can press 'No one'");
        ArrayList<ServerEvent.AliasCard> powerUpCardToChoose = new ArrayList<>();
        for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getName().equals("Targeting scope"))
                powerUpCardToChoose.add(new ServerEvent.AliasCard(powerUpCard.getName(), powerUpCard.getDescription(), powerUpCard.getColor()));
        }
        requestToDiscardPowerUpCardToPay.setPowerUpCards(powerUpCardToChoose);
        game.notify(requestToDiscardPowerUpCardToPay);
    }


     void handleDiscardPowerUpToUseTargeting(DiscardPowerUpCardAsAmmo message) {
         int indexOfCardToRemove = 0;
        if(message.getNameOfPowerUpCard()==null){
            alreadyAskedToUseTargeting=true;
            handleSendRequestAfterShoot(weaponToUse.getOwnerOfCard(), playersAttacked);
        }
        else{
            RequestToPayWithAmmoOrPUCard requestToPayWithAmmoOrPUCard = new RequestToPayWithAmmoOrPUCard();
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(weaponToUse.getOwnerOfCard(), ColorOfCard_Ammo.BLUE,false)>0)
                requestToPayWithAmmoOrPUCard.setBlueAmmo(true);
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(weaponToUse.getOwnerOfCard(), ColorOfCard_Ammo.RED,false)>0)
                requestToPayWithAmmoOrPUCard.setRedAmmo(true);
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(weaponToUse.getOwnerOfCard(), ColorOfCard_Ammo.YELLOW,false)>0)
                requestToPayWithAmmoOrPUCard.setYellowAmmo(true);
            for(PowerUpCard powerUpCard : weaponToUse.getOwnerOfCard().getPlayerBoard().getPowerUpCardsOwned()){
                requestToPayWithAmmoOrPUCard.getPowerUpCards().add(new ServerEvent.AliasCard(powerUpCard.getName(), powerUpCard.getDescription(), powerUpCard.getColor()));
            }
            for(ServerEvent.AliasCard card : requestToPayWithAmmoOrPUCard.getPowerUpCards()){
                if(card.getColor().equals(message.getColorOfCard()) && card.getName().equals(message.getNameOfPowerUpCard())){
                    indexOfCardToRemove = requestToPayWithAmmoOrPUCard.getPowerUpCards().indexOf(card);
                    break;
                }
            }
            targetingScopeToUse = controller.getCatchController().findPowerUpCard(message.getNameOfPowerUpCard(), message.getColorOfCard());
            requestToPayWithAmmoOrPUCard.getPowerUpCards().remove(indexOfCardToRemove);
            requestToPayWithAmmoOrPUCard.setNicknameInvolved(message.getNickname());
            requestToPayWithAmmoOrPUCard.setMessageForInvolved("Choose an ammo or a power up card to pay the effect of targeting scope");
            message.getMyVirtualView().update(requestToPayWithAmmoOrPUCard);
        }
    }


    void handlePaymentForTargeting(DiscardAmmoOrPowerUpToPayTargeting message){
        if(message.isBlueAmmo())
            ammoForPayTargeting = ColorOfCard_Ammo.BLUE;
        if(message.isRedAmmo())
            ammoForPayTargeting = ColorOfCard_Ammo.RED;
        if(message.isYellowAmmo())
            ammoForPayTargeting = ColorOfCard_Ammo.YELLOW;
        if(message.getPowerUpCard()!=null){
            powerUpCardToDiscardToPay = new ArrayList<>();
            powerUpCardToDiscardToPay.add(controller.getCatchController().findPowerUpCard(message.getPowerUpCard().getName(),message.getPowerUpCard().getColor()));
        }

        RequestToChooseAPlayer requestToChooseAPlayer = new RequestToChooseAPlayer();
        requestToChooseAPlayer.setToUseTargeting(true);
        for(Player player : playersAttacked){
            requestToChooseAPlayer.getNameOfPlayers().add(player.getNickname());
        }
        requestToChooseAPlayer.setNicknameInvolved(message.getNickname());
        requestToChooseAPlayer.setMessageForInvolved("Choose a player to use Targeting scope");
        message.getMyVirtualView().update(requestToChooseAPlayer);

    }

    void handleUseOfTargeting(ChoosePlayer message){
        Player playerAttacker = controller.findPlayerWithThisNickname(message.getNickname());
        Player playerAttacked = controller.findPlayerWithThisNickname(message.getNameOfPlayer());
        if(ammoForPayTargeting!=null)
            playerAttacker.useTargetingScope(playerAttacked,targetingScopeToUse,ammoForPayTargeting,null);
        else
            playerAttacker.useTargetingScope(playerAttacked,targetingScopeToUse,null,powerUpCardToDiscardToPay.get(0));
        sendPlayerBoardEventAfterTargeting(playerAttacker,controller.findPlayerWithThisNickname(message.getNameOfPlayer()));
        handleSendRequestAfterShoot(playerAttacker,playersAttacked);
    }

    private void sendPlayerBoardEventAfterTargeting(Player playerAttacker, Player playerAttacked) {

        String messageForAttacker = "You have used Targeting scope on " + playerAttacked.getNickname();
        String messageForAttacked = playerAttacker.getNickname() + " has used Targeting scope on you";
        String messageForOthers = playerAttacker.getNickname() + " has used Targeting scope on " + playerAttacked.getNickname();
        for(Player player : game.getPlayers()){
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknames(game.getListOfNickname());
            pbEvent.setNicknameInvolved(player.getNickname());
            pbEvent.setPlayerBoard(player.getPlayerBoard());
            if(player.equals(playerAttacker))
                pbEvent.setMessageForInvolved(messageForAttacker);
            if(player.equals(playerAttacked))
                pbEvent.setMessageForInvolved(messageForAttacked);
            if(!player.equals(playerAttacked)&&!player.equals(playerAttacker))
                pbEvent.setMessageForInvolved(messageForOthers);
            game.notify(pbEvent);

        }
    }

    private void sendRequestToUseTagbackGranade(ArrayList<Player> playersCouldUseTagback) {
        ArrayList<ServerEvent.AliasCard> tagbackGranadeCards = new ArrayList<>();
        for(Player player : playersCouldUseTagback){
            for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
                if(powerUpCard.getName().equalsIgnoreCase("Tagback granade"))
                    tagbackGranadeCards.add(new ServerEvent.AliasCard(powerUpCard.getName(), powerUpCard.getDescription(), powerUpCard.getColor()));
            }
            RequestToDiscardPowerUpCardToPay newEvent = new RequestToDiscardPowerUpCardToPay();
            newEvent.setNicknameInvolved(player.getNickname());
            newEvent.setMessageForInvolved("Choose a Tagback granade card to use against who attacked you or 'No one' if you want to avoid this");
            newEvent.setPowerUpCards(tagbackGranadeCards);
            newEvent.setToTagback(true);
            numberOfPlayerInWaitingForTagback++;
            game.notify(newEvent);
        }
    }

    private void handleRequestToUseTagbackGranade(ClientEvent message){
        //se ha deciso di non usarla decrementi numberOfPlayerinwaiting e se questo va a 0 chiami handleEndOfTheAction altrimenti null
        //se invece ha deciso di usarla la usi e controlli se ne ha altre e nel caso chiami di nuovo sendRequestToUseTagbackGranade
        //altrimenti decrementi e controlli se sei arrivato a 0, in tal caso chiami handleOfTheaction.
    }
}
