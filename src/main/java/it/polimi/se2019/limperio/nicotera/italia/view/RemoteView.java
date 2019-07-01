package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;
import java.util.ArrayList;

/**
 * This class represents the view on client side. It contains all of the information useful for
 * the creation of the GUI.
 * @author Pietro L'Imperio
 */


public class RemoteView extends Observable<ClientEvent>  {
    /**
     * Matches the instance of remote view with the client owner of it.
     */
    private Client client;
    /**
     * The reference to network handler
     */
    private NetworkHandler networkHandler;
    /**
     * The reference to player board view of the client owner of the view
     */
    private PlayerBoardView myPlayerBoardView;
    /**
     * The reference to map view
     */
    private MapView mapView;

    /**
     * The list of the player board views for each player
     */
    private ArrayList<PlayerBoardView> listOfPlayerBoardViews = new ArrayList<>();

    /**
     * The reference to killshot track view
     */
    private KillshotTrackView killshotTrackView;
    /**
     * The reference to initialization view
     */
    private InitializationView initializationView;
    /**
     * It states if the game has terminator mode
     */
    private boolean terminatorMode = false;

    /**
     * The reference to the main frame
     */
    private MainFrame mainFrame;

    /**
     * It states if the current turn is the turn of the client owner of this view
     */
    private boolean isMyTurn;

    /**
     * It creates and instance of all the parts of the view and it matches them to the specific client that is passed by parameter.
     * @param client the specific client to which to connect the other parts of view.
     * @param networkHandler the network handler connected with this specific remote view
     */
    public RemoteView(Client client, NetworkHandler networkHandler) {
        this.client = client;
        this.networkHandler = networkHandler;
        register(networkHandler);
        myPlayerBoardView = new PlayerBoardView();
        mapView = new MapView(this);
        killshotTrackView = new KillshotTrackView();
        initializationView = new InitializationView(this);
    }


    /**
     * It handles a player board event updating  (or creating) a reference in the list of player board view,
     * avoiding to store power up cards of other players in order to respect the rules of the game.
     * @param event The event handled in this method.
     */
    public void handlePlayerBoardEvent(PlayerBoardEvent event){
        if(listOfPlayerBoardViews.isEmpty() || !(playerBoardViewAlreadyExists(event.getPlayerBoard().getNicknameOfPlayer()))){
            listOfPlayerBoardViews.add(new PlayerBoardView());
            if(event.getPlayerBoard().getNicknameOfPlayer().equals(client.getNickname()))
                myPlayerBoardView=listOfPlayerBoardViews.get(listOfPlayerBoardViews.size()-1);
            else {
                removePowerUpCardsDeckFromEvent(event);
            }
            listOfPlayerBoardViews.get(listOfPlayerBoardViews.size()-1).update(event);
        }
        else{
            if(!(event.getNicknameInvolved().equals(client.getNickname())))
                removePowerUpCardsDeckFromEvent(event);
            getPlayerBoardViewOfThisPlayer(event.getPlayerBoard().getNicknameOfPlayer()).update(event);
        }
        if(mainFrame!=null)
            mainFrame.updateLeftPanelForWhoIsViewing(event.getPlayerBoard().getNicknameOfPlayer());
        if(mainFrame!=null && event.getNicknameInvolved().equals(myPlayerBoardView.getNicknameOfPlayer()) && event.getMessageForInvolved()!=null && !event.isNotifyAboutActionDone() && !event.isRequestToDiscardPowerUpCardToSpawnEvent())
            mainFrame.showMessage(event);
    }

    /**
     * Check if a player board view of some players already exists in the list of them.
     * @param nickname The nickname of the player board involved to the control of its existence
     * @return true if the player board view already exists, no otherwise.
     */
    private boolean playerBoardViewAlreadyExists(String nickname){
        for (PlayerBoardView playerBoardView: listOfPlayerBoardViews){
            if(playerBoardView.getNicknameOfPlayer().equals(nickname))
                return true;
        }
        return false;
    }

    /**
     * Remove power up cards from the deck in the player board view if this one is not the one of the client owner of this remote view.
     * @param event The event that contains the information about the player board from it has to remove power up cards.
     */
    private void removePowerUpCardsDeckFromEvent(PlayerBoardEvent event){
        while(!(event.getPowerUpCardsOwned().isEmpty())){
            event.getPowerUpCardsOwned().remove(0);
        }
    }

    /**
     * Get the player board view of the player with the nickname passed by parameter.
     * @param nickname The nickname of the player owner of the player board returned
     * @return The player board view of the player with the nickname passed by parameter.
     */
    public PlayerBoardView getPlayerBoardViewOfThisPlayer(String nickname){
        if(nickname.equals("ME"))
            return myPlayerBoardView;
        for (PlayerBoardView playerBoardView: listOfPlayerBoardViews){
            if(playerBoardView.getNicknameOfPlayer().equals(nickname))
                return playerBoardView;
        }
        throw new IllegalArgumentException();
    }

    public boolean isTerminatorMode() {
        return terminatorMode;
    }

     void setTerminatorMode(boolean terminatorMode) {
        this.terminatorMode = terminatorMode;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public PlayerBoardView getMyPlayerBoardView()
    {
        return myPlayerBoardView;
    }

    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public MapView getMapView() {
        return mapView;
    }

    public KillshotTrackView getKillshotTrackView() {
        return killshotTrackView;
    }

    public InitializationView getInitializationView() {
        return initializationView;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ArrayList<PlayerBoardView> getListOfPlayerBoardViews() {
        return listOfPlayerBoardViews;
    }

}
