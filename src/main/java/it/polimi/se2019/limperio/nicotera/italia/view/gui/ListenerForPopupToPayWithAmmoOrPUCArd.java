package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardAmmoOrPowerUpToPayTargeting;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class ListenerForPopupToPayWithAmmoOrPUCArd implements ActionListener{

     private JDialog dialog;
     private MainFrame mainFrame;
     private ServerEvent event;

      ListenerForPopupToPayWithAmmoOrPUCArd(JDialog dialog, MainFrame mainFrame, ServerEvent event) {
         this.dialog = dialog;
         this.mainFrame = mainFrame;
         this.event = event;
     }

     @Override
        public void actionPerformed(ActionEvent e) {
            DiscardAmmoOrPowerUpToPayTargeting discardAmmoOrPowerUpToPayTargeting = new DiscardAmmoOrPowerUpToPayTargeting("", event.getNicknameInvolved());
            switch (e.getActionCommand()){
                case "BlueAmmo":
                    discardAmmoOrPowerUpToPayTargeting.setBlueAmmo(true);
                    dialog.setVisible(false);
                    mainFrame.getRemoteView().notify(discardAmmoOrPowerUpToPayTargeting);
                    break;

                case "RedAmmo":
                    discardAmmoOrPowerUpToPayTargeting.setRedAmmo(true);
                    dialog.setVisible(false);
                    mainFrame.getRemoteView().notify(discardAmmoOrPowerUpToPayTargeting);
                    break;

                case "YellowAmmo":
                    discardAmmoOrPowerUpToPayTargeting.setYellowAmmo(true);
                    dialog.setVisible(false);
                    mainFrame.getRemoteView().notify(discardAmmoOrPowerUpToPayTargeting);
                    break;

                default:

                    String[] nameAndColor = e.getActionCommand().split(",");
                    for(ServerEvent.AliasCard card : mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck()){
                        if(card.getName().equals(nameAndColor[0])&& card.getColor().toString().equalsIgnoreCase(nameAndColor[1])) {
                            discardAmmoOrPowerUpToPayTargeting.setPowerUpCard(card);
                            dialog.setVisible(false);
                            mainFrame.getRemoteView().notify(discardAmmoOrPowerUpToPayTargeting);
                            break;
                        }
                    }
            }

        }
    }

