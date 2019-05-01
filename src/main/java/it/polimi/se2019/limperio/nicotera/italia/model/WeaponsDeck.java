package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

public class WeaponsDeck {
    private ArrayList<WeaponCard> weaponCards = new ArrayList<>();
    static WeaponsDeck instanceOfWeaponsDeck = null;

    static WeaponsDeck instanceOfWeaponsDeck(){
        if(instanceOfWeaponsDeck==null){
            instanceOfWeaponsDeck = new WeaponsDeck();
        }
        return instanceOfWeaponsDeck;
    }

    private WeaponsDeck() {
        for (int i=0; i<21; i++)
        {
            weaponCards.add(WeaponCard.createWeaponCard(i));
        }
        Collections.shuffle(weaponCards);

    }

    public WeaponCard removeWeaponCard() {
        return weaponCards.remove(0);
    }

    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }
}
