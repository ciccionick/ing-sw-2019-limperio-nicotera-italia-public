package it.polimi.se2019.limperio.nicotera.italia.view;

import com.sun.deploy.nativesandbox.comm.Request;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.SelectionViewForSquareWhereCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.*;
import it.polimi.se2019.limperio.nicotera.italia.model.*;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

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

    public void setPlayerBoardView(PlayerBoardView playerBoardView) {
        this.playerBoardView = playerBoardView;
    }

    public MapView getMapView() {
        return mapView;
    }

    public KillshotTrackView getKillshotTrackView() {
        return killshotTrackView;
    }

    @Override
    public void register(Observer<ViewEvent> observer) {
        this.networkHandler = (NetworkHandler) observer;
    }

    @Override
    public void deregister(Observer<ViewEvent> observer) {

    }

    @Override
    public void notify(ViewEvent message) {
        networkHandler.update(message);
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
            while(!action.equalsIgnoreCase("corri")&&!action.equalsIgnoreCase("raccogli")&&!action.equalsIgnoreCase("spara")){
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
                SpawnSquare spawnSquare;
                NormalSquare normalSquare;
                System.out.println("[" + square.getRow()+ "] [" + square.getColumn() + "]" );
                if(square.isSpawn()){
                    spawnSquare= (SpawnSquare) square;
                    for(WeaponCard card : ((SpawnSquare) square).getWeaponCards()){
                        System.out.println(card.getName() + " " + card.getColor());
                    }
                }
                else {
                    normalSquare = (NormalSquare) square;
                    System.out.println(normalSquare.getAmmoTile().toString());
                }
            }
        }

    }

    private void discardPowerUpCard(int i) {
        ModelEvent.AliasPowerUp powerUpCardToDiscard = playerBoardView.getPowerUpCardsDeck().remove(i);
        DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("Ho deciso di scartare questa carta", client.getNickname());
        newEvent.setPowerUpCard(powerUpCardToDiscard);
        notify(newEvent);
    }
}
