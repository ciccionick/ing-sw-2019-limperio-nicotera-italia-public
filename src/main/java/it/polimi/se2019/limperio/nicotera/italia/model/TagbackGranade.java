package it.polimi.se2019.limperio.nicotera.italia.model;


class TagbackGranade extends PowerUpCard  {
    int typeOfCard;
     TagbackGranade(ColorOfCard_Ammo color, int typeOfCard) {
        super(color, "Tagback granade", "bla bla bla");
        this.typeOfCard=typeOfCard;
    }

    public int getTypeOfCard() {
        return typeOfCard;
    }
}
