package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event to request to discard a weapon card when player wants to catch another one but has already the deck full.
 * @author Pietro L'Imperio.
 */
public class RequestToDiscardWeaponCard extends ServerEvent {
    /**
     * The name of the weapon card that player wants to catch.
     */
    private String nameOfWeaponCardToAddToTheDeck;

    /**
     * Constructor that initialize the message for involved player, the name of the weapon card he wants to catch and the boolean field relative to this kind of event.
     * @param message Message for involved player
     * @param nameOfWeaponCardToAdd The name of the weapon card that player wants to catch.
     */
    public RequestToDiscardWeaponCard(String message, String nameOfWeaponCardToAdd) {
        super(message);
        this.nameOfWeaponCardToAddToTheDeck = nameOfWeaponCardToAdd;
        setRequestToDiscardWeaponCard();
    }

    public String getNameOfWeaponCardToAddToTheDeck() {
        return nameOfWeaponCardToAddToTheDeck;
    }
}
