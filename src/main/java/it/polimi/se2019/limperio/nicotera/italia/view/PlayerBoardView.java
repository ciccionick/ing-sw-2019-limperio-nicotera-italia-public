package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.PlayerBoardEvent;
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
        //System.out.println(event.getMessage());
        updateStateOfPlayerBoard(event);
    }

    /**
     * Updates the player's board
     * @param event contains the updates of player's board
     */
    private void updateStateOfPlayerBoard (PlayerBoardEvent event){
        setDamages(event.getPlayerBoard().getDamages());
        setMarks(event.getPlayerBoard().getMarks());
        setInFrenzyBoardPlayer(event.getPlayerBoard().isInFrenzyBoardPlayer());
        setAmmo(event.getPlayerBoard().getAmmo());
        setPowerUpCardsDeck(event.getPowerUpCardsOwned());
        setWeaponCardDeck(event.getWeaponCardsOwned());
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


}
