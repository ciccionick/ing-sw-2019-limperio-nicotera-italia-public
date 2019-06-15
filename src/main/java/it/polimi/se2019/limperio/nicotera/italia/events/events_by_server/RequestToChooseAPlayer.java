package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

public class RequestToChooseAPlayer extends ServerEvent {
    private ArrayList<String> nameOfPlayers = new ArrayList<>();
    private boolean isToUseTargeting = false;

    public RequestToChooseAPlayer() {
        setRequestToChooseAPlayer(true);
    }

    public ArrayList<String> getNameOfPlayers() {
        return nameOfPlayers;
    }

    public void setNameOfPlayers(ArrayList<String> nameOfPlayers) {
        this.nameOfPlayers = nameOfPlayers;
    }

    public boolean isToUseTargeting() {
        return isToUseTargeting;
    }

    public void setToUseTargeting(boolean toUseTargeting) {
        isToUseTargeting = toUseTargeting;
    }
}
