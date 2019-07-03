package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;


/**
 * Event generated when the player chooses a weapon to reload it.
 * @author Pietro L'Imperio.
 */
public class SelectionWeaponToReload extends ClientEvent{

    /**
     * The name of the weapon that player wants to reload.
     */
    private String nameOfWeaponCardToReload;
    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public SelectionWeaponToReload(String message, String nickname) {
        super(message, nickname);
        setSelectionWeaponToReload();
    }

    public String getNameOfWeaponCardToReload() {
        return nameOfWeaponCardToReload;
    }

    public void setNameOfWeaponCardToReload(String nameOfWeaponCardToReload) {
        this.nameOfWeaponCardToReload = nameOfWeaponCardToReload;
    }
}
