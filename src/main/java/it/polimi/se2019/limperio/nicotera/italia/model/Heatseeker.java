package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the weapon card Heatseeker.
 * @author Giuseppe Italia.
 */

public class Heatseeker extends WeaponCard {

    /**
     * This weapon has only an effect. Assigns three damage to the only player in the list of involved players.
     * @param typeOfAttack Number of the effect of the weapon to use.
     * @param involvedPlayers List of object that contains player or/and square involved in the attack.
     */
    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        involvedPlayers.get(0).getPlayer().assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public  Heatseeker() {
        super(RED, "Heatseeker");
        Boolean[] kindOfAttack = {true, false, false, false};
        String description = "EFFECT:\nChoose 1 target you cannot see and deal 3 damage to it.\n" +
                "Notes:\nYes, this can only hit targets you cannot see.";
        getNamesOfAttack().add("EFFECT");
        getDescriptionsOfAttack().add("Choose 1 target you cannot see and deal 3 damage to it");
        setDescription(description);
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED, YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
