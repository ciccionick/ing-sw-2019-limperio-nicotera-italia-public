package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard.createPowerUpCard;

/**
 * The deck of the power up cards
 * @author Pietro L'Imperio
 */
public class PowerUpDeck {
    /**
     * List of power up cards
     */
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    /**
     * List of power up cards used, that are not more available but that can be in a personal deck of some players
     */
    private ArrayList<PowerUpCard> usedPowerUpCards = new ArrayList<>();
    /**
     * The instance used for the implementation of Singleton pattern
     */
    static PowerUpDeck instanceOfPowerUpDeck=null;

    /**
     * Creates an instance of the deck of power up cards.
     * @return The reference of the deck.
     */
    static PowerUpDeck instanceOfPowerUpDeck(){
        if(instanceOfPowerUpDeck==null)
            instanceOfPowerUpDeck = new PowerUpDeck();
        return instanceOfPowerUpDeck;
    }

    /**
     * Private constructor according with the implementation of the singleton pattern that calls for each kind of power up card (distinguishable from name and color) two times the method the creates it.
     */
    private PowerUpDeck() {
        for (int i = 1; i < 13; i++) {
            powerUpCards.add(createPowerUpCard(i));
            powerUpCards.add(createPowerUpCard(i));
        }
        Collections.shuffle(powerUpCards);
    }

    /**
     * Shuffles the deck of power up cards when remains lower than 3 card on it. To do this, takes the cards in usedPowerUpCards list that are not in deck of some players.
     */
    public void shuffleDeck (){
        for(PowerUpCard powerUpCard : usedPowerUpCards){
            if(!powerUpCard.isInTheDeckOfSomePlayer())
                powerUpCards.add(powerUpCard);
        }
        Collections.shuffle(powerUpCards);
    }


    public ArrayList<PowerUpCard> getUsedPowerUpCards() {
        return usedPowerUpCards;
    }

    public ArrayList<PowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }
}
