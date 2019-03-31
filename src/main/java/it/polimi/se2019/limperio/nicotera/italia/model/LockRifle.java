package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class LockRifle extends WheaponCard {
    private Boolean[] kindOfAttack =  {true, true, false, false};
    private ColorOfCard_Ammo[] buyPrice = {ColorOfCard_Ammo.BLUE};
    private ColorOfCard_Ammo[] reloadPrice = {ColorOfCard_Ammo.BLUE, ColorOfCard_Ammo.BLUE};


    @Override
    public void useWheapon(ArrayList<Integer> typeOfAttack) {
        return;

    }

    public LockRifle() {
        super(ColorOfCard_Ammo.BLUE, "LockRifle", "blablabla");
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        setPriceToBuy(buyPrice);
        setPriceToReload(reloadPrice);
    }
}
