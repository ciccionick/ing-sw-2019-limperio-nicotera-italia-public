package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the Zx2 of  WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Zx2 extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {

            switch (typeOfAttack.get(0)) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.basicEffect(involvedPlayers.get(j).getPlayer());
                    }
                    break;
                case 4:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(4))
                            this.scannerMode(involvedPlayers.get(j).getPlayer());
                    }
                    break;
            }

        setLoad(false);
    }

    private void basicEffect (Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void scannerMode(Player player){
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    public Zx2() {
        super(YELLOW, "Zx-2");
        String description;
        description = "BASIC MODE: Deal 1 damage and 2 marks to 1 target you can see.\n" +
                "IN SCANNER MODE: Choose up to 3 targets you can see and deal 1 mark to each.\n" +
                "Notes: Remember that the 3 targets can be in 3 different rooms.";
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
