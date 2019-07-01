package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DrawPowerUpCards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the button ("DRAW" or "OK") in the dialog for message
 */
class ListenerDrawOkButton implements ActionListener {

    /**
     * Dialog where is present the button linked with this action listener.
     */
    private JDialog dialogForRequestToDraw;
    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;
    /**
     * The number of card to draw after press the button "DRAW".
     */
    private int numOfCardToDraw;

    /**
     * Constructor of the class where are initialized the fields of the class.
     * @param dialogForRequestToDraw The reference of the dialog.
     * @param mainFrame The reference of main frame.
     * @param numOfCardToDraw The number of cards to draw.
     */
     ListenerDrawOkButton(JDialog dialogForRequestToDraw, MainFrame mainFrame, int numOfCardToDraw) {
        this.dialogForRequestToDraw = dialogForRequestToDraw;
        this.mainFrame = mainFrame;
        this.numOfCardToDraw = numOfCardToDraw;
    }

    /**
     * Handles the press of the button. If it's a "DRAW" button send a DrawPowerUpCards event with the number of the card to draw.
     * In any case makes not visible the dialog.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            if(numOfCardToDraw==2)
                mainFrame.getRemoteView().notify(new DrawPowerUpCards("", mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname(),2));
            if(numOfCardToDraw==1) {
                mainFrame.getRemoteView().notify(new DrawPowerUpCards("", mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname(), 1));
            }
        }
        dialogForRequestToDraw.setVisible(false);
    }
}
