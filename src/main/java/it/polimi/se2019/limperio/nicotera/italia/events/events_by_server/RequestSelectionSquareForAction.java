package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

public class RequestSelectionSquareForAction extends ServerEvent {

    private ArrayList<Square> squaresReachable = new ArrayList<>();
    private ArrayList<AliasCard> weaponNotAvailableForLackOfAmmo = new ArrayList<>();
    private boolean isSelectionForRun = false;
    private boolean isSelectionForCatch = false;

    public RequestSelectionSquareForAction(String message){
        super(message);
        setRequestSelectionSquareForAction(true);
    }

    public void setSquaresReachable(ArrayList<Square> squaresReachable) {
        this.squaresReachable.addAll(squaresReachable);
    }

    public ArrayList<Square> getSquaresReachable() {
        return squaresReachable;
    }

    public ArrayList<AliasCard> getWeaponNotAvailableForLackOfAmmo() {
        return weaponNotAvailableForLackOfAmmo;
    }

    public void setWeaponNotAvailableForLackOfAmmo(ArrayList<AliasCard> weaponNotAvailableForLackOfAmmo) {
        this.weaponNotAvailableForLackOfAmmo = weaponNotAvailableForLackOfAmmo;
    }

    public boolean isSelectionForRun() {
        return isSelectionForRun;
    }

    public void setSelectionForRun(boolean selectionForRun) {
        isSelectionForRun = selectionForRun;
    }

    public boolean isSelectionForCatch() {
        return isSelectionForCatch;
    }

    public void setSelectionForCatch(boolean selectionForCatch) {
        isSelectionForCatch = selectionForCatch;
    }
}
