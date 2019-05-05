package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;


/**
 * handles Telereporter of PowerUp Card
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
