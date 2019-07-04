package it.polimi.se2019.limperio.nicotera.italia.model;



/**
 * This class is used to represent the power up card  Targeting scope.
 * @author Giuseppe Italia
 */
 class TargetingScope extends PowerUpCard {

    /**
     * Constructor that initialize color, name and description of the card.
     */
     TargetingScope(ColorOfCard_Ammo color) {
            super(color, "Targeting scope", "You may play this card when you are dealing damage to one or more targets.\n" +
                    "Pay 1 ammo cube of any color. \nChoose 1 of those targets and give it an extra point of damage.\nNote: You cannot use this \n" +
                    "to do 1 damage to a target that is receiving only marks.");

        }

    /**
     * Assign a damage to a player passed by parameter that is  one of the players attacked by the owner of the card during his shoot action.
     */
    @Override
     public void useAsPowerUp(Player player, Square square)
     {
         player.assignDamage(this.getOwnerOfCard().getColorOfFigure(), 1);

     }


 }



