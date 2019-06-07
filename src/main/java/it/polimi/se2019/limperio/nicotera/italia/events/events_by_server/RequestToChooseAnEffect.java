package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestToChooseAnEffect extends ServerEvent {
    private ArrayList<Integer> usableEffects;
    private boolean canTerminateAction = false;
    public RequestToChooseAnEffect() {
        setRequestToChooseAnEffect(true);
    }

    public ArrayList<Integer> getUsableEffects() {
        return usableEffects;
    }

    public void setUsableEffects(ArrayList<Integer> usableEffects) {
        this.usableEffects = usableEffects;
    }

    public boolean isCanTerminateAction() {
        return canTerminateAction;
    }

    public void setCanTerminateAction(boolean canTerminateAction) {
        this.canTerminateAction = canTerminateAction;
    }
}
