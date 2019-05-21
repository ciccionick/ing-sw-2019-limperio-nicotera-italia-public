package it.polimi.se2019.limperio.nicotera.italia.view;


import it.polimi.se2019.limperio.nicotera.italia.controller.Controller;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Game;
import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.NetworkHandler;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class MapViewTest {

    Game game;
    Client client;
    RemoteView remoteView;
    MapView mapView;

    @Before
    public void setUp(){
        game.createPlayer("player1", true, 1, "BLUE");
        game.createPlayer("player2", false, 2, "YELLOW");
        game.createPlayer("player3", false, 3, "GREY");
        game.setGameOver(true);
        game.initializeGame(false, 1, false);
        game= Game.instanceOfGame();
        client= new Client();
        remoteView= new RemoteView(client, client.getMyNetworkHandler());
        mapView= new MapView(remoteView);
    }

    /*@Test
    public void updateTest()
    {
        game.getBoard().getMap().getMatrixOfSquares()[1][1].getPlayerOnThisSquare().add(game.getPlayers().get(0));
        MapEvent mapEvent=


    }*/



}