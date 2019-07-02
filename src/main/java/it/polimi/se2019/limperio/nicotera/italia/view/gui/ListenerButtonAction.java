package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestTerminatorActionByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToRunByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToShootByPlayer;
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
            switch(e.getActionCommand()){
                case "Run":
                    mainFrame.getRemoteView().notify(new RequestToRunByPlayer("", nickname));
                    break;
                case "Catch":
                    mainFrame.getRemoteView().notify(new RequestToCatchByPlayer("", nickname));
                    break;
                case "Shoot":
                    mainFrame.getRemoteView().notify(new RequestToShootByPlayer("", nickname));
                    break;
                case "Terminator":
                    mainFrame.getRemoteView().notify(new RequestTerminatorActionByPlayer("",  mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer()));
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
