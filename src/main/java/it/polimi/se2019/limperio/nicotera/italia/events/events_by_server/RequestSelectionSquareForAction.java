package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import java.util.ArrayList;

public class RequestSelectionSquareForAction extends ServerEvent {

    private ArrayList<Square> squaresReachable = new ArrayList<>();
    private ArrayList<AliasCard> weaponNotAvailableForLackOfAmmo = new ArrayList<>();
    private boolean isSelectionForRun = false;
    private boolean isSelectionForCatch = false;
    private boolean isSelectionForSpawnTerminator = false;
    private boolean isSelectionForMoveTerminator = false;
    private boolean isSelectionForTeleporter = false;
    private boolean isSelectionForNewton = false;
    private boolean isBeforeToShoot = false;

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

    public boolean isBeforeToShoot() {
        return isBeforeToShoot;
    }

    public void setBeforeToShoot(boolean beforeToShoot) {
        isBeforeToShoot = beforeToShoot;
    }

    public boolean isSelectionForMoveTerminator() {
        return isSelectionForMoveTerminator;
    }

    public void setSelectionForMoveTerminator(boolean selectionForMoveTerminator) {
        isSelectionForMoveTerminator = selectionForMoveTerminator;
    }

    public boolean isSelectionForSpawnTerminator() {
        return isSelectionForSpawnTerminator;
    }

    public void setSelectionForSpawnTerminator(boolean selectionForPutTerminator) {
        isSelectionForSpawnTerminator = selectionForPutTerminator;
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

    public boolean isSelectionForTeleporter() {
        return isSelectionForTeleporter;
    }

    public void setSelectionForTeleporter(boolean selectionForTeleporter) {
        isSelectionForTeleporter = selectionForTeleporter;
    }

    public boolean isSelectionForNewton() {
        return isSelectionForNewton;
    }

    public void setSelectionForNewton(boolean selectionForNewton) {
        isSelectionForNewton = selectionForNewton;
    }
}
