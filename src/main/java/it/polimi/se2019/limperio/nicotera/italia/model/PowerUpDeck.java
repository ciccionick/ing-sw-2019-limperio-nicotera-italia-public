package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard.createPowerUpCard;

/**
 * The deck of the powerUp cards
 *
 * @author Pietro L'Imperio
 */
public class PowerUpDeck {
    /**
     * List of powerUp cards
     */
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    /**
     * List of powerUpCards used, that are not more available but that can be in a personal deck of some players
     */
    private ArrayList<PowerUpCard> usedPowerUpCards = new ArrayList<>();
    /**
     * The instance used for the implementation of Singleton pattern
     */
    static PowerUpDeck instanceOfPowerUpDeck=null;

    static PowerUpDeck instanceOfPowerUpDeck(){
        if(instanceOfPowerUpDeck==null)
            instanceOfPowerUpDeck = new PowerUpDeck();
        return instanceOfPowerUpDeck;
    }

    private PowerUpDeck() {
        for (int i = 4; i < 7; i++) { //riporta da 1 a 13
            powerUpCards.add(createPowerUpCard(i));
            powerUpCards.add(createPowerUpCard(i));
        }
        Collections.shuffle(powerUpCards);
    }

    public ArrayList<PowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }


    public void shuffleDeck (){
        powerUpCards.addAll(usedPowerUpCards);
        Collections.shuffle(powerUpCards);
    }


    public ArrayList<PowerUpCard> getUsedPowerUpCards() {
        return usedPowerUpCards;
    }
}
