package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Sledgehammer of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Sledgehammer extends WeaponCard{

    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
            if(typeOfAttack==1)
                basicEffect(involvedPlayers.get(0).getPlayer());
                else
                    pulverizeMode(involvedPlayers.get(0).getPlayer(),involvedPlayers.get(0).getSquare());
    }



    public Sledgehammer() {
        super(YELLOW, "Sledgehammer");
        String description;
        description ="BASIC MODE: \nDeal 2 damage to 1 target on your square.\n" +
        "IN PULVERIZE MODE: \nDeal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.\n"+
        "Notes: \nRemember that moves go through doors, but not walls.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("IN PULVERIZE MODE");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target on your square");
        getDescriptionsOfAttack().add("Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{RED});
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW};
        setPriceToReload(reloadPrice);
    }

    private void basicEffect(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void pulverizeMode(Player player, Square square){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
        player.setPositionOnTheMap(square);
    }
}
