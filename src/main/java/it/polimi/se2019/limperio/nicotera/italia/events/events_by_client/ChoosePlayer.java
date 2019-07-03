package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class ChoosePlayer extends ClientEvent {
    private String nameOfPlayer;
    private boolean isToTargeting = false;
    private boolean isToNewton = false;
    private boolean isForAttack = false;
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
