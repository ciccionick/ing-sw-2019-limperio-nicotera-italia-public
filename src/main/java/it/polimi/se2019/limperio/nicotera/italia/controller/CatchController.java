package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class handles all of the operations concerning the catch actions of the players
 * @author Francesco Nicotera
 */
class CatchController {
    /**
     * Reference of the game.
     */
    private final Game game;
    /**
     * Reference of the controller.
     */
    private Controller controller;
    /**
     * List of power up to discard to pay a weapon to catch.
     */
    private ArrayList<PowerUpCard> powerUpCardsToDiscard = new ArrayList<>();
    /**
     * Weapon card to catch.
     */
    private WeaponCard weaponCard;
    /**
     * List of color of ammo that represents the colors of ammo to pay but available as ammo and so that need to substitute with a discard of power up card.
     */
    private ArrayList<ColorOfCard_Ammo> colorsNotEnough ;

    /**
     * Constructor of the class that initialize game and controller references.
     */
    CatchController(Game game, Controller controller){
        this.game=game;
        this.controller=controller;
    }

    /**
     * <p>
     *     This method replies to a catch request by the player;
     * </p>
     * <p>
     *     It finds the squares where the player can catch and then it sends to virtual view an event
     *     in order to show to the player these squares and the objects that it can catch
     * </p>
     *
     * @param event it contains the information of the player that want to catch
     */
    void replyToRequestToCatch(ClientEvent event){
        ArrayList<ServerEvent.AliasCard> weaponNotAffordable = new ArrayList<>();
        ArrayList<Square> squareAvailableToCatch = findSquareWherePlayerCanCatch(controller.findPlayerWithThisNickname(event.getNickname()), weaponNotAffordable);
        RequestSelectionSquareForAction newSelectionEvent = new RequestSelectionSquareForAction("Choose one of the enabled squares to catch something on that.\nRemember, before to choose, you can use enable powerUp cards.");
        newSelectionEvent.setSelectionForCatch();
        newSelectionEvent.setNicknameInvolved(event.getNickname());
        newSelectionEvent.setSquaresReachable(squareAvailableToCatch);
        event.getMyVirtualView().update(newSelectionEvent);

    }

    /**
     * <p>
     *     Handles the catch request of a player.
     * </p>
     * <p>
     *     If the player wanted to catch in a normal square, the method will update the ammo and the player's board
     *     If the player wanted to catch in a spawn square , the method will create a new event in which it asks to the player which weapon it want to catch
     * </p>
     *
     * @param event Contains the coordinates of the square in which the player want to catch and the player's nickname
     */
    void handleCatching(SelectionSquare event){
        Square square = game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()];
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        if(square.isSpawn()){
            RequestForChooseAWeaponToCatch newRequest = new RequestForChooseAWeaponToCatch("Choose which weapon do you want to catch. ");
            ArrayList<WeaponCard> weaponsAffordable = new ArrayList<>();
            for(WeaponCard weapon : ((SpawnSquare) square).getWeaponCards()){
                if(weaponIsAffordableByPlayer(player, weapon))
                    weaponsAffordable.add(weapon);
            }
            newRequest.setNicknameInvolved(event.getNickname());
            newRequest.setWeaponsAvailableToCatch(controller.substituteWeaponsCardWithTheirAlias(weaponsAffordable));
            event.getMyVirtualView().update(newRequest);
        }
        else{
            player.setPositionOnTheMap(square);
           addAmmoToPlayer(player.getPlayerBoard(), ((NormalSquare) square).getAmmoTile().getListOfAmmo());
           if(((NormalSquare) square).getAmmoTile().hasPowerUpCard()){
               PowerUpCard powerUpCard;
               powerUpCard = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
               player.drawPowerUpCard(powerUpCard);
               powerUpCard.setOwnerOfCard(player);
               game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(0, powerUpCard);
               game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0);
               powerUpCard.setInTheDeckOfSomePlayer(true);
           }
           game.getBoard().getAmmoTiledeck().getAmmoTilesOnTheMap().remove(((NormalSquare)square).getAmmoTile());
           game.getBoard().getAmmoTiledeck().getAmmoTilesDiscarded().add(((NormalSquare)square).getAmmoTile());
            ((NormalSquare)square).setAmmoTile(null);
            sendNotifyAfterCatching(player);
        }

    }

    /**
     * Sends the update of the map and the player board of the player that has caught after the catch action.
     * @param player The player involved in the catch action.
     */
    private void sendNotifyAfterCatching(Player player) {
        colorsNotEnough = new ArrayList<>();
        weaponCard = null;
        powerUpCardsToDiscard= new ArrayList<>();
        PlayerBoardEvent pBEvent = new PlayerBoardEvent();
        pBEvent.setPlayerBoard(player.getPlayerBoard());
        pBEvent.setNicknameInvolved(player.getNickname());
        pBEvent.setNicknames(game.getListOfNickname());
        game.notify(pBEvent);

        MapEvent mapEvent = new MapEvent();
        mapEvent.setMap(game.getBoard().getMap().getMatrixOfSquares());
        mapEvent.setNicknames(game.getListOfNickname());
        mapEvent.setNicknameInvolved(player.getNickname());
        game.notify(mapEvent);

        ServerEvent notifyActionDoneEvent = new ServerEvent();
        notifyActionDoneEvent.setNotifyAboutActionDone();
        notifyActionDoneEvent.setNumOfAction(game.getNumOfActionOfTheTurn());
        notifyActionDoneEvent.setNumOfMaxAction(game.getNumOfMaxActionForTurn());
        notifyActionDoneEvent.setNicknameInvolved(player.getNickname());
        notifyActionDoneEvent.setNicknames(game.getListOfNickname());
        notifyActionDoneEvent.setMessageForInvolved("Your catch has gone well!");
        notifyActionDoneEvent.setMessageForOthers(player.getNickname() + " has decided to catch! \nLook up his player board to see what he caught!");
        game.notify(notifyActionDoneEvent);

        controller.handleTheEndOfAnAction(false);

    }

    /**
     * Adds to a player the ammo it has caught;
     * @param playerBoard the player's board that has to be updated
     * @param ammoCaught the list of ammo that are caught
     */
    private void addAmmoToPlayer(PlayerBoard playerBoard, ArrayList<ColorOfCard_Ammo> ammoCaught) {
        int blueAmmo = 0;
        int redAmmo = 0;
        int yellowAmmo = 0;
        for (ColorOfCard_Ammo ammo : ammoCaught) {
            if (ammo.equals(BLUE))
                blueAmmo++;
            if (ammo.equals(RED))
                redAmmo++;
            if (ammo.equals(YELLOW))
                yellowAmmo++;
        }
        playerBoard.addAmmoToPlayer(BLUE, blueAmmo);
        playerBoard.addAmmoToPlayer(RED, redAmmo);
        playerBoard.addAmmoToPlayer(YELLOW, yellowAmmo);
    }



    /**
     * <p>
     *     This method return the squares in which the player can catch something.
     * </p>
     *<p>
     *     When the method analyzes the squares, when it finds a spawn square, it fills the second parameter
     *     with the weapons contained in that square that can not be caught because of lack of ammo by the player
     *</p>
      * @param player the player that wants to catch
     * @param weaponNotAffordable this list is filled by the method with the weapons the player can't catch because of lack of ammo
     * @return A list of squares that contains the positions in which the player can catch something
     */
      ArrayList<Square> findSquareWherePlayerCanCatch(Player player, ArrayList<ServerEvent.AliasCard> weaponNotAffordable){
        ArrayList<Square> listOfSquareReachable = new ArrayList<>();
        if(!game.isInFrenzy()) {
            if(player.isUnderThreeDamage()) {
                ArrayList<Square> squaresReachableWithOneMovement = new ArrayList<>();
                controller.findSquaresReachableWithThisMovements(player.getPositionOnTheMap(), 1, squaresReachableWithOneMovement);
                findSquaresWithSomethingToCatch(player, squaresReachableWithOneMovement, listOfSquareReachable, weaponNotAffordable);
                return listOfSquareReachable;
            }
            else{
                ArrayList<Square> squaresReachableWithTwoMovement = new ArrayList<>();
                controller.findSquaresReachableWithThisMovements(player.getPositionOnTheMap(), 2, squaresReachableWithTwoMovement);
                findSquaresWithSomethingToCatch(player, squaresReachableWithTwoMovement, listOfSquareReachable, weaponNotAffordable);
                return listOfSquareReachable;
            }
        }
        else{
            listOfSquareReachable.addAll(findSquaresWherePlayerCanCatchInFrenzyMode(player, weaponNotAffordable, listOfSquareReachable));
            return listOfSquareReachable;

        }

    }

    /**
     * From a list of squares, it fills another one in which there are only the squares in which there is something that can be caught
     * @param player the player that want to catch
     * @param listOfSquares the beginning list
     * @param squaresWithSomething the ending list in which there are only the useful squares
     * @param weaponNotAffordable the weapons that aren't affordable by the player
     */
    private void findSquaresWithSomethingToCatch(Player player, ArrayList<Square> listOfSquares, ArrayList<Square> squaresWithSomething, ArrayList<ServerEvent.AliasCard> weaponNotAffordable) {
        for (Square square : listOfSquares) {
            if (square.isSpawn()) {
                SpawnSquare spawnSquare = (SpawnSquare) square;
                if (canCatchSomethingInThisSquare(spawnSquare)) {
                    addWeaponNotAffordable(spawnSquare, player, weaponNotAffordable);
                    if(weaponNotAffordable.size() < 3)
                        squaresWithSomething.add(square);
                }
            }
            else if (canCatchSomethingInThisSquare(square)) {
                squaresWithSomething.add(square);
            }
        }
    }

    /**
     * Has the same role of findSquareWherePlayerCanCatch method, but this one finds the reachable squares in frenzy mode
     * @param player that want to catch
     * @param weaponNotAffordable list of weapons that aren't affordable by player
     * @param listOfSquareReachable the final list in which at the end there are the reachable squares
     * @return listOfSquareReachable
     */
    private ArrayList<Square> findSquaresWherePlayerCanCatchInFrenzyMode(Player player, ArrayList<ServerEvent.AliasCard> weaponNotAffordable, ArrayList<Square> listOfSquareReachable){
          if(player.getPosition() >= game.getFirstInFrenzyMode()){
              ArrayList<Square> squaresReachableWithTwoMovements = new ArrayList<>();
              controller.findSquaresReachableWithThisMovements(player.getPositionOnTheMap(), 2, squaresReachableWithTwoMovements);
              findSquaresWithSomethingToCatch(player, squaresReachableWithTwoMovements, listOfSquareReachable, weaponNotAffordable);
              return listOfSquareReachable;
          }
          else
          {
              ArrayList<Square> squaresReachableWithThreeMovements = new ArrayList<>();
              controller.findSquaresReachableWithThisMovements(player.getPositionOnTheMap(), 3, squaresReachableWithThreeMovements);
              findSquaresWithSomethingToCatch(player, squaresReachableWithThreeMovements, listOfSquareReachable, weaponNotAffordable);
              return listOfSquareReachable;
          }
    }

    /**
     * Adds to player's deck the weapon that the player want to catch
     * @param event contains the player's nickname, the coordinates of the spawn square in which the player want to catch and the name of the weapon
     */
    void handleSelectionWeaponToCatch(SelectionWeaponToCatch event) {
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        if(player.getPlayerBoard().getWeaponsOwned().size()<3) {
            player.setPositionOnTheMap(findSpawnSquareWithThisCard(event.getNameOfWeaponCard()));
            addWeaponCardToPlayerDeck(player, event.getNameOfWeaponCard());
        }
        else{
            RequestToDiscardWeaponCard requestToDiscardWeaponCard = new RequestToDiscardWeaponCard("Choose one of your weapon card to discard", event.getNameOfWeaponCard());
            requestToDiscardWeaponCard.setNicknameInvolved(event.getNickname());
            event.getMyVirtualView().update(requestToDiscardWeaponCard);
        }
    }

    /**
     * Adds to the weapon cards deck of the player a weapon card handling the payment for the weapond.
     * @param player Player that is trying to catch the weapon.
     * @param nameOfWeaponCard The name of the weapon to catch.
     */
    private void addWeaponCardToPlayerDeck(Player player, String nameOfWeaponCard) {
        Square squareWhereRemoveCard = findSpawnSquareWithThisCard(nameOfWeaponCard);
        ArrayList<PowerUpCard> powerUpCardToDiscard = new ArrayList<>();
        ArrayList<ColorOfCard_Ammo> colorsToRemove = new ArrayList<>();
        WeaponCard weaponCardToAddToDeck = null;
        for(WeaponCard weapon : ((SpawnSquare)squareWhereRemoveCard).getWeaponCards()){
            if(weapon.getName().equals(nameOfWeaponCard)) {
                weaponCardToAddToDeck = weapon;
            }
        }
        ((SpawnSquare) squareWhereRemoveCard).getWeaponCards().remove(weaponCardToAddToDeck);
        if(weaponCardToAddToDeck!=null && isAffordableOnlyWithAmmo(player, weaponCardToAddToDeck.getPriceToBuy())) {
            player.catchWeapon(weaponCardToAddToDeck, null);
            sendNotifyAfterCatching(player);
        }
        else{
            if(weaponCardToAddToDeck!=null)
                colorsNotEnough = getColorsOfAmmoNotEnough(player,weaponCardToAddToDeck.getPriceToBuy());
            for(ColorOfCard_Ammo color : colorsNotEnough){
                if(frequencyOfPowerUpUsableByPlayer(player, color)==1) {
                    powerUpCardToDiscard.add(findPowerUpOfThisColor(player, color));
                    colorsToRemove.add(color);
                }
            }

            for(ColorOfCard_Ammo color : colorsToRemove){
                colorsNotEnough.remove(color);
            }

            if(!colorsNotEnough.isEmpty())
            {
                sendRequestToDiscardPowerUpCard(player, colorsNotEnough);
                weaponCard = weaponCardToAddToDeck;
                return;
            }
            player.catchWeapon(weaponCardToAddToDeck, powerUpCardToDiscard);
            sendNotifyAfterCatching(player);
        }
    }

    /**
     * Getd the power up card of the color specified through the parameter.
     * @param player The player owner of the power up cards deck where is looking for a card.
     * @param color The color of the card that is looking for with.
     * @return The power up card of the color specified in the deck.
     */
     PowerUpCard findPowerUpOfThisColor(Player player, ColorOfCard_Ammo color) {
        for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getColor().equals(color))
                return powerUpCard;
        }
        throw new IllegalArgumentException();
    }


    /**
     * Sends to the player a reqeust to discard a power up card to pay the caught of a weapon.
     * @param player The player that is involved in the catch action.
     * @param colorsOfAmmoNotEnough The color of which there are not enough ammo usable.
     */
    private void sendRequestToDiscardPowerUpCard(Player player, ArrayList<ColorOfCard_Ammo> colorsOfAmmoNotEnough) {
        RequestToDiscardPowerUpCard requestToDiscardPowerUpCardToPay = new RequestToDiscardPowerUpCard();
        requestToDiscardPowerUpCardToPay.setNicknameInvolved(player.getNickname());
        requestToDiscardPowerUpCardToPay.setMessageForInvolved("Choose which power up card you want to discard to pay");
        requestToDiscardPowerUpCardToPay.setToCatch();
        if(!colorsOfAmmoNotEnough.isEmpty()) {
            requestToDiscardPowerUpCardToPay.setPowerUpCards(getPowerUpCardToChooseForDiscard(player, colorsOfAmmoNotEnough.remove(0)));
            game.notify(requestToDiscardPowerUpCardToPay);
        }
        else{
            player.catchWeapon(weaponCard, powerUpCardsToDiscard);
            sendNotifyAfterCatching(player);
        }
    }

    /**
     * Handles the selection of the power up card to discard by the player and calls again the method to send the request to discard, if needed, another power up card.
     * @param event The event received by the client.
     */
     void handleRequestToDiscardPowerUpCardAsAmmo(DiscardPowerUpCard event){
        powerUpCardsToDiscard.add(findPowerUpCard(event.getNameOfPowerUpCard(), event.getColorOfCard(), controller.findPlayerWithThisNickname(event.getNickname())));
        sendRequestToDiscardPowerUpCard(controller.findPlayerWithThisNickname(event.getNickname()), colorsNotEnough);
    }

    /**
     * Gets the power up card in the deck of a player starting from its name and its color passed by parameter.
     * @return The power up card in the deck of the player with the name and color passed by parameter.
     */
     PowerUpCard findPowerUpCard(String nameOfPowerUpCard, ColorOfCard_Ammo colorOfCard, Player player) {
        for (PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if (powerUpCard.getName().equals(nameOfPowerUpCard) && powerUpCard.getColor().equals(colorOfCard))
                return powerUpCard;
    }
     throw new IllegalArgumentException();
    }


    /**
     * Gets the list of power up card discardable to pay the price of a weapon.
     * @param player Player that wants to catch a weapon.
     * @param color The color of which is looking for the power up cards.
     * @return The list of alias cards representing the power up cards usable for the payment.
     */
     ArrayList<ServerEvent.AliasCard> getPowerUpCardToChooseForDiscard(Player player, ColorOfCard_Ammo color) {
        ArrayList<ServerEvent.AliasCard> listOfCards = new ArrayList<>();
        for(PowerUpCard powerUpCard : player.getPlayerBoard().getPowerUpCardsOwned()){
            if(powerUpCard.getColor().equals(color))
                listOfCards.add(new ServerEvent.AliasCard(powerUpCard.getName(), powerUpCard.getDescription(), powerUpCard.getColor()));
        }
        return listOfCards;
    }

    /**
     * Gets the list of color of which there are not enough usable ammo to pay the price.
     * @param player Player that wants to catch a weapon.
     * @param priceToBuy The price to pay.
     * @return A list of color representing the color of ammo not enough to complete the payment.
     */
     ArrayList<ColorOfCard_Ammo> getColorsOfAmmoNotEnough(Player player, ColorOfCard_Ammo[] priceToBuy) {
        ArrayList<ColorOfCard_Ammo> ammoNotEnough = new ArrayList<>();
        for(ColorOfCard_Ammo color : priceToBuy){
            if(!ammoNotEnough.contains(color) && frequencyOfAmmoUsableByPlayer(player, color, false)<frequencyAmmoInPrice(priceToBuy, color)){
                ammoNotEnough.add(color);
            }
        }
        return ammoNotEnough;
    }

    /**
     * Check if a price can be payed only with ammo or not.
     * @param player The player that has to pay.
     * @param priceToBuy The price to pay.
     * @return True if the price is payable only with ammo, false otherwise.
     */
     boolean isAffordableOnlyWithAmmo(Player player, ColorOfCard_Ammo[] priceToBuy) {
        int numOfRedAmmoRequired = frequencyAmmoInPrice(priceToBuy, RED);
        int numOfBlueAmmoRequired = frequencyAmmoInPrice(priceToBuy, BLUE);
        int numOfYellowAmmoRequired = frequencyAmmoInPrice(priceToBuy, YELLOW);
        return frequencyOfAmmoUsableByPlayer(player, RED, false) >= numOfRedAmmoRequired && frequencyOfAmmoUsableByPlayer(player, BLUE,false) >= numOfBlueAmmoRequired && frequencyOfAmmoUsableByPlayer(player, YELLOW, false)>=numOfYellowAmmoRequired;

    }

    /**
     * Gets the spawn square that contains the weapon card with the name passed by parameter.
     * @return The spawn square that contains the weapon card.
     */
    private Square findSpawnSquareWithThisCard(String nameOfWeaponCard){
        Square[][] matrixOfSquare = game.getBoard().getMap().getMatrixOfSquares();
        for(int i = 0; i< matrixOfSquare.length; i++){
            for (int j = 0; j< matrixOfSquare[i].length; j++){
                if(matrixOfSquare[i][j]!=null && matrixOfSquare[i][j].isSpawn()){
                    for(WeaponCard weapon : ((SpawnSquare)matrixOfSquare[i][j]).getWeaponCards())
                        if(weapon.getName().equals(nameOfWeaponCard))
                            return matrixOfSquare[i][j];
                }
            }
        }
        throw  new IllegalArgumentException();
    }

    /**
     * Handles the ultimate phase of the catch action where to catch a weapon the player had to discard one of his.
     * @param event Event received by client.
     */
    void handleSelectionWeaponToCatchAfterDiscard(SelectionWeaponToDiscard event){
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        player.setPositionOnTheMap(findSpawnSquareWithThisCard(event.getNameOfWeaponCardToRemove()));
        changeWeaponCardsBetweenSquareAndDeck(player, event.getNameOfWeaponCardToRemove(), event.getNameOfWeaponCardToAdd());

    }

    /**
     * Handles the change between the weapon that the player wants to catch and the weapon he wants to discard.
     * @param player Player that wants to do the change of weapon.
     * @param nameOfWeaponCardToRemoveFromTheSquare The name of the weapon card that the player wants to catch from the spawn square.
     * @param nameOfWeaponCardToAddToTheSquare The name of the weapon card that the player wants to discard and that will return on the spawn square.
     */
    private void changeWeaponCardsBetweenSquareAndDeck(Player player, String nameOfWeaponCardToRemoveFromTheSquare, String nameOfWeaponCardToAddToTheSquare) {
        Square squareWhereDoChange = findSpawnSquareWithThisCard(nameOfWeaponCardToRemoveFromTheSquare);
        WeaponCard weaponCardToAddToDeck = null;
        WeaponCard weaponCardToAddToSquare = null;
        for(WeaponCard weapon : ((SpawnSquare)squareWhereDoChange).getWeaponCards()){
            if(weapon.getName().equals(nameOfWeaponCardToRemoveFromTheSquare)){
                weaponCardToAddToDeck = weapon;
            }
        }

        for(WeaponCard weapon : player.getPlayerBoard().getWeaponsOwned()){
            if(weapon.getName().equals(nameOfWeaponCardToAddToTheSquare)){
                weaponCardToAddToSquare = weapon;
                weaponCardToAddToSquare.setLoad(true);
                weaponCardToAddToSquare.setOwnerOfCard(null);
            }
        }
        player.getPlayerBoard().getWeaponsOwned().remove(weaponCardToAddToSquare);
        ((SpawnSquare)squareWhereDoChange).getWeaponCards().add(weaponCardToAddToSquare);
        if(weaponCardToAddToDeck!=null){
            addWeaponCardToPlayerDeck(player, weaponCardToAddToDeck.getName());
            System.out.println(weaponCardToAddToDeck.getName());
        }

    }

    /**
     * This method fills the array that is passed with the weapons that the player can't catch in the square because of lack of ammo
     * @param square the spawn square in which the player would like to catch weapons
     * @param player the player that want to catch
     * @param weaponNotAffordable the list that, at the end of the method, will contain the weapons that can't be caught
     */
     void addWeaponNotAffordable(SpawnSquare square, Player player, ArrayList<ServerEvent.AliasCard> weaponNotAffordable) {
        for(WeaponCard card : square.getWeaponCards()){
            if(!weaponIsAffordableByPlayer(player, card))
                weaponNotAffordable.add(new ServerEvent.AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
    }

    /**
     * This method check if a weapon can be caught by a player, that has the ammo that are passed by the first parameter
     * @param card the weapon that contains the price that the method has to check if it would be payed with the ammo
     * @return a boolean that is true if the ammo are more than the weapon's price
     */
     private boolean weaponIsAffordableByPlayer(Player player, WeaponCard card) {

        int numOfRedAmmoRequired = frequencyAmmoInPrice(card.getPriceToBuy(), RED);
        int numOfBlueAmmoRequired = frequencyAmmoInPrice(card.getPriceToBuy(), BLUE);
        int numOfYellowAmmoRequired = frequencyAmmoInPrice(card.getPriceToBuy(), YELLOW);
        return frequencyOfAmmoUsableByPlayer(player, RED, true) >= numOfRedAmmoRequired && frequencyOfAmmoUsableByPlayer(player, BLUE,true) >= numOfBlueAmmoRequired && frequencyOfAmmoUsableByPlayer(player, YELLOW, true)>=numOfYellowAmmoRequired;

    }

    /**
     * This method calculates the frequency of a specific color of ammo in the price that is passed
     * @param price the price in which the method has to calculate the frequency of a color
     * @param colorToCheck the color of which the method has to calculate the frequency
     * @return the frequency of the colorToCheck in priceToBuy
     */
    int frequencyAmmoInPrice(ColorOfCard_Ammo[] price, ColorOfCard_Ammo colorToCheck){
        int frequency=0;
        if(price!=null) {
            for (ColorOfCard_Ammo color : price) {
                if (color.equals(colorToCheck)) {
                    frequency++;
                }
            }
        }
        return frequency;

    }

    /**
     * Gets the right weapon card according with the name passed by parameter.
     * @return The weapon card with the name passed by parameter.
     */
     WeaponCard getWeaponCardFromName(String name){
        for(Player player : game.getPlayers()){
            if(!player.getNickname().equals("terminator")){
                for(WeaponCard weapon : player.getPlayerBoard().getWeaponsOwned()){
                    if(weapon.getName().equals(name))
                        return  weapon;
                }
            }
        }

        for(WeaponCard weapon : game.getBoard().getWeaponsDeck().getWeaponCards()){
            if(weapon.getName().equals(name))
                return weapon;
        }

        throw new IllegalArgumentException();
    }

    /**
     * This method calculates the frequency of ammo that a player can use
     * @param colorToCheck the color of the ammo of which the method has to calculate the frequency
     * @return the number of ammo that can be used
     */
     int frequencyOfAmmoUsableByPlayer(Player player, ColorOfCard_Ammo colorToCheck, boolean powerUpCard){

        int frequency = 0;
        for(Ammo ammo : player.getPlayerBoard().getAmmo()){
            if(ammo.getColor().equals(colorToCheck) && ammo.isUsable()){
                frequency++;
            }
        }
        if(powerUpCard) {
            frequency = frequency + frequencyOfPowerUpUsableByPlayer(player, colorToCheck);
        }
        return frequency;
    }

    /**
     * Gets the number of the power up of the color passed by parameter that the player owns.
     * @return The frequency of the power up in the player's deck of the colorToCheck.
     */
     int frequencyOfPowerUpUsableByPlayer(Player player, ColorOfCard_Ammo colorToCheck){
        int frequency = 0;
        for (PowerUpCard card : player.getPlayerBoard().getPowerUpCardsOwned()) {
            if (card.getColor().equals(colorToCheck))
                frequency++;
        }
        return frequency;
    }



    /**
     * Checks if in the square there is something the player can be catch
     * @param square the position that has to be checked
     * @return true if there is something that can be caught in the square passed as parameter
     */
    private boolean canCatchSomethingInThisSquare(Square square){
        boolean canCatch = false;
        if(square.isSpawn()){
            if(((SpawnSquare) square).getWeaponCards() != null){
                canCatch = true;
            }}
            else{
                if(((NormalSquare) square).getAmmoTile()!= null)
                    canCatch = true;
            }

        return canCatch;
    }

}
