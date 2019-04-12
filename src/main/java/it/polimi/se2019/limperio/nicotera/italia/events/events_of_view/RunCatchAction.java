package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

public class RunCatchAction extends ViewEvent {

    private Square arrivalSquare;
    private boolean hasToCatchAmmoTile;
    private boolean hasToCatchAWeapon;

    public RunCatchAction(Player player, Square arrivalSquare, boolean hasToCatchAmmoTile, boolean hasToCatchAWeapon) {
        super(player);
        this.arrivalSquare = arrivalSquare;
        this.hasToCatchAmmoTile = hasToCatchAmmoTile;
        this.hasToCatchAWeapon = hasToCatchAWeapon;
    }

    public Square getArrivalSquare() {
        return arrivalSquare;
    }

    public boolean isHasToCatchAmmoTile() {
        return hasToCatchAmmoTile;
    }

    public boolean isHasToCatchAWeapon() {
        return hasToCatchAWeapon;
    }
}
