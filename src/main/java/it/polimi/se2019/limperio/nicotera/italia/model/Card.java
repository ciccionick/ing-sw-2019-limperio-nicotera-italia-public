package it.polimi.se2019.limperio.nicotera.italia.model;

/**
 * This class is an abstract class to represent the card extended by Weapon card and Power up card.
 *
 * @author Giuseppe Italia
 */
public abstract class Card  {
    /**
     * The color of the card.
     */
    private ColorOfCard_Ammo color;
    /**
     * The name of the card.
     */
    private String name;
    /**
     * The description of the card.
     */
    private String description;
    /**
     * The player owner of the card.
     */
    private Player ownerOfCard;


    /**
     * The constructor of the card that initialize the name and the color of the card.
     */
    public Card(ColorOfCard_Ammo color, String name) {
        this.color = color;
        this.name = name;

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ColorOfCard_Ammo getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Player getOwnerOfCard() {
        return ownerOfCard;
    }

    public void setOwnerOfCard(Player ownerOfCard) {
        this.ownerOfCard = ownerOfCard;
    }


}
