package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class DrawTwoPowerUpCardsEvent extends ModelEvent {
    public DrawTwoPowerUpCardsEvent(String message) {
        super(message);
        setDrawTwoPowerUpCardEvent(true);
    }
}
