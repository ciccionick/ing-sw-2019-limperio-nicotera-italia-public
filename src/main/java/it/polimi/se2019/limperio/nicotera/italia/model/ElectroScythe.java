package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * Represents the weapon card ElectroScythe
 * @author Giuseppe Italia.
 */

public class ElectroScythe extends WeaponCard
{
    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        switch (typeOfAttack) {
                case 1:
                    basicMode(involvedPlayers.get(0).getSquare());
                    break;
                case 4:
                    inReaperMode(involvedPlayers.get(0).getSquare());
                    break;
                default: throw new IllegalArgumentException();
        }

    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public ElectroScythe() {
        super(BLUE, "Electroscythe");
        String description;
        description = "BASIC MODE:\nDeal 1 damage to every other player on your square. \n" +
                "IN REAPER MODE:\nDeal 2 damage to every other player on your square.";
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN REAPER MODE");
        getDescriptionsOfAttack().add("Deal 1 damage to every other player on your square");
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add("Deal 2 damage to every other player on your square");
        setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{BLUE, RED});
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] reloadPrice = {BLUE};
        setPriceToReload(reloadPrice);
    }


    /**
     * Assigns 1 damage to every players in the square of owner of the card but not the owner of the card.
     */
    private void basicMode(Square square){
        for(Player player: square.getPlayerOnThisSquare()) {
            if(!player.equals(this.getOwnerOfCard())) {
                player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
            }
        }
    }

    /**
     * Assigns 2 damage to every players in the square of owner of the card but not the owner of the card.
     */
    private void inReaperMode(Square square) {
        for(Player player: square.getPlayerOnThisSquare()) {
            if(!player.equals(this.getOwnerOfCard()))
                player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        }

    }
}