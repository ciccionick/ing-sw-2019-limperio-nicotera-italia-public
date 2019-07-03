package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event generated to request to player to choose an effect of the weapon chosen to shoot.
 * @author Pietro L'Imperio.
 */
public class RequestToChooseAnEffect extends ServerEvent {
    /**
     * List of integer that represents the usable effects among which player can choose.
     */
    private ArrayList<Integer> usableEffects;
    /**
     * The name of card that is using for the shoot action.
     */
    private String nameOfCard;
    /**
     * It's true if the player can choose to avoid to use another effect because has already used one, false otherwise.
     */
    private boolean oneEffectAlreadyChosen = false;

    /**
     * Constructor that calls the method to make true the boolean field relative with this kind of event.
     */
    public RequestToChooseAnEffect() {
        setRequestToChooseAnEffect();
    }

    public ArrayList<Integer> getUsableEffects() {
        return usableEffects;
    }

    public void setUsableEffects(ArrayList<Integer> usableEffects) {
        this.usableEffects = usableEffects;
    }


    public String getNameOfCard() {
        return nameOfCard;
    }

    public void setNameOfCard(String nameOfCard) {
        this.nameOfCard = nameOfCard;
    }

    public boolean isOneEffectAlreadyChosen() {
        return oneEffectAlreadyChosen;
    }

    public void setOneEffectAlreadyChosen(boolean oneEffectAlreadyChosen) {
        this.oneEffectAlreadyChosen = oneEffectAlreadyChosen;
    }
}
