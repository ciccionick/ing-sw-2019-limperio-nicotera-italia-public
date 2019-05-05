package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * handles PowerGlove of WeaponCard
 *
 * @author giuseppeitalia
 */

public class PowerGlove extends WeaponCard {


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
                    for (int j = 0; j < involvedPlayers.size(); j++) {
                        if (involvedPlayers.get(j).getEffects().contains(1))
                            this.rocketFirstMode(involvedPlayers.get(j).getPlayer(),involvedPlayers.get(j).getSquare());
                    }
            }
        }
        setLoad(false);
    }

    private void basicEffect(Player player){
        this.getOwnerOfCard().setPositionOnTheMap(player.getPositionOnTheMap());
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 2);
    }

    private void rocketFirstMode(Player player, Square square){
        this.getOwnerOfCard().setPositionOnTheMap(square);
        player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 2);

    }


    PowerGlove() {
        super(YELLOW, "PowerGlove");
        String description;
        description = "basic mode: Choose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks." +
                "in rocket fist mode: Choose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there. If you want, you may move 1 more square in that same direction (but only if it is a legal move). You may deal 2 damage to 1 target there, as well." +
                "Notes: In rocket fist mode, you're flying 2 squares in a straight line, punching 1 person per square.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, BLUE};
        setPriceToReload(reloadPrice);
    }
}
