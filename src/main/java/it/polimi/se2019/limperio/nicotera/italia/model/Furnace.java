package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

/**
 * This class is used to represent the Furnace of WeaponCard
 *
 * @author giuseppeitalia
 */

public class Furnace extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        Square squareForRoom;
        squareForRoom = involvedPlayers.get(0).getSquare();
        if(typeOfAttack==1) {
            for (InvolvedPlayer involvedPlayer : involvedPlayers) {
                basicMode(involvedPlayer.getPlayer());
            }
        }
        else {
            for(Player player : squareForRoom.getPlayerOnThisSquare()){
                inCozyFireMode(player);
            }

        }
    }


    private void basicMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void inCozyFireMode(Player player){
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
        player.assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
    }

    public Furnace() {
        super(RED, "Furnace");
        String description = "BASIC MODE:\nChoose a room you can see, but not the room you are in. \nDeal 1 damage to everyone in that room.\n" +
                "IN COZY FIRE MODE:\nChoose a square exactly one move away. \nDeal 1 damage and 1 mark to everyone on that square.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN COZY FIRE MODE");
        getDescriptionsOfAttack().add("Choose a room you can see, but not the room you are in. \nDeal 1 damage to everyone in that room");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Choose a square exactly one move away. \nDeal 1 damage and 1 mark to everyone on that square");
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, RED};
        setPriceToReload(reloadPrice);
    }
}
