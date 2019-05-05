package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Shockwave of WeaponCard
 *
 * @author giuseppeitalia
 */


public class Shockwave extends WeaponCard {


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
                    this.tsunamiMode();
            }
        }
        setLoad(false);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void tsunamiMode(){
        for(Square square : this.getOwnerOfCard().getPositionOnTheMap().getAdjSquares()){
            for(Player enemy : square.getPlayerOfThisSquare()){
                enemy.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
            }
        }
    }

    Shockwave() {
        super(YELLOW, "Shockwave");
        String description;
        description = "basic mode: Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target." +
        "in tsunami mode: Deal 1 damage to all targets that are exactly 1 move away.\n";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW};
        setPriceToReload(reloadPrice);
    }

}
