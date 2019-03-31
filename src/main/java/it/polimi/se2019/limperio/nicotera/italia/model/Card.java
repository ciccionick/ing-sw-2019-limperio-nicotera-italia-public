package it.polimi.se2019.limperio.nicotera.italia.model;

public abstract class Card {
    private ColorOfCard_Ammo color;
    private String name;
    private String description;

    public Card() {
    }

    public Card(ColorOfCard_Ammo color, String name, String description) {
        this.color = color;
        this.name = name;
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


}
