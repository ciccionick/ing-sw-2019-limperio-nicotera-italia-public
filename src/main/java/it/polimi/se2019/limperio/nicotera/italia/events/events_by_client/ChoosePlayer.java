package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when the player chooses a player or to use targeting or newton or to attack him.
 * @author Pietro L'Imperio.
 */
public class ChoosePlayer extends ClientEvent {
    /**
     * Nickname of the player chosen.
     */
    private String nameOfPlayer;
    /**
     * It's true if the choice is done to use targeting scope, false otherwise.
     */
    private boolean isToTargeting = false;
    /**
     * It's true if the choice is done to use Newton, false otherwise.
     */
    private boolean isToNewton = false;
    /**
     * It's true if the choice is done to attack a player, false otherwise.
     */
    private boolean isForAttack = false;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public ChoosePlayer(String message, String nickname) {
        super(message, nickname);
        setChoosePlayer();
    }

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public void setNameOfPlayer(String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
    }

    public boolean isToTargeting() {
        return isToTargeting;
    }

    public void setToTargeting(boolean toTargeting) {
        isToTargeting = toTargeting;
    }

    public boolean isToNewton() {
        return isToNewton;
    }

    public void setToNewton(boolean toNewton) {
        isToNewton = toNewton;
    }

    public boolean isForAttack() {
        return isForAttack;
    }

    public void setForAttack(boolean forAttack) {
        isForAttack = forAttack;
    }
}
