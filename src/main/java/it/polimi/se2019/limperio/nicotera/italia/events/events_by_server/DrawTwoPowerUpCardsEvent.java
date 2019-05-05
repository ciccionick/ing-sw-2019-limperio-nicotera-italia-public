package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class DrawTwoPowerUpCardsEvent extends ServerEvent {
    public DrawTwoPowerUpCardsEvent(String message) {
        super(message);
        setDrawTwoPowerUpCardEvent(true);
    }
}
