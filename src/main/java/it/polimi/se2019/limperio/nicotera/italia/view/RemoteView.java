package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;


/**
 * This class represents the view on client side of client-server architecture.
 * @author Pietro L'Imperio
 */
import java.util.ArrayList;
import java.util.Scanner;

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
    private PlayerBoardView playerBoardView;
    /**
     * the reference to map view
     */
    private MapView mapView;
    /**
     * the reference to killshot track view
     */
    private KillshotTrackView killshotTrackView;
    /**
     * the reference to initialization view
     */
    private InitializationView initializationView;
    /**
     * It permits to read from command line
     */
    private Scanner stdin = new Scanner(System.in);

    /**
     * The constructor creates and instance of all the parts of the view and it matches them to the specific client that is passed by parameter.
     * @param client the specific client to which to connect the other parts of view.
     * @param networkHandler the network handler to which to connect this specific remote view
     */
    public RemoteView(Client client, NetworkHandler networkHandler) {
        this.client = client;
        this.networkHandler = networkHandler;
        register(networkHandler);
        playerBoardView = new PlayerBoardView();
        mapView = new MapView(this);
        killshotTrackView = new KillshotTrackView();
        initializationView = new InitializationView(this);
    }

    public PlayerBoardView getPlayerBoardView()
    {
        return playerBoardView;
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
     * @param message allows to distinguish what to ask for to the player.
     */
    @Override
    public void update(ServerEvent message) {
        System.out.println(message.getMessage() + " " + message.getNicknames());
        if (message.isRequestForDrawTwoPowerUpCardsEvent()) {
            System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
            String action = stdin.nextLine();
            while (!(action.equalsIgnoreCase("pesca"))) {
                System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
                action = stdin.nextLine();
            }
            notify(new DrawTwoPowerUpCards("Voglio pescare due carte", client.getNickname()));
        }

        if (message.isRequestToDiscardPowerUpCardToSpawnEvent()) {
            System.out.println("Hai pescato: " + playerBoardView.getPowerUpCardsDeck().get(0).getName() + " " + playerBoardView.getPowerUpCardsDeck().get(0).getColor() + " e " + playerBoardView.getPowerUpCardsDeck().get(1).getName() + " " + playerBoardView.getPowerUpCardsDeck().get(1).getColor());
            System.out.println(" Digita 1 se vuoi scartare la prima o 2 se vuoi scartare la seconda");
            int choose;
            choose = stdin.nextInt();
            while (choose != 1 && choose != 2) {
                System.out.println("Digita 1 se vuoi scartare la prima o 2 se vuoi scartare la seconda");
                choose = stdin.nextInt();
            }
            if (choose == 1) {
                discardPowerUpCard(0);
            } else
                discardPowerUpCard(1);
        }

        if(message.isFirstActionOfTurnEvent()){
            System.out.println("Digita 'corri' se vuoi correre, 'raccogli' se vuoi raccogliere o 'spara' se vuoi attaccare");
            String action;
            action = stdin.nextLine();
            while((!action.equalsIgnoreCase("corri"))&&(!action.equalsIgnoreCase("raccogli"))&&(!action.equalsIgnoreCase("spara"))){
                System.out.println("Digita 'corri' se vuoi correre, 'raccogli' se vuoi raccogliere o 'spara' se vuoi attaccare");
                action=stdin.nextLine();
            }
            if(action.equalsIgnoreCase("corri")){
                RequestToRunByPlayer newEvent = new RequestToRunByPlayer("Ho scelto corri", client.getNickname());
                notify(newEvent);
            }
            if(action.equalsIgnoreCase("raccogli")){
                RequestToCatchByPlayer newEvent = new RequestToCatchByPlayer("Ho scelto raccogli", client.getNickname());
                notify(newEvent);
            }
            if(action.equalsIgnoreCase("spara")){
                RequestToShootByPlayer newEvent = new RequestToShootByPlayer("Ho scelto spara", client.getNickname());
                notify(newEvent);
            }

        }

        if(message.isSelectionSquareForSquareWhereCatch()){
            SelectionViewForSquareWhereCatch event = (SelectionViewForSquareWhereCatch) message;
            System.out.println("Puoi pescare nei seguenti quadrati: ");
            for(Square square : event.getSquaresReachableForCatch() ){
                System.out.println("[" + square.getRow()+ "] [" + square.getColumn() + "]" );
                if(square.isSpawn()){
                        printListOfWeapons(((SpawnSquare)mapView.getMap()[square.getRow()][square.getColumn()]).getWeaponsCardsForRemoteView(), ((SelectionViewForSquareWhereCatch) message).getWeaponNotAvailableForLackOfAmmo());
                }
                else {
                    System.out.println( ((NormalSquare)square).getAmmoTile().toString());
                }
            }
        }

    }

    /**
     * It is called when a player wants to discard a power up card in order to be spawn
     * @param i It permit to distinguish which card has to be removed from player's deck.
     */
    private void discardPowerUpCard(int i) {
        ServerEvent.AliasCard powerUpCardToDiscard = playerBoardView.getPowerUpCardsDeck().remove(i);
        DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("Ho deciso di scartare questa carta", client.getNickname());
        newEvent.setPowerUpCard(powerUpCardToDiscard);
        notify(newEvent);
    }

    /**
     * Prints on command line the cards that are passed as parameter and that are available.
     * @param cards the cards the method has to print
     * @param cardsNotAvailable they aren't print because they are not available
     */
    private void printListOfWeapons(ArrayList<ServerEvent.AliasCard> cards, ArrayList<ServerEvent.AliasCard> cardsNotAvailable){
        for(ServerEvent.AliasCard card : cards){
            if(!isCardNotAffordable(card, cardsNotAvailable))
                System.out.println(card.getName() + " " + card.getColor());
        }
    }

    /**
     * It looks for the card in the list of not available cards and, if it find them it will return true
     * @param card it are looked for in the list
     * @param cardsNotAvaialable the list of the cards not available
     * @return true if the card is in the list of not available cards
     */
    private boolean isCardNotAffordable(ServerEvent.AliasCard card, ArrayList<ServerEvent.AliasCard> cardsNotAvaialable) {
        for (ServerEvent.AliasCard cardNotAvailable : cardsNotAvaialable){
            if(card.getName().equals(cardNotAvailable.getName()) && card.getColor().equals(cardNotAvailable.getColor()))
                return true;
        }
        return false;
    }

    public InitializationView getInitializationView() {
        return initializationView;
    }
}
