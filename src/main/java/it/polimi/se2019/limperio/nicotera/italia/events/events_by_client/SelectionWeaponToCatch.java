package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;


public class SelectionWeaponToCatch extends ClientEvent {
    private String  nameOfWeaponCard;


    public SelectionWeaponToCatch(String message, String nickname){
        super(message,nickname);
        setSelectionWeaponToCatch(true);
    }

    public String getNameOfWeaponCard() {
        return nameOfWeaponCard;
    }

    public void setNameOfWeaponCard(String nameOfWeaponCard) {
        this.nameOfWeaponCard = nameOfWeaponCard;
    }


    public String getCard() {
        return nameOfWeaponCard;
    }
}
