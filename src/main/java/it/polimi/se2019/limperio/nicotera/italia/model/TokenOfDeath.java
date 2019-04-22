package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;

public class TokenOfDeath implements Serializable {

    private ColorOfDeathToken color;
    private int numOfToken;

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

    public void setColor(ColorOfDeathToken color) {
        this.color = color;
    }

    public void setNumOfToken(int numOfToken) {
        this.numOfToken = numOfToken;
    }
}
