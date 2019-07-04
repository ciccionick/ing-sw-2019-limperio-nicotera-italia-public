package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * Represents the weapon card Lock rifle.
 * @author Giuseppe Italia
 */

public class LockRifle extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
            switch (typeOfAttack){
                case 1:
                    basicEffect(involvedPlayers.get(0).getPlayer());
                    break;
                case 2:
                    withSecondLock(involvedPlayers.get(0).getPlayer());
                    break;

                    default:
                        throw  new IllegalArgumentException();
            }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public LockRifle() {
        super(BLUE, "Lock rifle");
        String description;
        description = "BASIC EFFECT:\nDeal 2 damage and 1 mark to 1 target you can see.\n" +
                "WITH SECOND LOCK:\nDeal 1 mark to a different target you can see.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH SECOND LOCK");
        getDescriptionsOfAttack().add("Deal 2 damage and 1 mark to 1 target you can see");
        getDescriptionsOfAttack().add("Deal 1 mark to a different target you can see");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{RED});
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = {BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assign 2 damage and 1 mark to the player passed by parameter.
     */
    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Assign a mark to the player passed by parameter.
     */
    private void withSecondLock(Player player){
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

}
