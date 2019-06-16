package it.polimi.se2019.limperio.nicotera.italia.model;




/**
 * This class is used to represent the Teleporter of PowerUp Card
 *
 * @author Giuseppe Italia
 */

 class Teleporter extends PowerUpCard {
     private int typeOfCard;


     Teleporter(ColorOfCard_Ammo color, int typeOfCard){
        super(color , "Teleporter", "You may play this card on your turn before or after any action.\n" +
                "Pick up your figure and set it down on any square of the board.\n" +
                "(You can't use this after you see where someone respawns at the end of your turn. By then it is too late.)");
        this.typeOfCard = typeOfCard;
    }

     public int getTypeOfCard() {
         return typeOfCard;
     }



     @Override
    public void useAsPowerUp(Player player,  Square square)
     {
         this.getOwnerOfCard().setPositionOnTheMap(square);
     }


 }
