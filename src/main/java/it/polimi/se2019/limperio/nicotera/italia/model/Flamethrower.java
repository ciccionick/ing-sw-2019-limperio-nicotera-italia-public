package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represent the weapon card Flamethrower.
 *
 * @author Giuseppe Italia.
 */

public class Flamethrower extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        if (typeOfAttack==1){
            for (InvolvedPlayer involvedPlayer : involvedPlayers){
                basicMode(involvedPlayer.getPlayer());
            }
        }
        else{
            barbecueMode(involvedPlayers.get(0).getSquare(), involvedPlayers.get(1).getSquare());
        }

    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public Flamethrower() {
        super(RED, "Flamethrower");
        String description;
        description = "BASIC MODE:\nChoose a square 1 move away and possibly a second square 1 more move away in the same direction. \nOn each square, you may choose 1 target and give it 1 damage.\n" +
                "IN BARBECUE MODE:\nChoose 2 squares as above. \nDeal 2 damage to everyone on the first square and 1 damage \nto everyone on the second square.\n" +
                "Notes:\nThis weapon cannot damage anyone in your square. \nHowever, it can sometimes damage a target you can't see \nthe flame won't go through walls, but it will go through doors. \nThink of it as a straight-line blast of flame that can travel 2 squares in a cardinal direction.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN BARBECUE MODE");
        getDescriptionsOfAttack().add("Choose a square 1 move away and possibly a second square\n1 more move away in the same direction.\nOn each square, you may choose 1 target and give it 1 damage");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Choose 2 squares as above. \nDeal 2 damage to everyone on the first square \nand 1 damage to everyone on the second square");
         Boolean[] kindOfAttack = {true, false, false, true};
         setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{YELLOW, YELLOW});
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assigns 1 damage to the player passed by parameter.
     */
    private void basicMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Assigns two damage to every player in the first square passed by parameter and one to the second.
     */
    private void barbecueMode(Square square, Square square2) {
        for(Player player : square.getPlayerOnThisSquare())
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        for(Player player : square2.getPlayerOnThisSquare())
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }
}
