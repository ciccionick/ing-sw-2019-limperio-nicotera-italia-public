package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * Represents the weapon card Furnace
 * @author Giuseppe Italia
 */

public class Furnace extends WeaponCard {

    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        Square squareForRoom;
        squareForRoom = involvedPlayers.get(0).getSquare();
        if(typeOfAttack==1) {
            for (InvolvedPlayer involvedPlayer : involvedPlayers) {
                basicMode(involvedPlayer.getPlayer());
            }
        }
        else {
            for(Player player : squareForRoom.getPlayerOnThisSquare()){
                inCozyFireMode(player);
            }

        }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public Furnace() {
        super(RED, "Furnace");
        String description = "BASIC MODE:\nChoose a room you can see, but not the room you are in. \nDeal 1 damage to everyone in that room.\n" +
                "IN COZY FIRE MODE:\nChoose a square exactly one move away. \nDeal 1 damage and 1 mark to everyone on that square.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN COZY FIRE MODE");
        getDescriptionsOfAttack().add("Choose a room you can see, but not the room you are in. \nDeal 1 damage to everyone in that room");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Choose a square exactly one move away. \nDeal 1 damage and 1 mark to everyone on that square");
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, RED};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assigns one damage to the player passed by parameter.
     */
    private void basicMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Assigns one damage and one mark to the player passed by parameter.
     */
    private void inCozyFireMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
        player.assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
    }
}
