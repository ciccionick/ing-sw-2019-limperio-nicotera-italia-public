package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Shotgun of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Shotgun extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int typeOfCurrentAttack;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
                    break;
                case 4:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(4))
                            this.longBarrelMode(involvedPlayers.get(j).getPlayer());
                    }
                    break;
            }
        }
        setLoad(false);
    }

    private void basicEffect(Player player, Square square){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
        if(square != null)
            player.setPositionOnTheMap(square);
    }

    private void longBarrelMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    public Shotgun() {
        super(YELLOW, "Shotgun");
        String description;
        description = "BASIC MODE: Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square.\n" +
                      "IN LONG BARREL MODE: Deal 2 damage to 1 target on any square exactly one move away.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN LONG BARREL MODE");
        getDescriptionsOfAttack().add("Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target on any square exactly one move away");

        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
