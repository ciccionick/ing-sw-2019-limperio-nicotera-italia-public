package it.polimi.se2019.limperio.nicotera.italia.model;



/**
 * This class is used to represent the power up card Newton
 * @author Giuseppe Italia
 */
class Newton extends PowerUpCard {
    /**
     * Constructor of the class where is initialized the color of the card, the name and the description. It is initialized also the type of card.
     */
     Newton(ColorOfCard_Ammo color) {
        super(color, "Newton", "You may play this card on your turn before or after any action.\n" +
                "Choose any other player's figure and move it 1 or 2 squares in one direction.\n" +
                "(You can't use this to move a figure after it respawns at the end of your turn.\n" +
                "That would be too late.)");

    }

    /**
     * Moves the player passed by the parameter on the square passed as the second parameter.
     */
    @Override
    public void useAsPowerUp(Player player, Square square)
    {
        player.setPositionOnTheMap(square);
    }
}
