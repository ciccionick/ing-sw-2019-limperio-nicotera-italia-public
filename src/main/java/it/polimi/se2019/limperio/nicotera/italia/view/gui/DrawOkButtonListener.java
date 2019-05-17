package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DrawTwoPowerUpCards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawOkButtonListener implements ActionListener {

    private JDialog dialogForRequestToDraw;
    private MainFrame mainFrame;

     DrawOkButtonListener(JDialog dialogForRequestToDraw, MainFrame mainFrame) {
        this.dialogForRequestToDraw = dialogForRequestToDraw;
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            mainFrame.getRemoteView().notify(new DrawTwoPowerUpCards("", mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname()));
        }
        dialogForRequestToDraw.setVisible(false);
    }
}
