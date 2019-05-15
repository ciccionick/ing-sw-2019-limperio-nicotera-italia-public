package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import java.util.ArrayList;

/**
 * Event to show to a player in which square he can go to catch after he decided to catch as action.
 *
 * @author Pietro L'Imperio
 */
public class SelectionViewForSquareWhereCatch extends ServerEvent {
    /**
     * List of square reachable for catch by a player
     */
    private ArrayList<Square> squaresReachableForCatch;
    /**
     * List of weapons placed in spawn square but not available for the player because he has not enough ammos to pay their price.
     */
    private ArrayList<AliasCard> weaponNotAvailableForLackOfAmmo = new ArrayList<>();

    public SelectionViewForSquareWhereCatch(String message) {
        super(message);
        setSelectionSquareForCatching(true);
    }

    public ArrayList<Square> getSquaresReachableForCatch() {
        return squaresReachableForCatch;
    }

    public void setSquaresReachableForCatch(ArrayList<Square> squaresReachableForCatch) {
        this.squaresReachableForCatch = squaresReachableForCatch;
    }

    public ArrayList<AliasCard> getWeaponNotAvailableForLackOfAmmo() {
        return weaponNotAvailableForLackOfAmmo;
    }

    public void setWeaponNotAvailableForLackOfAmmo(ArrayList<AliasCard> weaponNotAvailableForLackOfAmmo) {
        this.weaponNotAvailableForLackOfAmmo = weaponNotAvailableForLackOfAmmo;
    }
}
