package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import it.polimi.se2019.limperio.nicotera.italia.model.*;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Event generated by some components located in server side.
 * <p>
 *     Represents a kind of general event and is usually adapted to the different
 *     situations according to the values of the different boolean attributes.
 * </p>
 * <p>
 *     The way to understand, by the client, which kind of Event was sent by Server is evaluated
 *     the state of the relative boolean attribute.
 *     Example: if an event is the type of MapEvent, all the boolean attributes will be false apart
 *     isMapEvent.
 *
 * </p>
 * @author Pietro L'Imperio
 */
public class ServerEvent implements Serializable {
    static final long serialVersionUID = 420000013;
    /**
     * The message stored in the event.
     */
    private String messageForInvolved;

    private String messageForOthers;

    /**
     * The list of nickname interested to receive the event.
     */
    private ArrayList<String> nicknames = new ArrayList<>();

    private String nicknameInvolved;

    /**
     * The list of powerUp cards in alias-mode to send to clients in some kind of events like RequestDiscardPowerUpCardEvent
     */
    private ArrayList<AliasCard> powerUpCards = null;

    private boolean isPlayerBoardEvent = false;
    private boolean isMapEvent = false;
    private boolean isKillshotTrackEvent = false;
    private boolean isRequestForDrawTwoPowerUpCardsEvent = false;
    private boolean isRequestToDiscardPowerUpCardToSpawnEvent = false;
    private boolean isRequestActionEvent = false;
    private boolean isRequestForChooseAWeaponToCatch = false;
    private boolean isFinished=false;
    private boolean isCatchActionDone = false;
    private boolean isRequestSelectionSquareForAction = false;
    private boolean isGenerationEvent = false;
    private boolean isNotifyAboutActionDone = false;
    private boolean isRequestToDiscardWeaponCard = false;
    private boolean isTimerOverEvent = false;
    private boolean isRequestToChooseTerminatorAction = false;
    private boolean isRequestToSelectionPlayerToAttackWithTerminator =false;
    private boolean isUpdateScoreEvent = false;
    private boolean isRequestForDrawOnePowerUpCardEvent = false;

    private int numOfAction;
    private int numOfMaxAction;




    public ServerEvent(){

    }

    public ServerEvent(String message) {
        this.messageForInvolved = message;
    }

    public boolean isRequestToSelectionPlayerToAttackWithTerminator() {
        return isRequestToSelectionPlayerToAttackWithTerminator;
    }

    public void setRequestToSelectionPlayerToAttackWithTerminator(boolean requestToSelectionPlayerToAttackWithTerminator) {
        isRequestToSelectionPlayerToAttackWithTerminator = requestToSelectionPlayerToAttackWithTerminator;
    }

    public String getMessageForInvolved() {
        return messageForInvolved;
    }

    public void setMessageForInvolved(String messageForInvolved) {
        this.messageForInvolved = messageForInvolved;
    }

    public String getMessageForOthers() {
        return messageForOthers;
    }

    public void setMessageForOthers(String messageForOthers) {
        this.messageForOthers = messageForOthers;
    }

    public void setNicknames(ArrayList<String> nicknames) {
        this.nicknames = nicknames;
    }

    public ArrayList<String> getNicknames() {
        return nicknames;
    }

    public String getNicknameInvolved() {
        return nicknameInvolved;
    }


    public void setNicknameInvolved(String nicknameInvolved) {
        this.nicknameInvolved = nicknameInvolved;
    }

    public int getNumOfMaxAction() {
        return numOfMaxAction;
    }

    public void setNumOfMaxAction(int numOfMaxAction) {
        this.numOfMaxAction = numOfMaxAction;
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

    public boolean isRequestForDrawOnePowerUpCardEvent() {
        return isRequestForDrawOnePowerUpCardEvent;
    }

    public void setRequestForDrawOnePowerUpCardEvent(boolean requestForDrawOnePowerUpCardEvent) {
        isRequestForDrawOnePowerUpCardEvent = requestForDrawOnePowerUpCardEvent;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public boolean isRequestActionEvent() {
        return isRequestActionEvent;
    }

    public void setRequestActionEvent(boolean requestActionEvent) {
        isRequestActionEvent = requestActionEvent;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isKillshotTrackEvent() {
        return isKillshotTrackEvent;
    }

    public void setKillshotTrackEvent(boolean killshotTrackEvent) {
        isKillshotTrackEvent = killshotTrackEvent;
    }

    public boolean isTimerOverEvent() {
        return isTimerOverEvent;
    }

    public boolean isUpdateScoreEvent() {
        return isUpdateScoreEvent;
    }

    public void setUpdateScoreEvent(boolean updateScoreEvent) {
        isUpdateScoreEvent = updateScoreEvent;
    }

    public void setTimerOverEvent(boolean timerOverEvent) {
        isTimerOverEvent = timerOverEvent;
    }

    public boolean isRequestForDrawTwoPowerUpCardsEvent() {
        return isRequestForDrawTwoPowerUpCardsEvent;
    }

    public void setRequestForDrawTwoPowerUpCardsEvent(boolean drawTwoPowerUpCard) {
        isRequestForDrawTwoPowerUpCardsEvent = drawTwoPowerUpCard;
    }

    public boolean isRequestToDiscardPowerUpCardToSpawnEvent() {
        return isRequestToDiscardPowerUpCardToSpawnEvent;
    }

    public void setRequestToDiscardPowerUpCardToSpawnEvent(boolean requestToDiscardPowerUpCardToSpawnEvent) {
        isRequestToDiscardPowerUpCardToSpawnEvent = requestToDiscardPowerUpCardToSpawnEvent;
    }

    public boolean isRequestForChooseAWeaponToCatch() {
        return isRequestForChooseAWeaponToCatch;
    }

    public void setRequestForChooseAWeaponToCatch(boolean requestForChooseAWeaponToCatch) {
        isRequestForChooseAWeaponToCatch = requestForChooseAWeaponToCatch;
    }

    public boolean isCatchActionDone() {
        return isCatchActionDone;
    }

    public void setCatchActionDone(boolean catchActionDone) {
        isCatchActionDone = catchActionDone;
    }

    public int getNumOfAction() {
        return numOfAction;
    }

    public void setNumOfAction(int numOfAction) {
        this.numOfAction = numOfAction;
    }

    public boolean isRequestSelectionSquareForAction() {
        return isRequestSelectionSquareForAction;
    }

    public void setRequestSelectionSquareForAction(boolean requestSelectionSquareForAction) {
        isRequestSelectionSquareForAction = requestSelectionSquareForAction;
    }

    public boolean isNotifyAboutActionDone() {
        return isNotifyAboutActionDone;
    }

    public void setNotifyAboutActionDone(boolean notifyAboutActionDone) {
        isNotifyAboutActionDone = notifyAboutActionDone;
    }



    public boolean isGenerationEvent() {
        return isGenerationEvent;
    }

    public boolean isRequestToDiscardWeaponCard() {
        return isRequestToDiscardWeaponCard;
    }

    public void setRequestToDiscardWeaponCard(boolean requestToDiscardWeaponCard) {
        isRequestToDiscardWeaponCard = requestToDiscardWeaponCard;
    }

    public void setGenerationEvent(boolean generationEvent) {
        isGenerationEvent = generationEvent;
    }

    public boolean isRequestToChooseTerminatorAction() {
        return isRequestToChooseTerminatorAction;
    }

    public void setRequestToChooseTerminatorAction(boolean requestToChooseTerminatorAction) {
        isRequestToChooseTerminatorAction = requestToChooseTerminatorAction;
    }

    /**
     * Represents a simplified structure to encapsulate the main attributes of cards
     * (Weapon and PowerUp) to send them to clients removing from them the possibility to use
     * directly their effects.
     *
     */

    public static class AliasCard implements Serializable{
        static final long serialVersionUID = 420000000;
        /**
         * The name of card
         */
        String name;
        /**
         * The description of card
         */
        String description;
        /**
         * The color of card
         */
        ColorOfCard_Ammo color;

        boolean isLoaded = true;

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

        public boolean isLoaded() {
            return isLoaded;
        }

        public void setLoaded(boolean loaded) {
            isLoaded = loaded;
        }
    }


}
