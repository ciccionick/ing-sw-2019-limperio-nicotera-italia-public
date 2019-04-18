package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import java.io.Serializable;

public class RequestNicknameEvent implements Serializable {
   private String message;
   private String nickname;
   private String color;
   private boolean wannaFrenzy;
   private boolean messageOfRequest;
   private boolean ack;
   private boolean valid;
   private boolean first;


    public RequestNicknameEvent(String message, String nickname, boolean messageOfRequest, boolean ack, boolean valid, boolean first) {
        this.message = message;
        this.nickname = nickname;
        this.messageOfRequest = messageOfRequest;
        this.ack = ack;
        this.valid = valid;
        this.first=first;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public String getColor() {
        return color;
    }

    public boolean isWannaFrenzy() {
        return wannaFrenzy;
    }

    public boolean isMessageOfRequest() {
        return messageOfRequest;
    }

    public boolean isAck() {
        return ack;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isFirst() {
        return first;
    }
}