package it.polimi.se2019.limperio.nicotera.italia.model;


/**
 *  This class is used to represent the TagbackGranade of PowerUp Card
 *
 * @author Giuseppe Italia
 */

class TagbackGranade extends PowerUpCard  {

    int typeOfCard;


     TagbackGranade(ColorOfCard_Ammo color, int typeOfCard) {
        super(color, "Tagback granade", "You may play this card when you receive damage from a player you can see. Give that player 1 mark.");
        this.typeOfCard=typeOfCard;
    }

    public int getTypeOfCard() {
        return typeOfCard;
    }


    @Override
    public void useAsPowerUp(Player player, Square square)
    {
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }
}
