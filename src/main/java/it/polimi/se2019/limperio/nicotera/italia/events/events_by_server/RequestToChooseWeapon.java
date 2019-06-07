package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

public class RequestToChooseWeapon extends ServerEvent {

    private boolean canUseWeapon1 = false;
    private boolean canUseWeapon2 = false;
    private boolean canUseWeapon3 = false;

    public RequestToChooseWeapon() {
        setRequestToChooseWeapon(true);
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
