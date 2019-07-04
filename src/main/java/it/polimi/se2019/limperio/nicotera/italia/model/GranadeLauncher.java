package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;


/**
 * Represents the weapon card Granade launcher.
 * @author Giuseppe Italia.
 */

public class GranadeLauncher extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
            switch (typeOfAttack) {
                case 1:
                    basicEffect(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
                    break;
                case 2:
                    for (InvolvedPlayer involvedPlayer : involvedPlayers) {
                        this.withExtraGranade(involvedPlayer.getSquare());
                    }
                    break;
                default:
                    throw new IllegalArgumentException();
            }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public GranadeLauncher() {
        super(RED, "Granade launcher");
        String description;
        description = "BASIC EFFECT:\nDeal 1 damage to 1 target you can see. Then you may move the target 1 square.\n" +
                "WITH EXTRA GRANADE:\nDeal 1 damage to every player on a square you can see. \nYou can use this before or after the basic effect's move.\n" +
                "Notes:\nFor example, you can shoot a target, move it onto a square with other targets,\nthen damage everyone including the first target. \nOr you can deal 2 to a main target, 1 to everyone else on that square,then move the main target. \nOr you can deal 1 to an isolated target and 1 to everyone on a different square. \nIf you target your own square, you will not be moved or damaged.";
        setDescription(description);
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH EXTRA GRANADE");
        getDescriptionsOfAttack().add("Deal 1 damage to 1 target you can see.\nThen you may move the target 1 square");
        getDescriptionsOfAttack().add("Deal 1 damage to every player on a square you can see.\nYou can use this before or after the basic effect's move");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{RED});
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assign a damage to the player passed by parameter and set, if square is not null, the same player in the square passed as second parameter.
     */
    private void basicEffect(Player player, Square square) {
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        if(square != null)
            player.setPositionOnTheMap(square);
    }

    /**
     * Assign a damage to every player on the square passed by parameter.
     */
    private void withExtraGranade(Square square){
        for(Player enemy : square.getPlayerOnThisSquare()){
            enemy.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);

        }
    }
}
