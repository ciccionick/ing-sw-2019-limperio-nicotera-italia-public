package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_of_model.ModelEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_of_view.ViewEvent;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observable;
import it.polimi.se2019.limperio.nicotera.italia.utils.Observer;

public class RemoteView extends Observable<ViewEvent> implements Observer<ModelEvent> {
    private Client client;
    private NetworkHandler networkHandler;
    private PlayerBoardView playerBoardView;
    private MapView mapView;
    private KillshotTrackView killshotTrackView;

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

    }
}
