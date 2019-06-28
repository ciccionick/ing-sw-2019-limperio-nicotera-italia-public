package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToDiscard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ListenerForChooseOfWeapon implements MouseListener, ActionListener {
    private String nameOfWeaponCard;
    private PopupForChooseWeaponCard popup;
    private ServerEvent event;
    private MainFrame mainFrame;

    ListenerForChooseOfWeapon(String nameOfWeaponCard, PopupForChooseWeaponCard popup, MainFrame mainFrame, ServerEvent event) {
        this.nameOfWeaponCard = nameOfWeaponCard;
        this.popup = popup;
        this.event = event;
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(popup!=null) {
            if(event.isRequestForChooseAWeaponToCatch()) {
                SelectionWeaponToCatch newEvent = new SelectionWeaponToCatch("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                newEvent.setNameOfWeaponCard(nameOfWeaponCard);
                popup.getPopupForChooseW().setVisible(false);
                mainFrame.getRemoteView().notify(newEvent);
            }
            if(event.isRequestToDiscardWeaponCard()){
                SelectionWeaponToDiscard selectionWeaponToDiscard = new SelectionWeaponToDiscard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(), nameOfWeaponCard, popup.getNameOfCardToStoreForDiscardEvent());
                popup.getPopupForChooseW().setVisible(false);
                mainFrame.getRemoteView().notify(selectionWeaponToDiscard);
            }
            if(event.isRequestSelectionWeaponToReload()){
                SelectionWeaponToReload selectionWeaponToReload = new SelectionWeaponToReload("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                selectionWeaponToReload.setNameOfWeaponCardToReload(nameOfWeaponCard);
                popup.getPopupForChooseW().setVisible(false);
                mainFrame.getRemoteView().notify(selectionWeaponToReload);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //not implemented
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //not implemented
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //not implemented
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //not implemented
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SelectionWeaponToReload selectionWeaponToReload = new SelectionWeaponToReload("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
        selectionWeaponToReload.setNameOfWeaponCardToReload(null);
        popup.getPopupForChooseW().setVisible(false);
        mainFrame.getRemoteView().notify(selectionWeaponToReload);

    }
}

