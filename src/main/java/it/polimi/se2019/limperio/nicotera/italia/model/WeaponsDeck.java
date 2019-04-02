package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

public class WeaponsDeck {
    private ArrayList<WeaponCard> weaponCards;

    public WeaponsDeck() {
        int[] temporaryArray = new int[21];
        int i;
        weaponCards = new ArrayList<WeaponCard>();
        for (i=0; i<temporaryArray.length; i++ ){
            temporaryArray[i] = (int)(Math.random()*21);
        }

        for (i=0; i<temporaryArray.length; i++)
        {
            weaponCards.add(WeaponCard.createWeaponCard(temporaryArray[i]));
        }

    }

    public WeaponCard getWheaponCard() {
        return weaponCards.remove(0);
    }

}
