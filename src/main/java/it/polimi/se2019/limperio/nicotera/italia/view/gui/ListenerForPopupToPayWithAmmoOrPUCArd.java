package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardAmmoOrPowerUpToPayTargeting;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for buttons concerning the choice of pay with ammo or power up cards.
 * @author Pietro L'Imperio
 */
 class ListenerForPopupToPayWithAmmoOrPUCArd implements ActionListener{

    /**
     * The reference of dialog where the choice happnes.
     */
    private JDialog dialog;
    /**
     * The reference of main frame.
     */
     private MainFrame mainFrame;
    /**
     * The event received by server side with the information about what choose.
     */
    private ServerEvent event;

    /**
     * The constructor where class fields are initialized.
     */
      ListenerForPopupToPayWithAmmoOrPUCArd(JDialog dialog, MainFrame mainFrame, ServerEvent event) {
         this.dialog = dialog;
         this.mainFrame = mainFrame;
         this.event = event;
     }

    /**
     * Creates an event with the information caught from the press of the buttons and calls the notify of the remote view.
     */
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

