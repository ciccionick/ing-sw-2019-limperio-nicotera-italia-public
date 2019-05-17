package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;

import java.util.ArrayList;

public class CatchActionDoneEvent extends ServerEvent {

    private boolean isCatchActionOfAmmoTile = false;
    private boolean isCatchActionOfWeapon = false;
    private ArrayList<ColorOfCard_Ammo> ammo = new ArrayList<>();
    private String nameOfWeaponCaught;

    public CatchActionDoneEvent(String message, ArrayList<ColorOfCard_Ammo> ammoCaught){
        super(message);
        setCatchActionDone(true);
        try{
            ammo.addAll(ammoCaught);
        }
        catch (NullPointerException e){

        }

    }

    public boolean isCatchActionOfAmmoTile() {
        return isCatchActionOfAmmoTile;
    }

    public void setCatchActionOfAmmoTile(boolean catchActionOfAmmoTile) {
        isCatchActionOfAmmoTile = catchActionOfAmmoTile;
    }

    public boolean isCatchActionOfWeapon() {
        return isCatchActionOfWeapon;
    }

    public ArrayList<ColorOfCard_Ammo> getAmmo() {
        return ammo;
    }

    public void setCatchActionOfWeapon(boolean catchActionOfWeapon) {
        isCatchActionOfWeapon = catchActionOfWeapon;
    }

    public String getNameOfWeaponCaught() {
        return nameOfWeaponCaught;
    }

    public void setNameOfWeaponCaught(String nameOfWeaponCaught) {
        this.nameOfWeaponCaught = nameOfWeaponCaught;
    }
}
