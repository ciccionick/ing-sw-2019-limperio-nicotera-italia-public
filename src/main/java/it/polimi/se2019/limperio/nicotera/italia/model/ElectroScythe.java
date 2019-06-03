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
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();
        int j;
        for(j=0; j<this.getOwnerOfCard().getPositionOnTheMap().getPlayerOnThisSquare().size(); j++) {
            if(this.getOwnerOfCard().getPositionOnTheMap().getPlayerOnThisSquare().get(j).getNickname()!= this.getOwnerOfCard().getNickname()){
                players.add(this.getOwnerOfCard().getPositionOnTheMap().getPlayerOnThisSquare().get(j));
            }
        }
        switch (typeOfAttack.get(0)) {
                case 1:
                    basicMode(players);
                    break;


                case 4:
                    inReaperMode(players);
                    break;

        }
        setLoad(false);
    }

    public ElectroScythe() {
        super(BLUE, "Electroscythe");
        String description;
        description = "BASIC MODE: Deal 1 damage to every other player on your square. \n" +
                "IN REAPER MODE: Deal 2 damage to every other player on your square.";
        getNamesOfAttack().add("BASIC MODE");
        getNamesOfAttack().add(null);
        getNamesOfAttack().add(null);
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


    private void basicMode(ArrayList<Player> players){
        for(Player player:players) {
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        }
    }

    private void inReaperMode(ArrayList<Player> players) {
        for(Player player:players) {
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        }
    }
}