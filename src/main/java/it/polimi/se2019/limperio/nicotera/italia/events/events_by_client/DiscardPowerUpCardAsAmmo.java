package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;

public class DiscardPowerUpCardAsAmmo extends ClientEvent {
    private String nameOfPowerUpCard;
    private ColorOfCard_Ammo colorOfCard;
    public DiscardPowerUpCardAsAmmo(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCardAsAmmo(true);
    }

    public String getNameOfPowerUpCard() {
        return nameOfPowerUpCard;
    }

    public void setNameOfPowerUpCard(String nameOfPowerUpCard) {
        this.nameOfPowerUpCard = nameOfPowerUpCard;
    }

    public ColorOfCard_Ammo getColorOfCard() {
        return colorOfCard;
    }

    public void setColorOfCard(ColorOfCard_Ammo colorOfCard) {
        this.colorOfCard = colorOfCard;
    }
}
