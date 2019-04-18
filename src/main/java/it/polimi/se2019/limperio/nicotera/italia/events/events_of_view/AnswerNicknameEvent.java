package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import java.io.Serializable;

public class AnswerNicknameEvent implements Serializable {
    String nickname;
    String color;
    int map;
    boolean frenzy;

    public AnswerNicknameEvent(String nickname, String color, int map, boolean frenzy) {
        this.nickname = nickname;
        this.color = color;
        this.map = map;
        this.frenzy = frenzy;
    }

    public String getNickname() {
        return nickname;
    }

    public String getColor() {
        return color;
    }

    public int getMap() {
        return map;
    }

    public boolean isFrenzy() {
        return frenzy;
    }
}
