package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

/**
 * Event generated bt the player when he decides a powerUp card to discard to be generated in the first turn or after a death.
 *
 * @author Pietro L'Imperio
 */
public class DiscardPowerUpCardToSpawnEvent extends ClientEvent {

    /**
     * The powerUp card that a player wants to discard. It will be not null only if the event is of the type
     * {@link DiscardPowerUpCardToSpawnEvent}
     */
    private ServerEvent.AliasCard powerUpCard = null;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public DiscardPowerUpCardToSpawnEvent(String message, String nickname) {
        super(message, nickname);
        setDiscardPowerUpCardToSpawn();
    }

    public ServerEvent.AliasCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ServerEvent.AliasCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }
}
