package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import javax.swing.*;
import java.awt.*;

class PopupForChooseWeaponCardToCatch {
    private JDialog popupForChooseW;
    private MainFrame mainFrame;

     PopupForChooseWeaponCardToCatch(RequestForChooseAWeaponToCatch receivedEvent, MainFrame mainFrame) {
         popupForChooseW = new JDialog();
         this.mainFrame = mainFrame;
         int width = (int) (mainFrame.getFrame().getWidth() / 2.08);
         int height = (int) (mainFrame.getFrame().getHeight() / 1.56);
         popupForChooseW.setSize(width, height);
         popupForChooseW.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - popupForChooseW.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - popupForChooseW.getHeight()) / 2);

         popupForChooseW.getContentPane().add(new PanelForWeapons(mainFrame, receivedEvent.getWeaponsAvailableToCatch(), receivedEvent, popupForChooseW).getContentPane());
         popupForChooseW.setVisible(true);
     }
}
