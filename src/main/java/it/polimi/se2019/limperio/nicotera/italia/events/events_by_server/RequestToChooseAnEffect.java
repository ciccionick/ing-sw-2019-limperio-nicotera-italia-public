package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestToChooseAnEffect extends ServerEvent {
    private ArrayList<Integer> usableEffects;
    private String nameOfCard;
    private boolean oneEffectAlreadyChosen = false;
    public RequestToChooseAnEffect() {
        setRequestToChooseAnEffect(true);
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
