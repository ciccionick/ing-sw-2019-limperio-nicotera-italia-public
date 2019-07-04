package it.polimi.se2019.limperio.nicotera.italia.model;


import it.polimi.se2019.limperio.nicotera.italia.controller.InvolvedPlayer;
import java.util.ArrayList;

/**
 * Interface to implements to describe the behaviour of the player during the game.
 * @author Pietro L'Imperio.
 */
public interface PlayerBehaviour {
    /**
     * Calls the method to use a weapon passing the parameter needed to complete correctly the attack.
     * @param effect The number of the effect of the weapon that player wants to use.
     * @param weaponCard The weapon card that player wants to use.
     * @param involvedPlayers The list of the objects containing player or/and square involved in the effect.
     * @param priceToPay The price to pay to use the effect
     * @param powerUpToDiscard The list of power up cards that the player has decided to discard to contribute to the payment of the effect.
     */
    void shoot (int effect, WeaponCard weaponCard, ArrayList<InvolvedPlayer> involvedPlayers, ColorOfCard_Ammo[] priceToPay, ArrayList<PowerUpCard> powerUpToDiscard);

    /**
     * Catch the weapon card from a spawn square during a catch action.
     * @param weaponCard The weapon card that player wants to catch.
     * @param powerUpCardToDiscard The list of power up cards that the player has decided to discard to contribute to the payment for the weapon.
     */
    void catchWeapon(WeaponCard weaponCard, ArrayList<PowerUpCard> powerUpCardToDiscard);

    /**
     * Draw a power up card and put it on the deck of the player.
     * @param powerUpCardsToDraw The power up card to draw.
     */
    void drawPowerUpCard(PowerUpCard powerUpCardsToDraw);

    /**
     * Uses the power up card targeting scope.
     * @param playerToAttack The player to attack with the effect of targeting scope.
     * @param targetingScope The power up card to use.
     * @param ammoToDiscard The ammo to discard to pay the effect.
     * @param powerUpCardToDiscard The power up card to discard to pay the effect.
     */
    void useTargetingScope(Player playerToAttack, PowerUpCard targetingScope, ColorOfCard_Ammo ammoToDiscard, PowerUpCard powerUpCardToDiscard);

    /**
     * Uses the effect of tagback granade.
     * @param tagback  The power up card that player decides to use.
     * @param playerToAttack The player who give the mark for the use of the effect of the tagback.
     */
    void useTagbackGranade(PowerUpCard tagback, Player playerToAttack);

    /**
     * Reloads a weapon chosen by the player.
     * @param card The weapon card that player wants to reload.
     * @param powerUpCardsToDiscard The list of power up cards that the player has decided to discard to contribute to the payment of the reload.
     */
    void reload(WeaponCard card, ArrayList<PowerUpCard> powerUpCardsToDiscard);
}
