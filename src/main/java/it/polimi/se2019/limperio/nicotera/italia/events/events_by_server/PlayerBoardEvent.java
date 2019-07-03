package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.util.ArrayList;

/**
 * Event for update of the player board view in the remote view of the players.
 * @author Pietro L'Imperio
 */
public class PlayerBoardEvent extends ServerEvent {
    /**
     * List of alias cards representing the weapons in the deck of the player board involved in the event.
     */
    private ArrayList<AliasCard> weaponCardsOwned = new ArrayList<>();
    /**
     * List of alias cards representing the power up cards
     * in the deck of the player board involved in the event.
     */
    private ArrayList<AliasCard> powerUpCardsOwned = new ArrayList<>();
    /**
     * It's true only in the case the player has to discard a power up card to decide where will be spawn.
     */
    private boolean hasToDiscardCard = false;


    /**
     * The player board updated in {@link PlayerBoardEvent}
     */
    private PlayerBoard playerBoard = null;

    /**
     * Constructor that sets true the boolean field relative of this event.
     */
    public PlayerBoardEvent() {
        setPlayerBoardEvent();
    }

    /**
     * Transforms all of the weapon cards in the deck in the correlative alias cards.
     * @param weaponsOwned List of weapon cards owned by the player owner of the player board involved in the event.
     */
    private void setWeaponCardsOwned(ArrayList<WeaponCard> weaponsOwned) {
        for(WeaponCard card : weaponsOwned){
            AliasCard aliasCard = new AliasCard(card.getName(), card.getDescription(), card.getColor());
            aliasCard.setLoaded(card.isLoad());
            aliasCard.setDescriptionOfEffects(card.getDescriptionsOfAttack());
            aliasCard.setNameOfEffects(card.getNamesOfAttack());
            this.weaponCardsOwned.add(aliasCard);
        }
    }

    /**
     * Transforms all of the power up cards in the deck in the correlative alias cards.
     * @param powerUpOwned List of power up cards owned by the player owner of the player board involved in the event.
     */
    private void setPowerUpCardsOwned(ArrayList<PowerUpCard> powerUpOwned) {
        for(PowerUpCard card : powerUpOwned){
            this.powerUpCardsOwned.add(new AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
    }

    /**
     * Sets the reference of the player board calling the methods that transform cards in alias cards.
     * @param playerBoard The reference of the player board.
     */
    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = (PlayerBoard) playerBoard.clone();
        setWeaponCardsOwned(playerBoard.getWeaponsOwned());
        setPowerUpCardsOwned(playerBoard.getPowerUpCardsOwned());

    }

    public boolean isHasToDiscardCard() {
        return hasToDiscardCard;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public ArrayList<AliasCard> getWeaponCardsOwned() {
        return weaponCardsOwned;
    }

    public ArrayList<AliasCard> getPowerUpCardsOwned() {
        return powerUpCardsOwned;
    }

}
