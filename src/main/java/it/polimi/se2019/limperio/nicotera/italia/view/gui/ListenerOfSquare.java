package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionSquare;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.AmmoTile;
import it.polimi.se2019.limperio.nicotera.italia.model.NormalSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.SpawnSquare;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

class ListenerOfSquare implements MouseListener {

        private int row;
        private int column;
        private Square[][] matrix;
        private PopupForSquare popupForSquare = null;
        private Square square;
        private MainFrame mainFrame;
        private ArrayList<ServerEvent.AliasCard> listOfWeaponOnTheSquare;
        private AmmoTile ammoTileOnTheSquare;
        private JLabel cell;
        private HashMap<String, JLabel> hashMapForCell;

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
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonMSelection().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonDisableSelection().setEnabled(false);
                mainFrame.updatePanelOfPlayers();
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (square!= null && !(mainFrame.getRemoteView().getMapView().isHasToChooseASquare())|| (mainFrame.getRemoteView().getMapView().isHasToChooseASquare()&&!cell.isEnabled())) {
                matrix = mainFrame.getRemoteView().getMapView().getMap();
                square=matrix[row][column];
                updatePopup(square);
                popupForSquare.getPopup().setVisible(true);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(square!=null&&popupForSquare!=null) {
                popupForSquare.getPopup().setVisible(false);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //not implemented
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //not implemented
        }

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

    }
