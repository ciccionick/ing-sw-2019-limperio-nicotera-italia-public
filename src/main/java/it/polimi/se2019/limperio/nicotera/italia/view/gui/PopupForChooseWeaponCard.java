package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


class PopupForChooseWeaponCard {
    private JDialog popupForChooseW;
    private String nameOfCardToStoreForDiscardEvent = null;

     PopupForChooseWeaponCard(ServerEvent receivedEvent, MainFrame mainFrame) {
         ClosingListener closingListener = new ClosingListener(mainFrame);
         popupForChooseW = new JDialog(mainFrame.getFrame());
         if(!receivedEvent.isRequestSelectionWeaponToReload())
            popupForChooseW.setUndecorated(false);
         else
             popupForChooseW.setUndecorated(true);
         popupForChooseW.addWindowListener(closingListener);
         JPanel contentPanel = new JPanel();
         contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
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

    class ClosingListener implements WindowListener {

         private MainFrame mainFrame;

         ClosingListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void windowOpened(WindowEvent e) {
            //not implemented
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
            //not implemented
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            //not implemented
        }

        @Override
        public void windowActivated(WindowEvent e) {
            //not implemented
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            //not implemented
        }
    }
}
