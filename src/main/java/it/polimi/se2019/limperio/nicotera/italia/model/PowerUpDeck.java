package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard.createPowerUpCard;



public class PowerUpDeck {
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    private ArrayList<PowerUpCard> usedPowerUpCards = new ArrayList<>();
    static PowerUpDeck instanceOfPowerUpDeck=null;

    static PowerUpDeck instanceOfPowerUpDeck(){
        if(instanceOfPowerUpDeck==null)
            instanceOfPowerUpDeck = new PowerUpDeck();
        return instanceOfPowerUpDeck;
    }

    private PowerUpDeck() {
        for (int i = 1; i < 13; i++) {
            powerUpCards.add(createPowerUpCard(i));
            powerUpCards.add(createPowerUpCard(i));
        }
        Collections.shuffle(powerUpCards);
    }

    public ArrayList<PowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void addUsedPowerUpCard (PowerUpCard card){
        usedPowerUpCards.add(card);
    }

    public void shuffleDeck (){
        powerUpCards.addAll(usedPowerUpCards);
        Collections.shuffle(powerUpCards);
    }


    public ArrayList<PowerUpCard> getUsedPowerUpCards() {
        return usedPowerUpCards;
    }
}
