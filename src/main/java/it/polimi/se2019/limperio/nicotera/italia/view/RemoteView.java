package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;
import java.util.ArrayList;

/**
 * This class represents the view on client side of client-server architecture.
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
     * the reference to player board view
     */
    private PlayerBoardView myPlayerBoardView;
    /**
     * the reference to map view
     */
    private MapView mapView;
    /**
     * the reference to killshot track view
     */
    private ArrayList<PlayerBoardView> listOfPlayerBoardViews = new ArrayList<>();

    private KillshotTrackView killshotTrackView;
    /**
     * the reference to initialization view
     */
    private InitializationView initializationView;
    /**
     * It permits to read from command line
     */
    private boolean terminatorMode= false;

    private MainFrame mainFrame;

    private boolean isMyTurn;

    /**
     * The constructor creates and instance of all the parts of the view and it matches them to the specific client that is passed by parameter.
     * @param client the specific client to which to connect the other parts of view.
     * @param networkHandler the network handler to which to connect this specific remote view
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
        if(mainFrame!=null && event.getNicknameInvolved().equals(myPlayerBoardView.getNicknameOfPlayer()) && event.getMessageForInvolved()!=null && !event.isNotifyAboutActionDone())
            mainFrame.showMessage(event);
    }

    private boolean playerBoardViewAlreadyExists(String nickname){
        for (PlayerBoardView playerBoardView: listOfPlayerBoardViews){
            if(playerBoardView.getNicknameOfPlayer().equals(nickname))
                return true;
        }
        return false;
    }

    private void removePowerUpCardsDeckFromEvent(PlayerBoardEvent event){
        while(!(event.getPowerUpCardsOwned().isEmpty())){
            event.getPowerUpCardsOwned().remove(0);
        }
    }

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
}
