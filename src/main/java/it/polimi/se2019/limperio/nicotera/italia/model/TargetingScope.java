package it.polimi.se2019.limperio.nicotera.italia.model;



/**
 * This class is used to represent the TargetingScope of PowerUp Card
 *
 * @author Giuseppe Italia
 */

 class TargetingScope extends PowerUpCard {
     int typeOfCard;


     TargetingScope(ColorOfCard_Ammo color, int typeOfCard) {
            super(color, "Targeting scope", "You may play this card when you are dealing damage to one or more targets.\n" +
                    "Pay 1 ammo cube of any color. \nChoose 1 of those targets and give it an extra point of damage.\nNote: You cannot use this " +
                    "to do 1 damage to a target that is receiving only marks.");
            this.typeOfCard=typeOfCard;
        }

     public int getTypeOfCard() {
         return typeOfCard;
     }

     @Override
     public void useAsPowerUp(Player player, Square square)
     {
         player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);

     }


 }



