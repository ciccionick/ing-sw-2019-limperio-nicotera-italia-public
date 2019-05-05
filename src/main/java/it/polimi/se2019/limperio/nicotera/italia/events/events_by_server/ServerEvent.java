package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 */
public class ServerEvent implements Serializable {
    private String message;
    private ArrayList<String> nicknames = new ArrayList<>();
    private PlayerBoard playerBoard = null;
    private Square[][] map = null;
    private KillShotTrack killShotTrack = null;
    private ArrayList<AliasCard> powerUpCards = null;

    private boolean isPlayerBoardEvent = false;
    private boolean isMapEvent = false;
    private boolean isKillshotTrackEvent = false;
    private boolean isDrawTwoPowerUpCardEvent = false;
    private boolean isDiscardPowerUpCardToSpawnEvent = false;
    private boolean isFirstActionOfTurnEvent = false;
    private boolean isSelectionSquareForSquareWhereCatch = false;

    public boolean isSelectionSquareForSquareWhereCatch() {
        return isSelectionSquareForSquareWhereCatch;
    }

    public void setSelectionSquareForSquareWhereCatch(boolean selectionSquareForSquareWhereCatch) {
        isSelectionSquareForSquareWhereCatch = selectionSquareForSquareWhereCatch;
    }

    public boolean isFirstActionOfTurnEvent() {
        return isFirstActionOfTurnEvent;
    }

    public void setFirstActionOfTurnEvent(boolean firstActionOfTurnEvent) {
        isFirstActionOfTurnEvent = firstActionOfTurnEvent;
    }

    public ArrayList<AliasCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<AliasCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public ServerEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getNickname() {
        return nicknames;
    }

    public void setNickname(ArrayList<String> nicknames) {
        this.nicknames = nicknames;
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    public PlayerBoard getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(PlayerBoard playerBoard) {
        this.playerBoard = playerBoard;
    }

    public boolean isPlayerBoardEvent() {
        return isPlayerBoardEvent;
    }

    public void setPlayerBoardEvent(boolean playerBoardEvent) {
        isPlayerBoardEvent = playerBoardEvent;
    }

    public boolean isMapEvent() {
        return isMapEvent;
    }

    public void setMapEvent(boolean mapEvent) {
        isMapEvent = mapEvent;
    }

    public Square[][] getMap() {
        return map;
    }

    public void setMap(Square[][] map) {
        this.map = map;
    }

    public KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }

    public void setKillShotTrack(KillShotTrack killShotTrack) {
        this.killShotTrack = killShotTrack;
    }

    public boolean isKillshotTrackEvent() {
        return isKillshotTrackEvent;
    }

    public void setKillshotTrackEvent(boolean killshotTrackEvent) {
        isKillshotTrackEvent = killshotTrackEvent;
    }


    public boolean isDrawTwoPowerUpCardEvent() {
        return isDrawTwoPowerUpCardEvent;
    }

    public void setDrawTwoPowerUpCardEvent(boolean drawTwoPowerUpCard) {
        isDrawTwoPowerUpCardEvent = drawTwoPowerUpCard;
    }

    public boolean isDiscardPowerUpCardToSpawnEvent() {
        return isDiscardPowerUpCardToSpawnEvent;
    }

    public void setDiscardPowerUpCardToSpawnEvent(boolean discardPowerUpCardToSpawnEvent) {
        isDiscardPowerUpCardToSpawnEvent = discardPowerUpCardToSpawnEvent;
    }

    public static class AliasCard implements Serializable{
        String name;
        String description;
        ColorOfCard_Ammo color;

        public AliasCard(String name, String description, ColorOfCard_Ammo color) {
            this.name = name;
            this.description = description;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public ColorOfCard_Ammo getColor() {
            return color;
        }
    }


}
