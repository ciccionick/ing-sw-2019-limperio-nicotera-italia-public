package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Railgun of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Railgun extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {

        switch (typeOfAttack) {
            case 1:
                   basicEffect(involvedPlayers.get(0).getPlayer());
                break;
            case 4:
                for (InvolvedPlayer involvedPlayer : involvedPlayers) {
                        piercingMode(involvedPlayer.getPlayer());
                }
                break;

                default:
                    throw new IllegalArgumentException();
        }
    }

    public Railgun() {
        super(YELLOW, "Railgun");
        String description;
        description = "BASIC MODE:\nChoose a cardinal direction and 1 target in that direction. Deal 3 damage to it.\n" +
                "IN PIERCING MODE:\nChoose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each.\n" +
                "Notes:\nBasically, you're shooting in a straight line and ignoring walls. You don't have to pick a target on the other side of a wall – it could even be someone on your own square – but shooting through walls sure is fun. There are only 4 cardinal directions. You imagine facing one wall or door, square-on, and firing in that direction. Anyone on a square in that direction (including yours) is a valid target. In piercing mode, the 2 targets can be on the same square or on different squares.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN PIERCING MODE");
        getDescriptionsOfAttack().add("Choose a cardinal direction and 1 target in that direction. Deal 3 damage to it");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Choose a cardinal direction and 1 or 2 targets in that direction. Deal 2 damage to each");
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW, BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, YELLOW, BLUE};
        setPriceToReload(reloadPrice);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
    }

    private void piercingMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

}
