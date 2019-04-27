package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.Serializable;


public class ViewEvent implements Serializable {

   private String message;
   private String nickname;
   private boolean isDrawTwoPowerUpCards = false;

    public ViewEvent(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isDrawTwoPowerUpCards() {
        return isDrawTwoPowerUpCards;
    }

    public void setDrawTwoPowerUpCards(boolean drawTwoPowerUpCards) {
        isDrawTwoPowerUpCards = drawTwoPowerUpCards;
    }
}
