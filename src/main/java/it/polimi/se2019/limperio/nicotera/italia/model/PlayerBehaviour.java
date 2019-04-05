package it.polimi.se2019.limperio.nicotera.italia.model;

public interface PlayerBehaviour {
    void run(Square square);
    void catchAmmoTile (Square square);
    void shoot (Player[] players, WeaponCard weaponCard, int[] typeOfAttack);
    void catchWheapon(Square square, WeaponCard weaponCard);
    void drawTwoPowerUpCard();
    void discardPowerUpCard(PowerUpCard card);

}
