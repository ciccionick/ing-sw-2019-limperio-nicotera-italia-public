package it.polimi.se2019.limperio.nicotera.italia.view;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;

import java.rmi.Remote;

/**
 * This class handles the part of view that build the map in the game
 * @author Francesco Nicotera
 */
public class MapView {
    /**
     * The map that consists in a matrix of squares
     */
    private Square[][] map = null;

    private RemoteView remoteView;

    private int typeOfMap;

    private boolean hasToChooseASquare = false;
    private boolean isSelectionForRun = false;
    private boolean isSelectionForCatch = false;

    public MapView(RemoteView remoteView) {
        this.remoteView = remoteView;
    }

    /**
     * Handles the creation and the set up of the map
     *
     * @param event contains the type of map that has to be created
     */
    public void update(MapEvent event) {

        if(map==null) {
            map = event.getMap();
            typeOfMap = event.getTypeOfMap();
            remoteView.setTerminatorMode(event.isTerminatorMode());
            remoteView.getInitializationView().getFrameForInitialization().getFrame().setVisible(false);
            remoteView.setMainFrame(new MainFrame(remoteView));
        }
        else {
            map = event.getMap();
        }
        updateMap(event);
    }

    private void updateMap(MapEvent event) {
        for (int i = 0; i < map.length; i++) {
            SpawnSquare spawnSquare;
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]!=null) {
                    map[i][j].setNicknamesOfPlayersOnThisSquare(event.getHashMapForPlayersInSquare().get(String.valueOf(i).concat(String.valueOf(j))));
                    if ((map[i][j].isSpawn())) {
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
    }

    public int getTypeOfMap() {
        return typeOfMap;
    }

    public Square[][] getMap() {
        return map;
    }

    public boolean isHasToChooseASquare() {
        return hasToChooseASquare;
    }

    public void setHasToChooseASquare(boolean hasToChooseASquare) {
        this.hasToChooseASquare = hasToChooseASquare;
    }

    public boolean isSelectionForRun() {
        return isSelectionForRun;
    }

    public void setSelectionForRun(boolean selectionForRun) {
        isSelectionForRun = selectionForRun;
    }

    public boolean isSelectionForCatch() {
        return isSelectionForCatch;
    }

    public void setSelectionForCatch(boolean selectionForCatch) {
        isSelectionForCatch = selectionForCatch;
    }
}

