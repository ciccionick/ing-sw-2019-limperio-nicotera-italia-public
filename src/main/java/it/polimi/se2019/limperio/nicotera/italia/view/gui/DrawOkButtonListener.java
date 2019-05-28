package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DrawPowerUpCards;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DrawOkButtonListener implements ActionListener {

    private JDialog dialogForRequestToDraw;
    private MainFrame mainFrame;
    private int numOfCardToDraw;

     DrawOkButtonListener(JDialog dialogForRequestToDraw, MainFrame mainFrame, int i) {
        this.dialogForRequestToDraw = dialogForRequestToDraw;
        this.mainFrame = mainFrame;
        this.numOfCardToDraw = i;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DRAW")){
            if(numOfCardToDraw==2)
                mainFrame.getRemoteView().notify(new DrawPowerUpCards("", mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname(),2));
            if(numOfCardToDraw==1) {
                mainFrame.getRemoteView().notify(new DrawPowerUpCards("", mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname(), 1));
                System.out.println("L'evento lo genero");
            }
        }
        dialogForRequestToDraw.setVisible(false);
    }
}
