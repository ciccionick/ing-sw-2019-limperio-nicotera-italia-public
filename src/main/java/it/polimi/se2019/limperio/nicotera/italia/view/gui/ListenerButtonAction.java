package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons located in the panel of actions.
 * @author Pietro L'Imperio.
 */
    public class ListenerButtonAction implements ActionListener {

    /**
     * The reference of main frame.
     */
    private MainFrame mainFrame;

    /**
     * Constructor of the class where the field of the reference of main frame is initialized.
     * @param mainFrame Reference of the main frame.
     */
         ListenerButtonAction(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

    /**
     * Creates an event of request to do an action according with the button pressed or makes again enable the buttons of the actions that player can do if he has pressed the cancel button.
     */
    @Override
        public void actionPerformed(ActionEvent e) {
            String nickname = mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer();
            ClientEvent event = new ClientEvent("", nickname);
            switch(e.getActionCommand()){
                case "Run":
                    event.setRequestToRunByPlayer(true);
                    break;
                case "Catch":
                    event.setRequestToCatchByPlayer(true);
                    break;
                case "Shoot":
                    event.setRequestToShootByPlayer(true);
                    break;
                case "Terminator":
                    event.setRequestTerminatorActionByPlayer();
                    break;
                case "Cancel":
                    mainFrame.getRightPanel().getPanelOfActions().updateStateOfButton();
                    mainFrame.getRemoteView().getMyPlayerBoardView().disableWeaponsButton();
                    mainFrame.getRemoteView().getMapView().setHasToChooseASquare(false);
                    mainFrame.updateLeftPanelForWhoIsViewing(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                    for(JLabel label : mainFrame.getMapPanel().getHashMapForCell().values()){
                        label.setEnabled(true);
                    }
                    break;
                default: throw new IllegalArgumentException();
            }
            mainFrame.getRemoteView().notify(event);

            if(!(e.getActionCommand().equals("Cancel"))) {
                mainFrame.getRightPanel().getPanelOfActions().disablePowerUpCards();
                mainFrame.getRightPanel().getPanelOfActions().getButtonCatch().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfActions().getButtonRun().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfActions().getButtonShoot().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfActions().getButtonTerminator().setEnabled(false);
                mainFrame.getRightPanel().getPanelOfActions().getButtonCancel().setEnabled(true);
            }
        }


}
