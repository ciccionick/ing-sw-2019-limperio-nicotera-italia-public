package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.io.Serializable;

/**
 * Event to ask to player information for his initialization like nickname, color and, if he is the first
 * player if he wants to play with frenzy mode at the end of normal game, if he wants to play in
 * terminator mode in the case the game stars with only three players, and which type of map he wants
 * to play with.
 *<p>
 *     For each information the event will reply with another one by the client, so to understand which
 *     kind of request is the event addressed boolean attributes play a crucial role.
 *</p>
 * <p>
 *     All the boolean attributes are set false for default.
 * </p>
 * @author Pietro L'Imperio
 */
public class RequestInitializationEvent implements Serializable {
    static final long serialVersionUID = 420000016;
    /**
     * The message stored in the event about its nature.
     */
    private String message;
    private boolean isNicknameRequest;
    private boolean isColorRequest;
    private boolean isFrenzyRequest;
    private boolean isTerminatorModeRequest;
    private boolean isMapRequest;
    /**
     * It's true if the request is about something already asked but not accepted for the previous choose of someone else.
     */
    private boolean isRetake = false;
    /**
     * It's true at the end of the process of initialization to move the client in a phase where he remain waiting for other events.
     */
    private boolean isAck = false;

    public RequestInitializationEvent(String message, boolean isNicknameRequest, boolean isColorRequest, boolean isFrenzyRequest, boolean isTerminatorModeRequest, boolean isMapRequest) {
        this.message = message;
        this.isNicknameRequest = isNicknameRequest;
        this.isColorRequest = isColorRequest;
        this.isFrenzyRequest = isFrenzyRequest;
        this.isTerminatorModeRequest = isTerminatorModeRequest;
        this.isMapRequest = isMapRequest;
    }

    public String getMessage() {
        return message;
    }

    public boolean isNicknameRequest() {
        return isNicknameRequest;
    }

    public boolean isColorRequest() {
        return isColorRequest;
    }

    public boolean isFrenzyRequest() {
        return isFrenzyRequest;
    }

    public boolean isTerminatorModeRequest() {
        return isTerminatorModeRequest;
    }

    public boolean isMapRequest() {
        return isMapRequest;
    }

    public boolean isRetake() {
        return isRetake;
    }

    public void setRetake(boolean retake) {
        isRetake = retake;
    }

    public boolean isAck() {
        return isAck;
    }

    public void setAck(boolean ack) {
        isAck = ack;
    }
}