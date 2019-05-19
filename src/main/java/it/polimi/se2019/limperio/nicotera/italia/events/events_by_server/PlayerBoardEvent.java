package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.PlayerBoard;
import it.polimi.se2019.limperio.nicotera.italia.model.PowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.model.WeaponCard;

import java.util.ArrayList;

/**
 * Event for notify a player about creation or update of his player board.
 *
 * @author Pietro L'Imperio
 */
public class PlayerBoardEvent extends ServerEvent {
    private ArrayList<AliasCard> weaponCardsOwned = new ArrayList<>();
    private ArrayList<AliasCard> powerUpCardsOwned = new ArrayList<>();
    private boolean canShoot = false;
    private boolean canUseNewton = false;
    private boolean canUseTeleporter = false;
    private boolean canUseTagbackGranade = false;
    private boolean hasToDiscardCard = false;

    /**
     * The player board updated in {@link PlayerBoardEvent}
     */
    private PlayerBoard playerBoard = null;

    public PlayerBoardEvent() {
        setPlayerBoardEvent(true);
    }

    private void setWeaponCardsOwned(ArrayList<WeaponCard> weaponsOwned) {
        for(WeaponCard card : weaponsOwned){
            this.weaponCardsOwned.add(new AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
    }

    public void setPowerUpCardsOwned(ArrayList<PowerUpCard> powerUpOwned) {
        for(PowerUpCard card : powerUpOwned){
            this.powerUpCardsOwned.add(new AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
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

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
        setWeaponCardsOwned(playerBoard.getWeaponsOwned());
        setPowerUpCardsOwned(playerBoard.getPowerUpCardsOwned());

    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
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

    public boolean isHasToDiscardCard() {
        return hasToDiscardCard;
    }

    public void setHasToDiscardCard(boolean hasToDiscardCard) {
        this.hasToDiscardCard = hasToDiscardCard;
    }
}
