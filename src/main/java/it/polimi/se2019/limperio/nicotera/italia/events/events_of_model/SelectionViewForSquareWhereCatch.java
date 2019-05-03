package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;


import it.polimi.se2019.limperio.nicotera.italia.model.Square;


import java.util.ArrayList;

public class SelectionViewForSquareWhereCatch extends ModelEvent {
    private ArrayList<Square> SquaresReachableForCatch;
    private ArrayList<AliasCard> WeaponNotAvailableForLackOfAmmos = new ArrayList<>();

    public SelectionViewForSquareWhereCatch(String message) {
        super(message);
        setSelectionSquareForSquareWhereCatch(true);
    }

    public ArrayList<Square> getSquaresReachableForCatch() {
        return SquaresReachableForCatch;
    }

    public ArrayList<AliasCard> getWeaponNotAvailableForLackOfAmmos() {
        return WeaponNotAvailableForLackOfAmmos;
    }

    public void setWeaponNotAvailableForLackOfAmmos(ArrayList<AliasCard> weaponNotAvailableForLackOfAmmos) {
        WeaponNotAvailableForLackOfAmmos = weaponNotAvailableForLackOfAmmos;
    }

    public void setSquaresReachableForCatch(ArrayList<Square> squaresReachableForCatch) {
        SquaresReachableForCatch = squaresReachableForCatch;
    }
}
