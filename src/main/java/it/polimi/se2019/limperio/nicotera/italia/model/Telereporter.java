package it.polimi.se2019.limperio.nicotera.italia.model;




/**
 * This class is used to represent the Telereporter of PowerUp Card
 *
 * @author Giuseppe Italia
 */

 class Telereporter extends PowerUpCard {
     int typeOfCard;


     Telereporter(ColorOfCard_Ammo color, int typeOfCard){
        super(color , "Teleporter", "bla bla bla");
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
