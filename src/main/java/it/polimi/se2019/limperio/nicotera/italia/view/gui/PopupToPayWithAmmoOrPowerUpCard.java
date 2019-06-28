package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardAmmoOrPowerUpToPayTargeting;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToPayWithAmmoOrPUCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;



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
         dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

         int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
         int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);

         contentPanel.setBorder(new EmptyBorder(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));

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
        gbc.insets = new Insets(0, leftRightBorder/2, topBottomBorder, leftRightBorder/2);
        contentPanel.add(text,gbc);

        ListenerForPopupToPayWithAmmoOrPUCArd listenerForPopupToPayWithAmmoOrPUCArd = new ListenerForPopupToPayWithAmmoOrPUCArd(dialog,mainFrame,event);

        gbc.gridy=1;
        gbc.insets.bottom=0;
        gbc.gridwidth = 1;
        ImageIcon icon;
        Image image;
        int widthAmmo = mainFrame.getFrame().getWidth()/40;
        int heightAmmo = mainFrame.getFrame().getHeight()/20;

        if(event.isBlueAmmo()){
            JButton blueButton = new JButton();
            icon = new ImageIcon(mainFrame.getResource("/playerboards/blueammo.png"));
            image = icon.getImage().getScaledInstance(widthAmmo,heightAmmo,Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            blueButton.setActionCommand("BlueAmmo");
            blueButton.setIcon(icon);
            blueButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
            gbc.gridx = 0;
            contentPanel.add(blueButton,gbc);

        }

         if(event.isRedAmmo()){
             JButton redButton = new JButton();
             icon = new ImageIcon(mainFrame.getResource("/playerboards/redammo.png"));
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
             icon = new ImageIcon(mainFrame.getResource("/playerboards/yellowammo.png"));
             image = icon.getImage().getScaledInstance(widthAmmo,heightAmmo,Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);
             yellowButton.setActionCommand("YellowAmmo");
             yellowButton.setIcon(icon);
             yellowButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
             gbc.gridx++;
             contentPanel.add(yellowButton,gbc);
         }

         String folderPath = "/powerupcards/";
         for(ServerEvent.AliasCard card : event.getPowerUpCards()){
             JButton cardButton = new JButton();
             String nameOfCard = card.getName();
             String color = card.getColor().toString();
             cardButton.setActionCommand(card.getName() + ","+ card.getColor().toString().toUpperCase());
             cardButton.setIcon(new ImageIcon(mainFrame.getResource(folderPath.concat(nameOfCard+ " ").concat(color+".png"))));
             cardButton.addActionListener(listenerForPopupToPayWithAmmoOrPUCArd);
             gbc.gridx++;
             contentPanel.add(cardButton,gbc);
         }

         dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

         dialog.setVisible(true);

    }

}
