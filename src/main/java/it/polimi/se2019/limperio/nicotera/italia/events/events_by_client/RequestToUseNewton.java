package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToUseNewton extends ClientEvent {
    private int numOfCard;
    public RequestToUseNewton(String message, String nickname) {
        super(message, nickname);
        setRequestToUseNewton(true);
    }

    public int getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }
}
