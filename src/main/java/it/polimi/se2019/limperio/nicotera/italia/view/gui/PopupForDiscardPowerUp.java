package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 class PopupForDiscardPowerUp {

    private JDialog dialog;
    private MainFrame mainFrame;

     PopupForDiscardPowerUp(MainFrame mainFrame, ServerEvent receveidEvent) {
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setModal(false);
        int width = (int) (mainFrame.getFrame().getWidth() / 2.08);
        int height = (int) (mainFrame.getFrame().getHeight() / 1.56);
        dialog.setSize(width, height);
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
        dialog.setUndecorated(true);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));
        dialog.getContentPane().add(contentPanel);

        ArrayList<ServerEvent.AliasCard> listOfPowerUpCards = new ArrayList<>();
        listOfPowerUpCards.addAll(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck());

         int widthCard;
         int heightCard;

         if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
             widthCard = (int) (mainFrame.getFrame().getSize().getWidth() / 15);
             heightCard = (int) (widthCard * 1.70);
         } else {
             heightCard = (int) (mainFrame.getFrame().getSize().getHeight() / 5);
             widthCard = (int) (heightCard * 0.59);
         }



        JTextArea message = new JTextArea(receveidEvent.getMessageForInvolved());
         message.setBackground(SystemColor.menu);
         message.setEditable(false);
         GridBagConstraints gbcLabelMessage = new GridBagConstraints();
         gbcLabelMessage.insets = new Insets(5, 5, 5, 5);
         gbcLabelMessage.gridx = 0;
         gbcLabelMessage.gridy = 0;
         gbcLabelMessage.gridwidth = listOfPowerUpCards.size();
         contentPanel.add(message, gbcLabelMessage);

         contentPanel.add(message,gbcLabelMessage);

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.insets = new Insets(10, 10, 10, 10);

         ImageIcon icon;
         Image image;
         String folderPath = "resources/powerupcards/";

        while(!listOfPowerUpCards.isEmpty()){
            JLabel card = new JLabel();
            String nameOfCard = listOfPowerUpCards.get(0).getName();
            String color = listOfPowerUpCards.get(0).getColor().toString();
            icon = new ImageIcon(folderPath.concat(nameOfCard+ " ").concat(color+".png"));
            image = icon.getImage().getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            card.setIcon(icon);
            contentPanel.add(card,gbc);
            card.addMouseListener(new ListenerForPowerUpCard(card, mainFrame, gbc.gridx+1));

            gbc.gridy++;

            JButton button = new JButton("Discard");
            button.setActionCommand(nameOfCard.concat(" "+ color));
            button.addActionListener(new ListenerForDiscardPowerUp(mainFrame, listOfPowerUpCards.get(0)));
            contentPanel.add(button,gbc);

            gbc.gridy=1;
            gbc.gridx++;
            listOfPowerUpCards.remove(0);

        }

        dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

         dialog.setVisible(true);


     }

     private class ListenerForDiscardPowerUp implements ActionListener {
         private MainFrame mainFrame;
         private ServerEvent.AliasCard card;

          ListenerForDiscardPowerUp(MainFrame mainFrame, ServerEvent.AliasCard aliasCard) {
                this.mainFrame = mainFrame;
                this.card = aliasCard;
          }

         @Override
         public void actionPerformed(ActionEvent e) {
             DiscardPowerUpCardToSpawnEvent event = new DiscardPowerUpCardToSpawnEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
             event.setPowerUpCard(card);
             mainFrame.getRemoteView().notify(event);
             dialog.setVisible(false);
         }
     }
 }
