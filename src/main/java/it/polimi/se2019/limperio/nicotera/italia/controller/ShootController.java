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
    private WeaponCard weaponToUse = null;
    private ColorOfCard_Ammo[] priceToPay = null;
    private ArrayList<ColorOfCard_Ammo> colorsNotEnough = new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCardToDiscardToPay = new ArrayList<>();
    private ColorOfCard_Ammo ammoForPayTargeting;
    private ArrayList<Player> playersAttacked = new ArrayList<>();
    private boolean alreadyAskedToUseTargeting = false;
    private PowerUpCard targetingScopeToUse;
    private ArrayList<Player> playersAreChoosingForTagback = new ArrayList<>();
    private boolean isForTerminator = false;
    private boolean doesntWantToContinueToShoot = false;
    private Square originalSquareOfTheTarget = null;
    private boolean needToStoreOriginalSquare = false;
    private boolean needToChooseAPlayer = false;
    private boolean needToChooseASquare = false;

    public ShootController(Game game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }


     void replyToRequestToShoot(ClientEvent message) {
        needToChooseASquare = false;
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
        originalSquareOfTheTarget = null;
        needToStoreOriginalSquare = false;
        needToChooseAPlayer = false;
        targetingScopeToUse = null;
        playersAreChoosingForTagback = new ArrayList<>();
        colorsNotEnough = new ArrayList<>();

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
        weaponToUse = findWeaponCardWithThisName(nameOfWeaponCard, player);
        priceToPay = null;
         ArrayList<Integer> usableEffectsForThisWeapon = controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse);
         RequestToChooseAnEffect requestToChooseAnEffect = new RequestToChooseAnEffect();
         if(typeOfAttack.isEmpty())
            requestToChooseAnEffect.setMessageForInvolved("Choose, to start, one of these effects:");
         else{
             if(!((weaponToUse.getName().equals("Plasma gun")||weaponToUse.getName().equals("Cyberblade")||weaponToUse.getName().equals("Granade launcher") || weaponToUse.getName().equals("Rocket launcher"))&& typeOfAttack.size()==1 && typeOfAttack.contains(2))){ //condizione eventualmente da modificare
                 requestToChooseAnEffect.setOneEffectAlreadyChosen(true);
                 requestToChooseAnEffect.setMessageForInvolved("Choose another effect or press on 'END ACTION'");
             }
             else
                 requestToChooseAnEffect.setMessageForInvolved("Choose another effect");
         }
         requestToChooseAnEffect.setNameOfCard(nameOfWeaponCard);
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
         ArrayList<Player> visiblePlayers = controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0);
         ArrayList<Player> availablePlayers = new ArrayList<>();
         if(message.getNumOfEffect()==0){
            doesntWantToContinueToShoot=true;
            handleSendRequestAfterShoot(weaponToUse.getOwnerOfCard(), playersAttacked, false);
            return;
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
                    player.shoot(1, weaponToUse, getInvolvedPlayersForThisEffect(1), null, null);
                    sendPlayerBoardEvent();
                    sendMapEvent();
                    handleSendRequestAfterShoot(player, playersAttacked, false);
                }
                else {
                    priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                    handlePaymentForEffect(player, playersAttacked);
                }
                break;

             case "Cyberblade":
                 ArrayList<Player> playersInMySquare = new ArrayList<>();
                 for(String nickname : controller.getWeaponController().getPlayersInMySquare(0, squareOfPlayer)){
                     playersInMySquare.add(controller.findPlayerWithThisNickname(nickname));
                 }
                 if(!playersAttacked.isEmpty())
                    playersInMySquare.remove(playersAttacked.get(0));
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1,playersInMySquare, false);
                         break;
                     case 2:
                         sendRequestToChooseSquare(getSquareWhereThisWeaponIsUsable(weaponToUse, 1));
                         break;
                     case 3:
                         priceToPay = weaponToUse.getPriceToPayForEffect2();
                         sendRequestToChoosePlayer(1, playersInMySquare, false);
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Sledgehammer":
                 for(String nicknameOfPlayerInMySquare : controller.getWeaponController().getPlayersInMySquare(0, squareOfPlayer)){
                     availablePlayers.add(controller.findPlayerWithThisNickname(nicknameOfPlayerInMySquare));
                 }
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, availablePlayers, false);
                         break;
                     case 4:
                         needToChooseASquare = true;
                         sendRequestToChoosePlayer(1, visiblePlayers, false);
                         break;
                     default: throw new IllegalArgumentException();
                 }
                 break;

             case "Shotgun":
                if(message.getNumOfEffect() == 1){
                    ArrayList<Player> playersCouldBeAttacked = new ArrayList<>(squareOfPlayer.getPlayerOnThisSquare());
                    playersCouldBeAttacked.remove(weaponToUse.getOwnerOfCard());
                    sendRequestToChoosePlayer(1, playersCouldBeAttacked, false);
                    needToChooseASquare = true;
                }
                else{
                    sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 1), false);
                }
                break;

             case "Shockwave":
                 if(message.getNumOfEffect() == 1){
                    sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersOnlyInAdjSquares(0, squareOfPlayer), false);
                    needToChooseAPlayer = true;
                 }
                 else {
                         for (Player adjPlayers : controller.getWeaponController().getPlayersOnlyInAdjSquares(0, squareOfPlayer)) {
                             involvedPlayers.add(new InvolvedPlayer(adjPlayers, message.getNumOfEffect(), null));
                             playersAttacked.add(adjPlayers);
                         }
                     priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                     handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
                 }
                 break;

             case "Furnace":
                if(message.getNumOfEffect() == 1){
                    sendRequestToChooseSquare(controller.getWeaponController().getSquaresOfVisibleRoom(0, squareOfPlayer, 0, true));
                }
                else{
                    sendRequestToChooseSquare(controller.getWeaponController().getSquaresOfVisibleRoom(0, squareOfPlayer, 1, true));
                }
                break;

             case "Lock rifle":
                 if(message.getNumOfEffect() == 1){
                     sendRequestToChoosePlayer(1,controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(),0), false);
                 }
                 else if(message.getNumOfEffect()==2){
                     priceToPay = weaponToUse.getPriceToPayForEffect1();
                     ArrayList<Player> visibleAndAttackablePlayers = controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0);
                     visibleAndAttackablePlayers.remove(playersAttacked.get(0));
                     sendRequestToChoosePlayer(1,visibleAndAttackablePlayers, false);
                 }
                 break;

             case "Zx-2":
                if(message.getNumOfEffect() == 1)
                    sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0), false);
                else
                    sendRequestToChoosePlayer(3, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0), false);
                break;

             case "Machine gun":
                switch (message.getNumOfEffect()){
                    case 1:
                        sendRequestToChoosePlayer(2, visiblePlayers, false);
                        break;

                    case 2:
                        priceToPay = weaponToUse.getPriceToPayForEffect1();
                        if(!typeOfAttack.contains(3))
                            sendRequestToChoosePlayer(1, playersAttacked, false);
                        else{
                            visiblePlayers = new ArrayList<>(playersAttacked);
                            Player playerToRemove = null;
                            for(Player visiblePlayer : visiblePlayers){
                                if(getPlayersInvolvedInAnEffect(3).contains(visiblePlayer))
                                    playerToRemove = visiblePlayer;
                            }
                            visiblePlayers.remove(playerToRemove);
                            sendRequestToChoosePlayer(1, visiblePlayers, false);
                        }
                        break;

                    case 3:
                        priceToPay = weaponToUse.getPriceToPayForEffect2();
                        if(typeOfAttack.contains(2)) {
                            Player playerToRemove = null;
                            for (Player visiblePlayer : visiblePlayers) {
                                if (getPlayersInvolvedInAnEffect(2).contains(visiblePlayer))
                                    playerToRemove = visiblePlayer;
                            }
                            visiblePlayers.remove(playerToRemove);
                        }
                            sendRequestToChoosePlayer(2, visiblePlayers, false);
                        break;

                        default:
                            throw new IllegalArgumentException();
                }
                break;


             case "Granade launcher":
                 if(message.getNumOfEffect() == 1){
                     needToChooseASquare = true;
                     sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0 , weaponToUse.getOwnerOfCard(), 0), false);
                 }
                 else{
                     sendRequestToChooseSquare(controller.getWeaponController().getSquaresOfVisibleRoom(0, squareOfPlayer, 0, false));
                 }
                 break;
             case "Plasma gun":
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0), false);
                         break;
                     case 2:
                         sendRequestToChooseSquare(getSquareWhereThisWeaponIsUsable(weaponToUse, 2));
                         break;

                     case 3:
                         priceToPay = weaponToUse.getPriceToPayForEffect2();
                         involvedPlayers.add(new InvolvedPlayer(playersAttacked.get(0), 3, null));
                         handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
                         break;

                     default: throw new IllegalArgumentException();
                 }
                 break;

             case "Railgun":
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersInCardinalDirections(0, squareOfPlayer, true),false);
                         break;
                     case 4:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersInCardinalDirections(0, squareOfPlayer, true), false);
                         needToChooseAPlayer=true;
                         break;

                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Heatseeker":
                 sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersNotVisible(0, weaponToUse.getOwnerOfCard()), false);
                 break;

             case "Rocket launcher":
                 switch (message.getNumOfEffect()){
                     case 1:
                         needToStoreOriginalSquare = true;
                         sendRequestToChoosePlayer(1,controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 1), false);
                         needToChooseASquare = true;
                         break;
                     case 2:
                         priceToPay = weaponToUse.getPriceToPayForEffect1();
                         ArrayList<Square> reachableSquares = getSquaresFromWherePlayerCanAttack(player,2);
                         sendRequestToChooseSquare(reachableSquares);
                         break;
                     case 3:
                         priceToPay = weaponToUse.getPriceToPayForEffect2();
                         involvedPlayers.add(new InvolvedPlayer(playersAttacked.get(0), 3, originalSquareOfTheTarget));
                         playersAttacked.addAll(originalSquareOfTheTarget.getPlayerOnThisSquare());
                         if(!playersAttacked.contains(getInvolvedPlayersForThisEffect(1).get(0).getPlayer()))
                             playersAttacked.add(involvedPlayers.get(involvedPlayers.size()-1).getPlayer());
                         handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Hellion":
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 1), false);
                         break;
                     case 4:
                         priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0,weaponToUse.getOwnerOfCard(), 1), false);
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Whisper":
                 sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 1), false);
                 break;

             case "THOR":
                 switch (message.getNumOfEffect()){
                     case 1 :
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getVisiblePlayers(0, weaponToUse.getOwnerOfCard(), 0), false);
                         break;
                     case 2:
                         priceToPay = weaponToUse.getPriceToPayForEffect1();
                         visiblePlayers = controller.getWeaponController().getVisiblePlayers(0, playersAttacked.get(0),0);
                         visiblePlayers.remove(weaponToUse.getOwnerOfCard());
                         sendRequestToChoosePlayer(1, visiblePlayers, false);
                         break;
                     case 3:
                         priceToPay = weaponToUse.getPriceToPayForEffect2();
                         visiblePlayers = controller.getWeaponController().getVisiblePlayers(0, playersAttacked.get(1), 0);
                         visiblePlayers.remove(weaponToUse.getOwnerOfCard());
                         visiblePlayers.remove(playersAttacked.get(1));
                         sendRequestToChoosePlayer(1, visiblePlayers, false);
                         break;
                         default:
                             throw new IllegalArgumentException();
                 }
                 break;

             case "Flamethrower":
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersOnlyInAdjSquares(0, squareOfPlayer), false);
                         needToChooseAPlayer = true;
                         break;
                     case 4:
                         ArrayList<Square> adjSquareWithPlayers = squareOfPlayer.getAdjSquares();
                         controller.getWeaponController().removeSquareWithoutPlayers(adjSquareWithPlayers);
                         sendRequestToChooseSquare(adjSquareWithPlayers);
                         needToChooseASquare = true;
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Power glove":
                 switch (message.getNumOfEffect()){
                     case 1:
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersOnlyInAdjSquares(0, squareOfPlayer), false);
                         break;
                     case 4:
                         priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                         needToChooseAPlayer = true;
                         sendRequestToChoosePlayer(1, controller.getWeaponController().getPlayersOnlyInAdjSquares(0, squareOfPlayer), false);
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Tractor beam":
                 ArrayList<Player> playersCouldBeAttacked = new ArrayList<>();
                 switch (message.getNumOfEffect()){
                     case 1:
                         needToChooseASquare = true;
                         sendRequestToChoosePlayer(1 , controller.getWeaponController().getPlayersCouldBeAttackedFromBasicEffectOfTractorBeam(weaponToUse), false);
                         break;
                     case 4:
                         priceToPay = weaponToUse.getPriceToPayForAlternativeMode();
                         ArrayList<String> nicknameOfPlayersCouldBeAttacked = controller.getWeaponController().getPlayersInMySquare(2 ,weaponToUse.getOwnerOfCard().getPositionOnTheMap());
                         for(String nickname : nicknameOfPlayersCouldBeAttacked){
                             playersCouldBeAttacked.add(controller.findPlayerWithThisNickname(nickname));
                         }
                         sendRequestToChoosePlayer(1, playersCouldBeAttacked, false);
                         break;
                         default: throw new IllegalArgumentException();
                 }
                 break;

             case "Vortex cannon":
                switch (message.getNumOfEffect()){
                    case 1:
                        sendRequestToChooseSquare(controller.getWeaponController().getSquaresForVortexCannon(squareOfPlayer));
                        needToChooseAPlayer = true;
                        break;
                    case 2:
                        involvedPlayers.add(new InvolvedPlayer(null,2  , involvedPlayers.get(0).getSquare()));
                        priceToPay = weaponToUse.getPriceToPayForEffect1();
                        availablePlayers = new ArrayList<>();
                        availablePlayers.addAll(involvedPlayers.get(0).getSquare().getPlayerOnThisSquare());
                        availablePlayers.remove(involvedPlayers.get(1).getPlayer());
                        for(Square adjSquare : involvedPlayers.get(0).getSquare().getAdjSquares()){
                            availablePlayers.addAll(adjSquare.getPlayerOnThisSquare());
                        }
                        availablePlayers.remove(weaponToUse.getOwnerOfCard());
                        sendRequestToChoosePlayer(2, availablePlayers, false);
                        break;
                        default: throw new IllegalArgumentException();
                }
                break;
             default:
                 throw  new IllegalArgumentException();
         }
    }

    private void sendRequestToChooseSquare(ArrayList<Square> squaresReachableWithThisMovements) {
        RequestSelectionSquareForAction requestSelectionSquareForAction = new RequestSelectionSquareForAction("Choose a square");
        requestSelectionSquareForAction.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
        requestSelectionSquareForAction.setSquaresReachable(squaresReachableWithThisMovements);
        requestSelectionSquareForAction.setForActionShoot(true);
        game.notify(requestSelectionSquareForAction);

    }

    void setSquareInInvolvedPlayers(SelectionSquareForShootAction message) {
        ArrayList<Player> playersHasToBeAttacked = new ArrayList<>();

        if (weaponToUse.getName().equals("Furnace") && typeOfAttack.get(0) == 1) {
            Square chosenSquare = game.getBoard().getMap().getMatrixOfSquares()[message.getRow()][message.getColumn()];
            for (int i = 0; i < game.getBoard().getMap().getMatrixOfSquares().length; i++) {
                for (int j = 0; j < game.getBoard().getMap().getMatrixOfSquares()[i].length; j++) {
                    Square square = game.getBoard().getMap().getMatrixOfSquares()[i][j];
                    if (square != null && square.getColor().equals(chosenSquare.getColor()) && !square.getPlayerOnThisSquare().isEmpty()) {
                        playersHasToBeAttacked.addAll(square.getPlayerOnThisSquare());
                    }
                }
            }
            setPlayersInInvolvedPlayers(playersHasToBeAttacked);
            return;
        }

        if (needToChooseASquare) {
            if (weaponToUse.getName().equals("Flamethrower")) {
                involvedPlayers.add(new InvolvedPlayer(null, 4, game.getBoard().getMap().getMatrixOfSquares()[message.getRow()][message.getColumn()]));
                playersAttacked.addAll(involvedPlayers.get(0).getSquare().getPlayerOnThisSquare());
                if (controller.getWeaponController().getSquareForAlternativeModeOfPowerGloveAndFlamethrower(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), involvedPlayers.get(0).getSquare()) != null) {
                    involvedPlayers.add(new InvolvedPlayer(null, 4, controller.getWeaponController().getSquareForAlternativeModeOfPowerGloveAndFlamethrower(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), involvedPlayers.get(0).getSquare())));
                    playersAttacked.addAll(involvedPlayers.get(1).getSquare().getPlayerOnThisSquare());
                }
                for (Player player : playersAttacked) {
                    involvedPlayers.add(new InvolvedPlayer(player, 4, null));
                }
                needToChooseASquare = false;
                handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
                return;
            } else {
                involvedPlayers.get(involvedPlayers.size() - 1).setSquare(game.getBoard().getMap().getMatrixOfSquares()[message.getRow()][message.getColumn()]);
                needToChooseASquare = false;
            }
        }
        else {
                involvedPlayers.add(new InvolvedPlayer(null, typeOfAttack.get(typeOfAttack.size() - 1), game.getBoard().getMap().getMatrixOfSquares()[message.getRow()][message.getColumn()]));
            }
        if(needToChooseAPlayer){
            ArrayList<Player> players = new ArrayList<>();
            players.addAll(involvedPlayers.get(0).getSquare().getPlayerOnThisSquare());
            for(Square adjSquare : involvedPlayers.get(0).getSquare().getAdjSquares()){
                players.addAll(adjSquare.getPlayerOnThisSquare());
            }
            players.remove(weaponToUse.getOwnerOfCard());
            sendRequestToChoosePlayer(1, players, false);
            needToChooseAPlayer=false;
            return;
        }
            setPlayersInInvolvedPlayers(playersHasToBeAttacked);
        }


    private void sendRequestToChoosePlayer(int numOfMaxPlayerToChoose, ArrayList<Player> listOfPlayersChoosable, boolean canRefuse) {
        if(numOfMaxPlayerToChoose==1){
            RequestToChooseAPlayer requestToChooseAPlayer = new RequestToChooseAPlayer();
            requestToChooseAPlayer.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
            requestToChooseAPlayer.setMessageForInvolved("Choose a player to attack with the selected effect");
            for (Player player : listOfPlayersChoosable) {
                requestToChooseAPlayer.getNameOfPlayers().add(player.getNickname());
            }
            requestToChooseAPlayer.setChoosePlayerForAttack(true);
            requestToChooseAPlayer.setCanRefuse(canRefuse);
            game.notify(requestToChooseAPlayer);
        } else {
            RequestToChooseMultiplePlayers requestToChooseMultiplePlayers = new RequestToChooseMultiplePlayers();
            requestToChooseMultiplePlayers.setNumOfMaxPlayersToChoose(numOfMaxPlayerToChoose);
            requestToChooseMultiplePlayers.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
            requestToChooseMultiplePlayers.setMessageForInvolved("Choose until " + numOfMaxPlayerToChoose + " players to attack with the effect you have chosen");
            for (Player player : listOfPlayersChoosable){
                requestToChooseMultiplePlayers.getNamesOfPlayers().add(player.getNickname());
            }
            game.notify(requestToChooseMultiplePlayers);
        }
    }

    ArrayList<Player> getPlayersAttacked() {
        return playersAttacked;
    }


    private ArrayList<Player> getPlayersInvolvedInAnEffect(int effect){
        ArrayList<Player> players = new ArrayList<>();
        for(InvolvedPlayer involvedPlayer : involvedPlayers){
            if(involvedPlayer.getEffect() == effect){
                players.add(involvedPlayer.getPlayer());
            }
        }
        return players;
    }

    void setPlayersInInvolvedPlayers(ArrayList<Player> players){
        for(Player player : players){
            involvedPlayers.add(new InvolvedPlayer(player, typeOfAttack.get(typeOfAttack.size()-1), null));
            if(!playersAttacked.contains(player) && !(weaponToUse.getName().equals("Zx-2")&&typeOfAttack.get(typeOfAttack.size()-1).equals(4)))
                playersAttacked.add(player);
        }

        if(needToStoreOriginalSquare){
            originalSquareOfTheTarget = players.get(0).getPositionOnTheMap();
            needToStoreOriginalSquare=false;
        }
        if(needToChooseASquare){
            switch (weaponToUse.getName()) {
                case "Tractor beam":
                    sendRequestToChooseSquare(controller.getWeaponController().getSquareCouldBeSelectedForTractorBeam(weaponToUse, playersAttacked.get(0)));
                    break;
                case "Rocket launcher":
                    ArrayList<Square> squareReachable = new ArrayList<>();
                    controller.findSquaresReachableWithThisMovements(players.get(0).getPositionOnTheMap(), 1, squareReachable);
                    sendRequestToChooseSquare(squareReachable);
                    break;
                case "Shotgun":
                    ArrayList<Square> squares = new ArrayList<>();
                    for (Square square : weaponToUse.getOwnerOfCard().getPositionOnTheMap().getAdjSquares()) {
                        if (square != null)
                            squares.add(square);
                    }
                    sendRequestToChooseSquare(squares);
                    break;
                case "Granade launcher":
                    ArrayList<Square> squares1 = new ArrayList<>();
                    for(Square square : playersAttacked.get(0).getPositionOnTheMap().getAdjSquares()){
                        if(square != null)
                            squares1.add(square);
                    }
                    sendRequestToChooseSquare(squares1);
                    break;
                    default: throw new IllegalArgumentException();
            }
            return;
        }

        if(needToChooseAPlayer){
            ArrayList<Player> playersChoosable = new ArrayList<>();
            if(weaponToUse.getName().equals("Power glove") && controller.getWeaponController().getSquareForAlternativeModeOfPowerGloveAndFlamethrower(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), playersAttacked.get(0).getPositionOnTheMap())!=null) {
                playersChoosable = controller.getWeaponController().getSquareForAlternativeModeOfPowerGloveAndFlamethrower(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), playersAttacked.get(0).getPositionOnTheMap()).getPlayerOnThisSquare();
                sendRequestToChoosePlayer(1, playersChoosable, true);
                needToChooseAPlayer = false;
                return;
            }
            else if(weaponToUse.getName().equals("Shockwave")){
                playersChoosable = controller.getWeaponController().getPlayersOnlyInAdjSquares(0,weaponToUse.getOwnerOfCard().getPositionOnTheMap());
                removePlayersForShockwave(playersChoosable);
                if(!playersChoosable.isEmpty() && playersAttacked.size()<3){
                    sendRequestToChoosePlayer(1, playersChoosable, false);
                    return;
                }
                else
                    needToChooseAPlayer = false;
            }
            else if(weaponToUse.getName().equals("Railgun")){
                playersChoosable = controller.getWeaponController().getPlayersForAlternativeModeOfRailgun(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), playersAttacked.get(0).getPositionOnTheMap());
                playersChoosable.remove(playersAttacked.get(0));
                sendRequestToChoosePlayer(1, playersChoosable, true);
                needToChooseAPlayer = false;
                return;
            }
            else if(weaponToUse.getName().equals("Flamethrower")){
                Square square = controller.getWeaponController().getSquareForAlternativeModeOfPowerGloveAndFlamethrower(weaponToUse.getOwnerOfCard().getPositionOnTheMap(), playersAttacked.get(0).getPositionOnTheMap());
                if(square!=null)
                    playersChoosable.addAll(square.getPlayerOnThisSquare());
                if(!playersChoosable.isEmpty()) {
                    sendRequestToChoosePlayer(1, playersChoosable, true);
                    needToChooseAPlayer = false;
                    return;
                }
                needToChooseAPlayer = false;
            }
        }

        if(priceToPay == null) {
            weaponToUse.getOwnerOfCard().shoot(typeOfAttack.get(typeOfAttack.size() - 1), weaponToUse, getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size() - 1)), null, null);
                sendMapEvent();
                sendPlayerBoardEvent();
            if(!controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse).isEmpty())
                replyWithUsableEffectsOfThisWeapon(weaponToUse.getName(), weaponToUse.getOwnerOfCard());
            else
                handleSendRequestAfterShoot(weaponToUse.getOwnerOfCard(), playersAttacked, false);
        }
        else
            handlePaymentForEffect(weaponToUse.getOwnerOfCard(), playersAttacked);
    }


    private void removePlayersForShockwave(ArrayList<Player> playersChoosable) {
        for(Player playerAlreadyChosen : playersAttacked){
            for(Player playerInTheSquare : playerAlreadyChosen.getPositionOnTheMap().getPlayerOnThisSquare())
                playersChoosable.remove(playerInTheSquare);
        }
    }

    private void sendMapEvent() {
        MapEvent mapEvent = new MapEvent();
        mapEvent.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
        mapEvent.setNicknames(game.getListOfNickname());
        mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        mapEvent.setMessageForInvolved("You have moved in another square using the effect of your weapon");
        mapEvent.setMessageForOthers(weaponToUse.getOwnerOfCard().getNickname() + " has moved in another square using the effect of his weapon");
        game.notify(mapEvent);
    }


    private void sendPlayerBoardEvent() {
        String messageForAttacked = null;
        String messageForAttacker = null;
        if(getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size()-1)).get(0).getPlayer()!=null) {
            messageForAttacked = weaponToUse.getOwnerOfCard().getNickname() + " has attacked: ";
            messageForAttacker = "You have attacked: ";
            ArrayList<Player> playersInvolvedInTheLastEffect = getPlayersInvolvedInAnEffect(typeOfAttack.get(typeOfAttack.size() - 1));
            for (Player player : playersInvolvedInTheLastEffect) {
                if (playersInvolvedInTheLastEffect.indexOf(player) == playersInvolvedInTheLastEffect.size() - 1) {
                    messageForAttacked = messageForAttacked.concat(player.getNickname());
                    messageForAttacker = messageForAttacker.concat(player.getNickname());
                } else {
                    messageForAttacked = messageForAttacked.concat(player.getNickname() + ", ");
                    messageForAttacker = messageForAttacker.concat(player.getNickname() + ", ");
                }
            }
            messageForAttacked = messageForAttacked.concat("\nCheck player boards to see their new situation!");

        }
            for (Player player : game.getPlayers()) {
                PlayerBoardEvent pbEvent = new PlayerBoardEvent();
                if (player.equals(weaponToUse.getOwnerOfCard()))
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
            sendPlayerBoardEvent();
            sendMapEvent();
            if(!doesntWantToContinueToShoot && !controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse).isEmpty())
                replyWithUsableEffectsOfThisWeapon(weaponToUse.getName(), weaponToUse.getOwnerOfCard());
            else
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
            sendPlayerBoardEvent();
            sendMapEvent();
            if(!doesntWantToContinueToShoot && !controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse).isEmpty())
                replyWithUsableEffectsOfThisWeapon(weaponToUse.getName(), weaponToUse.getOwnerOfCard());
            else
                handleSendRequestAfterShoot(player, playersAttacked, false);
        }

    }

    private void sendRequestToDiscardPowerUpCard(Player player, ArrayList<ColorOfCard_Ammo> colorsNotEnough) {
        RequestToDiscardPowerUpCard requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCard();
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which power up card you want to discard to pay for the effect");
        requestToDiscardPowerUpCardToPay.setToPayAnEffect(true);
        if(!colorsNotEnough.isEmpty()) {
            requestToDiscardPowerUpCardToPay.setPowerUpCards(controller.getCatchController().getPowerUpCardToChooseForDiscard(player, colorsNotEnough.remove(0)));
            game.notify(requestToDiscardPowerUpCardToPay);
        }
        else{
            player.shoot(typeOfAttack.get(typeOfAttack.size()-1), weaponToUse, getInvolvedPlayersForThisEffect(typeOfAttack.get(typeOfAttack.size()-1)), priceToPay, powerUpCardToDiscardToPay);
            sendPlayerBoardEvent();
            sendMapEvent();
            if(!doesntWantToContinueToShoot && !controller.getWeaponController().getUsableEffectsForThisWeapon(weaponToUse).isEmpty())
                replyWithUsableEffectsOfThisWeapon(weaponToUse.getName(), weaponToUse.getOwnerOfCard());
            else
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
            if(couldUseTagback(playerAttacked))
                playersCouldUseTagback.add(playerAttacked);
        }

        if(playersCouldUseTagback.isEmpty()){
            if(!controller.getRoundController().getPlayersDeadInThisTurn().isEmpty()) {
                for (Player playerDead : controller.getRoundController().getPlayersDeadInThisTurn()) {
                    controller.getDeathController().handleDeath(player, playerDead);
                }

                controller.handleTheEndOfAnAction(false);
            }
            else
                controller.handleTheEndOfAnAction(false);
        }
        else{
            sendRequestToUseTagbackGranade(playersCouldUseTagback);
        }
    }

    private boolean couldUseTagback(Player playerAttacked) {
        if(weaponToUse!=null && !controller.getWeaponController().getVisiblePlayers(0, playerAttacked, 0).contains(weaponToUse.getOwnerOfCard()))
            return false;
        if(weaponToUse == null && !controller.getWeaponController().getVisiblePlayers(0, playerAttacked, 0).contains(controller.findPlayerWithThisNickname("terminator")))
            return false;

            for (PowerUpCard powerUpCard : playerAttacked.getPlayerBoard().getPowerUpCardsOwned()) {
                if (powerUpCard.getName().equals("Tagback granade")) {
                    return true;
                }
            }

        return false;
    }

    private void sendRequestToUseTargeting(Player player) {
        RequestToDiscardPowerUpCard requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCard();
        requestToDiscardPowerUpCardToPay.setToTargeting(true);
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        if(isForTerminator) {
            requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which Targeting scope the terminator has to use. \nIf you don't want you can press 'No one'");
        }
        else {
            requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which Targeting scope you want to use. \nIf you don't want you can press 'No one'");
        }
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
        Player playerAttacker;
        if(isForTerminator)
            playerAttacker = controller.findPlayerWithThisNickname("terminator");
        else
         playerAttacker = controller.findPlayerWithThisNickname(message.getNickname());
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
            RequestToDiscardPowerUpCard newEvent = new RequestToDiscardPowerUpCard();
            newEvent.setNicknameInvolved(player.getNickname());
            newEvent.setMessageForInvolved("Choose a Tagback granade card to use against who attacked you or 'No one' if you want to avoid this");
            newEvent.setPowerUpCards(tagbackGranadeCards);
            newEvent.setToTagback(true);
            playersAreChoosingForTagback.add(player);
            game.notify(newEvent);
        }
        RequestToDiscardPowerUpCard newEvent = new RequestToDiscardPowerUpCard();
        newEvent.setToTagback(true);
        newEvent.setMessageForInvolved("Wait that the players you have attacked and have tagback decide if use them or not");
        if(isForTerminator)
            newEvent.setNicknameInvolved(game.getPlayers().get(game.getPlayerOfTurn() - 1).getNickname());
        else
            newEvent.setNicknameInvolved(weaponToUse.getOwnerOfCard().getNickname());
        game.notify(newEvent);

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
                if(!controller.getRoundController().getPlayersDeadInThisTurn().isEmpty()) {
                    for (Player playerDead : controller.getRoundController().getPlayersDeadInThisTurn()) {
                        if (isForTerminator)
                            controller.getDeathController().handleDeath(controller.findPlayerWithThisNickname("terminator"),playerDead);
                        controller.getDeathController().handleDeath(weaponToUse.getOwnerOfCard(), playerDead);
                    }
                 controller.handleTheEndOfAnAction(false);
                }
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
            if(playersAreChoosingForTagback.isEmpty()) {
                if(!controller.getRoundController().getPlayersDeadInThisTurn().isEmpty()) {
                    for (Player playerDead : controller.getRoundController().getPlayersDeadInThisTurn()) {
                        if (isForTerminator)
                            controller.getDeathController().handleDeath(controller.findPlayerWithThisNickname("terminator"),playerDead);
                        controller.getDeathController().handleDeath(weaponToUse.getOwnerOfCard(), playerDead);
                    }
                    controller.handleTheEndOfAnAction(false);
                }
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
                if (!controller.getWeaponController().getUsableEffectsForThisWeapon(weaponCard).isEmpty()) {
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


    private ArrayList<Square> getSquareWhereThisWeaponIsUsable(WeaponCard weaponCard, int movement){
        ArrayList<Square> startingSquares = new ArrayList<>();
        ArrayList<Square> squaresToRemove = new ArrayList<>();
        Square originalSquare = weaponCard.getOwnerOfCard().getPositionOnTheMap();

        controller.findSquaresReachableWithThisMovements(originalSquare, movement, startingSquares);
        for(Square square : startingSquares){
            weaponCard.getOwnerOfCard().setPositionOnTheMap(square);
            if(controller.getWeaponController().getUsableEffectsForThisWeapon(weaponCard).isEmpty())
                squaresToRemove.add(square);
        }

        for(Square squareToRemove : squaresToRemove){
            startingSquares.remove(squareToRemove);
        }
        weaponCard.getOwnerOfCard().setPositionOnTheMap(originalSquare);
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

    public PowerUpCard getTargetingScopeToUse()
    {
        return targetingScopeToUse;
    }

    public void  setWeaponToUse(WeaponCard weaponToUse)
    {
        this.weaponToUse= weaponToUse;
    }


}
