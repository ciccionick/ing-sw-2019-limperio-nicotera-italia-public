package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the view on client side of client-server architecture.
 * @author Pietro L'Imperio
 */


public class RemoteView extends Observable<ClientEvent> implements Observer<ServerEvent> {
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
    private Scanner stdin = new Scanner(System.in);

    private MainFrame mainFrame;

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

    public void setMyPlayerBoardView(PlayerBoardView myPlayerBoardView) {
        this.myPlayerBoardView = myPlayerBoardView;
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

    /**
     * Handles all the interactions with the player and through the parameter it asks or notifies something to the client that is playing.
     * @param receivedEvent allows to distinguish what to ask for to the player.
     */
    @Override
    public void update(ServerEvent receivedEvent) {

        if (receivedEvent.isRequestForDrawTwoPowerUpCardsEvent()) {
            mainFrame.showMessage(receivedEvent);
        }

        if (receivedEvent.isRequestToDiscardPowerUpCardToSpawnEvent()) { //quando ricevi la richiesta di scartare una power up. Devi aggiornare la pb dato che hai appena pescato almeno una carta
            mainFrame.handleRequestToDiscardPowerUpCard(receivedEvent);
        }

        if(receivedEvent.isGenerationEvent()){
            mainFrame.showMessage(receivedEvent);
        }

        if(receivedEvent.isRequestActionEvent()){
            myPlayerBoardView.updateThingsPlayerCanDo((RequestActionEvent)receivedEvent);
            mainFrame.updateLeftPanelForWhoIsViewing(getMyPlayerBoardView().getNicknameOfPlayer());
            mainFrame.updatePanelOfAction();
            mainFrame.showMessage(receivedEvent);
        }

        if(receivedEvent.isRequestSelectionSquareForRun()){
            mapView.setHasToChooseASquare(true);
            mainFrame.updateEnableSquares(((RequestSelectionSquareForRun)receivedEvent).getSquaresReachableWithRunAction());
            mainFrame.showMessage(receivedEvent);
        }

        if(receivedEvent.isNotifyAboutActionDone())
        {
            mainFrame.showMessage(receivedEvent);
        }

        if(receivedEvent.isSelectionSquareForCatching()){

        }

        if(receivedEvent.isRequestForChooseAWeaponToCatch())
        {
            RequestForChooseAWeaponToCatch event = (RequestForChooseAWeaponToCatch) receivedEvent;
            System.out.println("Puoi pescare le seguenti armi:");
            for(ServerEvent.AliasCard card : event.getWeaponsAvailableToCatch()){
                System.out.println(card.getName() + "\t");
            }
            System.out.println("Digita 1 se vuoi pescare la prima arma, 2 per la seconda, 3 per la terza");
            int choose = stdin.nextInt();
            SelectionWeaponToCatch newEvent = new SelectionWeaponToCatch("Scelto arma", client.getNickname());
            if(choose == 1 ){
                newEvent.setNameOfWeaponCard(event.getWeaponsAvailableToCatch().get(0).getName());
                newEvent.setRow(event.getRow());
                newEvent.setColumn(event.getColumn());
            }
            else if(choose == 2){
                newEvent.setNameOfWeaponCard(event.getWeaponsAvailableToCatch().get(1).getName());
                newEvent.setRow(event.getRow());
                newEvent.setColumn(event.getColumn());
            }
            else if (choose == 3){
                newEvent.setNameOfWeaponCard(event.getWeaponsAvailableToCatch().get(2).getName());
                newEvent.setRow(event.getRow());
                newEvent.setColumn(event.getColumn());
            }
            notify(newEvent);
        }


        if(receivedEvent.isCatchActionDone()){
            if(((CatchActionDoneEvent) receivedEvent).isCatchActionOfAmmoTile()){
                System.out.println("Hai pescato un ammo tile con le seguenti munizioni: ");
                for(ColorOfCard_Ammo ammo : ((CatchActionDoneEvent) receivedEvent).getAmmo()){
                    System.out.println("/t" + ammo.toString());
                }
            }
            else{
                System.out.println("Hai pescato una weapon card: " + ((CatchActionDoneEvent)receivedEvent).getNameOfWeaponCaught());
            }
         }

        if(receivedEvent.isRequestSelectionSquareForRun()){
            RequestSelectionSquareForRun event = (RequestSelectionSquareForRun) receivedEvent;
            System.out.println("Ecco i quadrati raggiungibili: ");
            for(Square square : event.getSquaresReachableWithRunAction()){
                System.out.println("["+ square.getRow()+"]" + " ["+square.getColumn()+"]");
            }
            System.out.println("Scrivi le coordinate del quadrato che vuoi raggiungere:");
            int row = stdin.nextInt();
            int column = stdin.nextInt();
            SelectionSquareForRun newEvent = new SelectionSquareForRun("selezionato quadrato", client.getNickname(), row, column);
            notify(newEvent);
        }

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
        if(listOfPlayerBoardViews.isEmpty() || !(playerBoardViewAlreadyExists(event.getNicknameInvolved()))){
            listOfPlayerBoardViews.add(new PlayerBoardView());
            if(event.getNicknameInvolved().equals(client.getNickname()))
                myPlayerBoardView=listOfPlayerBoardViews.get(listOfPlayerBoardViews.size()-1);
            else {
                removePowerUpCardsDeckFromEvent(event);
            }
            listOfPlayerBoardViews.get(listOfPlayerBoardViews.size()-1).update(event);
        }
        else{
            if(!(event.getNicknameInvolved().equals(client.getNickname())))
                removePowerUpCardsDeckFromEvent(event);
            getPlayerBoardViewOfThisPlayer(event.getNicknameInvolved()).update(event);
        }
        if(mainFrame!=null)
            mainFrame.updateLeftPanelForWhoIsViewing(event.getPlayerBoard().getNicknameOfPlayer());
    }

    private boolean playerBoardViewAlreadyExists(String nickname){
        for (PlayerBoardView playerBoardView: listOfPlayerBoardViews){
            if(playerBoardView.getNicknameOfPlayer().equals(nickname))
                return true;
        }
        return false;
    }

    private void removePowerUpCardsDeckFromEvent(PlayerBoardEvent event){
        for (int i = 0; i < event.getPowerUpCardsOwned().size(); i++) {
            event.getPowerUpCardsOwned().remove(i);
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
}
