package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the weapon card Rocket launcher.
 * @author Giuseppe Italia
 */

public class RocketLauncher extends  WeaponCard{


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {

            switch (typeOfAttack) {
                case 1:
                    basicEffect(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
                    break;
                case 2:
                    this.rocketJump(involvedPlayers.get(0).getSquare());
                    break;
                case 3:
                    withFragmentingWarhead(involvedPlayers.get(0).getPlayer(),involvedPlayers.get(0).getSquare());
                    break;

                    default:
                        throw new IllegalArgumentException();
            }
    }

    /**
     * Constructor that calls the super constructor to initializes color and name. Then initializes the description, the list of the names of the effects with the relative single descriptions.
     * Sets the price to buy the weapon and to reload it. At the end initializes the array of boolean that shows what kind of effect the weapon has.
     */
    public RocketLauncher() {
        super(RED, "Rocket launcher");
        String description;
        description = "BASIC EFFECT: \nDeal 2 damage to 1 target you can see that is not on your square.\nThen you may move the target 1 square." +
                      "WITH ROCKET JUMP: \nMove 1 or 2 squares. \nThis effect can be used either before or after the basic effect.\n" +
                      "WITH FRAGMENTIN WARHED: \nDuring the basic effect, deal 1 damage to every player on your target's original square \nincluding the target, even if you move it." +
                      "Notes: \nIf you use the rocket jump before the basic effect, \nyou consider only your new square when determining if a target is legal. \nYou can even move off a square so you can shoot someone on it. \nIf you use the fragmenting warhead, you deal damage to everyone on the target's square \nbefore you move the target \nyour target will take 3 damage total.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, true, false};
        setHasThisKindOfAttack(kindOfAttack);
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH ROCKET JUMP");
        getNamesOfAttack().add("WITH FRAGMENTIN WARHED");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target you can see that is not on your square. \nThen you may move the target 1 square");
        getDescriptionsOfAttack().add("Move 1 or 2 squares. \nThis effect can be used either before or after the basic effect");
        getDescriptionsOfAttack().add("During the basic effect, deal 1 damage to every player on your target's original square \nincluding the target, even if you move it");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{BLUE});
        setPriceToPayForEffect2(new ColorOfCard_Ammo[]{YELLOW});
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED};
        setPriceToReload(reloadPrice);
    }

    /**
     * Assigns a damage to every player in the square passed by parameter and to the player passed by parameter if he is not on the previous square.
     */
    private void withFragmentingWarhead(Player player, Square square){
        for (Player playerInTheSquare : square.getPlayerOnThisSquare())
            playerInTheSquare.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        if(!square.getPlayerOnThisSquare().contains(player))
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
    }

    /**
     * Moves the owner of the card in the square passed by parameter.
     */
    private void rocketJump(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }

    /**
     * Assigns two damage to the player passed by parameter and then if tha parameter position is not null moves him on that square.
     */
    private void basicEffect(Player player, Square position){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        if(position != null)
            player.setPositionOnTheMap(position);
    }

}
