package it.polimi.se2019.limperio.nicotera.italia.model;



/**
 * This class is used to represent the power up card Teleporter.
 * @author Giuseppe Italia
 */

 class Teleporter extends PowerUpCard {

    /**
     * Constructor that initialize color, name and description of the card.
     */
     Teleporter(ColorOfCard_Ammo color){
        super(color , "Teleporter", "You may play this card on your turn before or after any action.\n" +
                "Pick up your figure and set it down on any square of the board.\n" +
                "(You can't use this after you see where someone respawns at the end of your turn. \nBy then it is too late.)");
    }

    /**
     * Moves the owner of the square on the square passed by parameter.
     */
     @Override
    public void useAsPowerUp(Player player,  Square square)
     {
         this.getOwnerOfCard().setPositionOnTheMap(square);
     }


 }
