package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;


import java.util.ArrayList;

public class SpawnSquare extends Square  {

    private ArrayList<ServerEvent.AliasCard> weaponsCardsForRemoteView = new ArrayList<>();
    private ArrayList<WeaponCard> weaponCards;


     SpawnSquare( ColorOfFigure_Square color, boolean hasDoor, int row, int column) {
        super(color, hasDoor, row,column);
        weaponCards=new ArrayList<>();
        setSpawn(true);
    }

    public ArrayList<ServerEvent.AliasCard> getWeaponsCardsForRemoteView() {
        return weaponsCardsForRemoteView;
    }

    public ArrayList<WeaponCard> getWeaponCards() {
        return weaponCards;
    }

    public void setWeaponsCardsForRemoteView(ArrayList<ServerEvent.AliasCard> weaponsCardsForRemoteView) {
        this.weaponsCardsForRemoteView = weaponsCardsForRemoteView;
    }
}
