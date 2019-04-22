package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ModelEvent implements Serializable {
    private String message;
    private ArrayList<String> nicknames = new ArrayList<>();
    private PlayerBoard playerBoard = null;
    private Square[][] map = null;
    private KillShotTrack killShotTrack = null;
    private boolean isPlayerBoardEvent = false;
    private boolean isMapEvent = false;
    private boolean isKillshotTrackEvent = false;


    public ModelEvent(String message) {
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
}
