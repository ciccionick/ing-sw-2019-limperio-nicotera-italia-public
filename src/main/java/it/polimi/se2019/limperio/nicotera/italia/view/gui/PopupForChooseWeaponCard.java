package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


class PopupForChooseWeaponCard {
    private JDialog popupForChooseW;
    private MainFrame mainFrame;
    private String nameOfCardToStoreForDiscardEvent = null;

     PopupForChooseWeaponCard(ServerEvent receivedEvent, MainFrame mainFrame) {
         ClosingListener closingListener = new ClosingListener(mainFrame);
         popupForChooseW = new JDialog(mainFrame.getFrame());
         popupForChooseW.addWindowListener(closingListener);
         this.mainFrame = mainFrame;
         PanelForWeapons panelForWeapons;
         panelForWeapons = new PanelForWeapons(mainFrame, null, receivedEvent, this);
         popupForChooseW.getContentPane().add(panelForWeapons.getContentPane());


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
