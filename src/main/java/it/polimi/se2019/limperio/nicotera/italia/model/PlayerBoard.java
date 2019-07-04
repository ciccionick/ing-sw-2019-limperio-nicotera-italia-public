package it.polimi.se2019.limperio.nicotera.italia.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import static it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo.*;

/**
 * Represents the player board of a player including his decks of weapon cards and power up cards.
 * @author Pietro L'Imperio.
 */
public class PlayerBoard implements Serializable, Cloneable {
    static final long serialVersionUID = 420000001;
    /**
     * The nickname of the player owner of the player board.
     */
    private String nicknameOfPlayer;
    /**
     * The color of the player owner of the player board.
     */
    private ColorOfFigure_Square colorOfPlayer;
    /**
     * The list of colors of figure that represents the damage of the player owner of the player board.
     */
    private ArrayList<ColorOfFigure_Square> damages;
    /**
     * The list of colors of figure that represents the marks of the player owner of the player board.
     */
    private ArrayList<ColorOfFigure_Square> marks;
    /**
     * The list of ammo of the player owner of the player board.
     */
    private ArrayList<Ammo> ammo;
    /**
     * It's true if the player board is completely in frenzy mode (action bar and central board), false otherwise.
     */
    private boolean isFrenzyBoardPlayer =false;
    /**
     * It's true if the frenzy has began when the player has still damage and so only the action bar is in the frenzy mode, false otherwise.
     */
    private boolean isFrenzyActionBar = false;
    /**
     * The list of the score to give to the players that attacked the owner of the player board after his death in the normal mode.
     */
    private ArrayList<Integer> scoreBarForNormalMode = new ArrayList<>();
    /**
     * The list of the score to give to the players that attacked the owner of the player board after his death in the frenzy mode.
     */
    private ArrayList<Integer> scoreBarForFrenzyMode = new ArrayList<>();
    /**
     * The deck of the weapon cards of the owner of the player board.
     */
    private transient ArrayList<WeaponCard>  weaponsOwned = new ArrayList<>();
    /**
     * The deck of power up cards of the owner of the player board.
     */
    private transient ArrayList<PowerUpCard> powerUpCardsOwned = new ArrayList<>();

    /**
     * The constructor marks as usable the beginning ammo according to game's rules and initializes the score for normal and frenzy mode
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

    /**
     * Override of the clone method to make possible the serialization avoiding the shallow copy.
     * @return The cloned object.
     */
    public Object clone(){
        PlayerBoard playerBoard;
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
        playerBoard.damages.addAll(this.damages);
        playerBoard.marks = new ArrayList<>();
        playerBoard.marks.addAll(this.marks);
        playerBoard.scoreBarForFrenzyMode = new ArrayList<>();
        playerBoard.scoreBarForFrenzyMode.addAll(this.scoreBarForFrenzyMode);
        playerBoard.scoreBarForNormalMode = new ArrayList<>();
        playerBoard.scoreBarForNormalMode.addAll(this.scoreBarForNormalMode);
        return playerBoard;
    }


    /**
     * Checks if the player is dead
     * @return true if the damages are more than 9
     */
    boolean isDeath(){
        return damages.size()>9;
    }

    /**
     * Removes an ammo of one color from the ammo deck of the player
     * @param color the type of ammo that must be removed
     */
     void removeAmmoOfThisColor(ColorOfCard_Ammo color) {
        for (Ammo ammoItem : ammo) {
            if(ammoItem.getColor().equals(color)) {
                ammoItem.setIsUsable(false);
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

    public boolean isFrenzyBoardPlayer() {
        return isFrenzyBoardPlayer;
    }


    int getNumOfMarksOfOneColor(ColorOfFigure_Square color){
        return Collections.frequency(marks, color);
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

    public ArrayList<Integer> getScoreBarForFrenzyMode() {
        return scoreBarForFrenzyMode;
    }


    public void setDamages(ArrayList<ColorOfFigure_Square> damages) {
        this.damages = damages;
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

}
