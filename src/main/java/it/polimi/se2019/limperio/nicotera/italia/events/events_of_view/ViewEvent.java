package it.polimi.se2019.limperio.nicotera.italia.events.events_of_view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import java.io.Serializable;


public class ViewEvent implements Serializable {

   private String message;
   private String nickname;
   private boolean isDrawTwoPowerUpCards = false;
   private boolean isDiscardPowerUpCardToSpawn = false;
   private ModelEvent.AliasCard powerUpCard = null;
   private boolean isRequestToRunByPlayer = false;
   private boolean isRequestToCatchByPlayer = false;
   private boolean isRequestToShootByPlayer = false;
   private VirtualView myVirtualView = null;

    public ViewEvent(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }

    public VirtualView getMyVirtualView() {
        return myVirtualView;
    }

    public void setMyVirtualView(VirtualView myVirtualView) {
        this.myVirtualView = myVirtualView;
    }

    public boolean isRequestToRunByPlayer() {
        return isRequestToRunByPlayer;
    }

    public boolean isRequestToCatchByPlayer() {
        return isRequestToCatchByPlayer;
    }

    public boolean isRequestToShootByPlayer() {
        return isRequestToShootByPlayer;
    }

    public void setRequestToShootByPlayer(boolean requestToShootByPlayer) {
        isRequestToShootByPlayer = requestToShootByPlayer;
    }

    public void setRequestToCatchByPlayer(boolean requestToCatchByPlayer) {
        isRequestToCatchByPlayer = requestToCatchByPlayer;
    }

    public void setRequestToRunByPlayer(boolean requestToRunByPlayer) {
        isRequestToRunByPlayer = requestToRunByPlayer;
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

     void setDrawTwoPowerUpCards(boolean drawTwoPowerUpCards) {
        isDrawTwoPowerUpCards = drawTwoPowerUpCards;
    }

    public boolean isDiscardPowerUpCardToSpawn() {
        return isDiscardPowerUpCardToSpawn;
    }

     void setDiscardPowerUpCardToSpawn(boolean discardPowerUpCardToSpawn) {
        isDiscardPowerUpCardToSpawn = discardPowerUpCardToSpawn;
    }

    public ModelEvent.AliasCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ModelEvent.AliasCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }
}
