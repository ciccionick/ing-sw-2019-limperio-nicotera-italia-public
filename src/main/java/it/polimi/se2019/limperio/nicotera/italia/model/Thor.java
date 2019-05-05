package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * This class is used to represent the Thor of WeaponCard
 *
 * @author giuseppeitalia
 */


public class Thor extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        for(int type: typeOfAttack){
            switch (type){
                case 1:
                    for(InvolvedPlayer player: involvedPlayers){
                        if(player.getEffects().contains(1))
                            basicEffect(player.getPlayer());
                    }
                case 2:
                    for (InvolvedPlayer player: involvedPlayers){
                        if(player.getEffects().contains(2))
                            withChainReaction(player.getPlayer());

                    }
                case 3:
                    for(InvolvedPlayer player: involvedPlayers){
                        if(player.getEffects().contains(3))
                            withHighVoltage(player.getPlayer());
                    }

            }
        }
        setLoad(false);
    }

    Thor() {
        super(BLUE, "Thor");
        String description = "basic effect: Deal 2 damage to 1 target you can see.\n" +
                "with chain reaction: Deal 1 damage to a second target that your first target can see.\n" +
                "with high voltage: Deal 2 damage to a third target that your second target can see. You cannot use this effect unless you first use the chain reaction.\n" +
                "Notes: This card constrains the order in which you can use its effects. (Most cards don't.) Also note that each target must be a different player.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, true, false};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, RED};
        setPriceToReload(reloadPrice);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void withChainReaction(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void withHighVoltage(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }


}
