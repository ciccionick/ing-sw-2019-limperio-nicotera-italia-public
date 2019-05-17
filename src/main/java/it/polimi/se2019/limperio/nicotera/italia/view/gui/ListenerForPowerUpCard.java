package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerForPowerUpCard implements MouseListener, ActionListener {
    private JLabel labelOfCard;
    private int numOfCard;
    MainFrame mainFrame;
    private PopupForPowerUpCard popupForPowerUpCard = null;

     ListenerForPowerUpCard(JLabel labelOfCard, MainFrame mainFrame, int numOfCard) {
        this.labelOfCard = labelOfCard;
        this.mainFrame = mainFrame;
        this.numOfCard = numOfCard;
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(labelOfCard.isEnabled()){
            popupForPowerUpCard = new PopupForPowerUpCard(mainFrame, numOfCard);
            popupForPowerUpCard.getPopupForPC().setVisible(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
         if(labelOfCard.isEnabled()) {
             popupForPowerUpCard.getPopupForPC().setVisible(false);
         }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainFrame.hasToChoosePowerUpCard()){
            DiscardPowerUpCardToSpawnEvent event = new DiscardPowerUpCardToSpawnEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
            event.setPowerUpCard(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1));
            mainFrame.getRemoteView().notify(event);
            mainFrame.setHasToChoosePowerUpCardForSpawn(false);
        }

    }
}
