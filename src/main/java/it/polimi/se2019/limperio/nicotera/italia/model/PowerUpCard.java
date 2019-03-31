package it.polimi.se2019.limperio.nicotera.italia.model;

public class PowerUpCard extends Card{
    private boolean isAlreadyFished;
    public void useAsAmmo(){};
    public void useAsPowerUp(){};
    @Override
    public Card draw(){};
    @Override
    public void discard(){};
    @Override
    public void useCard(){};
    public PowerUpCard(ColorOfCard_Ammo color, String name, String description){
        super(color, name, description);
    }
    public PowerUpCard(int typeOfPowerUpCard) {
        switch (typeOfPowerUpCard) {
            case 1:
                new TargetingScope(ColorOfCard_Ammo.YELLOW);
            case 2:
                new TargetingScope(ColorOfCard_Ammo.RED);
            case 3:
                new TargetingScope(ColorOfCard_Ammo.BLUE);
            case 4:
                new Telereporter(ColorOfCard_Ammo.YELLOW);
            case 5:
                new Telereporter(ColorOfCard_Ammo.RED);
            case 6:
                new Telereporter(ColorOfCard_Ammo.BLUE);
            case 7:
                new Newton(ColorOfCard_Ammo.YELLOW);
            case 8:
                new Newton(ColorOfCard_Ammo.RED);
            case 9:
                new Newton(ColorOfCard_Ammo.BLUE);
            case 10:
                new TagbackGranade(ColorOfCard_Ammo.YELLOW);
            case 11:
                new TagbackGranade(ColorOfCard_Ammo.RED);
            case 12:
                new TagbackGranade(ColorOfCard_Ammo.BLUE);
        }
    }

    public boolean isAlreadyFished() {
        return isAlreadyFished;
    }
}
