package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons that permits to return to the normal view on the map panel when the player has to select a square or to return the selection view.
 * @author Pietro L'Imperio
 */
    class ListenerForButtonSelectionSquare implements ActionListener {

    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;

    /**
     * Constructor of the class that initialize the reference of main frame.
     * @param mainFrame Reference of the main frame.
     */
        ListenerForButtonSelectionSquare(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

    /**
     * Makes enable all of the JLabel representing squares on map panel if the action command is equal to "Disable", else makes enable only selectable squares.
     * @param e
     */
    @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Disable")) {
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(false);
                for (JLabel label : mainFrame.getMapPanel().getHashMapForCell().values()) {
                    label.setEnabled(true);
                }
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonReturnToSelection().setEnabled(true);
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonDisableSelection().setEnabled(false);
            } else {
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(true);
                mainFrame.updateEnableSquares(mainFrame.getRemoteView().getMapView().getReachableSquares());
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonReturnToSelection().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfPlayers().getButtonDisableSelection().setEnabled(true);
            }
        }
}
