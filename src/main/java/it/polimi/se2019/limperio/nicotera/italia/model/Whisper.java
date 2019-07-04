package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * Represents the weapon card Whisper.
 * @author Giuseppe Italia
 */

public class Whisper extends WeaponCard {


    /**
     * This weapon has only an effect. Assigns three damage and a mark to the only player in the list of involved players.
     * @param typeOfAttack Number of the effect of the weapon to use.
     * @param involvedPlayers List of object that contains player or/and square involved in the attack.
     */
    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        involvedPlayers.get(0).getPlayer().assignDamage(getOwnerOfCard().getColorOfFigure(),3);
        involvedPlayers.get(0).getPlayer().assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */

    public Whisper() {
        super(BLUE, "Whisper");
        Boolean[] kindOfAttack = {true, false, false, false};
        String description = "EFFECT:\nDeal 3 damage and 1 mark to 1 target you can see. \nYour target must be at least 2 moves away from you.\n" +
                "Notes:\nFor example, in the 2-by-2 room, you cannot shoot a target on an adjacent square, \nbut you can shoot a target on the diagonal. \nIf you are beside a door, you can't shoot a target on the other side of the door, \nbut you can shoot a target on a different square of that room.";
        setDescription(description);
        getNamesOfAttack().add("EFFECT");
        getDescriptionsOfAttack().add("Deal 3 damage and 1 mark to 1 target you can see. \nYour target must be at least 2 moves away from you");
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE, YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
