package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerBoardView {

    private ArrayList<ColorOfFigure_Square> damages;
    private ArrayList<ColorOfFigure_Square> marks;
    private ArrayList<Ammo> ammo;
    private boolean isInFrenzyBoardPlayer=false;
    private ArrayList<ModelEvent.AliasPowerUp> powerUpCardsDeck = new ArrayList<>();
    private ArrayList<WeaponCard> weaponCardDeck = new ArrayList<>();
    private Scanner stdin = new Scanner(System.in);

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

    public void update (ModelEvent event){
        System.out.println(event.getMessage());
        updateStateOfPlayerBoard(event);
    }

    private void updateStateOfPlayerBoard (ModelEvent event){
        setDamages(event.getPlayerBoard().getDamages());
        setMarks(event.getPlayerBoard().getMarks());
        setInFrenzyBoardPlayer(event.getPlayerBoard().isInFrenzyBoardPlayer());
        setAmmo(event.getPlayerBoard().getAmmo());
        setPowerUpCardsDeck(event.getPowerUpCards());
        getWeaponCardDeck().addAll(event.getPlayerBoard().getWeaponsOwned());
    }

    public ArrayList<ModelEvent.AliasPowerUp> getPowerUpCardsDeck() {
        return powerUpCardsDeck;
    }

    public void setPowerUpCardsDeck(ArrayList<ModelEvent.AliasPowerUp> powerUpCardsDeck) {
        this.powerUpCardsDeck = powerUpCardsDeck;
    }

    public ArrayList<WeaponCard> getWeaponCardDeck() {
        return weaponCardDeck;
    }


}