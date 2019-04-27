package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

public class DrawTwoPowerUpCards extends ViewEvent {
    public DrawTwoPowerUpCards(String message, String nickname) {
        super(message, nickname);
        setDrawTwoPowerUpCards(true);
    }
}
