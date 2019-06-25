package it.polimi.se2019.limperio.nicotera.italia.model;


/**
 *  This class is used to represent the TagbackGranade of PowerUp Card
 *
 * @author Giuseppe Italia
 */

class TagbackGranade extends PowerUpCard  {

    private int typeOfCard;


     TagbackGranade(ColorOfCard_Ammo color, int typeOfCard) {
        super(color, "Tagback granade", "You may play this card when you receive damage from a player you can see.\nGive that player 1 mark.");
        this.typeOfCard=typeOfCard;
    }

    @Override
    public int getTypeOfCard() {
        return typeOfCard;
    }


    @Override
    public void useAsPowerUp(Player player, Square square)
    {
        player.assignMarks(this.getOwnerOfCard().getColorOfFigure(), 1);
    }
}
