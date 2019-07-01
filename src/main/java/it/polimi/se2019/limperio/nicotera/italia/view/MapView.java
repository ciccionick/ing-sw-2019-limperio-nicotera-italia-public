package it.polimi.se2019.limperio.nicotera.italia.view;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.MapEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.gui.MainFrame;
import java.util.ArrayList;

/**
 * This class handles the part of view that store the information about the map
 * @author Francesco Nicotera
 */
public class MapView {
    /**
     * The matrix of squares that represents tha map.
     */
    private Square[][] map = null;
    /**
     * The reference to the remote view.
     */
    private RemoteView remoteView;
    /**
     * The type of map choose by the first player or by the server.
     */
    private int typeOfMap;
    /**
     * It states if the player has to choose a square in any moment.
     */
    private boolean hasToChooseASquare = false;
    /**
     * It states if the player has to choose a square in any moment to run.
     */
    private boolean isSelectionForRun = false;
    /**
     * It states if the player has to choose a square in any moment to catch.
     */
    private boolean isSelectionForCatch = false;
    /**
     * It states if the player has to choose a square in any moment to decide where the terminator has to be spawn.
     */
    private boolean isSelectionForGenerationOfTerminator = false;
    /**
     * It states if the player has to choose a square in any moment to decide where move the terminator.
     */
    private boolean isSelectionForMoveTerminator = false;
    /**
     * It states if the player has to choose a square in any moment to move with teleporter.
     */
    private boolean isSelectionForTeleporter = false;
    /**
     * It states if the player has to choose a square in any moment to use Newton.
     */
    private boolean isSelectionForNewton = false;
    /**
     * It states if the player has to choose a square to move before shoot.
     */
    private boolean isSelectionBeforeToShoot = false;
    /**
     * It states if the player has to choose a square during his shoot action due to some effects of his weapon.
     */
    private boolean isSelectionForShootAction = false;
    /**
     * The list of the square among the player can choose.
     */
    private ArrayList<Square> reachableSquares = new ArrayList<>();

    /**
     * Instance map view associating the reference of the remote view.
     * @param remoteView The reference of the remote view.
     */
    public MapView(RemoteView remoteView) {
        this.remoteView = remoteView;
    }

    /**
     * Handles the creation and the update  of the map
     *
     * @param event Contains the new information that has to be implemented in the map
     */
    public void update(MapEvent event) {

        if (map == null) {
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
        remoteView.getMainFrame().updateFigureOnMap();
    }

    /**
     * Update everything is stored in the square, players, weapons and ammo tiles.
     * @param event Contains the information useful for the update.
     */
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

    /**
     * Get all of the squares like an ArrayList.
     * @return The ArrayList containing all of the square in the map.
     */
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


    public boolean isSelectionBeforeToShoot() {
        return isSelectionBeforeToShoot;
    }

     public void setSelectionBeforeToShoot(boolean selectionBeforeToShoot) {
        isSelectionBeforeToShoot = selectionBeforeToShoot;
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

    public boolean isSelectionForShootAction() {
        return isSelectionForShootAction;
    }

    public void setSelectionForShootAction(boolean selectionForShootAction) {
        isSelectionForShootAction = selectionForShootAction;
    }

    public void setSelectionForNewton(boolean selectionForNewton) {
        isSelectionForNewton = selectionForNewton;
    }
}

