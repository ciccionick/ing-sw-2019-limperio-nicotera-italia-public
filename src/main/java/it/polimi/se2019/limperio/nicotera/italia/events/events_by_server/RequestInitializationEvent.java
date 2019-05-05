package it.polimi.se2019.limperio.nicotera.italia.events.events_by_server;

import java.io.Serializable;

public class RequestInitializationEvent implements Serializable {
    private String message;
    private boolean isNicknameRequest = false;
    private boolean isColorRequest = false;
    private boolean isFrenzyRequest = false;
    private boolean isTerminatorModeRequest = false;
    private boolean isMapRequest = false;
    private boolean isRetake = false;
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