package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

public class ShootAction extends ViewEvent {

    private ArrayList<InvolvedPlayer> playersToShoot;
    private WeaponCard weaponToUse;
    private ArrayList<TargetingScope> targetingScopeToUse;
    private boolean[] typeOfAttack;


    public ShootAction(Player player, ArrayList<InvolvedPlayer> playersToShoot, WeaponCard weaponToUse, ArrayList<TargetingScope> targetingScopeToUse, boolean[] typeOfAttack) {
        super(player);
        this.playersToShoot = playersToShoot;
        this.weaponToUse = weaponToUse;
        this.targetingScopeToUse = targetingScopeToUse;
        this.typeOfAttack = typeOfAttack;


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

    public boolean[] getTypeOfAttack() {
        return typeOfAttack;
    }
}
