package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * Represents the weapon card Shotgun.
 * @author Giuseppe Italia
 */

public class Shotgun extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
            switch (typeOfAttack) {
                case 1:
                    basicEffect(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
                    break;
                case 4:
                    this.longBarrelMode(involvedPlayers.get(0).getPlayer());
                    break;

                    default: throw new IllegalArgumentException();
            }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public Shotgun() {
        super(YELLOW, "Shotgun");
        String description;
        description = "BASIC MODE: \nDeal 3 damage to 1 target on your square. \nIf you want, you may then move the target 1 square.\n" +
                      "IN LONG BARREL MODE: \nDeal 2 damage to 1 target on any square exactly one move away.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN LONG BARREL MODE");
        getDescriptionsOfAttack().add("Deal 3 damage to 1 target on your square. \nIf you want, you may then move the target 1 square");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target on any square exactly one move away");
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, YELLOW};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assigns three damage to the player passed by parameter and then, if the square is not null, moves him on that square.
     */
    private void basicEffect(Player player, Square square){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
        if(square != null)
            player.setPositionOnTheMap(square);
    }

    /**
     * Assigns two damage to the player passed by parameter.
     */
    private void longBarrelMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

}
