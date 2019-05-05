package it.polimi.se2019.limperio.nicotera.italia.events.events_by_client;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.network.server.VirtualView;

import java.io.Serializable;


public class ClientEvent implements Serializable {

   private String message;
   private String nickname;
   private boolean isDrawTwoPowerUpCards = false;
   private boolean isDiscardPowerUpCardToSpawn = false;
   private ServerEvent.AliasCard powerUpCard = null;
   private boolean isRequestToRunByPlayer = false;
   private boolean isRequestToCatchByPlayer = false;
   private boolean isRequestToShootByPlayer = false;
   private VirtualView myVirtualView = null;

    public ClientEvent(String message, String nickname) {
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

    public ServerEvent.AliasCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(ServerEvent.AliasCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }
}
