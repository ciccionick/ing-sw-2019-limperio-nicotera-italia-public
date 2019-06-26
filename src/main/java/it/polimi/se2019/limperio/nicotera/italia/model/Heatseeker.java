package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the Heatseeker of WeaponCard
 *
 * @author giuseppeitalia
 */

public class Heatseeker extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        involvedPlayers.get(0).getPlayer().assignDamage(getOwnerOfCard().getColorOfFigure(), 3);
    }

    public  Heatseeker() {
        super(RED, "Heatseeker");
        Boolean[] kindOfAttack = {true, false, false, false};
        String description = "EFFECT:\nChoose 1 target you cannot see and deal 3 damage to it.\n" +
                "Notes:\nYes, this can only hit targets you cannot see.";
        getNamesOfAttack().add("EFFECT");
        getDescriptionsOfAttack().add("Choose 1 target you cannot see and deal 3 damage to it");
        setDescription(description);
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED, YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
