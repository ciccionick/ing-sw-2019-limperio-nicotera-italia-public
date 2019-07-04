package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * Represents the weapon card Plasma gun.
 * @author Giuseppe Italia
 */

public class PlasmaGun extends WeaponCard{


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
            switch (typeOfAttack) {
                case 1:
                    basicEffect(involvedPlayers.get(0).getPlayer());
                    break;
                case 2:
                    withPhaseGlide(involvedPlayers.get(0).getSquare());
                    break;
                case 3:
                    withChargedShot(involvedPlayers.get(0).getPlayer());
                    break;
                    default:
                        throw new IllegalArgumentException();
            }
    }


    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public PlasmaGun() {
        super(BLUE, "Plasma gun");
        String description = "BASIC EFFECT:\nDeal 2 damage to 1 target you can see.\n" +
                "WITH PHASE GLIDE:\nMove 1 or 2 squares. This effect can be used either before or after the basic effect.\n" +
                "WITH CHARGED SHOT:\nDeal 1 additional damage to your target.\n" +
                "Notes:\nThe two moves have no ammo cost. You don't have to be able to see your target when you play the card. \nFor example, you can move 2 squares and shoot a target you now see. \nYou cannot use 1 move before shooting and 1 move after.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, true, false};
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH PHASE GLIDE");
        getNamesOfAttack().add("WITH CHARGED SHOT");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target you can see");
        getDescriptionsOfAttack().add("Move 1 or 2 squares. \nThis effect can be used either before or after the basic effect");
        getDescriptionsOfAttack().add("Deal 1 additional damage to your target");
        setPriceToPayForEffect2(new ColorOfCard_Ammo[]{BLUE});
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, YELLOW};
        setPriceToReload(reloadPrice);
    }


    /**
     * Assigns two damage to the player passed by parameter.
     */
    private void basicEffect(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 2);
    }

    /**
     * Assigns one damage to the player passed by parameter.
     */
    private void withChargedShot(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Moves the owner of the card on the square passed by parameter
     */
    private void withPhaseGlide(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);

    }
}
