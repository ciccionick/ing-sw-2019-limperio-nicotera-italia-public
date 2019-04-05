package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

public class ElectroScythe extends WeaponCard
{


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        ArrayList<Player> players = new ArrayList<Player>();
        int j;
        for(j=0;j<involvedPlayers.size();j++) {
            players.add(involvedPlayers.get(j).getPlayer());
        }
        switch (typeOfAttack.get(0)) {
                case 1:
                    basicMode(players);

                case 2:
                    inReaperMode(players);

                    default:
                        throw new IllegalArgumentException();

        }
    }

    public ElectroScythe() {
        super(BLUE, "Electro Scythe");
        String description;
        description = "basic mode: Deal 1 damage to every other player on your square. \n" +
                "in reaper mode: Deal 2 damage to every other player on your square.";
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