package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionWeaponToDiscard extends ClientEvent {
    private String nameOfWeaponCardToAdd;
    private String nameOfWeaponCardToRemove;

    public SelectionWeaponToDiscard(String message, String nickname, String nameOfWeaponCardToAdd, String nameOfWeaponCardToRemove) {
        super(message, nickname);
        this.nameOfWeaponCardToAdd = nameOfWeaponCardToAdd;
        this.nameOfWeaponCardToRemove = nameOfWeaponCardToRemove;
    }

    public String getNameOfWeaponCardToAdd() {
        return nameOfWeaponCardToAdd;
    }

    public String getNameOfWeaponCardToRemove() {
        return nameOfWeaponCardToRemove;
    }
}
