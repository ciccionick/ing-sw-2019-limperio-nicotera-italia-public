package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the weapon card Tractor beam.
 * @author Giuseppe Italia
 */


public class TractorBeam extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        switch (typeOfAttack){
            case 1:
                basicMode(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
                break;
            case 4:
                inPunisherMode(involvedPlayers.get(0).getPlayer());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public TractorBeam() {
        super(BLUE, "Tractor beam");
        String description;
        description = "BASIC MODE: \nMove a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.\n" +
                "IN PUNISHER MODE: \nChoose a target 0, 1, or 2 moves away from you. \nMove the target to your square and deal 3 damage to it.\n" +
                "Notes: \nYou can move a target even if you can't see it. \nThe target ends up in a place where you can see and damage it. \nThe moves do not have to be in the same direction.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN PUNISHER MODE");
        getDescriptionsOfAttack().add("Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Choose a target 0, 1, or 2 moves away from you. \nMove the target to your square and deal 3 damage to it");
        setHasThisKindOfAttack(kindOfAttack);
        setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{RED, YELLOW});
        ColorOfCard_Ammo[] reloadPrice = {BLUE};
        setPriceToReload(reloadPrice);
    }

    /**
     * Moves the player passed by parameter to the square passed by parameter and then assigns him a damage.
     */
    private void basicMode(Player player, Square square){
        player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Moves the player passed by parameter in the square of the owner of the card and then assigns him three damage.
     */
    private void inPunisherMode(Player player){
        player.setPositionOnTheMap(this.getOwnerOfCard().getPositionOnTheMap());
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
    }

}
