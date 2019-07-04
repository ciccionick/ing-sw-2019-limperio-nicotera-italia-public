package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the weapon card Hellion.
 * @author Giuseppe Italia
 */

public class Hellion extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        if(typeOfAttack==1)
            basicMode(involvedPlayers.get(0).getPlayer());
        else
            inNanoTracerMode(involvedPlayers.get(0).getPlayer());

    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public Hellion() {
        super(RED, "Hellion");
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        String description = "BASIC MODE:\nDeal 1 damage to 1 target you can see at least 1 move away. \nThen give 1 mark to that target and everyone else on that square.\n" +
                "IN NANO-TRACER MODE:\nDeal 1 damage to 1 target you can see at least 1 move away. \nThen give 2 marks to that target and everyone else on that square.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN NANO-TRACER MODE");
        getDescriptionsOfAttack().add("Deal 1 damage to 1 target you can see at least 1 move away. \nThen give 1 mark to that target and everyone else on that square");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Deal 1 damage to 1 target you can see at least 1 move away. \nThen give 2 marks to that target and everyone else on that square");
        setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{RED});
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, YELLOW};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assign a damage to the player passed by parameter and one mark to every player in his square.
     */
    private void basicMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOnThisSquare()){
            otherPlayer.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
        }

    }

    /**
     * Assign a damage to the player passed by parameter and two marks to every player in his square.
     */
    private void inNanoTracerMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOnThisSquare()){
            otherPlayer.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 2);
        }
    }

}
