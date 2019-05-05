package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Railgun of WeaponCard
 *
 * @author giuseppeitalia
 */

public class Railgun extends WeaponCard  {


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
                            this.piercingMode(involvedPlayers.get(j).getPlayer());
                    }
            }
        }
        setLoad(false);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
    }

    private void piercingMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }


    Railgun() {
        super(YELLOW, "Railgun");
        String description;
        description = "basic mode: Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it." +
                "in piercing mode: Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each." +
                "Notes: Basically, you're shooting in a straight line and ignoring walls. You don't have to pick a target on the other side of a wall – it could even be someone on your own square – but shooting through walls sure is fun. There are only 4 cardinal directions. You imagine facing one wall or door, square-on, and firing in that direction. Anyone on a square in that direction (including yours) is a valid target. In piercing mode, the 2 targets can be on the same square or on different squares.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW, BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, YELLOW, BLUE};
        setPriceToReload(reloadPrice);
    }
}
