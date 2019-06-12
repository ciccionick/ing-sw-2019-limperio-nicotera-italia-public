package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseWeaponCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerForWeaponCard implements MouseListener, ActionListener {

    private int numOfCard;
    private MainFrame mainFrame;
    private PopupForWeaponCard popupForWeaponCard = null;
    private JButton button;

     ListenerForWeaponCard(JButton buttonW, int numOfCard, MainFrame mainFrame) {
        this.numOfCard = numOfCard;
        this.mainFrame = mainFrame;
        this.button = buttonW;
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
        if(button.isEnabled()) {
            RequestToUseWeaponCard requestToUseWeaponCard = new RequestToUseWeaponCard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
            requestToUseWeaponCard.setWeaponWantUse(mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck().get(numOfCard - 1));
            mainFrame.getRemoteView().notify(requestToUseWeaponCard);
            mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon1(false);
            mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon2(false);
            mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon3(false);
            mainFrame.getLeftPanel().getButtonW1().setEnabled(false);
            mainFrame.getLeftPanel().getButtonW2().setEnabled(false);
            mainFrame.getLeftPanel().getButtonW3().setEnabled(false);
        }

    }
}
