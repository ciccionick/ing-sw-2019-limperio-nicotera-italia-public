package it.polimi.se2019.limperio.nicotera.italia.model;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken.SKULL;

public class PlayerBoard implements Serializable {
    private String nicknameOfPlayer;
    private ColorOfFigure_Square colorOfPlayer;
    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;
    private transient ArrayList<WeaponCard>  weaponsOwned = new ArrayList<>();
    private transient ArrayList<PowerUpCard> powerUpCardsOwned = new ArrayList<>();

    /**
     * The constructor marks as usable the beginning ammo according to game's rules
     */
    public PlayerBoard(String nickname, ColorOfFigure_Square color) {
        this.nicknameOfPlayer = nickname;
        this.colorOfPlayer = color;
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

    /**
     * Cleans the board from damages when a player dies
     */
    public void cleanPlayerBoard(){
        damages.clear();
    }

    /**
     * Checks if the player is dead
     * @return true if the damages are more than 9
     */
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


    public ArrayList<WeaponCard> getWeaponsOwned() {
        return weaponsOwned;
    }

    public ArrayList<PowerUpCard> getPowerUpCardsOwned() {
        return powerUpCardsOwned;
    }

    public String getNicknameOfPlayer() {
        return nicknameOfPlayer;
    }

    public ColorOfFigure_Square getColorOfPlayer() {
        return colorOfPlayer;
    }

    /**
     * Removes an ammo of one color from the ammo deck of the player
     * @param color the type of ammo that must be removed
     */
    public void removeAmmoOfThisColor(ColorOfCard_Ammo color) {
        for (Ammo ammo : ammo) {
            if(ammo.getColor().equals(color)) {
                ammo.setIsUsable(false);
                break;
            }
        }
    }

    /**
     * Adds ammo to player's deck of ammo
     * @param color the color of the ammo that has to be added
     * @param numOfAmmoCaught the number of ammo thhat has to be added
     */
    public void addAmmoToPlayer(ColorOfCard_Ammo color, int numOfAmmoCaught){
        int numOfAmmoUsable = numOfAmmoCaught;
        for(int i=0; i<9 && numOfAmmoUsable>0; i++){
            if(getAmmo().get(i).getColor().equals(color) && !getAmmo().get(i).isUsable()){
                getAmmo().get(i).setIsUsable(true);
                numOfAmmoUsable--;
            }
        }
    }
}
