package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import java.io.Serializable;

public class RequestNicknameEvent implements Serializable {
   private String message;
   private String nickname;
   private boolean notFirstAttempt;
    private boolean solved;

    public RequestNicknameEvent(String message, boolean notFirstAttempt, boolean solved) {
        this.message = message;
        this.nickname = null;
        this.notFirstAttempt = notFirstAttempt;
        this.solved = solved;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isNotFirstAttempt() {
        return notFirstAttempt;
    }

    public boolean isSolved() {
        return solved;
    }
}
