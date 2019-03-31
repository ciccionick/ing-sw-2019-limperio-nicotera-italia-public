package it.polimi.se2019.limperio.nicotera.italia.model;

public abstract class Card {
    final private ColorOfCard_Ammo color;
    final private String name;
    final private String description;

    public Card() {
    }

    public Card(ColorOfCard_Ammo color, String name, String description) {
        this.color = color;
        this.name = name;
        this.description = description;
    }

    public abstract Card draw();

    public abstract void discard();

    public ColorOfCard_Ammo getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void useCard();
}
