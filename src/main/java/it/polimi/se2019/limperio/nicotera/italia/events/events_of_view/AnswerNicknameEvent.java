package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import java.io.Serializable;

public class AnswerNicknameEvent implements Serializable {
    private String nickname;
    private String color;
    private  int map;
    private boolean frenzy;
    private boolean terminator;

    public AnswerNicknameEvent(String nickname, String color, int map, boolean frenzy, boolean terminator) {
        this.nickname = nickname;
        this.color = color;
        this.map = map;
        this.frenzy = frenzy;
        this.terminator= terminator;
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

    public boolean isTerminator() {
        return terminator;
    }
}
