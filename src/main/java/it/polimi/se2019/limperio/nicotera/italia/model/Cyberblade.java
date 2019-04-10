package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.*;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

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
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            this.withShadowstepEffect(involvedPlayers.get(j).getSquare());
                    }
                case 3:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(3))
                            this.withSliceAndDice(involvedPlayers.get(j).getPlayer());
                    }
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
    private void withSliceAndDice(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void withShadowstepEffect(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }
    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    Cyberblade() {
        super(YELLOW, "Cyberblade");
        String description;
        description = "basic effect: Deal 2 damage to 1 target on your square.\n" +
        "with shadowstep: Move 1 square before or after the basic effect.\n" +
        "with slice and dice: Deal 2 damage to a different target on your square. The shadowstep may be used before or after this effect.\n" +
        "Notes: Combining all effects allows you to move onto a square and whack 2 people; or whack somebody, move, and whack somebody else; or whack 2 people and then move.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, RED};
        setPriceToReload(reloadPrice);

    }
}
