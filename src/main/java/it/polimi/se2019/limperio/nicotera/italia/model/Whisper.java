package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Whisper  of  WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Whisper extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        involvedPlayers.get(0).getPlayer().assignDamage(getOwnerOfCard().getColorOfFigure(),3);
        involvedPlayers.get(0).getPlayer().assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
        setLoad(false);
    }

    public Whisper() {
        super(BLUE, "Whisper");
        Boolean[] kindOfAttack = {true, false, false, false};
        String description = "EFFECT: Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you.\n" +
                "Notes: For example, in the 2-by-2 room, you cannot shoot a target on an adjacent square, but you can shoot a target on the diagonal. If you are beside a door, you can't shoot a target on the other side of the door, but you can shoot a target on a different square of that room.";
        setDescription(description);
        getNamesOfAttack().add("EFFECT");
        getDescriptionsOfAttack().add("Deal 3 damage and 1 mark to 1 target you can see. Your target must be at least 2 moves away from you");
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE, YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
