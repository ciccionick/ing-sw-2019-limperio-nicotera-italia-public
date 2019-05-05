package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class DiscardPowerUpCardToSpawnEvent extends ServerEvent {
    public DiscardPowerUpCardToSpawnEvent(String message) {
        super(message);
        setDiscardPowerUpCardToSpawnEvent(true);
    }
}
