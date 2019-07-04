package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the weapon card Cyberblade.
 * @author Giuseppe Italia.
 */

public class Cyberblade extends WeaponCard {

    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        switch (typeOfAttack) {
            case 1:
                basicEffect(involvedPlayers.get(0).getPlayer());
                break;
            case 2:
                withShadowstepEffect(involvedPlayers.get(0).getSquare());
                break;
            case 3:
                withSliceAndDice(involvedPlayers.get(0).getPlayer());
                break;

            default:
                throw new IllegalArgumentException();
        }

    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public Cyberblade() {
        super(YELLOW, "Cyberblade");
        String description;
        description = "BASIC EFFECT:\nDeal 2 damage to 1 target on your square.\n" +
                "WITH SHADOWSTEP:\nMove 1 square before or after the basic effect.\n" +
                "WITH SLICE AND DICE:\nDeal 2 damage to a different target on your square. \nThe shadowstep may be used before or after this effect.\n" +
                "Notes:\nCombining all effects allows you to move onto a square and whack 2 people; \nor whack somebody, move, and whack somebody else; \nor whack 2 people and then move.\n";
        setDescription(description);
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH SHADOWSTEP");
        getNamesOfAttack().add("WITH SLICE AND DICE");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target on your square");
        getDescriptionsOfAttack().add("Move 1 square before or after the basic effect");
        getDescriptionsOfAttack().add("Deal 2 damage to a different target on your square. \nThe shadowstep may be used before or after this effect");
        setPriceToPayForEffect1(null);
        setPriceToPayForEffect2(new ColorOfCard_Ammo[]{YELLOW});
        Boolean[] kindOfAttack = {true, true, true, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, RED};
        setPriceToReload(reloadPrice);

    }

    /**
     * Assigns 2 damage to the player passed by parameter.
     */
    private void withSliceAndDice(Player player) {
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    /**
     * Moves the owner of the card on the square passed by parameter.
     */
    private void withShadowstepEffect(Square square) {
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }

    /**
     * Assigns 2 damage to the player passed by parameter.
     */
    public void basicEffect(Player player) {
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }
}

