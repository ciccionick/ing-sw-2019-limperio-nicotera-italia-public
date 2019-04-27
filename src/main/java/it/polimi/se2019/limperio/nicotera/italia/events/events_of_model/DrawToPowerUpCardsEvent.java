package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class DrawToPowerUpCardsEvent extends ModelEvent {
    public DrawToPowerUpCardsEvent(String message) {
        super(message);
        setDrawTwoPowerUpCardEvent(true);
    }
}
