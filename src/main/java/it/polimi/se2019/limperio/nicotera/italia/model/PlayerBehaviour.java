package it.polimi.se2019.limperio.nicotera.italia.model;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.InvolvedPlayer;

import java.util.ArrayList;

public interface PlayerBehaviour {
    void shoot (int effect, WeaponCard weaponCard, ArrayList<InvolvedPlayer> involvedPlayers, ColorOfCard_Ammo[] priceToPay, ArrayList<PowerUpCard> powerUpToDiscard);
    void catchWeapon(WeaponCard weaponCard, ArrayList<PowerUpCard> powerUpCardToDiscard);
    void drawPowerUpCard(PowerUpCard powerUpCardsToDraw);
    void useTargetingScope(Player playerToAttack, PowerUpCard targetingScope, ColorOfCard_Ammo ammoToDiscard, PowerUpCard powerUpCardToDiscard);
    void useTagbackGranade(PowerUpCard tagback, Player ownerOfCard);

    void reload(WeaponCard card, ArrayList<PowerUpCard> powerUpCardsToDiscard);
}
