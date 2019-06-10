package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToUseEffect extends ClientEvent {
    private int numOfEffect;
    public RequestToUseEffect(String message, String nickname) {
        super(message, nickname);
        setRequestToUseEffect(true);
    }

    public int getNumOfEffect() {
        return numOfEffect;
    }

    public void setNumOfEffect(int numOfEffect) {
        this.numOfEffect = numOfEffect;
    }
}
