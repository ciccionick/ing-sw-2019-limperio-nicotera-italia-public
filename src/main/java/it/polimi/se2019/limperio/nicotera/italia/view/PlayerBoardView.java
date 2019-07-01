package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseWeapon;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * This class handles the part of view concerning with all of the information about the player board.
 * @author Giuseppe Italia
 */
public class PlayerBoardView {


    /**
     * The list of damage on the board.
     */
    private ArrayList<ColorOfFigure_Square> damages;
    /**
     * The list of marks on the board.
     */
    private ArrayList<ColorOfFigure_Square> marks;
    /**
     * The list of available ammo.
     */
    private ArrayList<Ammo> ammo;
    /**
     * The deck of power up cards.
     */
    private ArrayList<ServerEvent.AliasCard> powerUpCardsDeck = new ArrayList<>();
    /**
     * The deck of weapon cards.
     */
    private ArrayList<ServerEvent.AliasCard> weaponCardDeck = new ArrayList<>();
    /**
     * The nickname of the player owner of the current player board.
     */
    private String nicknameOfPlayer;
    /**
     * The color of the figure of the player owner of the player board.
     */
    private ColorOfFigure_Square colorOfPlayer;
    /**
     * The list of the score to give to the players that have attacked the player owner after his death before the frenzy mode.
     */
    private ArrayList<Integer> scoreBarForNormalMode;
    /**
     * The list of the score to give to the players that have attacked the player owner after his death during the frenzy mode.
     */
    private ArrayList<Integer> scoreBarForFrenzyMode;
    /**
     * It states if during this action the player has to choose a square.
     */
    private boolean hasToChoosePowerUpCardForSpawn = false;
    /**
     * It states if in any moments of the action the player can use Newton.
     */
    private boolean canUseNewton = false;
    /**
     * It states if in any moments of the action the player can use Teleporter.
     */
    private boolean canUseTeleporter = false;
    /**
     * It states if, after to have choose to shoot, the player can use the first weapon in his deck.
     */
    private boolean canChooseWeapon1 = false;
    /**
     * It states if, after to have choose to shoot, the player can use the second weapon in his deck.
     */
    private boolean canChooseWeapon2 = false;
    /**
     * It states if, after to have choose to shoot, the player can use the third weapon in his deck.
     */
    private boolean canChooseWeapon3 = false;
    /**
     * It states if during the phase where the player has to choose an action, he can shoot.
     */
    private boolean canShoot = false;
    /**
     * It states if during the phase where the player has to choose an action, he can catch.
     */
    private boolean canRun = false;
    /**
     * It states if during the phase where the player has to choose an action, he can run.
     */
    private boolean canCatch = false;
    /**
     * It states if during the phase where the player has to choose an action, he can use the terminator action.
     */
    private boolean hasToDoTerminatorAction = false;
    /**
     * It states if the player board is completely in frenzy mode (action board and damage board)
     */
    private boolean isFrenzyPlayerBoard = false;
    /**
     * It states if the player board has the action board like the action board of the frenzy mode.
     */
    private boolean isFrenzyActionBar = false;



    /**
     * Updates the player board with the information contained in the event
     * @param event contains the information of player board that has to be updated
     */
    public void update(PlayerBoardEvent event){
            setDamages(event.getPlayerBoard().getDamages());
            setMarks(event.getPlayerBoard().getMarks());
            setFrenzyPlayerBoard(event.getPlayerBoard().isFrenzyBoardPlayer());
            setFrenzyActionBar(event.getPlayerBoard().isFrenzyActionBar());
            setAmmo(event.getPlayerBoard().getAmmo());
            setPowerUpCardsDeck(event.getPowerUpCardsOwned());
            setScoreBarForNormalMode(event.getPlayerBoard().getScoreBarForNormalMode());
            setScoreBarForFrenzyMode(event.getPlayerBoard().getScoreBarForFrenzyMode());
            setWeaponCardDeck(event.getWeaponCardsOwned());
            setHasToChoosePowerUpCardForSpawn(event.isHasToDiscardCard());
            this.nicknameOfPlayer = event.getPlayerBoard().getNicknameOfPlayer();
            this.colorOfPlayer = event.getPlayerBoard().getColorOfPlayer();
    }

    /**
     * It updates the boolean fields about the things that the player can do during his turn.
     * @param receivedEvent The event containing the information about the things the player can do
     */

    public void updateThingsPlayerCanDo(RequestActionEvent receivedEvent) {
        canUseTeleporter = receivedEvent.isCanUseTeleporter();
        canUseNewton = receivedEvent.isCanUseNewton();
        canShoot = receivedEvent.isCanShoot();
        hasToDoTerminatorAction= receivedEvent.isHasToDoTerminatorAction();
        canCatch = receivedEvent.isCanCatch();
        canRun = receivedEvent.isCanRun();
    }

    /**
     * It updates the state of the boolean fields that indicate which weapons the player can use.
     * @param receivedEvent The event containing the information about the weapon usable by the player.
     */

    public void updateWeaponCanUse(RequestToChooseWeapon receivedEvent){
        canChooseWeapon1 = receivedEvent.isCanUseWeapon1();
        canChooseWeapon2 = receivedEvent.isCanUseWeapon2();
        canChooseWeapon3 = receivedEvent.isCanUseWeapon3();
    }

    /**
     * Makes false all of the boolean fields about the option to use weapon to make disable the button in the GUI.
     */
    public void disableWeaponsButton(){
        canChooseWeapon1 = false;
        canChooseWeapon2 = false;
        canChooseWeapon3 = false;
    }


    /**
     * Makes false all of the boolean fields about the things that a player can do, for example when finish his turn.
     */
         public void disableEveryThingPlayerCanDo() {
             hasToChoosePowerUpCardForSpawn = false;
             canUseNewton = false;
             canUseTeleporter = false;
             canChooseWeapon1 = false;
             canChooseWeapon2 = false;
             canChooseWeapon3 = false;
             canShoot = false;
             canRun = false;
             canCatch = false;
             hasToDoTerminatorAction = false;
         }

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


    public boolean isCanShoot() {
        return canShoot;
    }

    public ArrayList<ServerEvent.AliasCard> getPowerUpCardsDeck() {
        return powerUpCardsDeck;
    }

    private void setWeaponCardDeck(ArrayList<ServerEvent.AliasCard> weaponCardDeck) {
        this.weaponCardDeck = weaponCardDeck;
    }

    private void setPowerUpCardsDeck(ArrayList<ServerEvent.AliasCard> powerUpCardsDeck) {
        this.powerUpCardsDeck = powerUpCardsDeck;
    }

    public ColorOfFigure_Square getColorOfPlayer() {
        return colorOfPlayer;
    }

    public String getNicknameOfPlayer() {
        return nicknameOfPlayer;
    }

    public ArrayList<ServerEvent.AliasCard> getWeaponCardDeck() {
        return weaponCardDeck;
    }


    public boolean isHasToChoosePowerUpCardForSpawn() {
        return hasToChoosePowerUpCardForSpawn;
    }

    private void setHasToChoosePowerUpCardForSpawn(boolean hasToChoosePowerUpCardForSpawn) {
        this.hasToChoosePowerUpCardForSpawn = hasToChoosePowerUpCardForSpawn;
    }

    public boolean isCanUseNewton() {
        return canUseNewton;
    }


    public boolean isCanUseTeleporter() {
        return canUseTeleporter;
    }

    public boolean isCanChooseWeapon1() {
        return canChooseWeapon1;
    }

    public boolean isCanChooseWeapon2() {
        return canChooseWeapon2;
    }

    public boolean isCanChooseWeapon3() {
        return canChooseWeapon3;
    }

    public void setCanChooseWeapon1(boolean canChooseWeapon1) {
        this.canChooseWeapon1 = canChooseWeapon1;
    }


    public void setCanChooseWeapon2(boolean canChooseWeapon2) {
        this.canChooseWeapon2 = canChooseWeapon2;
    }


    public void setCanChooseWeapon3(boolean canChooseWeapon3) {
        this.canChooseWeapon3 = canChooseWeapon3;
    }


    public boolean isCanRun() {
        return canRun;
    }

    public boolean isFrenzyPlayerBoard() {
        return isFrenzyPlayerBoard;
    }

    private void setFrenzyPlayerBoard(boolean frenzyPlayerBoard) {
        isFrenzyPlayerBoard = frenzyPlayerBoard;
    }

    public boolean isFrenzyActionBar() {
        return isFrenzyActionBar;
    }

    private void setFrenzyActionBar(boolean frenzyActionBar) {
        isFrenzyActionBar = frenzyActionBar;
    }


    public boolean isCanCatch() {
        return canCatch;
    }


    public ArrayList<Integer> getScoreBarForNormalMode() {
        return scoreBarForNormalMode;
    }

    private void setScoreBarForNormalMode(ArrayList<Integer> scoreBarForNormalMode) {
        this.scoreBarForNormalMode = scoreBarForNormalMode;
    }

    public ArrayList<Integer> getScoreBarForFrenzyMode() {
        return scoreBarForFrenzyMode;
    }

    private void setScoreBarForFrenzyMode(ArrayList<Integer> scoreBarForFrenzyMode) {
        this.scoreBarForFrenzyMode = scoreBarForFrenzyMode;
    }

    public boolean isHasToDoTerminatorAction() {
        return hasToDoTerminatorAction;
    }



}


