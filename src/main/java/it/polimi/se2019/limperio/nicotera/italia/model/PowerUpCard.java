package it.polimi.se2019.limperio.nicotera.italia.model;

public abstract class PowerUpCard extends Card{




    public void useAsAmmo(){}

    public void useAsPowerUp(){}


    public PowerUpCard(ColorOfCard_Ammo color, String name, String description){
        super(color, name);
        setDescription(description);
    }


    static PowerUpCard createPowerUpCard(int typeOfPowerUpCard) {
        switch (typeOfPowerUpCard) {
            case 1:
                return new TargetingScope(ColorOfCard_Ammo.YELLOW);
            case 2:
                return new TargetingScope(ColorOfCard_Ammo.RED);

            case 3:
                return new TargetingScope(ColorOfCard_Ammo.BLUE);

            case 4:
                return new Telereporter(ColorOfCard_Ammo.YELLOW);

            case 5:
                return new Telereporter(ColorOfCard_Ammo.RED);

            case 6:
                return new Telereporter(ColorOfCard_Ammo.BLUE);

            case 7:
                return  new Newton(ColorOfCard_Ammo.YELLOW);

            case 8:
                return new Newton(ColorOfCard_Ammo.RED);

            case 9:
                return new Newton(ColorOfCard_Ammo.BLUE);

            case 10:
                return new TagbackGranade(ColorOfCard_Ammo.YELLOW);

            case 11:
                return new TagbackGranade(ColorOfCard_Ammo.RED);

            case 12:
                return new TagbackGranade(ColorOfCard_Ammo.BLUE);

            default:
                throw new IllegalArgumentException();
        }
    }
}
