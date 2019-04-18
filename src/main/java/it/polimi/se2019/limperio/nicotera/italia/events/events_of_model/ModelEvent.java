package it.polimi.se2019.limperio.nicotera.italia.events.events_of_model;

import it.polimi.se2019.limperio.nicotera.italia.model.Board;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.model.Player;

import java.io.Serializable;

public class ModelEvent implements Serializable {
    String message;
    String nickname;
    Game game;

    public ModelEvent(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public Game getGame() {
        return game;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
