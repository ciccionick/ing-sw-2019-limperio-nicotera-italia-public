package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent Cyberblade of WeaponCard
 *
 * @author giuseppeitalia
 */

public class Cyberblade extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int typeOfCurrentAttack;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.basicEffect(involvedPlayers.get(j).getPlayer());
                    }
                    break;
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            this.withShadowstepEffect(involvedPlayers.get(j).getSquare());
                    }
                    break;
                case 3:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(3))
                            this.withSliceAndDice(involvedPlayers.get(j).getPlayer());
                    }
                    break;

            }
        }
        setLoad(false);
    }
    private void withSliceAndDice(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void withShadowstepEffect(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }

    public void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    public Cyberblade() {
        super(YELLOW, "Cyberblade");
        String description;
        description = "BASIC EFFECT: Deal 2 damage to 1 target on your square.\n" +
        "WITH SHADOWSTEP: Move 1 square before or after the basic effect.\n" +
        "WITH SLICE AND DICE: Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.\n" +
        "Notes: Combining all effects allows you to move onto a square and whack 2 people; or whack somebody, move, and whack somebody else; or whack 2 people and then move.\n";
        setDescription(description);
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH SHADOWSTEP");
        getNamesOfAttack().add("WITH SLICE AND DICE");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target on your square");
        getDescriptionsOfAttack().add("Move 1 square before or after the basic effect");
        getDescriptionsOfAttack().add("Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect");
        setPriceToPayForEffect1(null);
        setGetPriceToPayForEffect2(new ColorOfCard_Ammo[]{YELLOW});
        Boolean[] kindOfAttack = {true, true, true, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, RED};
        setPriceToReload(reloadPrice);

    }
}
