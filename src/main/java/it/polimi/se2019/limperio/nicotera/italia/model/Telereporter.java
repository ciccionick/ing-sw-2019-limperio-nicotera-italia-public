package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;


/**
 * This class is used to represent the Telereporter of PowerUp Card
 *
 * @author giuseppeitalia
 */

 class Telereporter extends PowerUpCard {
     int typeOfCard;
     Telereporter(ColorOfCard_Ammo color, int typeOfCard){
        super(color , "Telereporter", "bla bla bla");
        this.typeOfCard = typeOfCard;
    }

     public int getTypeOfCard() {
         return typeOfCard;
     }
 }
