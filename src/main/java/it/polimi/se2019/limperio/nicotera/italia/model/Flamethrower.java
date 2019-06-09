package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent Flamethrower of WeaponCard
 *
 * @author giuseppeitalia
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



    Flamethrower() {
        super(RED, "Flamethrower");
        String description;
        description = "BASIC MODE:\n Choose a square 1 move away and possibly a second square 1 more move away in the same direction. On each square, you may choose 1 target and give it 1 damage.\n" +
                "IN BARBECUE MODE:\n Choose 2 squares as above. Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square.\n" +
                "Notes:\n This weapon cannot damage anyone in your square. However, it can sometimes damage a target you can't see – the flame won't go through walls, but it will go through doors. Think of it as a straight-line blast of flame that can travel 2 squares in a cardinal direction.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add(null);
        getNamesOfAttack().add(null);
        getNamesOfAttack().add("IN BARBECUE MODE");
        getDescriptionsOfAttack().add("Choose a square 1 move away and possibly a second square\n1 more move away in the same direction.\nOn each square, you may choose 1 target and give it 1 damage");
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add("Choose 2 squares as above. \nDeal 2 damage to everyone on the first square and 1 damage to everyone on the second square");
         Boolean[] kindOfAttack = {true, false, false, true};
         setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{YELLOW, YELLOW});
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED};
        setPriceToReload(reloadPrice);
    }

    private void basicMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void barbecueMode(Square square, Square square2) {
        for(Player player : square.getPlayerOnThisSquare())
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        for(Player player : square2.getPlayerOnThisSquare())
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }
}
