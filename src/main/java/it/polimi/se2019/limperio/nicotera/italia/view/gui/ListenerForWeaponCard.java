package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseWeaponCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerForWeaponCard implements MouseListener, ActionListener {

    private int numOfCard;
    private MainFrame mainFrame;
    private PopupForWeaponCard popupForWeaponCard = null;

     ListenerForWeaponCard(int numOfCard, MainFrame mainFrame) {
        this.numOfCard = numOfCard;
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(mainFrame.getLeftPanel().getPlayerBoardView().getWeaponCardDeck().size()>=numOfCard) {
            popupForWeaponCard = new PopupForWeaponCard(mainFrame, numOfCard);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(popupForWeaponCard!=null)
            popupForWeaponCard.getDialog().setVisible(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck().size()>=numOfCard) {
            RequestToUseWeaponCard requestToUseWeaponCard = new RequestToUseWeaponCard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
            requestToUseWeaponCard.setWeaponWantUse(mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck().get(numOfCard - 1));
            mainFrame.getRemoteView().notify(requestToUseWeaponCard);
        }

    }
}
