package it.polimi.se2019.limperio.nicotera.italia.model;

public class PlayerBoard {
    private ColorOfFigure_Square[] damages;
    private ColorOfFigure_Square[] marks;
    private Ammo[] ammo;
    private boolean isInFrenzyBoardPlayer=false;


    public ColorOfFigure_Square[] getDamages() {
        return damages;
    }

    public ColorOfFigure_Square[] getMarks() {
        return marks;
    }
    public void cleanPlayerBoard(){}
    public boolean isDeath(){
        return true;
    }

    public boolean isInFrenzyBoardPlayer() {
        return isInFrenzyBoardPlayer;
    }

    public void setInFrenzyBoardPlayer(boolean inFrenzyBoardPlayer) {
        isInFrenzyBoardPlayer = inFrenzyBoardPlayer;
    }
}
