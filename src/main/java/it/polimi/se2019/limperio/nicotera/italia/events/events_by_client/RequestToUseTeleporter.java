package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class RequestToUseTeleporter extends ClientEvent {
    int numOfCard;
    public RequestToUseTeleporter(String message, String nickname) {
        super(message, nickname);
        setRequestToUseTeleporter(true);
    }

    public int getNumOfCard() {
        return numOfCard;
    }

    public void setNumOfCard(int numOfCard) {
        this.numOfCard = numOfCard;
    }
}
