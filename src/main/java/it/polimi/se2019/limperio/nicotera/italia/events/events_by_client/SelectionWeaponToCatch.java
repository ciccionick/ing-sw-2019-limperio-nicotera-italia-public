package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

/**
 * Event generated when the player decides a weapon to catch.
 * @author Pietro L'Imperio.
 */
public class SelectionWeaponToCatch extends ClientEvent {
    /**
     * The name of the weapon that player wants to catch.
     */
    private String  nameOfWeaponCard;

    /**
     * Constructor of the class where the message for the server and nickname of the player involved are initialized.
     * Calls also the method to make true the boolean field relative of this kind of event.
     */
    public SelectionWeaponToCatch(String message, String nickname){
        super(message,nickname);
        setSelectionWeaponToCatch();
    }

    public String getNameOfWeaponCard() {
        return nameOfWeaponCard;
    }

    public void setNameOfWeaponCard(String nameOfWeaponCard) {
        this.nameOfWeaponCard = nameOfWeaponCard;
    }

}
