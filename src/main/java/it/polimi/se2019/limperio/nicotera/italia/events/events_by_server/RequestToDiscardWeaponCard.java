package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class RequestToDiscardWeaponCard extends ServerEvent {
    private String nameOfWeaponCardToAdd;

    public RequestToDiscardWeaponCard(String message, String nameOfWeaponCardToAdd) {
        super(message);
        this.nameOfWeaponCardToAdd = nameOfWeaponCardToAdd;
        setRequestToDiscardWeaponCard(true);
    }

}
