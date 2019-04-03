package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WeaponsDeck {
    private ArrayList<WeaponCard> weaponCards;

    public WeaponsDeck() {
        ArrayList<Integer> temporaryArray = new ArrayList<>();
        int i;
        weaponCards = new ArrayList<WeaponCard>();
        for (i=0; i<temporaryArray.size(); i++ ){
            temporaryArray.add(new Integer(i));
        }
        Collections.shuffle(temporaryArray);

        for (i=0; i<temporaryArray.size(); i++)
        {
            weaponCards.add(WeaponCard.createWeaponCard(temporaryArray.get(i)));
        }

    }

    public WeaponCard getWheaponCard() {
        return weaponCards.remove(0);
    }

}
