package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

/**
 * This class is used to represent the LockRifle of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class LockRifle extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int i;
        int j;
        int typeOfCurrentAttack;
        for (i=0; i<typeOfAttack.size(); i++){
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack){
                case 1:
                    for (j=0; j<involvedPlayers.size(); j++){
                        if(involvedPlayers.get(j).getEffects().contains(1))
                            basicEffect(involvedPlayers.get(j).getPlayer());
                    }
                case 2:
                    for (j=0; j<involvedPlayers.size(); j++){
                        if(involvedPlayers.get(j).getEffects().contains(2))
                            withSecondLock(involvedPlayers.get(j).getPlayer());
                    }

            }
        }
        setLoad(false);
    }

    public LockRifle() {
        super(BLUE, "Lock rifle");
        String description;
        description = "BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.\n" +
                "WITH SECOND LOCK: Deal 1 mark to a different target you can see.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = {BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE};
        setPriceToReload(reloadPrice);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void withSecondLock(Player player){
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

}
