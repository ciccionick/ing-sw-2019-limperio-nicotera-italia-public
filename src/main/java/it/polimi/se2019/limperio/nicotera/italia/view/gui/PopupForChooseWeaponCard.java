package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardWeaponCard;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


class PopupForChooseWeaponCard {
    private JDialog popupForChooseW;
    private MainFrame mainFrame;
    private String nameOfCardToStoreForDiscardEvent = null;

     PopupForChooseWeaponCard(RequestForChooseAWeaponToCatch requestForChooseAWeaponToCatch, RequestToDiscardWeaponCard requestToDiscardWeaponCard, MainFrame mainFrame) {
         ClosingListener closingListener = new ClosingListener(mainFrame);
         popupForChooseW = new JDialog(mainFrame.getFrame());
         this.mainFrame = mainFrame;
         int width = (int) (mainFrame.getFrame().getWidth() / 2.08);
         int height = (int) (mainFrame.getFrame().getHeight() / 1.56);
         popupForChooseW.setSize(width, height);
         popupForChooseW.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - popupForChooseW.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - popupForChooseW.getHeight()) / 2);
         PanelForWeapons panelForWeapons = null;
         if(requestForChooseAWeaponToCatch!=null){
             panelForWeapons = new PanelForWeapons(mainFrame, requestForChooseAWeaponToCatch.getWeaponsAvailableToCatch(), this);
         }
         if(requestToDiscardWeaponCard!=null){
             nameOfCardToStoreForDiscardEvent = requestToDiscardWeaponCard.getNameOfWeaponCardToAdd();
             panelForWeapons = new PanelForWeapons(mainFrame, mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck(), this);
         }
         if(panelForWeapons!=null)
            popupForChooseW.getContentPane().add(panelForWeapons.getContentPane());
         popupForChooseW.setVisible(true);
     }


     String getNameOfCardToStoreForDiscardEvent() {
        return nameOfCardToStoreForDiscardEvent;
    }

     JDialog getPopupForChooseW() {
        return popupForChooseW;
    }

    class ClosingListener implements WindowListener {

         private MainFrame mainFrame;

         ClosingListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            mainFrame.getRightPanel().getPanelOfActions().updateStateOfButton();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            mainFrame.getRightPanel().getPanelOfActions().updateStateOfButton();
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
