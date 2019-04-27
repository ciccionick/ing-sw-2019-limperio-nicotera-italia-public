package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

public class DiscardPowerUpCardToSpawnEvent extends ModelEvent {
    public DiscardPowerUpCardToSpawnEvent(String message) {
        super(message);
        setDiscardPowerUpCardToSpawnEvent(true);
    }
}
