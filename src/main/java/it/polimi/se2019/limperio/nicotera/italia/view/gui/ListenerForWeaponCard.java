package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerForWeaponCard implements MouseListener {

    private int numOfCard;
    private MainFrame mainFrame;
    private PopupForWeaponCard popupForWeaponCard = null;

    public ListenerForWeaponCard(int numOfCard, MainFrame mainFrame) {
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
}
