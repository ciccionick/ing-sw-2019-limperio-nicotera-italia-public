package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseWeaponCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Listener for the weapon cards stored in the deck and relative buttons to use them.
 * @author Pietro L'Imperio
 */
public class ListenerForWeaponCard extends MouseAdapter implements ActionListener {

    /**
     * The position of the card in the deck.
     */
    private int numOfCard;
    /**
     * The reference of main frame.
     */
    private MainFrame mainFrame;
    /**
     * The reference of the class tha creates a dialog that shows information about weapon card.
     */
    private PopupForWeaponCard popupForWeaponCard = null;
    /**
     * The button used to select a weapon card.
     */
    private JButton button;

    /**
     * Constructor where the class fields are initialized.
     */
     ListenerForWeaponCard(JButton buttonW, int numOfCard, MainFrame mainFrame) {
        this.numOfCard = numOfCard;
        this.mainFrame = mainFrame;
        this.button = buttonW;
    }

    /**
     * If the reference of the popup is null creates a new instance, otherwise makes not visible the dialog creating previously and makes null the reference of the popup.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(popupForWeaponCard!=null) {
            popupForWeaponCard.getDialog().setVisible(false);
            popupForWeaponCard=null;
        }
        else{
            if(mainFrame.getLeftPanel().getPlayerBoardView().getWeaponCardDeck().size()>=numOfCard) {
                popupForWeaponCard = new PopupForWeaponCard(mainFrame, numOfCard);
            }
        }
    }


    /**
     * Creates an event with the request to use a weapon and calls the notify of the remote view. Makes not enable all of the others buttons of weapons.
     */
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
