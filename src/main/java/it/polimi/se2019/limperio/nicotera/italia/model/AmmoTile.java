package it.polimi.se2019.limperio.nicotera.italia.model;


import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

public class AmmoTile {

    ColorOfCard_Ammo[] ammos; //da rivedere perchè dimensione minima è 2;
    boolean hasPowerUpCard;

    public AmmoTile(int typeAmmoTile)
    {
        switch(typeAmmoTile) {
            case 1:
                hasPowerUpCard = true;
                ammos[0] = RED;
                ammos[1] = BLUE;
                break;
            case 2:
                hasPowerUpCard = true;
                ammos[0] =YELLOW;
                ammos[1] = BLUE;
                break;
            case 3:
                hasPowerUpCard = true;
                ammos[0] =YELLOW;
                ammos[1] = RED;
                break;
            case 4:
                hasPowerUpCard = false;
                ammos[0] = BLUE;
                ammos[1] = BLUE;
                ammos[2] = RED;
                break;
            case 5:
                hasPowerUpCard = false;
                ammos[0] = RED;
                ammos[1] =YELLOW;
                ammos[2] =YELLOW;
                break;
            case 6:
                hasPowerUpCard = false;
                ammos[0] = BLUE;
                ammos[1] =YELLOW;
                ammos[2] =YELLOW;
                break;
            case 8:
                hasPowerUpCard = false;
                ammos[0] = BLUE;
                ammos[1] =YELLOW;
                ammos[2] = BLUE;
                break;
            case 9:
                hasPowerUpCard = false;
                ammos[0] = BLUE;
                ammos[1] = RED;
                ammos[2] = RED;
                break;
            case 10:
                hasPowerUpCard = false;
                ammos[0] = RED;
                ammos[1] = RED;
                ammos[2] =YELLOW;
                break;
            case 11:
                hasPowerUpCard = true;
                ammos[0] = RED;
                ammos[1] = RED;
                break;
            case 12:
                hasPowerUpCard = true;
                ammos[0] = BLUE;
                ammos[1] = BLUE;
                break;
            case 13:
                hasPowerUpCard = true;
                ammos[0] =YELLOW;
                ammos[1] =YELLOW;
                break;
        }


    }


    public ColorOfCard_Ammo[] getAmmos() {
        return ammos;
    }

    public boolean hasPowerUpCard() {
        if (hasPowerUpCard) return true;
        return false;
    }




}
