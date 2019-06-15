package it.polimi.se2019.limperio.nicotera.italia.view;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;

import java.rmi.Remote;
import java.util.ArrayList;

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
    private boolean isSelectionForGenerationOfTerminator = false;
    private boolean isSelectionForMoveTerminator = false;
    private boolean isSelectionForTeleporter = false;
    private boolean isSelectionForNewton = false;
    private ArrayList<Square> reachableSquares = new ArrayList<>();

    public MapView(RemoteView remoteView) {
        this.remoteView = remoteView;
    }

    /**
     * Handles the creation and the set up of the map
     *
     * @param event contains the type of map that has to be created
     */
    public void update(MapEvent event) {

        if (map == null) {
            System.out.println("Sono in update di map view");
            map = event.getMap();
            typeOfMap = event.getTypeOfMap();
            remoteView.setTerminatorMode(event.isTerminatorMode());
            remoteView.getInitializationView().getFrameForInitialization().getFrame().setVisible(false);
            remoteView.setMainFrame(new MainFrame(remoteView));
        } else {
            map = event.getMap();
            if(event.getNicknameInvolved()!=null && event.getNicknameInvolved().equals("terminator"))
                remoteView.getMainFrame().showMessage(event);
        }
        updateMap(event);
    }

    private void updateMap(MapEvent event) {
        for (int i = 0; i < map.length; i++) {
            SpawnSquare spawnSquare;
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
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

    public ArrayList<Square> getListOfSquareAsArrayList() {
        ArrayList<Square> allSquares = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    allSquares.add(map[i][j]);
                }
            }

        }
        return allSquares;
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

    public boolean isSelectionForGenerationOfTerminator() {
        return isSelectionForGenerationOfTerminator;
    }

    public void setSelectionForGenerationOfTerminator(boolean selectionForGenerationOfTerminator) {
        isSelectionForGenerationOfTerminator = selectionForGenerationOfTerminator;
    }

    public boolean isSelectionForMoveTerminator() {
        return isSelectionForMoveTerminator;
    }

    public void setSelectionForMoveTerminator(boolean selectionForMoveTerminator) {
        isSelectionForMoveTerminator = selectionForMoveTerminator;
    }

    public ArrayList<Square> getReachableSquares() {
        return reachableSquares;
    }

    public void setReachableSquares(ArrayList<Square> reachableSquares) {
        this.reachableSquares = reachableSquares;
    }

    public boolean isSelectionForTeleporter() {
        return isSelectionForTeleporter;
    }

    public void setSelectionForTeleporter(boolean selectionForTeleporter) {
        isSelectionForTeleporter = selectionForTeleporter;
    }

    public boolean isSelectionForNewton() {
        return isSelectionForNewton;
    }

    public void setSelectionForNewton(boolean selectionForNewton) {
        isSelectionForNewton = selectionForNewton;
    }
}

