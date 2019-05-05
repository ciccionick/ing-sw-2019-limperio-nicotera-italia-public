package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated bt the player when he decides a powerUp card to discard to be generated in the first turn or after a death.
 *
 * @author Pietro L'Imperio
 */
public class DiscardPowerUpCardToSpawnEvent extends ClientEvent {
    public DiscardPowerUpCardToSpawnEvent(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCardToSpawn(true);
    }
}
