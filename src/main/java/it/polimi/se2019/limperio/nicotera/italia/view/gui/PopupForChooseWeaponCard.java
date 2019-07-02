package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardWeaponCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Handles the creation of the dialog to choose a weapon card.
 * @author Pietro L'Imperio.
 */
class PopupForChooseWeaponCard {
    /**
     * Dialog that is created in the constructor and let the player choose a weapon card.
     */
    private JDialog popupForChooseW;

    /**
     * The name of the weapon card that the player wants to catch if the event is of RequestToDiscardWeaponCard type.
     */
    private String nameOfCardToStoreForDiscardEvent = null;

    /**
     * The constructor of the class that creates a dialog adding the panel of weapon cards.
     * @param receivedEvent The event received by server.
     * @param mainFrame The reference of main frame.
     */
     PopupForChooseWeaponCard(ServerEvent receivedEvent, MainFrame mainFrame) {
        popupForChooseW = new JDialog(mainFrame.getFrame());
        popupForChooseW.setAutoRequestFocus(true);
        popupForChooseW.setUndecorated(receivedEvent.isRequestSelectionWeaponToReload());
         popupForChooseW.addWindowListener(new WindowAdapter() {
             @Override
             public void windowClosing(WindowEvent e) {
                 mainFrame.getRightPanel().getPanelOfActions().updateStateOfButton();
             }});

         if(receivedEvent.isRequestToDiscardWeaponCard())
             nameOfCardToStoreForDiscardEvent = ((RequestToDiscardWeaponCard)receivedEvent).getNameOfWeaponCardToAdd();
         JPanel contentPanel = new JPanel();
         int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
         int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);
         contentPanel.setBorder(new EmptyBorder(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));
         popupForChooseW.getContentPane().add(contentPanel);
         PanelForWeapons panelForWeapons;
         panelForWeapons = new PanelForWeapons(mainFrame, null, receivedEvent, this);
         contentPanel.add(panelForWeapons.getContentPane());

         popupForChooseW.pack();
         popupForChooseW.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - popupForChooseW.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - popupForChooseW.getHeight()) / 2);
         popupForChooseW.setVisible(true);
     }


     String getNameOfCardToStoreForDiscardEvent() {
        return nameOfCardToStoreForDiscardEvent;
    }

     JDialog getPopupForChooseW() {
        return popupForChooseW;
    }

}
