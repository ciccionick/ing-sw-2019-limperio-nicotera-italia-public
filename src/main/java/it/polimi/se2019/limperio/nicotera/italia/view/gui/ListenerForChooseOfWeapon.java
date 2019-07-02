package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToDiscard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.event.*;

/**
 * Listener for the buttons concerning the choice of a weapon in three different occasion.
 * The first one is when someone wants to grab a weapon.
 * The second one is when someone wants to grab a weapon but to do this has to discard one of his weapons.
 * The third one is when someone wants to reload one of his weapons.
 * @author Pietro L'Imperio
 */
class ListenerForChooseOfWeapon extends MouseAdapter implements ActionListener {
    /**
     * The name of the weapon card involved in the choice.
     */
    private String nameOfWeaponCard;
    /**
     * The reference of the class where the dialog where happens the choice is created
     */
    private PopupForChooseWeaponCard popup;
    /**
     * The event received by the server side with the list of weapon.
     */
    private ServerEvent event;
    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;

    /**
     * Constructor where the class field are initialized.
     * @param nameOfWeaponCard The name of the weapon card.
     * @param popup The reference of the class where the dialog is created.
     * @param mainFrame The reference of the main frame.
     * @param event The event received by server side.
     */
    ListenerForChooseOfWeapon(String nameOfWeaponCard, PopupForChooseWeaponCard popup, MainFrame mainFrame, ServerEvent event) {
        this.nameOfWeaponCard = nameOfWeaponCard;
        this.popup = popup;
        this.event = event;
        this.mainFrame = mainFrame;
    }

    /**
     * Creates an event when the player clicks on the weapon card he wants to select and calls the notify of the remote view.
     */
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

    /**
     * Sends an event without name of weapon card in the case of choice of weapon to reload. In the dialog where this choice happens there is also a button to avoid to reload. The pressing of this buttons calls this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SelectionWeaponToReload selectionWeaponToReload = new SelectionWeaponToReload("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
        selectionWeaponToReload.setNameOfWeaponCardToReload(null);
        popup.getPopupForChooseW().setVisible(false);
        mainFrame.getRemoteView().notify(selectionWeaponToReload);

    }
}

