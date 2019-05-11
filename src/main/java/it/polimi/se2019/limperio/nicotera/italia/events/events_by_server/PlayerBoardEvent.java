package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;


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
    private ArrayList<AliasCard> weaponsCardOwned = new ArrayList<>();
    private ArrayList<AliasCard> powerUpCardsOwned = new ArrayList<>();

    /**
     * The player board updated in {@link PlayerBoardEvent}
     */
    private PlayerBoard playerBoard = null;

    public PlayerBoardEvent(String message) {
        super(message);
        setPlayerBoardEvent(true);
    }

    private void setWeaponsCardOwned(PlayerBoard playerBoard) {
        ArrayList<AliasCard> weaponsOwned = new ArrayList<>();
        for(WeaponCard card : playerBoard.getWeaponsOwned()){
            weaponsOwned.add(new AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
        playerBoard.setWeaponsOwnedAsAlias(weaponsOwned);
    }

    private void setPowerUpCardsOwned(PlayerBoard playerBoard) {
        ArrayList<AliasCard> powerUpOwned = new ArrayList<>();
        for(PowerUpCard card : playerBoard.getPowerUpCardsOwned()){
            powerUpOwned.add(new AliasCard(card.getName(), card.getDescription(), card.getColor()));
        }
        playerBoard.setPowerUpCardsOwnedAsAlias(powerUpOwned);
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public ArrayList<AliasCard> getWeaponsCardOwned() {
        return weaponsCardOwned;
    }

    public ArrayList<AliasCard> getPowerUpCardsOwned() {
        return powerUpCardsOwned;
    }

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
        setWeaponsCardOwned(playerBoard);
        setPowerUpCardsOwned(playerBoard);
    }

}
