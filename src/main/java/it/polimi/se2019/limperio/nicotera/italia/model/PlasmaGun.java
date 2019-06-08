package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the PlasmaGun  of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class PlasmaGun extends WeaponCard{


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int typeOfCurrentAttack;
        boolean thirdEffect=false;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1)) {
                            if(typeOfAttack.contains(3))
                                thirdEffect=true;
                            basicEffect(involvedPlayers.get(j).getPlayer(), thirdEffect);
                        }
                    }
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            withPhaseGlide(involvedPlayers.get(i).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
            }

        }
        setLoad(false);
    }

    private void basicEffect(Player player, boolean thirdEffect){
        if(thirdEffect)
            player.assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
        else
            player.assignDamage(getOwnerOfCard().getColorOfFigure(), 2);

    }

    private void withPhaseGlide(Player player, Square square){
        player.setPositionOnTheMap(square);

    }



    public PlasmaGun() {
        super(BLUE, "Plasma gun");
        String description = "BASIC EFFECT: Deal 2 damage to 1 target you can see.\n" +
                "WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n" +
                "WITH CHARGED SHOT: Deal 1 additional damage to your target.\n" +
                "Notes: The two moves have no ammo cost. You don't have to be able to see your target when you play the card. For example, you can move 2 squares and shoot a target you now see. You cannot use 1 move before shooting and 1 move after.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, true, false};
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH PHASE GLIDE");
        getNamesOfAttack().add("WITH CHARGED SHOT");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target you can see");
        getDescriptionsOfAttack().add("Move 1 or 2 squares. This effect can be used either before or after the basic effect");
        getDescriptionsOfAttack().add("Deal 1 additional damage to your target");
        setPriceToPayForEffect2(new ColorOfCard_Ammo[]{BLUE});
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
