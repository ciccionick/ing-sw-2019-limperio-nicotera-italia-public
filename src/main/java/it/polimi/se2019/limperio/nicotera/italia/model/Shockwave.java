package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * This class is used to represent the Shockwave of WeaponCard
 *
 * @author Giuseppe Italia
 */


public class Shockwave extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
       int j=0;
        if(typeOfAttack.get(0)==1)
        {

            for(j=0; j<involvedPlayers.size(); j++)
            {this.basicEffect(involvedPlayers.get(j).getPlayer());}

        }
        else
        {

            this.tsunamiMode();
        }

        setLoad(false);
    }

    private void basicEffect(Player player){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void tsunamiMode(){

        for(Square square : this.getOwnerOfCard().getPositionOnTheMap().getAdjSquares()){
            for(Player enemy : square.getPlayerOnThisSquare()){
                enemy.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
            }
        }
    }

    public Shockwave() {
        super(YELLOW, "Shockwave");
        String description;
        description = "BASIC MODE: Choose up to 3 targets on different squares, each exactly 1 move away. Deal 1 damage to each target.\n" +
        "IN TSUNAMI MODE: Deal 1 damage to all targets that are exactly 1 move away.\n";
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
