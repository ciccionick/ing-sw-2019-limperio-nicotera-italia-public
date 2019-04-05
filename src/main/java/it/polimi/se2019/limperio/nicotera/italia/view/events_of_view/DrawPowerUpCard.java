package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class DrawPowerUpCard extends ViewEvent  {

    private int numOfCard;

    public DrawPowerUpCard(Player player, int numOfCard) {
        super(player);
        this.numOfCard = numOfCard;
    }

    public int getNumOfCard() {
        return numOfCard;
    }
}
