package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.util.ArrayList;

/**
 * Event to request to player to choose a player.
 * @author Pietro L'Imperio.
 */
public class RequestToChooseAPlayer extends ServerEvent {
    /**
     * It's true if the request to choose a player is to use Newton on him.
     */
    private boolean isChoosePlayerForNewton = false;
    /**
     * It's true if the request to choose a player is to complete shoot action, false otherwise.
     */
    private boolean isChoosePlayerForAttack = false;
    /**
     * It's true if the choice of player is relative to the use of targeting scope.
     */
    private boolean isToUseTargeting = false;
    /**
     * It's true if the player can refuse to choose a player, false otherwise.
     */
    private boolean canRefuse = false;

    /**
     * List of nickname of players among them the player can choose who select.
     */
    private ArrayList<String> nameOfPlayers = new ArrayList<>();


    /**
     * Constructor of the class that calls the method to make true the boolean field relative of this kind of event.
     */
    public RequestToChooseAPlayer() {
        setRequestToChooseAPlayer();
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

    public void setToUseTargeting() {
        isToUseTargeting = true;
    }

    public boolean isChoosePlayerForNewton() {
        return isChoosePlayerForNewton;
    }

    public void setChoosePlayerForNewton() {
        isChoosePlayerForNewton = true;
    }

    public boolean isCanRefuse() {
        return canRefuse;
    }

    public void setCanRefuse(boolean canRefuse) {
        this.canRefuse = canRefuse;
    }

    public boolean isChoosePlayerForAttack() {
        return isChoosePlayerForAttack;
    }

    public void setChoosePlayerForAttack() {
        isChoosePlayerForAttack = true;
    }
}
