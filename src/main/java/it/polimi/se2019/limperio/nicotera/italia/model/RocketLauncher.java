package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * This class is used to represent the RocketLauncher of WeaponCard
 *
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


    public RocketLauncher() {
        super(RED, "Rocket launcher");
        String description;
        description = "BASIC EFFECT: \nDeal 2 damage to 1 target you can see that is not on your square.\n Then you may move the target 1 square." +
                      "WITH ROCKET JUMP: \nMove 1 or 2 squares. This effect can be used either before or after the basic effect." +
                      "WITH FRAGMENTIN WARHED: \nDuring the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it." +
                      "Notes: \nIf you use the rocket jump before the basic effect, you consider only your new square when determining if a target is legal. You can even move off a square so you can shoot someone on it. If you use the fragmenting warhead, you deal damage to everyone on the target's square before you move the target – your target will take 3 damage total.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, true, false};
        setHasThisKindOfAttack(kindOfAttack);
        getNamesOfAttack().add("BASIC EFFECT");
        getNamesOfAttack().add("WITH ROCKET JUMP");
        getNamesOfAttack().add("WITH FRAGMENTIN WARHED");
        getDescriptionsOfAttack().add("Deal 2 damage to 1 target you can see that is not on your square.\n Then you may move the target 1 square");
        getDescriptionsOfAttack().add("Move 1 or 2 squares. This effect can be used either before or after the basic effect");
        getDescriptionsOfAttack().add("During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it");
        setPriceToPayForEffect1(new ColorOfCard_Ammo[]{BLUE});
        setPriceToPayForEffect2(new ColorOfCard_Ammo[]{YELLOW});
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED};
        setPriceToReload(reloadPrice);
    }

    private void withFragmentingWarhead(Player player, Square square){
        if(!square.getPlayerOnThisSquare().contains(player))
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        else {
            for (Player playerInTheSquare : square.getPlayerOnThisSquare())
                playerInTheSquare.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        }
    }

    private void rocketJump(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }

    private void basicEffect(Player player, Square position){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        if(position != null)
            player.setPositionOnTheMap(position);
    }

}
