package it.polimi.se2019.limperio.nicotera.italia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken.SKULL;

public class PlayerBoard implements Serializable {
    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;
    private ArrayList<WeaponCard>  weaponsOwned = new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCardsOwned = new ArrayList<>();

    PlayerBoard() {
        damages = new ArrayList<>();
        marks = new ArrayList<>();
        ammo = new ArrayList<>();
        ammo.add(new Ammo(RED,true));
        ammo.add(new Ammo(BLUE,true));
        ammo.add(new Ammo(YELLOW,true));
        for (int i=0; i<2; i++){
            ammo.add(new Ammo(RED,false));
            ammo.add(new Ammo(BLUE,false));
            ammo.add(new Ammo(YELLOW,false));
        }

    }

    public ArrayList<ColorOfFigure_Square> getDamages() {
        return damages;
    }

    public ArrayList<ColorOfFigure_Square> getMarks() {
        return marks;
    }

    public void cleanPlayerBoard(){
        damages.clear();
    }


    public boolean isDeath(){
        if(getDamages().size()>9)
            return true;
        return false;
    }

    public boolean isInFrenzyBoardPlayer() {
        return isInFrenzyBoardPlayer;
    }



    public int getNumOfMarksOfOneColor(ColorOfFigure_Square color){
        return Collections.frequency(marks, color);
    }

    public void removeMarkOfOneColor(ColorOfFigure_Square color){
        this.marks.removeAll(Collections.singleton(color));
    }

    public void setInFrenzyBoardPlayer(boolean inFrenzyBoardPlayer) {
        isInFrenzyBoardPlayer = inFrenzyBoardPlayer;
    }

    public ArrayList<Ammo> getAmmo() {
        return ammo;
    }

    public void setWeaponsOwned(ArrayList<WeaponCard> weaponsOwned) {
        this.weaponsOwned = weaponsOwned;
    }

    public void setPowerUpCardsOwned(ArrayList<PowerUpCard> powerUpCardsOwned) {
        this.powerUpCardsOwned = powerUpCardsOwned;
    }

    public ArrayList<WeaponCard> getWeaponsOwned() {
        return weaponsOwned;
    }

    public ArrayList<PowerUpCard> getPowerUpCardsOwned() {
        return powerUpCardsOwned;
    }

    public void removeAmmoOfThisColor(ColorOfCard_Ammo color) {
        for (Ammo ammo : ammo) {
            if(ammo.getColor().equals(color)) {
                this.ammo.remove(ammo);
                break;
            }
        }
    }
}
