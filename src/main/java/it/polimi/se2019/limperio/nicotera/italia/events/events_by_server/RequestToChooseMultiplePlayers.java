package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event to request to player to choose multiple players for one of his attack.
 * @author Pietro L'Imperio.
 */
public class RequestToChooseMultiplePlayers extends ServerEvent {
    /**
     * The maximum number that player can choose.
     */
    private int numOfMaxPlayersToChoose;
    /**
     * The list of nicknames of players among them the player can choose.
     */
    private ArrayList<String> namesOfPlayers = new ArrayList<>();

    /**
     * Constructor of the class that calls the method that make true the boolean field relative of this kind of class.
     */
    public RequestToChooseMultiplePlayers() {
        setRequestToChooseMultiplePlayers();
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
