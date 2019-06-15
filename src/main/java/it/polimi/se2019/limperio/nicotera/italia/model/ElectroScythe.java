package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * This class is used to represent ElectroScythee of WeaponCard
 *
 * @author giuseppeitalia
 */

public class ElectroScythe extends WeaponCard
{


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        switch (typeOfAttack) {
                case 1:
                    basicMode(involvedPlayers.get(0).getSquare());
                    break;
                case 4:
                    inReaperMode(involvedPlayers.get(0).getSquare());
                    break;
                default: throw new IllegalArgumentException();
        }

    }

    public ElectroScythe() {
        super(BLUE, "Electroscythe");
        String description;
        description = "BASIC MODE:\n Deal 1 damage to every other player on your square. \n" +
                "IN REAPER MODE:\n Deal 2 damage to every other player on your square.";
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN REAPER MODE");
        getDescriptionsOfAttack().add("Deal 1 damage to every other player on your square");
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add(null);
        getDescriptionsOfAttack().add("Deal 2 damage to every other player on your square");
        setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{BLUE, RED});
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] reloadPrice = {BLUE};
        setPriceToReload(reloadPrice);
    }


    private void basicMode(Square square){
        for(Player player: square.getPlayerOnThisSquare()) {
            if(!player.equals(this.getOwnerOfCard())) {
                player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
            }
        }
    }

    private void inReaperMode(Square square) {
        for(Player player: square.getPlayerOnThisSquare()) {
            if(!player.equals(this.getOwnerOfCard()))
                player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        }

    }
}