package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;

import java.util.ArrayList;

public class PlayerBoardView {

    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;

    public ArrayList<ColorOfFigure_Square> getDamages() {
        return damages;
    }

    public void setDamages(ArrayList<ColorOfFigure_Square> damages) {
        this.damages = damages;
    }

    public ArrayList<ColorOfFigure_Square> getMarks() {
        return marks;
    }

    private void setMarks(ArrayList<ColorOfFigure_Square> marks) {
        this.marks = marks;
    }

    public ArrayList<Ammo> getAmmo() {
        return ammo;
    }

    public void setAmmo(ArrayList<Ammo> ammo) {
        this.ammo = ammo;
    }

    public boolean isInFrenzyBoardPlayer() {
        return isInFrenzyBoardPlayer;
    }

    private void setInFrenzyBoardPlayer(boolean inFrenzyBoardPlayer) {
        isInFrenzyBoardPlayer = inFrenzyBoardPlayer;
    }

    public void update (PlayerBoardEvent event){
        System.out.println(event.getMessage());
        setDamages(event.getPlayerBoard().getDamages());
        setMarks(event.getPlayerBoard().getMarks());
        setInFrenzyBoardPlayer(event.getPlayerBoard().isInFrenzyBoardPlayer());
        setAmmo(event.getPlayerBoard().getAmmo());

    }


}
