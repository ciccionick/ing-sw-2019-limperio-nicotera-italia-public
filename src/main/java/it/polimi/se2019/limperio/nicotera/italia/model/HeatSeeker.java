package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the HeatSeeker of WeaponCard
 *
 * @author giuseppeitalia
 */

public class HeatSeeker extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        involvedPlayers.get(0).getPlayer().assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
        setLoad(false);
    }

    public HeatSeeker() {
        super(RED, "Heatseeker");
        Boolean[] kindOfAttack = {true, false, false, false};
        String description = "effect: Choose 1 target you cannot see and deal 3 damage to it.\n" +
                "Notes: Yes, this can only hit targets you cannot see.";
        setDescription(description);
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED, YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
