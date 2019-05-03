package it.polimi.se2019.limperio.nicotera.italia.controller;



import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

class CatchController {
    private final Game game;
    private Controller controller;
    CatchController(Game game, Controller controller){
        this.game=game;
        this.controller=controller;
    }

    void replyToRequestToCatch(RequestToCatchByPlayer event){
        ArrayList<ModelEvent.AliasCard> weaponNotAffordable = new ArrayList<>();
        ArrayList<Square> squareAvailableToCatch = findSquareWherePlayerCanCatch(controller.findPlayerWithThisNickname(event.getNickname()), weaponNotAffordable);
        SelectionViewForSquareWhereCatch newSelectionEvent = new SelectionViewForSquareWhereCatch("Scegli in quale fra questi quadrati vuoi raccogliere");
        newSelectionEvent.getNickname().add(event.getNickname());
        newSelectionEvent.setSquaresReachableForCatch(squareAvailableToCatch);
        newSelectionEvent.setWeaponNotAvailableForLackOfAmmos(weaponNotAffordable);
        event.getMyVirtualView().update(newSelectionEvent);

    }



    private ArrayList<Square> findSquareWherePlayerCanCatch(Player player, ArrayList<ModelEvent.AliasCard> weaponNotAffordable){
        ArrayList<Square> listOfSquareReachable = new ArrayList<>();
        SpawnSquare spawnSquare;
        NormalSquare normalSquare;
        if(player.getPositionOnTheMap().isSpawn()) {
            spawnSquare = (SpawnSquare) player.getPositionOnTheMap();
            if(!spawnSquare.getWeaponCards().isEmpty()) {
                addWeaponNotAffordable(spawnSquare, player, weaponNotAffordable);
                listOfSquareReachable.add(player.getPositionOnTheMap());
            }
        }
        else{
            normalSquare= (NormalSquare) player.getPositionOnTheMap();
            if(normalSquare.getAmmoTile()!=null)
                listOfSquareReachable.add(player.getPositionOnTheMap());
        }
        for(Square square : player.getPositionOnTheMap().getAdjSquares()){
            if(square!=null) {
                if (square.isSpawn()) {
                    spawnSquare = (SpawnSquare) square;
                    if (!spawnSquare.getWeaponCards().isEmpty()){
                        addWeaponNotAffordable(spawnSquare, player, weaponNotAffordable);
                        listOfSquareReachable.add(square);
                    }
                } else {
                    normalSquare = (NormalSquare) square;
                    if (normalSquare.getAmmoTile() != null)
                        listOfSquareReachable.add(normalSquare);
                }
            }
        }
        return listOfSquareReachable;

    }

    private void addWeaponNotAffordable(SpawnSquare square, Player player, ArrayList<ModelEvent.AliasCard> weaponNotAffordable) {
        for(WeaponCard card : square.getWeaponCards()){
            if(!weaponIsAffordableByPlayer(player.getPlayerBoard().getAmmo(), card))
                weaponNotAffordable.add(new ModelEvent.AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
    }

    private boolean weaponIsAffordableByPlayer(ArrayList<Ammo> ammos, WeaponCard card) {

        int numOfRedAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), RED);
        int numOfBlueAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), ColorOfCard_Ammo.BLUE);
        int numOfYellowAmmoRequired = frequencyAmmosInPriceToBuy(card.getPriceToBuy(), ColorOfCard_Ammo.YELLOW);
        return frequencyOfAmmosUsableByPlayer(ammos, RED) >= numOfRedAmmoRequired && frequencyOfAmmosUsableByPlayer(ammos, BLUE) >= numOfBlueAmmoRequired && frequencyOfAmmosUsableByPlayer(ammos, YELLOW)>=numOfYellowAmmoRequired;

    }


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

    private int frequencyOfAmmosUsableByPlayer (ArrayList<Ammo> ammos, ColorOfCard_Ammo colorToCheck){
        int frequency = 0;
        for(Ammo ammo : ammos){
            if(ammo.getColor().equals(colorToCheck) && ammo.isUsable()){
                frequency++;
            }
        }
        return frequency;
    }
}
