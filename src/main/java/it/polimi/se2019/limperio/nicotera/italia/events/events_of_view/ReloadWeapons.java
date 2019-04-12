package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

public class ReloadWeapons extends ViewEvent {

    private ArrayList<WeaponCard> weaponsToReload;

    public ReloadWeapons(Player player, ArrayList<WeaponCard> weaponsToReload) {
        super(player);
        this.weaponsToReload = weaponsToReload;
    }

    public ArrayList<WeaponCard> getWeaponsToReload() {
        return weaponsToReload;
    }
}
