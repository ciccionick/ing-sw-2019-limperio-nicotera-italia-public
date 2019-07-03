package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import java.util.ArrayList;

/**
 * Event generated when the player chooses more than a player for an attack.
 * @author Pietro L'Imperio.
 */
public class SelectionMultiplePlayers extends ClientEvent {

    /**
     * List of nicknames of the players chosen.
     */
    private ArrayList<String> namesOfPlayers = new ArrayList<>();

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
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
