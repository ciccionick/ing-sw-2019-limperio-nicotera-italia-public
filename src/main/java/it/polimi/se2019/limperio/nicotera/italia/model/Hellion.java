package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the Hellion of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class Hellion extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        if(typeOfAttack==1)
            basicMode(involvedPlayers.get(0).getPlayer());
        else
            inNanoTracerMode(involvedPlayers.get(0).getPlayer());

    }

    private void basicMode(Player player){

        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOnThisSquare()){
            otherPlayer.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
        }

    }

    private void inNanoTracerMode(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOnThisSquare()){
            otherPlayer.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 2);
        }
    }



    public Hellion() {
        super(RED, "Hellion");
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        String description = "BASIC MODE:\n Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.\n" +
                "IN NANO-TRACER MODE:\n Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.";
        setDescription(description);
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("");
        getNamesOfAttack().add("IN NANO-TRACER MODE");
        getDescriptionsOfAttack().add("Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("");
        getDescriptionsOfAttack().add("Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square");
        setPriceToPayForAlternativeMode(new ColorOfCard_Ammo[]{RED});
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
