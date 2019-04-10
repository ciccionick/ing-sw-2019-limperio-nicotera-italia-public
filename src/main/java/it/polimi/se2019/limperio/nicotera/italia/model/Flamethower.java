package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

public class Flamethower extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        /*int typeOfCurrentAttack;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            this.barbecueMode(involvedPlayers.get(j).getSquare());
                    }
            }
        }*/

    }

    public Flamethower() {
        super(BLUE, "Flamethower");
        String description;
        description = "basic mode: Choose a square 1 move away and possibly a second square 1 more move away in the same direction. On each square, you may choose 1 target and give it 1 damage." +
                "in barbecue mode: Choose 2 squares as above. Deal 2 damage to everyone on the first square and 1 damage to everyone on the second square." +
                "Notes: This weapon cannot damage anyone in your square. However, it can sometimes damage a target you can't see – the flame won't go through walls, but it will go through doors. Think of it as a straight-line blast of flame that can travel 2 squares in a cardinal direction.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED};
        setPriceToReload(reloadPrice);
    }
}
