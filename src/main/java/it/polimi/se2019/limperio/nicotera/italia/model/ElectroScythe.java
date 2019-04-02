package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.BLUE;

public class ElectroScythe extends WeaponCard
{


    @Override
    public void useWheapon(ArrayList<Integer> typeOfAttack) {

    }

    public ElectroScythe() {
        super(BLUE, "Electro Scythe", "blablabla");
        Boolean[] kindOfAttack = {true, true, false, false};
        setHasThisKindOfAttack(kindOfAttack);
        setLoad(true);
        ColorOfCard_Ammo[] buyPrice = {BLUE};
        setPriceToBuy(buyPrice);
        ColorOfCard_Ammo[] reloadPrice = {BLUE, BLUE};
        setPriceToReload(reloadPrice);
    }
}
