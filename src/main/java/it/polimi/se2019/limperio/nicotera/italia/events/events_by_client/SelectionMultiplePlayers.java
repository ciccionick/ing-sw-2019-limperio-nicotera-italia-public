package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import java.util.ArrayList;

public class SelectionMultiplePlayers extends ClientEvent {

    private ArrayList<String> namesOfPlayers = new ArrayList<>();

    public SelectionMultiplePlayers(String message, String nickname) {
        super(message, nickname);
        setSelectionMultiplePlayers();
    }

    public ArrayList<String> getNamesOfPlayers() {
        return namesOfPlayers;
    }

    public void setNamesOfPlayers(ArrayList<String> namesOfPlayers) {
        this.namesOfPlayers = namesOfPlayers;
    }
}
