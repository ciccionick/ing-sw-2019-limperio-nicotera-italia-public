package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

public class DiscardPowerUpCardToSpawnEvent extends ViewEvent {
    public DiscardPowerUpCardToSpawnEvent(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCardToSpawn(true);
    }
}
