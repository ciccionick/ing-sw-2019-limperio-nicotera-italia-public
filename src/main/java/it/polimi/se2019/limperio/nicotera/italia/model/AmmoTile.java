package it.polimi.se2019.limperio.nicotera.italia.model;

public class AmmoTile {

    ColoOfCard_Ammo[3] ammos; //da rivedere perchè dimensione minima è 2;
    boolean hasPowerUpCard;

    public AmmoTile(int typeAmmotile)
    {
        switch(typeAmmotile) {
            case 1:
                hasPowerUpCard = true;
                ammos[0] = Ammo(red);
                ammos[1] = Ammo(blu);
                break;
            case 2:
                hasPowerUpCard = true;
                ammos[0] = Ammo(yellow);
                ammos[1] = Ammo(blue);
                break;
            case 3:
                hasPowerUpCard = true;
                ammos[0] = Ammo(yellow);
                ammos[1] = Ammo(red);
                break;
            case 4:
                hasPowerUpCard = false;
                ammos[0] = Ammo(blue);
                ammos[1] = Ammo(blue);
                ammos[2] = Ammo(red);
                break;
            case 5:
                hasPowerUpCard = false;
                ammos[0] = Ammo(red);
                ammos[1] = Ammo(yellow);
                ammos[2] = Ammo(yellow);
                break;
            case 6:
                hasPowerUpCard = false;
                ammos[0] = Ammo(blue);
                ammos[1] = Ammo(yellow);
                ammos[2] = Ammo(yellow);
                break;
            case 8:
                hasPowerUpCard = false;
                ammos[0] = Ammo(blue);
                ammos[1] = Ammo(yellow);
                ammos[2] = Ammo(blu);
                break;
            case 9:
                hasPowerUpCard = false;
                ammos[0] = Ammo(blue);
                ammos[1] = Ammo(red);
                ammos[2] = Ammo(red);
                break;
            case 10:
                hasPowerUpCard = false;
                ammos[0] = Ammo(red);
                ammos[1] = Ammo(red);
                ammos[2] = Ammo(yellow);
                break;
            case 11:
                hasPowerUpCard = true;
                ammos[0] = Ammo(red);
                ammos[1] = Ammo(red);
                break;
            case 12:
                hasPowerUpCard = true;
                ammos[0] = Ammo(blue);
                ammos[1] = Ammo(blue);
                break;
            case 13:
                hasPowerUpCard = true;
                ammos[0] = Ammo(yellow);
                ammos[1] = Ammo(yellow);
                break;
        }


    }



    public ColorOfCard_Ammo getAmmo();

    public boolean hasPowerUpCard() {
        if (hasPowerUpCard) return true;
        return false;
    }




}
