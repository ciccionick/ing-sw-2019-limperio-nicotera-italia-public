package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

import java.util.ArrayList;
import java.util.Scanner;

public class RemoteView extends Observable<ViewEvent> implements Observer<ModelEvent> {
    private Client client;
    private NetworkHandler networkHandler;
    private PlayerBoardView playerBoardView;
    private MapView mapView;
    private KillshotTrackView killshotTrackView;
    private Scanner stdin = new Scanner(System.in);

    public RemoteView(Client client, NetworkHandler networkHandler) {
        this.client = client;
        register(networkHandler);
        playerBoardView = new PlayerBoardView();
        mapView = new MapView();
        killshotTrackView = new KillshotTrackView();
    }

    public PlayerBoardView getPlayerBoardView()
    {
        return playerBoardView;
    }


    public MapView getMapView() {
        return mapView;
    }

    public KillshotTrackView getKillshotTrackView() {
        return killshotTrackView;
    }


    @Override
    public void update(ModelEvent message) {
        System.out.println(message.getMessage() + " " + message.getNickname());
        if (message.isDrawTwoPowerUpCardEvent()) {
            System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
            String action = stdin.nextLine();
            while (!(action.equalsIgnoreCase("pesca"))) {
                System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
                action = stdin.nextLine();
            }
            notify(new DrawTwoPowerUpCards("Voglio pescare due carte", client.getNickname()));
        }

        if (message.isDiscardPowerUpCardToSpawnEvent()) {
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
                        printListOfWeapons(((SpawnSquare)mapView.getMap()[square.getRow()][square.getColumn()]).getWeaponsCardsForRemoteView(), ((SelectionViewForSquareWhereCatch) message).getWeaponNotAvailableForLackOfAmmos());
                }
                else {
                    System.out.println( ((NormalSquare)square).getAmmoTile().toString());
                }
            }
        }

    }

    private void discardPowerUpCard(int i) {
        ModelEvent.AliasCard powerUpCardToDiscard = playerBoardView.getPowerUpCardsDeck().remove(i);
        DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("Ho deciso di scartare questa carta", client.getNickname());
        newEvent.setPowerUpCard(powerUpCardToDiscard);
        notify(newEvent);
    }

    private void printListOfWeapons(ArrayList<ModelEvent.AliasCard> cards, ArrayList<ModelEvent.AliasCard> cardsNotAvaialable){
        for(ModelEvent.AliasCard card : cards){
            if(!isCardNotAffordable(card, cardsNotAvaialable))
                System.out.println(card.getName() + " " + card.getColor());
        }
    }

    private boolean isCardNotAffordable(ModelEvent.AliasCard card, ArrayList<ModelEvent.AliasCard> cardsNotAvaialable) {
        for (ModelEvent.AliasCard cardNotAvailabe : cardsNotAvaialable){
            if(card.getName().equals(cardNotAvailabe.getName()) && card.getColor().equals(cardNotAvailabe.getColor()))
                return true;
        }
        return false;
    }
}
