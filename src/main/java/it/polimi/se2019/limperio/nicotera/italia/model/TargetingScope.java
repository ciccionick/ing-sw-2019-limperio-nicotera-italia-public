package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

 class TargetingScope extends PowerUpCard {
     int typeOfCard;
     TargetingScope(ColorOfCard_Ammo color, int typeOfCard) {
            super(color, "Targeting scope", "This power up bla bla bla");
            this.typeOfCard=typeOfCard;
        }

     public int getTypeOfCard() {
         return typeOfCard;
     }
 }

