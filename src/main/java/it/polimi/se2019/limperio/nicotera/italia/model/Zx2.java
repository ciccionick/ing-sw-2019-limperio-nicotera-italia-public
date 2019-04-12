package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

public class Zx2 extends WeaponCard {


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
                            this.scannerMode(involvedPlayers.get(j).getPlayer());
                    }
            }
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
        super(YELLOW, "Zx2");
        String description;
        description = "basic mode: Deal 1 damage and 2 marks to 1 target you can see." +
                "in scanner mode: Choose up to 3 targets you can see and deal 1 mark to each." +
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
