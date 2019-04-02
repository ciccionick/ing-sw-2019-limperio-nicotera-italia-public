package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

public class MachineGun extends WeaponCard {


    @Override
    public void useWheapon(ArrayList<Integer> typeOfAttack) {
        return;

    }

    public MachineGun() {
        super(BLUE, "MachineGun", "blablabla");
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = new ColorOfCard_Ammo[]{BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE};
        setPriceToReload(reloadPrice);
    }
}
