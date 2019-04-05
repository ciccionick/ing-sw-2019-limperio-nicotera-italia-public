package it.polimi.se2019.limperio.nicotera.italia.model;

import java.util.ArrayList;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;
import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken.SKULL;

public class PlayerBoard {
    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;

    public PlayerBoard() {
        damages = new ArrayList<ColorOfFigure_Square>();
        marks = new ArrayList<ColorOfFigure_Square>();
        ammo = new ArrayList<Ammo>();
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
    public void cleanPlayerBoard(){}


    public boolean isDeath(){
        if(getDamages().get(10).equals(SKULL))
            return true;
        return false;
    }

    public boolean isInFrenzyBoardPlayer() {
        return isInFrenzyBoardPlayer;
    }

    public void setInFrenzyBoardPlayer(boolean inFrenzyBoardPlayer) {
        isInFrenzyBoardPlayer = inFrenzyBoardPlayer;
    }
}
