package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;


public class PlayerBoard implements Serializable, Cloneable {
    static final long serialVersionUID = 420000001;
    private String nicknameOfPlayer;
    private ColorOfFigure_Square colorOfPlayer;
    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isFrenzyBoardPlayer =false;
    private boolean isFrenzyActionBar = false;
    private ArrayList<Integer> scoreBarForNormalMode = new ArrayList<>();
    private ArrayList<Integer> scoreBarForFrenzyMode = new ArrayList<>();
    private transient ArrayList<WeaponCard>  weaponsOwned = new ArrayList<>();
    private transient ArrayList<PowerUpCard> powerUpCardsOwned = new ArrayList<>();

    /**
     * The constructor marks as usable the beginning ammo according to game's rules
     */
    public PlayerBoard(String nickname, ColorOfFigure_Square color) {
        this.nicknameOfPlayer = nickname;
        this.colorOfPlayer = color;
        scoreBarForNormalMode.add(8);
        scoreBarForNormalMode.add(6);
        scoreBarForNormalMode.add(4);
        scoreBarForNormalMode.add(2);
        scoreBarForNormalMode.add(1);
        scoreBarForNormalMode.add(1);
        scoreBarForFrenzyMode.add(2);
        scoreBarForFrenzyMode.add(1);
        scoreBarForFrenzyMode.add(1);
        scoreBarForFrenzyMode.add(1);
        damages = new ArrayList<>();
        if(nickname.equals("pietro")){
            for(int i=0;i<4;i++)
                damages.add(ColorOfFigure_Square.GREEN);
            for(int i=0;i<4;i++)
                damages.add(ColorOfFigure_Square.GREY);
            for(int i=0;i<2;i++)
                damages.add(ColorOfFigure_Square.YELLOW);
        }
        if(nickname.equals("ciccio")){
            for(int i=0;i<4;i++)
                damages.add(ColorOfFigure_Square.BLUE);
            for(int i=0;i<4;i++)
                damages.add(ColorOfFigure_Square.GREY);
            for(int i=0;i<2;i++)
                damages.add(ColorOfFigure_Square.YELLOW);
        }


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

    public Object clone(){
        PlayerBoard playerBoard = null;
        try{
            playerBoard = (PlayerBoard) super.clone();
        } catch (CloneNotSupportedException e) {
            playerBoard = new PlayerBoard(this.nicknameOfPlayer, this.colorOfPlayer);
        }
        playerBoard.scoreBarForFrenzyMode = this.scoreBarForFrenzyMode;
        playerBoard.scoreBarForNormalMode = this.scoreBarForNormalMode;
        playerBoard.ammo=new ArrayList<>();
        for(Ammo ammoItem : this.ammo) {
            playerBoard.ammo.add ( (Ammo) ammoItem.clone());
        }
        playerBoard.damages = new ArrayList<>();

        for (ColorOfFigure_Square damage : this.damages){
            playerBoard.damages.add(damage);
        }
        playerBoard.marks = new ArrayList<>();
        for (ColorOfFigure_Square mark : this.marks){
            playerBoard.marks.add(mark);
        }
        playerBoard.scoreBarForFrenzyMode = new ArrayList<>();
        for(Integer score : this.scoreBarForFrenzyMode)
            playerBoard.scoreBarForFrenzyMode.add(score);
        playerBoard.scoreBarForNormalMode = new ArrayList<>();
        for(Integer score : this.scoreBarForNormalMode)
            playerBoard.scoreBarForNormalMode.add(score);


        return playerBoard;
    }

    public ArrayList<ColorOfFigure_Square> getDamages() {
        return damages;
    }

    public ArrayList<ColorOfFigure_Square> getMarks() {
        return marks;
    }

    public boolean isFrenzyActionBar() {
        return isFrenzyActionBar;
    }

    public void setFrenzyActionBar(boolean frenzyActionBar) {
        isFrenzyActionBar = frenzyActionBar;
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

    public boolean isFrenzyBoardPlayer() {
        return isFrenzyBoardPlayer;
    }


    public int getNumOfMarksOfOneColor(ColorOfFigure_Square color){
        return Collections.frequency(marks, color);
    }

    public void removeMarkOfOneColor(ColorOfFigure_Square color){
        this.marks.removeAll(Collections.singleton(color));
    }

    public void setFrenzyBoardPlayer(boolean frenzyBoardPlayer) {
        isFrenzyBoardPlayer = frenzyBoardPlayer;
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

    public ArrayList<Integer> getScoreBarForNormalMode() {
        return scoreBarForNormalMode;
    }

    public void setScoreBarForNormalMode(ArrayList<Integer> scoreBarForNormalMode) {
        this.scoreBarForNormalMode = scoreBarForNormalMode;
    }

    public ArrayList<Integer> getScoreBarForFrenzyMode() {
        return scoreBarForFrenzyMode;
    }

    public void setScoreBarForFrenzyMode(ArrayList<Integer> scoreBarForFrenzyMode) {
        this.scoreBarForFrenzyMode = scoreBarForFrenzyMode;
    }

    public void setDamages(ArrayList<ColorOfFigure_Square> damages) {
        this.damages = damages;
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
