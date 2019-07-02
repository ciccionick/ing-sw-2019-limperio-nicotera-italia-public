package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.AmmoTile;
import it.polimi.se2019.limperio.nicotera.italia.model.NormalSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Listener for the labels that represent the squares of the map. It is used in two different occasion.
 * The first one is when a player tap and holding on the square wants to know what is on that square.
 * The second one is when a player has to select a square.
 * @author Pietro L'Imperio
 */
class ListenerOfSquare extends MouseAdapter {

    /**
     * The row of the square linked with an instance of this class.
     */
    private int row;
    /**
     * The column of the square linked with an instance of this class.
     */
        private int column;
    /**
     * The matrix of square that represents the map.
     */
    private Square[][] matrix;
    /**
     * The popup that creates the dialog showing what is on the square linked with the instance of this class.
     */
        private PopupForSquare popupForSquare = null;
    /**
     * The square linked with the instance of this class.
     */
    private Square square;
    /**
     * The reference of main frame.
     */
        private MainFrame mainFrame;
    /**
     * The list of alias cards representing the weapons on a spawn square.
     */
    private ArrayList<ServerEvent.AliasCard> listOfWeaponOnTheSquare;
    /**
     * The ammo tile on a normal square.
     */
        private AmmoTile ammoTileOnTheSquare;
    /**
     * The JLabel representing through an icon the square linked with the instance of this class.
     */
    private JLabel cell;
    /**
     * The hash map that bind the name of the variable with the relative JLabel
     */
        private HashMap<String, JLabel> hashMapForCell;

    /**
     * Constructor that initialize the class fields.
     * @param mapPanel The reference of the map panel.
     */
        ListenerOfSquare(int row, int column, MainFrame mainFrame, MapPanel mapPanel) {
            this.mainFrame = mainFrame;
            this.row = row;
            this.column = column;
            this.matrix = mainFrame.getRemoteView().getMapView().getMap();
            this.hashMapForCell = mapPanel.getHashMapForCell();
            cell = hashMapForCell.get("cell".concat(String.valueOf(row)).concat(String.valueOf(column)));
            square = matrix[row][column];
            if(square!=null){
                if(square.isSpawn())
                    listOfWeaponOnTheSquare = ((SpawnSquare)square).getWeaponsCardsForRemoteView();
                else
                    ammoTileOnTheSquare = ((NormalSquare)square).getAmmoTile();
            }
        }

    /**
     * Calls the constructor of the right popup for square checking if the square is spawn or not and preparing the list of weapon or ammotile.
     * @param square The square of which the player wants to know what is on it.
     */
    private void updatePopup(Square square){
        ArrayList<String> listOfNicknames = new ArrayList<>();
        if (!(square.getNicknamesOfPlayersOnThisSquare().isEmpty())) {
            listOfNicknames = square.getNicknamesOfPlayersOnThisSquare();
        }
        Point positionOfSquare = mainFrame.getMapPanel().getHashMapForCell().get("cell".concat(String.valueOf(row)).concat(String.valueOf(column))).getLocation();
        if (square.isSpawn()) {
            listOfWeaponOnTheSquare = ((SpawnSquare) square).getWeaponsCardsForRemoteView();
            popupForSquare = new PopupForSpawnSquare(listOfNicknames, mainFrame, listOfWeaponOnTheSquare );
        } else {
            ammoTileOnTheSquare = ((NormalSquare)square).getAmmoTile();
            popupForSquare = new PopupForNormalSquare(listOfNicknames,ammoTileOnTheSquare, mainFrame, positionOfSquare);
        }

    }

    /**
     * Check if a square is selectable.
     * @return True if the square is not null and is contained in array list of square selectable present in the map view. Otherwise false.
     */
        private boolean isClickableForSelection(){
            if(mainFrame.getRemoteView().getMapView().isHasToChooseASquare() && cell.isEnabled())
            {
                for(Square squareReachable : mainFrame.getRemoteView().getMapView().getReachableSquares()) {
                    if (square.getRow() == squareReachable.getRow() && square.getColumn() == squareReachable.getColumn())
                        return true;
                }
            }
            return false;
        }

    /**
     * Creates an event of answer to the event received by the remote view when the player has to select a square and click on one selectable.
     */
    @Override
        public void mouseClicked(MouseEvent e) {
            if(isClickableForSelection()){
                SelectionSquare selectionSquare = new SelectionSquare("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(),row,column);
                if(mainFrame.getRemoteView().getMapView().isSelectionForRun())
                    selectionSquare.setRunEvent(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionForCatch())
                    selectionSquare.setCatchEvent(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionForGenerationOfTerminator())
                    selectionSquare.setGenerationTerminatorEvent(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionForTeleporter())
                    selectionSquare.setSelectionSquareToUseTeleporter(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionForNewton())
                    selectionSquare.setSelectionSquareToUseNewton(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionForMoveTerminator())
                    selectionSquare.setMoveTerminatorEvent(true);
                if(mainFrame.getRemoteView().getMapView().isSelectionBeforeToShoot()){
                    selectionSquare.setBeforeToShoot(true);
                }
                if(mainFrame.getRemoteView().getMapView().isSelectionForShootAction()){
                    selectionSquare.setSelectionSquareForShootAction(true);
                }
                mainFrame.getRemoteView().notify(selectionSquare);
                for(JLabel label : hashMapForCell.values()){
                    label.setEnabled(true);
                }
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(false);
                mainFrame.getRemoteView().getMapView().setReachableSquares(null);
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonReturnToSelection().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonDisableSelection().setEnabled(false);
                mainFrame.updatePanelOfPlayers();
            }

        }

    /**
      Calls the updatePopup in the case the square is not null to show the dialog that contains information about who and what is in that square.
     */
    @Override
        public void mousePressed(MouseEvent e) {
            if (square!= null && !(mainFrame.getRemoteView().getMapView().isHasToChooseASquare())|| (mainFrame.getRemoteView().getMapView().isHasToChooseASquare()&&!cell.isEnabled())) {
                matrix = mainFrame.getRemoteView().getMapView().getMap();
                square=matrix[row][column];
                updatePopup(square);
                popupForSquare.getPopup().setVisible(true);
            }
        }

    /**
     * Makes not visible the dialog created with previous pressing.
     */
    @Override
        public void mouseReleased(MouseEvent e) {
            if(square!=null&&popupForSquare!=null) {
                popupForSquare.getPopup().setVisible(false);
            }
        }

    }
