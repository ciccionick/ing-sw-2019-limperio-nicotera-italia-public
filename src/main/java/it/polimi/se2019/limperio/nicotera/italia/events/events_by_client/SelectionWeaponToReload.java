package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

public class SelectionWeaponToReload extends ClientEvent{

    private String nameOfWeaponCardToReload;
    public SelectionWeaponToReload(String message, String nickname) {
        super(message, nickname);
        setSelectionWeaponToReload(true);
    }

    public String getNameOfWeaponCardToReload() {
        return nameOfWeaponCardToReload;
    }

    public void setNameOfWeaponCardToReload(String nameOfWeaponCardToReload) {
        this.nameOfWeaponCardToReload = nameOfWeaponCardToReload;
    }
}
