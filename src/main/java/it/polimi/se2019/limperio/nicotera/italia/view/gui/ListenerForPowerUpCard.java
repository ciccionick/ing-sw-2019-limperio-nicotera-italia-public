package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseNewton;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseTeleporter;

import javax.swing.*;
import java.awt.*;
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
        if((numOfCard == 1 && mainFrame.getLeftPanel().getButtonPC1().isEnabled()) || (numOfCard == 2 && mainFrame.getLeftPanel().getButtonPC2().isEnabled()) || (numOfCard == 3 && mainFrame.getLeftPanel().getButtonPC3().isEnabled())){
            if(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName().equals("Teleporter")){
                RequestToUseTeleporter requestToUseTeleporter = new RequestToUseTeleporter("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseTeleporter.setNumOfCard(numOfCard);
                mainFrame.getRemoteView().notify(requestToUseTeleporter);
            }
            else if(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName().equals("Newton")){
                RequestToUseNewton requestToUseNewton = new RequestToUseNewton("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseNewton.setNumOfCard(numOfCard);
                mainFrame.getRemoteView().notify(requestToUseNewton);
            }
        }
    }
}
