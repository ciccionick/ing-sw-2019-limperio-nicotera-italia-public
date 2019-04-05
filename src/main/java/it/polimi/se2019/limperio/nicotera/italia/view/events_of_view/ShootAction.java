package it.polimi.se2019.limperio.nicotera.italia.view.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

public class ShootAction extends ViewEvent {

    private ArrayList<InvolvedPlayer> playersToShoot;
    private WeaponCard weaponToUse;
    private ArrayList<TargetingScope> targetingScopeToUse;


    public ShootAction(Player player, ArrayList<InvolvedPlayer> playersToShoot, WeaponCard weaponToUse, ArrayList<TargetingScope> targetingScopeToUse) {
        super(player);
        this.playersToShoot = playersToShoot;
        this.weaponToUse = weaponToUse;
        this.targetingScopeToUse = targetingScopeToUse;
    }

    public ArrayList<InvolvedPlayer> getPlayersToShoot() {
        return playersToShoot;
    }

    public WeaponCard getWeaponToUse() {
        return weaponToUse;
    }

    public ArrayList<TargetingScope> getTargetingScopeToUse() {
        return targetingScopeToUse;
    }
}
