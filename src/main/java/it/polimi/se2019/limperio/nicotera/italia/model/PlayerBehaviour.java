package it.polimi.se2019.limperio.nicotera.italia.model;

public interface PlayerBehaviour {
    void run(Square square);
    void catchAmmoTile (Square square);
    void shoot (Player[] players, WheaponCard weaponCard, int[] typeOfAttack);
    void catchWheapon(Square square, WheaponCard weaponCard);
    void fishTwoPowerUpCard();
    void discardPowerUpCard(PowerUpCard card);

}
