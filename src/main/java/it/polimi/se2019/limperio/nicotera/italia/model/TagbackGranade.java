package it.polimi.se2019.limperio.nicotera.italia.model;


/**
 *  This class is used to represent the power up card Tagback granade.
 *
 * @author Giuseppe Italia
 */

class TagbackGranade extends PowerUpCard  {

    /**
     * Constructor that initialize color, name and description of the card.
     */
     TagbackGranade(ColorOfCard_Ammo color) {
        super(color, "Tagback granade", "You may play this card when you receive damage from a player you can see.\nGive that player 1 mark.");
    }


    /**
     * Assigns a mark to the player passed by parameter that attacked the owner of the card previously.
     */
    @Override
    public void useAsPowerUp(Player player, Square square)
    {
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }
}
