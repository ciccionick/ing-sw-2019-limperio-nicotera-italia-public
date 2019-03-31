package it.polimi.se2019.limperio.nicotera.italia.model;

public class TokenOfDeath {

    ColorOfDeathToken color;
    int numOfToken;

    public TokenOfDeath(int numOfToken,ColorOfDeathToken color) {
        this.color = color;
        this.numOfToken= numOfToken;
    }

    public ColorOfDeathToken getColor() {
        return color;
    }

    public int getNumOfToken() {
        return numOfToken;
    }

}
