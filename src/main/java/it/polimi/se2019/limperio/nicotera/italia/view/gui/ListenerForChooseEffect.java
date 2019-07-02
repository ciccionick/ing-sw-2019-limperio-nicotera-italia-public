package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseEffect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons concerning the choose of an effect of a weapon.
 * @author Pietro L'Imperio
 */
 class ListenerForChooseEffect implements ActionListener{
    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;
    /**
     * The reference of the instance of the class where is created the dialog where happens the choice.
     */
        private PopupForChooseEffect popup;

    /**
     * Constructor where the class fields are initialized.
     * @param mainFrame The reference of the main frame.
     * @param popup The reference of the class creates the dialog.
     */
    ListenerForChooseEffect(MainFrame mainFrame, PopupForChooseEffect popup) {
            this.mainFrame = mainFrame;
            this.popup = popup;
        }

    /**
     * Creates an event with the number of the effect chosen by the player and calls the notify of the remote view.
     */
        @Override
        public void actionPerformed(ActionEvent e) {
            int numOfEffect = Integer.parseInt(e.getActionCommand());
            if(e.getActionCommand().equals("0") || popup.getEffectButtons().get(numOfEffect-1).isEnabled()){
                RequestToUseEffect requestToUseEffect = new RequestToUseEffect("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseEffect.setNumOfEffect(numOfEffect);
                popup.getDialog().setVisible(false);
                mainFrame.getRemoteView().notify(requestToUseEffect);
            }
        }
    }

