package it.polimi.se2019.limperio.nicotera.italia.model;

/**
 * Represents a power up card of the game inheriting from the Card.
 * @author Giuseppe Italia.
 */
public abstract class PowerUpCard extends Card  {


    private boolean isInTheDeckOfSomePlayer = false;

    public void useAsPowerUp(Player player, Square square) {}

    /**
     * Constructor of the class the initializes name, color and description of the card.
     */
    public PowerUpCard(ColorOfCard_Ammo color, String name, String description){
        super(color, name);
        setDescription(description);
    }


    /**
     * Creates a power up card according with the number passed by parameter from the class PowerUpDeck
     * @param typeOfPowerUpCard The number that identify the correct power up card to create.
     * @return The reference of the power up created.
     */
    public static PowerUpCard createPowerUpCard(int typeOfPowerUpCard) {
        switch (typeOfPowerUpCard) {
            case 1:
                return new TargetingScope(ColorOfCard_Ammo.YELLOW );
            case 2:
                return new TargetingScope(ColorOfCard_Ammo.RED);

            case 3:
                return new TargetingScope(ColorOfCard_Ammo.BLUE);

            case 4:
                return new Teleporter(ColorOfCard_Ammo.YELLOW);

            case 5:
                return new Teleporter(ColorOfCard_Ammo.RED);

            case 6:
                return new Teleporter(ColorOfCard_Ammo.BLUE);

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

     boolean isInTheDeckOfSomePlayer() {
        return isInTheDeckOfSomePlayer;
    }

    public void setInTheDeckOfSomePlayer(boolean inTheDeckOfSomePlayer) {
        isInTheDeckOfSomePlayer = inTheDeckOfSomePlayer;
    }


}

