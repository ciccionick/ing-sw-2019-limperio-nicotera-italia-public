package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;


import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

/**
 * Represents the weapon card Vortex cannon.
 * @author Giuseppe Italia
 */


public class VortexCannon extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {

        switch (typeOfAttack) {
            case 1:
                basicEffect(involvedPlayers.get(1).getPlayer(), involvedPlayers.get(0).getSquare());
                break;
            case 2:
                for (int i = 1; i < involvedPlayers.size(); i++) {
                    withBlackHoleEffect(involvedPlayers.get(i).getPlayer(), involvedPlayers.get(0).getSquare());
                }
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public VortexCannon() {
        super(RED, "Vortex cannon");
        String description;
        description = "BASIC EFFECT: \nChoose a square you can see, but not your square. Call it the vortex. \nChoose a target on the vortex or 1 move away from it. \nMove it onto the vortex and give it 2 damage.\n" +
                "WITH BLACK HOLE: \nChoose up to 2 other targets on the vortex or 1 move away from it. \nMove them onto the vortex and give them each 1 damage.\n" +
                "Notes: \nThe 3 targets must be different, but some might start on the same square. \nIt is legal to choose targets on your square, on the vortex, or even on squares you can't see. \nThey all end up on the vortex.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH BLACK HOLE");
        getDescriptionsOfAttack().add("Choose a square you can see, but not your square. Call it the vortex. \nChoose a target on the vortex or 1 move away from it. \nMove it onto the vortex and give it 2 damage");
        getDescriptionsOfAttack().add("Choose up to 2 other targets on the vortex or 1 move away from it. \nMove them onto the vortex and give them each 1 damage");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{RED});
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = new ColorOfCard_Ammo[]{BLUE, RED};
        setPriceToReload(reloadPrice);
    }

    /**
     * Moves tha player passed by parameter on the square passed by parameter and then assigns him two damage.
     */
    private void basicEffect(Player player, Square square) {
        player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    /**
     * Moves tha player passed by parameter on the square passed by parameter and then assigns him a damage.
     */
    private void withBlackHoleEffect(Player player, Square square){
        player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

}