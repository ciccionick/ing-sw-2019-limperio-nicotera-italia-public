package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to represent the Weapons Deck
 * @author Giuseppe Italia
 */

public class WeaponsDeck {
    /**
     * The list of weapon cards in the deck.
     */
    private ArrayList<WeaponCard> weaponCards = new ArrayList<>();
    /**
     * The instance of the class to implements correctly the singleton pattern.
     */
    private static WeaponsDeck instanceOfWeaponsDeck = null;

    /**
     * Creates or, if has been already created return, the instance of the class.
     * @return The instance of the class.
     */
    static WeaponsDeck instanceOfWeaponsDeck(){
        if(instanceOfWeaponsDeck==null){
            instanceOfWeaponsDeck = new WeaponsDeck();
        }
        return instanceOfWeaponsDeck;
    }

    /**
     * Private constructor to respect the implementation of the singleton pattern that calling the method of the Weapon card class creates an instance of each weapon, add them to a list and finally shuffle it.
     */
    private WeaponsDeck() {
        for (int i=0; i<21; i++)
        {
            weaponCards.add(WeaponCard.createWeaponCard(i));
        }
        Collections.shuffle(weaponCards);

    }

    /**
     * Removes the first weapon card of the deck.
     * @return The first weapon card of the deck.
     */
     WeaponCard removeWeaponCard() {
        return weaponCards.remove(0);
    }

    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }
}
