package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestActionEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;

/**
 * This class handles the part of view that creates the player's board in the game
 * @author Giuseppe Italia
 */
public class PlayerBoardView {


    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;
    private ArrayList<ServerEvent.AliasCard> powerUpCardsDeck = new ArrayList<>();
    private ArrayList<ServerEvent.AliasCard> weaponCardDeck = new ArrayList<>();
    private String nicknameOfPlayer;
    private ColorOfFigure_Square colorOfPlayer;

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

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    /**
     * Updates the player's board
     * @param event contains the updates of player's board
     */
    public void update(PlayerBoardEvent event){

            setDamages(event.getPlayerBoard().getDamages());
            setMarks(event.getPlayerBoard().getMarks());
            setInFrenzyBoardPlayer(event.getPlayerBoard().isInFrenzyBoardPlayer());
            setAmmo(event.getPlayerBoard().getAmmo());
            setPowerUpCardsDeck(event.getPowerUpCardsOwned());
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

     void setPowerUpCardsDeck(ArrayList<ServerEvent.AliasCard> powerUpCardsDeck) {
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

    public void setHasToChoosePowerUpCardForSpawn(boolean hasToChoosePowerUpCardForSpawn) {
        this.hasToChoosePowerUpCardForSpawn = hasToChoosePowerUpCardForSpawn;
    }

    public boolean isCanUseNewton() {
        return canUseNewton;
    }

    public void setCanUseNewton(boolean canUseNewton) {
        this.canUseNewton = canUseNewton;
    }

    public boolean isCanUseTeleporter() {
        return canUseTeleporter;
    }

    public void setCanUseTeleporter(boolean canUseTeleporter) {
        this.canUseTeleporter = canUseTeleporter;
    }

    public boolean isCanUseTagbackGranade() {
        return canUseTagbackGranade;
    }

    public void setCanUseTagbackGranade(boolean canUseTagbackGranade) {
        this.canUseTagbackGranade = canUseTagbackGranade;
    }

    public boolean isCanChooseWeapon1() {
        return canChooseWeapon1;
    }

    public void setCanChooseWeapon1(boolean canChooseWeapon1) {
        this.canChooseWeapon1 = canChooseWeapon1;
    }

    public boolean isCanChooseWeapon2() {
        return canChooseWeapon2;
    }

    public void setCanChooseWeapon2(boolean canChooseWeapon2) {
        this.canChooseWeapon2 = canChooseWeapon2;
    }

    public boolean isCanChooseWeapon3() {
        return canChooseWeapon3;
    }

    public void setCanChooseWeapon3(boolean canChooseWeapon3) {
        this.canChooseWeapon3 = canChooseWeapon3;
    }

    public boolean isCanUseTargetingScope() {
        return canUseTargetingScope;
    }

    public void setCanUseTargetingScope(boolean canUseTargetingScope) {
        this.canUseTargetingScope = canUseTargetingScope;
    }

    public boolean isCanRun() {
        return canRun;
    }

    public void setCanRun(boolean canRun) {
        this.canRun = canRun;
    }

    public boolean isCanCatch() {
        return canCatch;
    }

    public void setCanCatch(boolean canCatch) {
        this.canCatch = canCatch;
    }


    public boolean isHasToDoTerminatorAction() {
        return hasToDoTerminatorAction;
    }

    void updateThingsPlayerCanDo(RequestActionEvent receivedEvent) {
        canUseTeleporter = receivedEvent.isCanUseTeleporter();
        canUseTagbackGranade = receivedEvent.isCanUseTagbackGranade();
        canUseNewton = receivedEvent.isCanUseNewton();
        canChooseWeapon1 = receivedEvent.isCanUseWeapon1();
        canChooseWeapon2 = receivedEvent.isCanUseWeapon2();
        canChooseWeapon3 = receivedEvent.isCanUseWeapon3();
        canShoot = receivedEvent.isCanShoot();
        hasToDoTerminatorAction= receivedEvent.isHasToDoTerminatorAction();
        canRun = true;
        canCatch = true;

    }


}
