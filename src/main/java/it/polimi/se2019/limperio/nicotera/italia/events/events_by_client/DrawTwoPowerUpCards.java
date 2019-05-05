package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class DrawTwoPowerUpCards extends ClientEvent {
    public DrawTwoPowerUpCards(String message, String nickname) {
        super(message, nickname);
        setDrawTwoPowerUpCards(true);
    }
}
