package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.CatchEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.CatchActionDoneEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.awt.*;
import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class handles the checking of the catch actions of the players
 * @author Francesco Nicotera
 */
class CatchController {
    private final Game game;
    private Controller controller;

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
    void replyToRequestToCatch(RequestToCatchByPlayer event){
        ArrayList<ServerEvent.AliasCard> weaponNotAffordable = new ArrayList<>();
        ArrayList<Square> squareAvailableToCatch = findSquareWherePlayerCanCatch(controller.findPlayerWithThisNickname(event.getNickname()), weaponNotAffordable);
        SelectionViewForSquareWhereCatch newSelectionEvent = new SelectionViewForSquareWhereCatch("Scegli in quale fra questi quadrati vuoi raccogliere");
        newSelectionEvent.getNicknames().add(event.getNickname());
        newSelectionEvent.setSquaresReachableForCatch(squareAvailableToCatch);
        newSelectionEvent.setWeaponNotAvailableForLackOfAmmo(weaponNotAffordable);
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
    void handleCatching(CatchEvent event){
        Square square = game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()];
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        if(square.isSpawn()){
            RequestForChooseAWeaponToCatch newRequest = new RequestForChooseAWeaponToCatch("Scegli arma");
            ArrayList<WeaponCard> weaponsAffordable = new ArrayList<>();
            for(WeaponCard weapon : ((SpawnSquare) square).getWeaponCards()){
                if(weaponIsAffordableByPlayer(player.getPlayerBoard().getAmmo(), weapon))
                    weaponsAffordable.add(weapon);
            }
            newRequest.setNicknameInvolved(event.getNickname());
            newRequest.setWeaponsAvailableToCatch(controller.substituteWeaponsCardWithTheirAlias(weaponsAffordable));
            newRequest.setRow(event.getRow());
            newRequest.setColumn(event.getColumn());
            game.notify(newRequest);
        }
        else{
           addAmmoToPlayer(player.getPlayerBoard(), ((NormalSquare) square).getAmmoTile().getAmmos());
           if(((NormalSquare) square).getAmmoTile().hasPowerUpCard()){
               PowerUpCard powerUpCard;
               powerUpCard = game.getBoard().getPowerUpDeck().getPowerUpCards().get(0);
               player.drawPowerUpCard(powerUpCard);
               game.getBoard().getPowerUpDeck().getUsedPowerUpCards().add(0, powerUpCard);
               game.getBoard().getPowerUpDeck().getPowerUpCards().remove(0);
               powerUpCard.setInTheDeckOfSomePlayer(true);
           }
           game.incrementNumOfActionsOfThisTurn();
           CatchActionDoneEvent actionDoneEvent = new CatchActionDoneEvent("avvenuta raccolta", ((NormalSquare) square).getAmmoTile().getAmmos());
           actionDoneEvent.setCatchActionOfAmmoTile(true);
           actionDoneEvent.setNumOfAction(game.getNumOfActionOfTheTurn());
        }
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
                return  listOfSquareReachable;
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
    void addWeaponToPlayer(SelectionWeaponToCatch event) {
        Player player = controller.findPlayerWithThisNickname(event.getNickname());
        SpawnSquare square = (SpawnSquare) game.getBoard().getMap().getMatrixOfSquares()[event.getRow()][event.getColumn()];
        String nameOfWeaponCaught = null;
        for (WeaponCard card : square.getWeaponCards()) {
            if (card.getName().equals(event.getNameOfWeaponCard())) {
                nameOfWeaponCaught = card.getName();
                player.getPlayerBoard().getWeaponsOwned().add(card);
                card.setOwnerOfCard(player);
                for (ColorOfCard_Ammo ammo : card.getPriceToBuy()) {
                    player.getPlayerBoard().removeAmmoOfThisColor(ammo);
                }
                square.getWeaponCards().remove(card);
                break;
            }
        }
        game.incrementNumOfActionsOfThisTurn();
        CatchActionDoneEvent catchWeaponDone = new CatchActionDoneEvent("Avvenuta raccolta dell'arma", null);
        catchWeaponDone.setNameOfWeaponCaught(nameOfWeaponCaught);
        catchWeaponDone.setNicknameInvolved(event.getNickname());
    }
    /**
     * This method fills the array that is passed with the weapons that the player can't catch in the square because of lack of ammo
     * @param square the spawn square in which the player would like to catch weapons
     * @param player the player that want to catch
     * @param weaponNotAffordable the list that, at the end of the method, will contain the weapons that can't be caught
     */
     void addWeaponNotAffordable(SpawnSquare square, Player player, ArrayList<ServerEvent.AliasCard> weaponNotAffordable) {
        for(WeaponCard card : square.getWeaponCards()){
            if(!weaponIsAffordableByPlayer(player.getPlayerBoard().getAmmo(), card))
                weaponNotAffordable.add(new ServerEvent.AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
    }

    /**
     * This method check if a weapon can be caught by a player, that has the ammo that are passed by the first parameter
     * @param ammos the ammo that the player has
     * @param card the weapon that contains the price that the method has to check if it would be payed with the ammo
     * @return a boolean that is true if the ammo are more than the weapon's price
     */
     private boolean weaponIsAffordableByPlayer(ArrayList<Ammo> ammos, WeaponCard card) {

        int numOfRedAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), RED);
        int numOfBlueAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), ColorOfCard_Ammo.BLUE);
        int numOfYellowAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), ColorOfCard_Ammo.YELLOW);
        return frequencyOfAmmoUsableByPlayer(ammos, RED) >= numOfRedAmmoRequired && frequencyOfAmmoUsableByPlayer(ammos, BLUE) >= numOfBlueAmmoRequired && frequencyOfAmmoUsableByPlayer(ammos, YELLOW)>=numOfYellowAmmoRequired;

    }

    /**
     * This method calculates the frequency of a specific color of ammo in the price that is passed
     * @param priceToBuy the price in which the method has to calculate the frequency of a color
     * @param colorToCheck the color of which the method has to calculate the frequency
     * @return the frequency of the colorToCheck in priceToBuy
     */
     private int frequencyAmmosInPriceToBuy (ColorOfCard_Ammo[] priceToBuy, ColorOfCard_Ammo colorToCheck){
        int frequency=0;
        if(priceToBuy!=null) {
            for (ColorOfCard_Ammo color : priceToBuy) {
                if (color.equals(colorToCheck)) {
                    frequency++;
                }
            }
        }
            return frequency;

    }

    /**
     * This method calculates the frequency of ammo that a player can use
     * @param ammoContained this list contains ALL the ammo of a player, both available and not available
     * @param colorToCheck the color of the ammo of which the method has to calculate the frequency
     * @return the number of ammo that can be used
     */
     int frequencyOfAmmoUsableByPlayer(ArrayList<Ammo> ammoContained, ColorOfCard_Ammo colorToCheck){
        int frequency = 0;
        for(Ammo ammo : ammoContained){
            if(ammo.getColor().equals(colorToCheck) && ammo.isUsable()){
                frequency++;
            }
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
