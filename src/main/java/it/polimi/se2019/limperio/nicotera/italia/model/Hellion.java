package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

public class Hellion extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        if(typeOfAttack.get(0)==1){
            basicMode(involvedPlayers.get(0).getPlayer());
        }
        else{
            inNanoTracerMode(involvedPlayers.get(0).getPlayer());
        }
        setLoad(false);
    }

    private void basicMode(Player player){
        player.assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOfThisSquare()){
            otherPlayer.assignMarks(getOwnerOfCard().getColorOfFigure(), 1);
        }

    }

    private void inNanoTracerMode(Player player){
        player.assignMarks(getOwnerOfCard().getColorOfFigure(), 2);
        player.assignDamage(getOwnerOfCard().getColorOfFigure(), 1);
        for (Player otherPlayer: player.getPositionOnTheMap().getPlayerOfThisSquare()){
            otherPlayer.assignMarks(getOwnerOfCard().getColorOfFigure(), 2);
        }
    }



    public Hellion() {
        super(RED, "Hellion");
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        String description = "basic mode: Deal 1 damage to 1 target you can see at least 1 move away. Then give 1 mark to that target and everyone else on that square.\n" +
                "in nano-tracer mode: Deal 1 damage to 1 target you can see at least 1 move away. Then give 2 marks to that target and everyone else on that square.";
        setDescription(description);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{YELLOW};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, YELLOW};
        setPriceToReload(reloadPrice);
    }
}
