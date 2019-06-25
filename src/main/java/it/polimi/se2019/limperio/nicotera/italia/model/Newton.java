package it.polimi.se2019.limperio.nicotera.italia.model;



/**
 * This class is used to represent the Newton of PowerUp Card
 *
 * @author Giuseppe Italia
 */

class Newton extends PowerUpCard {
    private int typeOfCard;
     Newton(ColorOfCard_Ammo color, int typeOfCard) {
        super(color, "Newton", "You may play this card on your turn before or after any action.\n" +
                "Choose any other player's figure and move it 1 or 2 squares in one direction.\n" +
                "(You can't use this to move a figure after it respawns at the end of your turn.\n" +
                "That would be too late.)");
        this.typeOfCard=typeOfCard;
    }

    @Override
    public int getTypeOfCard() {
        return typeOfCard;
    }


    @Override
    public void useAsPowerUp(Player player, Square square)
    {
        player.setPositionOnTheMap(square);
    }
}
