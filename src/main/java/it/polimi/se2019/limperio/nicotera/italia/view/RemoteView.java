package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.DrawTwoPowerUpCards;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;
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
        if(message.isDrawTwoPowerUpCardEvent()){
            System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
            String action = stdin.nextLine();
            while(!(action.equalsIgnoreCase("pesca"))){
                System.out.println("Digita 'pesca' se vuoi pescare le due carte potenziamento ");
                action=stdin.nextLine();
            }
            notify(new DrawTwoPowerUpCards("Voglio pescare due carte", client.getNickname()));
        }
        if(message.isDiscardPowerUpCardToSpawnEvent()) {
            System.out.println("Hai pescato: " + playerBoardView.getPowerUpCardsDeck().get(0).getName() + " " +  playerBoardView.getPowerUpCardsDeck().get(0).getColor() + " e " + playerBoardView.getPowerUpCardsDeck().get(1).getName() + " " + playerBoardView.getPowerUpCardsDeck().get(1).getColor());
            System.out.println(" Digita 1 se vuoi scartare la prima o 2 se vuoi scartare la seconda");
            int choose;
            choose = stdin.nextInt();
            while (choose != 1 && choose != 2) {
                System.out.println("Digita 1 se vuoi scartare la prima o 2 se vuoi scartare la seconda");
                choose = stdin.nextInt();
            }
        }



    }
}
