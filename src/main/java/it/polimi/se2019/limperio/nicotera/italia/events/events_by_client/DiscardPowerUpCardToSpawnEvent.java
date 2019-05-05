package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class DiscardPowerUpCardToSpawnEvent extends ClientEvent {
    public DiscardPowerUpCardToSpawnEvent(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCardToSpawn(true);
    }
}
