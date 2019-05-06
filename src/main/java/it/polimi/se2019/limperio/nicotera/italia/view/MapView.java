package it.polimi.se2019.limperio.nicotera.italia.view;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

/**
 * This class handles the part of view that build the map in the game
 * @author Francesco Nicotera
 */
public class MapView {
    /**
     * The map that consists in a matrix of squares
     */
    private Square[][] map;

    /**
     * Handles the creation and the set up of the map
     *
     * @param event contains the type of map that has to be created
     */
    public void update(MapEvent event) {
        map = event.getMap();
        updateMap(event);
        System.out.println("Mappa aggiornata con successo");

    }

    private void updateMap(MapEvent event) {
        for (int i = 0; i < map.length; i++) {
            SpawnSquare spawnSquare;
            for (int j = 0; j < map[i].length; j++) {
                if ((map[i][j] != null) && (map[i][j].isSpawn())) {
                    spawnSquare = (SpawnSquare) map[i][j];
                    if (spawnSquare.getColor().equals(ColorOfFigure_Square.RED))
                        spawnSquare.setWeaponsCardsForRemoteView((event).getWeaponsCardsForRedSpawnSquare());
                    if (spawnSquare.getColor().equals(ColorOfFigure_Square.BLUE))
                        spawnSquare.setWeaponsCardsForRemoteView((event).getWeaponsCardsForBlueSpawnSquare());
                    if (spawnSquare.getColor().equals(ColorOfFigure_Square.YELLOW))
                        spawnSquare.setWeaponsCardsForRemoteView(event.getWeaponsCardsForYellowSpawnSquare());
                }
            }
        }
    }


    public Square[][] getMap() {
        return map;
    }

}

