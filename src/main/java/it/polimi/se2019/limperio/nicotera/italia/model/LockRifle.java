package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

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

                    default:
                        throw new IllegalArgumentException();

            }
        }

    }

    public LockRifle() {
        super(BLUE, "Lock Rifle");
        String description;
        description = "basic effect: Deal 2 damage and 1 mark to 1 target you can see.\n" +
                "with second lock: Deal 1 mark to a different target you can see.";
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
