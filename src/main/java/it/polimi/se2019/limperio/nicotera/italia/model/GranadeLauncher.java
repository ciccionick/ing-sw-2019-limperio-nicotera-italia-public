package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;


/**
 * This class is used to represent the  GranadeLauncher of WeaponCard
 *
 * @author giuseppeitalia
 */

public class GranadeLauncher extends WeaponCard {


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int typeOfCurrentAttack;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare());
                    }
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            this.withExtraGranade(involvedPlayers.get(j).getSquare());
                    }
            }
        }
        setLoad(false);
    }

    private void basicEffect(Player player, Square square) {
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        if(square != null)
            player.setPositionOnTheMap(square);
    }

    private void withExtraGranade(Square square){
        for(Player enemy : square.getPlayerOnThisSquare()){
            enemy.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);

        }
    }

    public GranadeLauncher() {
        super(RED, "Granade launcher");
        String description;
        description = "basic effect: Deal 1 damage to 1 target you can see. Then you may move the target 1 square." +
                "with extra grenade: Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect's move." +
                "Notes: For example, you can shoot a target, move it onto a square with other targets, then damage everyone including the first target. Or you can deal 2 to a main target, 1 to everyone else on that square, then move the main target. Or you can deal 1 to an isolated target and 1 to everyone on a different square. If you target your own square, you will not be moved or damaged.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED};
        setPriceToReload(reloadPrice);
    }
}
