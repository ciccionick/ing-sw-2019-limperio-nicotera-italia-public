package it.polimi.se2019.limperio.nicotera.italia.model;


import java.util.ArrayList;

public interface PlayerBehaviour {
    void run(Square square);
    void catchAmmoTile (Square square);
    void shoot (Player[] players, WeaponCard weaponCard, int[] typeOfAttack);
    void catchWeapon(Square square, WeaponCard weaponCard);
    void drawPowerUpCard(ArrayList<PowerUpCard> powerUpCardsToDraw);
    void discardPowerUpCard(PowerUpCard card);

}
