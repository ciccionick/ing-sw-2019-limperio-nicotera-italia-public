package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardAmmoOrPowerUpToPayTargeting;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToPayWithAmmoOrPUCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PopupToPayWithAmmoOrPowerUpCard {
    private JDialog dialog;
    private MainFrame mainFrame;
    private RequestToPayWithAmmoOrPUCard event;

     PopupToPayWithAmmoOrPowerUpCard(MainFrame mainFrame, ServerEvent receivedEvent) {
         this.event = (RequestToPayWithAmmoOrPUCard) receivedEvent;
         this.mainFrame = mainFrame;

        dialog = new JDialog(mainFrame.getFrame());
        JPanel contentPanel = new JPanel(new GridBagLayout());
        dialog.getContentPane().add(contentPanel);
        dialog.setUndecorated(true);
         dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));

         contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        int gridWidthForText=0;
        if(event.isBlueAmmo())
            gridWidthForText++;
        if(event.isRedAmmo())
            gridWidthForText++;
        if(event.isYellowAmmo())
            gridWidthForText++;
        gridWidthForText = gridWidthForText + event.getPowerUpCards().size();

        GridBagConstraints gbc = new GridBagConstraints();
        JTextArea text = new JTextArea(event.getMessageForInvolved());
        text.setBackground(SystemColor.menu);
        text.setEditable(false);
        gbc.gridy=0;
        gbc.gridx=0;
        gbc.gridwidth= gridWidthForText;
        gbc.insets = new Insets(0, 10, 20, 10);
        contentPanel.add(text,gbc);

        ListenerForPopupToPayWithAmmoOrPUCArd listenerForPopupToPayWithAmmoOrPUCArd = new ListenerForPopupToPayWithAmmoOrPUCArd();

        gbc.gridy=1;
        gbc.insets.bottom=0;
        ImageIcon icon;
        Image image;
        int widthAmmo = mainFrame.getFrame().getWidth()/40;
        int heightAmmo = mainFrame.getFrame().getHeight()/20;

        if(event.isBlueAmmo()){
            JButton blueButton = new JButton();
            icon = new ImageIcon("resources/playerboards/blueammo.png");
            image = icon.getImage().getScaledInstance(widthAmmo,heightAmmo,Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            blueButton.setActionCommand("BlueAmmo");
            blueButton.setIcon(icon);
            blueButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
            contentPanel.add(blueButton,gbc);

        }

         if(event.isRedAmmo()){
             JButton redButton = new JButton();
             icon = new ImageIcon("resources/playerboards/redammo.png");
             image = icon.getImage().getScaledInstance(widthAmmo,heightAmmo,Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);
             redButton.setActionCommand("RedAmmo");
             redButton.setIcon(icon);
             redButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
             gbc.gridx++;
             contentPanel.add(redButton, gbc);
         }

         if(event.isYellowAmmo()){
             JButton yellowButton = new JButton();
             icon = new ImageIcon("resources/playerboards/yellowammo.png");
             image = icon.getImage().getScaledInstance(widthAmmo,heightAmmo,Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);
             yellowButton.setActionCommand("YellowAmmo");
             yellowButton.setIcon(icon);
             yellowButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
             gbc.gridx++;
             contentPanel.add(yellowButton,gbc);
         }

         String folderPath = "resources/powerupcards/";
         for(ServerEvent.AliasCard card : event.getPowerUpCards()){
             JButton cardButton = new JButton();
             String nameOfCard = card.getName();
             String color = card.getColor().toString();
             /*icon = new ImageIcon(folderPath.concat(nameOfCard+ " ").concat(color+".png"));
             image = icon.getImage().getScaledInstance(widthCard,heightCard,Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);*/
             cardButton.setActionCommand(card.getName() + ","+ card.getColor().toString().toUpperCase());
             cardButton.setIcon(new ImageIcon(folderPath.concat(nameOfCard+ " ").concat(color+".png")));
             cardButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
             gbc.gridx++;
             contentPanel.add(cardButton,gbc);
         }

         dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

         dialog.setVisible(true);

    }

    private class ListenerForPopupToPayWithAmmoOrPUCArd implements ActionListener {


         ListenerForPopupToPayWithAmmoOrPUCArd() {
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
}
