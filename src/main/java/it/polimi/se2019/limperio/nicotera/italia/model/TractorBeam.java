package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

/**
 * This class is used to represent the TractorBeam  of  WeaponCard
 *
 * @author giuseppeitalia
 */


public class TractorBeam extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        switch (typeOfAttack.get(0)){
            case 1:
                basicMode(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
            case 4:
                inPunisherMode(involvedPlayers.get(0).getPlayer());


        }
        setLoad(false);
    }

    public TractorBeam() {
        super(BLUE, "TractorBeam");
        String description;
        description = "basic mode: Move a target 0, 1, or 2 squares to a square you can see, and give it 1 damage.\n" +
                "in punisher mode: Choose a target 0, 1, or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n" +
                "Notes: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        ColorOfCard_Ammo[] reloadPrice = {BLUE};
        setPriceToReload(reloadPrice);
    }

    private void basicMode(Player player, Square square){
        player.setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    private void inPunisherMode(Player player){
        player.setPositionOnTheMap(this.getOwnerOfCard().getPositionOnTheMap());
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 3);
    }

}
