package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;


import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

/**
 * This class is used to represent the VortexCannon  of WeaponCard
 *
 * @author Giuseppe Italia
 */


public class VortexCannon extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int i;
        int j;
        int typeOfCurrentAttack;
        Square squareOfVortex=null;
        if(typeOfAttack.contains(2))
            squareOfVortex=involvedPlayers.get(0).getSquare();
        for (i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
                case 2:
                    for (j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            withBlackHoleEffect(involvedPlayers.get(j).getPlayer(), squareOfVortex);
                    }


            }
        }
        setLoad(false);
    }

    public VortexCannon() {
        super(RED, "Vortex cannon");
        String description;
        description = "basic effect: Choose a square you can see, but not your square. Call it \"the vortex\". Choose a target on the vortex or 1 move away from it. Move it onto the vortex and give it 2 damage.\n" +
                "with black hole: Choose up to 2 other targets on the vortex or 1 move away from it. Move them onto the vortex and give them each 1 damage.\n" +
                "Notes: The 3 targets must be different, but some might start on the same square. It is legal to choose targets on your square, on the vortex, or even on squares you can't see. They all end up on the vortex.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = new ColorOfCard_Ammo[]{BLUE, RED};
        setPriceToReload(reloadPrice);
    }


    private void basicEffect(Player player, Square square) {

        player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void withBlackHoleEffect(Player player, Square square){
        if(player.getPositionOnTheMap()!=square)
            player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

}