package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

public class Sledgehammer extends WeaponCard{

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
                            this.pulverizeMode(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
                default:
                    throw new IllegalArgumentException();

            }
        }
    }
    private void basicEffect(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 2);
    }
    private void pulverizeMode(Player player, Square square){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
        player.setPositionOnTheMap(square);
    }

    Sledgehammer() {
        super(YELLOW, "Sledgehammer");
        String description;
        description ="Basic mode: Deal 2 damage to 1 target on your square.\n" +
        "in pulverize mode: Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.\n"+
        "Notes: Remember that moves go through doors, but not walls.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW};
        setPriceToReload(reloadPrice);
    }
}
