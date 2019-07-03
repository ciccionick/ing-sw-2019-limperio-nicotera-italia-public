package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when player wants to catch a weapon but has already the deck full and so decide to discard one of them.
 * @author Pietro L'Imperio.
 */
public class SelectionWeaponToDiscard extends ClientEvent {
    /**
     * The name of the card that player wants to add to his deck.
     */
    private String nameOfWeaponCardToAdd;
    /**
     * The name of the card that player wants to discard and to add to the spawn square where he is catching.
     */
    private String nameOfWeaponCardToRemove;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     * The name of the two weapon cards involved in the exchange are initialized here as well.
     */
    public SelectionWeaponToDiscard(String message, String nickname, String nameOfWeaponCardToAdd, String nameOfWeaponCardToRemove) {
        super(message, nickname);
        this.nameOfWeaponCardToAdd = nameOfWeaponCardToAdd;
        this.nameOfWeaponCardToRemove = nameOfWeaponCardToRemove;
        setSelectionWeaponToDiscard();
    }

    public String getNameOfWeaponCardToAdd() {
        return nameOfWeaponCardToAdd;
    }

    public String getNameOfWeaponCardToRemove() {
        return nameOfWeaponCardToRemove;
    }
}
