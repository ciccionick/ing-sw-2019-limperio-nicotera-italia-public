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
    private ArrayList<Player> playersAreChoosingForTagback = new ArrayList<>();
    private boolean isForTerminator = false;
    private boolean doesntWantToContinueToShoot = false;

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
        isForTerminator = false;
         doesntWantToContinueToShoot = false;
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        if(game.isInFrenzy()  || !game.isInFrenzy() && player.isOverSixDamage()) {
            ArrayList<Square> squares;
            if (game.isInFrenzy() && player.getPosition() >= game.getFirstInFrenzyMode() || !game.isInFrenzy() && player.isOverSixDamage())
               squares = getSquaresFromWherePlayerCanAttack(player,1);
            else
                squares = getSquaresFromWherePlayerCanAttack(player,2);

            if(squares.size()==1 && player.getPositionOnTheMap().equals(squares.get(0)))
                sendRequestToChooseAWeapon(player);

            else {
                RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Choose a square where move yourself before to shoot");
                requestSelectionSquareForAction.setSquaresReachable(squares);
                requestSelectionSquareForAction.setNicknameInvolved(player.getNickname());
                requestSelectionSquareForAction.setBeforeToShoot(true);
                message.getMyVirtualView().update(requestSelectionSquareForAction);
            }
        }
        else
            sendRequestToChooseAWeapon(player);
    }



     void sendRequestToChooseAWeapon(Player player){
        boolean[] canUseWeapon = new boolean[3];
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

     void replyWithUsableEffectsOfThisWeapon(String nameOfWeaponCard, Player player ) {
        if(weaponToUse == null) {
            weaponToUse = findWeaponCardWithThisName(nameOfWeaponCard, player);
        }
         ArrayList<Integer> usableEffectsForThisWeapon = controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse);
         RequestToChooseAnEffect requestToChooseAnEffect = new RequestToChooseAnEffect();
         if(typeOfAttack.isEmpty())
            requestToChooseAnEffect.setMessageForInvolved("Choose, to start, one of these effects:");
         else{
             for(Integer effect : typeOfAttack){
                     usableEffectsForThisWeapon.remove(effect);
             }
             requestToChooseAnEffect.setOneEffectAlreadyChosen(true);
             requestToChooseAnEffect.setMessageForInvolved("Choose another effect or press on 'END ACTION'");
             requestToChooseAnEffect.setCanTerminateAction(true);
         }
         requestToChooseAnEffect.setNameOfCard(nameOfWeaponCard);
         if(!typeOfAttack.isEmpty())
             requestToChooseAnEffect.setOneEffectAlreadyChosen(true);
         requestToChooseAnEffect.setNicknameInvolved(player.getNickname());
         requestToChooseAnEffect.setUsableEffects(usableEffectsForThisWeapon);
         game.notify(requestToChooseAnEffect);
    }

    public ArrayList<InvolvedPlayer> getInvolvedPlayers() {
        return involvedPlayers;
    }

    public ArrayList<Integer> getTypeOfAttack() {
        return typeOfAttack;
    }

     void handleRequestToUseEffect(RequestToUseEffect message) {
        if(message.getNumOfEffect()==0){
            doesntWantToContinueToShoot=true;
            handleSendRequestAfterShoot(weaponToUse.getOwnerOfCard(), playersAttacked, false);
        }
         Player player = controller.findPlayerWithThisNickname(message.getNickname());
         Square squareOfPlayer = player.getPositionOnTheMap();
         typeOfAttack.add(message.getNumOfEffect());
         switch (weaponToUse.getName()){
             case "Electroscythe":
                 involvedPlayers.add(new InvolvedPlayer(null, message.getNumOfEffect(), squareOfPlayer));
                 playersAttacked = squareOfPlayer.getPlayerOnThisSquare();
                 playersAttacked.remove(player);
                if (message.getNumOfEffect() == 1) {
                    player.shoot(1, weaponToUse, involvedPlayers, null, null);
                    sendPlayerBoardEvent(playersAttacked);
                    handleSendRequestAfterShoot(player, playersAttacked, false);
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
                 if(message.getNumOfEffect() == 1){
                     RequestToChooseAPlayer requestToChooseAPlayer = new RequestToChooseAPlayer();
                     requestToChooseAPlayer.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
                     //mandare richiesta di scegliere player; una volta scelto, arriva al controller che chiama un metodo che mi mette il player in involvedpl; dopo, avendo salvato effetto in typeofattack e nome dell arma in weaponcard posso chiamare player.shoot
                 }
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


    void setPlayersInInvolvedPlayers(ArrayList<Player> players){
        for(Player player : players){
            involvedPlayers.add(new InvolvedPlayer(player, 12, null));
            playersAttacked.add(player);
        }
        if(priceToPay == null)
            weaponToUse.getOwnerOfCard().shoot(typeOfAttack.get(typeOfAttack.size()-1), weaponToUse, involvedPlayers, null, null );
        else
            handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
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
            handleSendRequestAfterShoot(player, playersAttacked, false);
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
            handleSendRequestAfterShoot(player, playersAttacked, false);
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
            handleSendRequestAfterShoot(player, playersAttacked, false);
        }
    }

    void handleDiscardPowerUpToPayAnEffect(ClientEvent message){
        DiscardPowerUpCardAsAmmo event = (DiscardPowerUpCardAsAmmo) message;
        powerUpCardToDiscardToPay.add(controller.getCatchController().findPowerUpCard(event.getNameOfPowerUpCard(), event.getColorOfCard(), controller.findPlayerWithThisNickname(message.getNickname())));
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

     void handleSendRequestAfterShoot(Player player, ArrayList<Player> playersAttacked, boolean isForTerminator){
        this.isForTerminator = isForTerminator;
        if(!isForTerminator && weaponToUse!=null) {
            if(!doesntWantToContinueToShoot && !controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse).isEmpty()){
                replyWithUsableEffectsOfThisWeapon(weaponToUse.getName(), weaponToUse.getOwnerOfCard());
            }
            weaponToUse.setLoad(false);
            PlayerBoardEvent pbEvent = new PlayerBoardEvent();
            pbEvent.setNicknames(game.getListOfNickname());
            pbEvent.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
            pbEvent.setPlayerBoard(weaponToUse.getOwnerOfCard().getPlayerBoard());
            game.notify(pbEvent);
        }
        else
            this.playersAttacked = playersAttacked;

        ArrayList<Player> playersCouldUseTagback = new ArrayList<>();

        for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getName().equals("Targeting scope") && !alreadyAskedToUseTargeting){
                sendRequestToUseTargeting(player);
                return;
            }
        }
        for(Player playerAttacked : playersAttacked){
            if(hasTagBack(playerAttacked))
                playersCouldUseTagback.add(playerAttacked);
        }

        if(playersCouldUseTagback.isEmpty()){
            if(isForTerminator && playersAttacked.get(0).getPlayerBoard().getDamages().size()>=11 )
                controller.getDeathController().handleDeath(player, playersAttacked.get(0));
            else
                controller.handleTheEndOfAnAction(false);
        }
        else{
            sendRequestToUseTagbackGranade(playersCouldUseTagback);
        }
    }

    private boolean hasTagBack(Player playerAttacked) {
            for (PowerUpCard powerUpCard : playerAttacked.getPlayerBoard().getPowerUpCardsOwned()) {
                if (powerUpCard.getName().equals("Tagback granade"))
                    return true;
            }

        return false;
    }

    private void sendRequestToUseTargeting(Player player) {
        RequestToDiscardPowerUpCardToPay requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCardToPay();
        requestToDiscardPowerUpCardToPay.setToTargeting(true);
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        if(isForTerminator)
            requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which Targeting scope the terminator has to use. \nIf you don't want you can press 'No one'");
        else
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
         Player player;
         player = game.getPlayers().get(game.getPlayerOfTurn()-1);
        if(message.getNameOfPowerUpCard()==null){
            alreadyAskedToUseTargeting=true;
            handleSendRequestAfterShoot(player, playersAttacked, isForTerminator);
        }
        else{
            RequestToPayWithAmmoOrPUCard requestToPayWithAmmoOrPUCard = new RequestToPayWithAmmoOrPUCard();
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, ColorOfCard_Ammo.BLUE,false)>0)
                requestToPayWithAmmoOrPUCard.setBlueAmmo(true);
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, ColorOfCard_Ammo.RED,false)>0)
                requestToPayWithAmmoOrPUCard.setRedAmmo(true);
            if(controller.getCatchController().frequencyOfAmmoUsableByPlayer(player, ColorOfCard_Ammo.YELLOW,false)>0)
                requestToPayWithAmmoOrPUCard.setYellowAmmo(true);
            for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
                requestToPayWithAmmoOrPUCard.getPowerUpCards().add(new ServerEvent.AliasCard(powerUpCard.getName(), powerUpCard.getDescription(), powerUpCard.getColor()));
            }
            for(ServerEvent.AliasCard card : requestToPayWithAmmoOrPUCard.getPowerUpCards()){
                if(card.getColor().equals(message.getColorOfCard()) && card.getName().equals(message.getNameOfPowerUpCard())){
                    indexOfCardToRemove = requestToPayWithAmmoOrPUCard.getPowerUpCards().indexOf(card);
                    break;
                }
            }
            targetingScopeToUse = controller.getCatchController().findPowerUpCard(message.getNameOfPowerUpCard(), message.getColorOfCard(), controller.findPlayerWithThisNickname(message.getNickname()));
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
            powerUpCardToDiscardToPay.add(controller.getCatchController().findPowerUpCard(message.getPowerUpCard().getName(),message.getPowerUpCard().getColor(), controller.findPlayerWithThisNickname(message.getNickname())));
        }

        RequestToChooseAPlayer requestToChooseAPlayer = new RequestToChooseAPlayer();
        requestToChooseAPlayer.setToUseTargeting(true);
        if(playersAttacked.size()==1) {
            Player player = controller.findPlayerWithThisNickname(message.getNickname());
            if (ammoForPayTargeting != null)
                player.useTargetingScope(playersAttacked.get(0), targetingScopeToUse, ammoForPayTargeting, null);
            else
                player.useTargetingScope(playersAttacked.get(0), targetingScopeToUse, null, powerUpCardToDiscardToPay.get(0));
            sendPlayerBoardEventAfterTargetingOrTagback(player, playersAttacked.get(0), true);
            handleSendRequestAfterShoot(player, playersAttacked, isForTerminator);
        }

        else {
            for (Player player : playersAttacked) {
                requestToChooseAPlayer.getNameOfPlayers().add(player.getNickname());
            }
            requestToChooseAPlayer.setNicknameInvolved(message.getNickname());
            requestToChooseAPlayer.setMessageForInvolved("Choose a player to use Targeting scope");
            message.getMyVirtualView().update(requestToChooseAPlayer);
        }

    }

    void handleUseOfTargeting(ChoosePlayer message){
        Player playerAttacker = controller.findPlayerWithThisNickname(message.getNickname());
        Player playerAttacked = controller.findPlayerWithThisNickname(message.getNameOfPlayer());
        if(ammoForPayTargeting!=null)
            playerAttacker.useTargetingScope(playerAttacked,targetingScopeToUse,ammoForPayTargeting,null);
        else
            playerAttacker.useTargetingScope(playerAttacked,targetingScopeToUse,null,powerUpCardToDiscardToPay.get(0));
        sendPlayerBoardEventAfterTargetingOrTagback(playerAttacker,controller.findPlayerWithThisNickname(message.getNameOfPlayer()),true);
        handleSendRequestAfterShoot(playerAttacker,playersAttacked, false);
    }

    private void sendPlayerBoardEventAfterTargetingOrTagback(Player playerAttacker, Player playerAttacked, boolean isAfterTargeting) {
        String nameOfCard;
        if(isAfterTargeting)
            nameOfCard = "Targeting scope";
        else
            nameOfCard = "Tagback granade";
        String messageForAttacker = "You have used " + nameOfCard +" on " + playerAttacked.getNickname();
        String messageForAttacked = playerAttacker.getNickname() + " has used " + nameOfCard +" on you";
        String messageForOthers = playerAttacker.getNickname() + " has used " + nameOfCard +" on " + playerAttacked.getNickname();
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
            playersAreChoosingForTagback.add(player);
            game.notify(newEvent);
        }
    }

     void handleRequestToUseTagbackGranade(DiscardPowerUpCardAsAmmo message){
        Player playerWithTagback = controller.findPlayerWithThisNickname(message.getNickname());
        Player playerToAttack = null;
        if(!isForTerminator)
            playerToAttack = weaponToUse.getOwnerOfCard();
        else{
            for(Player player : game.getPlayers()){
                if(player.getNickname().equals("terminator"))
                    playerToAttack = player;
            }
        }

        if(message.getNameOfPowerUpCard()==null){
            playersAreChoosingForTagback.remove(playerWithTagback);
            if(playersAreChoosingForTagback.isEmpty()) {
                if(isForTerminator && playersAttacked.get(0).getPlayerBoard().getDamages().size()>=11)
                    controller.getDeathController().handleDeath(playerToAttack, playersAttacked.get(0));
                else
                    controller.handleTheEndOfAnAction(false);
            }
        }
        else{
            PowerUpCard tagback = controller.getCatchController().findPowerUpCard(message.getNameOfPowerUpCard(), message.getColorOfCard(), playerWithTagback);
            playerWithTagback.useTagbackGranade(tagback,playerToAttack);
            sendPlayerBoardEventAfterTargetingOrTagback(playerWithTagback, playerToAttack, false);
            ArrayList<Player> playersCouldUseTagback = new ArrayList<>();
            for(PowerUpCard card : playerWithTagback.getPlayerBoard().getPowerUpCardsOwned()){
                if(card.getName().equals("Tagback granade")) {
                    playersCouldUseTagback.add(playerWithTagback);
                    sendRequestToUseTagbackGranade(playersCouldUseTagback);
                    return;
                }
            }
            playersAreChoosingForTagback.remove(playerWithTagback);
            if(playersAreChoosingForTagback.isEmpty()){
                if(isForTerminator && playersAttacked.get(0).getPlayerBoard().getDamages().size()>=11)
                    controller.getDeathController().handleDeath(playerToAttack, playersAttacked.get(0));
                else
                    controller.handleTheEndOfAnAction(false);
            }
        }
    }

    private ArrayList<Square> getSquaresFromWherePlayerCanAttack(Player player, int movement) {
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> squaresToRemove = new ArrayList<>();
        boolean isToRemove = true;
        Square originalSquare = player.getPositionOnTheMap();
        controller.findSquaresReachableWithThisMovements(originalSquare, movement, startingSquares);
        for(Square square : startingSquares){
            player.setPositionOnTheMap(square);
            for(WeaponCard weaponCard : player.getPlayerBoard().getWeaponsOwned()) {
                if (controller.checkIfThisWeaponIsUsable(weaponCard,0)) {
                    isToRemove = false;
                    break;
                }
            }
            if(isToRemove)
                squaresToRemove.add(square);
            isToRemove = true;
        }

        for(Square squareToRemove : squaresToRemove){
            startingSquares.remove(squareToRemove);
        }
        player.setPositionOnTheMap(originalSquare);
        return startingSquares;
    }


     void handleMovementBeforeShoot(ClientEvent message) {
        controller.getRunController().doRunAction((RunEvent) message, true);
        Player player = controller.findPlayerWithThisNickname(message.getNickname());
        if(game.isInFrenzy()){
            if(controller.getReloadController().playerCanReload(player))
                controller.getReloadController().sendRequestToReload(player,false);
            else
                sendRequestToChooseAWeapon(player);
        }
        else{
            sendRequestToChooseAWeapon(player);
        }
    }
}
