package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.view.events_of_view.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.RED;

public class RocketLauncher extends  WeaponCard{


    @Override
    public void useWeapon(ArrayList<Integer> typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        int typeOfCurrentAttack;
        for (int i = 0; i < typeOfAttack.size(); i++) {
            typeOfCurrentAttack = typeOfAttack.get(i);
            switch (typeOfCurrentAttack) {
                case 1:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1)) {
                            if (involvedPlayers.get(j).getEffects().contains(3))
                                this.basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare(), true);
                            else
                                this.basicEffect(involvedPlayers.get(j).getPlayer(), involvedPlayers.get(j).getSquare(), false);
                        }
                    }
                case 2:
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(2))
                            this.rocketJump(involvedPlayers.get(j).getSquare());
                    }
            }
        }
        setLoad(false);
    }

    private void rocketJump(Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
    }

    private void basicEffect(Player player, Square position, boolean withThirdEffect){
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);
        if(withThirdEffect)
            player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        if(position != null)
            player.setPositionOnTheMap(position);
    }

    public RocketLauncher() {
        super(RED, "Rocket Launcher");
        String description;
        description = "basic effect: Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square." +
                      "with rocket jump: Move 1 or 2 squares. This effect can be used either before or after the basic effect." +
                      "with fragmenting warhead: During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it." +
                      "Notes: If you use the rocket jump before the basic effect, you consider only your new square when determining if a target is legal. You can even move off a square so you can shoot someone on it. If you use the fragmenting warhead, you deal damage to everyone on the target's square before you move the target – your target will take 3 damage total.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{RED};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {RED, RED};
        setPriceToReload(reloadPrice);
    }
}
