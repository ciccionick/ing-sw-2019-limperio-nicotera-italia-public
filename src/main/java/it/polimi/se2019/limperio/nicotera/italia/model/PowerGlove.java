package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.YELLOW;

/**
 * handles PowerGlove of WeaponCard
 *
 * @author Giuseppe Italia
 */

public class PowerGlove extends WeaponCard {


    @Override
    public void useWeapon(int typeOfAttack, ArrayList<InvolvedPlayer> involvedPlayers) {
        if(typeOfAttack==1)
        {
            this.basicEffect(involvedPlayers.get(0).getPlayer());
        }
         else {
            this.rocketFirstMode(involvedPlayers.get(0).getPlayer(), involvedPlayers.get(0).getSquare());
            if (involvedPlayers.size() == 2) {
                this.rocketFirstMode(involvedPlayers.get(1).getPlayer(), involvedPlayers.get(1).getSquare());
            }
        }
    }


    public PowerGlove() {
        super(YELLOW, "Power glove");
        String description;
        description = "BASIC MODE:\nChoose 1 target on any square exactly 1 move away. Move onto that square and give the target 1 damage and 2 marks." +
                "IN ROCKET FIST MODE:\nChoose a square exactly 1 move away. Move onto that square. You may deal 2 damage to 1 target there. If you want, you may move 1 more square in that same direction (but only if it is a legal move). You may deal 2 damage to 1 target there, as well." +
                "Notes:\n In rocket fist mode, you're flying 2 squares in a straight line, punching 1 person per square.";
        setDescription(description);
        Boolean[] kindOfAttack = {true, false, false, true};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {YELLOW, BLUE};
        setPriceToReload(reloadPrice);
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

}
