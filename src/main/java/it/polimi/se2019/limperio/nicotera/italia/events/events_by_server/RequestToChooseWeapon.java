package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

/**
 * Event to request to player to choose one weapon after that he decided to shoot.
 * @author Pietro L'Imperio.
 */
public class RequestToChooseWeapon extends ServerEvent {

    /**
     * It's true if the player can choose the first weapon of his deck, false otherwise.
     */
    private boolean canUseWeapon1 = false;
    /**
     * It's true if the player can choose the second weapon of his deck, false otherwise.
     */
    private boolean canUseWeapon2 = false;
    /**
     * It's true if the player can choose the third weapon of his deck, false otherwise.
     */
    private boolean canUseWeapon3 = false;

    /**
     * Constructor of the class that calls the method that make true the boolean field relative of this kind of class.
     */

    public RequestToChooseWeapon() {
        setRequestToChooseWeapon();
    }

    public boolean isCanUseWeapon1() {
        return canUseWeapon1;
    }

    public void setCanUseWeapon1(boolean canUseWeapon1) {
        this.canUseWeapon1 = canUseWeapon1;
    }

    public boolean isCanUseWeapon2() {
        return canUseWeapon2;
    }

    public void setCanUseWeapon2(boolean canUseWeapon2) {
        this.canUseWeapon2 = canUseWeapon2;
    }

    public boolean isCanUseWeapon3() {
        return canUseWeapon3;
    }

    public void setCanUseWeapon3(boolean canUseWeapon3) {
        this.canUseWeapon3 = canUseWeapon3;
    }
}
