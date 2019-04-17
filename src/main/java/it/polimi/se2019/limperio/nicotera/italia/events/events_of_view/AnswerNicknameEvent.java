package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import java.io.Serializable;

public class AnswerNicknameEvent implements Serializable {
    private String nickname;

    public AnswerNicknameEvent(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
