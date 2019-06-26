package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseWeapon;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class handles the part of view that creates the player's board in the game
 * @author Giuseppe Italia
 */
public class PlayerBoardView {


    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private ArrayList<ServerEvent.AliasCard> powerUpCardsDeck = new ArrayList<>();
    private ArrayList<ServerEvent.AliasCard> weaponCardDeck = new ArrayList<>();
    private String nicknameOfPlayer;
    private ColorOfFigure_Square colorOfPlayer;
    private ArrayList<Integer> scoreBarForNormalMode;
    private ArrayList<Integer> scoreBarForFrenzyMode;

    private boolean hasToChoosePowerUpCardForSpawn = false;
    private boolean canUseNewton = false;
    private boolean canUseTeleporter = false;
    private boolean canUseTagbackGranade = false;
    private boolean canUseTargetingScope = false;
    private boolean canChooseWeapon1 = false;
    private boolean canChooseWeapon2 = false;
    private boolean canChooseWeapon3 = false;
    private boolean canShoot = false;
    private boolean canRun = false;
    private boolean canCatch = false;
    private boolean hasToDoTerminatorAction = false;
    private boolean isFrenzyPlayerBoard = false;
    private boolean isFrenzyActionBar = false;



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


    /**
     * Updates the player's board
     * @param event contains the updates of player's board
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


    public boolean isCanUseTagbackGranade() {
        return canUseTagbackGranade;
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

    public void updateThingsPlayerCanDo(RequestActionEvent receivedEvent) {
        canUseTeleporter = receivedEvent.isCanUseTeleporter();
        canUseTagbackGranade = receivedEvent.isCanUseTagbackGranade();
        canUseNewton = receivedEvent.isCanUseNewton();
        canShoot = receivedEvent.isCanShoot();
        hasToDoTerminatorAction= receivedEvent.isHasToDoTerminatorAction();
        canCatch = receivedEvent.isCanCatch();
        canRun = receivedEvent.isCanRun();
    }

    public void updateWeaponCanUse(RequestToChooseWeapon receivedEvent){
        canChooseWeapon1 = receivedEvent.isCanUseWeapon1();
        canChooseWeapon2 = receivedEvent.isCanUseWeapon2();
        canChooseWeapon3 = receivedEvent.isCanUseWeapon3();

    }

    public void disableWeaponsButton(){
        canChooseWeapon1 = false;
        canChooseWeapon2 = false;
        canChooseWeapon3 = false;
    }


         public void disableEveryThingPlayerCanDo() {
             hasToChoosePowerUpCardForSpawn = false;
             canUseNewton = false;
             canUseTeleporter = false;
             canUseTagbackGranade = false;
             canUseTargetingScope = false;
             canChooseWeapon1 = false;
             canChooseWeapon2 = false;
             canChooseWeapon3 = false;
             canShoot = false;
             canRun = false;
             canCatch = false;
             hasToDoTerminatorAction = false;
         }


}


