package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class ChoosePlayer extends ClientEvent {
    private String nameOfPlayer;
    private boolean isToTargeting = false;
    private boolean isToNewton = false;

    public ChoosePlayer(String message, String nickname) {
        super(message, nickname);
        setChoosePlayer(true);
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
}
