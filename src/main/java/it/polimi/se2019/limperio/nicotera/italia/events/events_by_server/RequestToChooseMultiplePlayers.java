package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import java.util.ArrayList;

public class RequestToChooseMultiplePlayers extends ServerEvent {
    private int numOfMaxPlayersToChoose;
    private ArrayList<String> namesOfPlayers = new ArrayList<>();

    public RequestToChooseMultiplePlayers() {
        setRequestToChooseMultiplePlayers(true);
    }

    public int getNumOfMaxPlayersToChoose() {
        return numOfMaxPlayersToChoose;
    }

    public void setNumOfMaxPlayersToChoose(int numOfMaxPlayersToChoose) {
        this.numOfMaxPlayersToChoose = numOfMaxPlayersToChoose;
    }

    public ArrayList<String> getNamesOfPlayers() {
        return namesOfPlayers;
    }

}
