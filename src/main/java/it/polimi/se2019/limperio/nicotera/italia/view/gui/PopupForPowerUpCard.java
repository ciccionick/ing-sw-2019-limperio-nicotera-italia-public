package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

 class PopupForPowerUpCard {

    private JDialog popupForPC;
    private MainFrame mainFrame;

     PopupForPowerUpCard(MainFrame mainFrame, int numOfCard) {
         this.mainFrame = mainFrame;
         JPanel contentPane = new JPanel(new GridBagLayout());
         popupForPC = new JDialog(mainFrame.getFrame());
         popupForPC.setUndecorated(true);
         popupForPC.getContentPane().setLayout(new BorderLayout(0, 0));
         contentPane.setBorder(new EmptyBorder(10,10,10,10));
         popupForPC.getContentPane().add(contentPane);

         int widthCard;
         int heightCard;

         if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
             widthCard = (int) (mainFrame.getFrame().getSize().getWidth() / 12);
             heightCard = (int) (widthCard * 1.70);
         } else {
             heightCard = (int) (mainFrame.getFrame().getSize().getHeight() / 3);
             widthCard = (int) (heightCard * 0.59);
         }

         JPanel panelForImage = new JPanel();
         GridBagConstraints gbcPanelForImage = new GridBagConstraints();
         gbcPanelForImage.insets = new Insets(0, 0, 0, 5);
         gbcPanelForImage.gridx = 0;
         gbcPanelForImage.gridy = 0;
         contentPane.add(panelForImage, gbcPanelForImage);

         JLabel powerUpCardIcon = new JLabel("");
         String path = "resources/powerupcards/";
         path = path.concat(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName());
         path = path.concat(" ");
         path = path.concat(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getColor().toString().toLowerCase());
         ImageIcon imageIcon = new ImageIcon(path.concat(".png"));
         Image image = imageIcon.getImage();
         Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         powerUpCardIcon.setIcon(imageIcon);
         powerUpCardIcon.setHorizontalTextPosition(SwingConstants.CENTER);
         powerUpCardIcon.setHorizontalAlignment(SwingConstants.CENTER);
         panelForImage.add(powerUpCardIcon);

         JPanel panelForDescription = new JPanel();
         GridBagConstraints gbcPanelForDescription = new GridBagConstraints();
         gbcPanelForDescription.insets = new Insets(0, 0, 5, 0);
         gbcPanelForDescription.fill = GridBagConstraints.HORIZONTAL;
         gbcPanelForDescription.gridx = 1;
         gbcPanelForDescription.gridy = 0;
         contentPane.add(panelForDescription, gbcPanelForDescription);

         JTextArea descriptionOfCard = new JTextArea();
         descriptionOfCard.setBackground(SystemColor.menu);
         descriptionOfCard.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
         descriptionOfCard.setColumns(20);
         descriptionOfCard.setLineWrap(false);
         descriptionOfCard.setText(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard - 1).getDescription());
         descriptionOfCard.setEditable(false);
         panelForDescription.add(descriptionOfCard);

         popupForPC.pack();
         popupForPC.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - popupForPC.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - popupForPC.getHeight()) / 2);



     }

     public JDialog getPopupForPC() {
         return popupForPC;
     }
 }
