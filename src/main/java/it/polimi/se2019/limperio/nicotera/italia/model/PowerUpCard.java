package it.polimi.se2019.limperio.nicotera.italia.model;

/**
 * handles the game's powerUp Card
 *
 * @author giuseppeitalia
 */

public abstract class PowerUpCard extends Card  {

    private int typeOfCard;

    private boolean isInTheDeckOfSomePlayer = false;

    public void useAsPowerUp(Player player, Square square) {}


    public PowerUpCard(ColorOfCard_Ammo color, String name, String description){
        super(color, name);
        setDescription(description);
    }


    public static PowerUpCard createPowerUpCard(int typeOfPowerUpCard) {
        switch (typeOfPowerUpCard) {
            case 1:
                return new TargetingScope(ColorOfCard_Ammo.YELLOW, 1);
            case 2:
                return new TargetingScope(ColorOfCard_Ammo.RED, 2);

            case 3:
                return new TargetingScope(ColorOfCard_Ammo.BLUE, 3);

            case 4:
                return new Teleporter(ColorOfCard_Ammo.YELLOW, 4);

            case 5:
                return new Teleporter(ColorOfCard_Ammo.RED, 5);

            case 6:
                return new Teleporter(ColorOfCard_Ammo.BLUE, 6);

            case 7:
                return  new Newton(ColorOfCard_Ammo.YELLOW, 7);

            case 8:
                return new Newton(ColorOfCard_Ammo.RED, 8);

            case 9:
                return new Newton(ColorOfCard_Ammo.BLUE, 9);

            case 10:
                return new TagbackGranade(ColorOfCard_Ammo.YELLOW, 10);

            case 11:
                return new TagbackGranade(ColorOfCard_Ammo.RED, 11);

            case 12:
                return new TagbackGranade(ColorOfCard_Ammo.BLUE, 12);

            default:
                throw new IllegalArgumentException();
        }
    }

     boolean isInTheDeckOfSomePlayer() {
        return isInTheDeckOfSomePlayer;
    }

    public void setInTheDeckOfSomePlayer(boolean inTheDeckOfSomePlayer) {
        isInTheDeckOfSomePlayer = inTheDeckOfSomePlayer;
    }

    public int getTypeOfCard() {
        return typeOfCard;
    }


}

