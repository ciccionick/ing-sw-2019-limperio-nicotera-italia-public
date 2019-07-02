package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Handles the creation of the dialog that show information about a power up card.
 * @author Pietro L'Imperio
 */
 class PopupForPowerUpCard {

    /**
     * Dialog that is created in the constructor.
     */
    private JDialog popupForPC;

    /**
     * Constructor that creates the dialog.
     * @param mainFrame Reference of main frame.
     * @param numOfCard The number that represent the position of the card in the deck.
     */
     PopupForPowerUpCard(MainFrame mainFrame, int numOfCard) {
         JPanel contentPane = new JPanel(new GridBagLayout());
         popupForPC = new JDialog(mainFrame.getFrame());
         popupForPC.setUndecorated(true);
         popupForPC.setAutoRequestFocus(true);
         popupForPC.getContentPane().setLayout(new BorderLayout());
         int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 10);
         int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10);
         contentPane.setBorder(new EmptyBorder(topBottomBorder,leftRightBorder,topBottomBorder,leftRightBorder));
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
         gbcPanelForImage.insets = new Insets(0, 0, 0, leftRightBorder/2);
         gbcPanelForImage.gridx = 0;
         gbcPanelForImage.gridy = 0;
         contentPane.add(panelForImage, gbcPanelForImage);

         JLabel powerUpCardIcon = new JLabel("");
         String path = "/powerupcards/";
         path = path.concat(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getName());
         path = path.concat(" ");
         path = path.concat(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(numOfCard-1).getColor().toString().toLowerCase());
         ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(path.concat(".png")));
         Image image = imageIcon.getImage();
         Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         powerUpCardIcon.setIcon(imageIcon);
         powerUpCardIcon.setHorizontalTextPosition(SwingConstants.CENTER);
         powerUpCardIcon.setHorizontalAlignment(SwingConstants.CENTER);
         panelForImage.add(powerUpCardIcon);

         JPanel panelForDescription = new JPanel();
         GridBagConstraints gbcPanelForDescription = new GridBagConstraints();
         gbcPanelForDescription.insets = new Insets(0, 0, topBottomBorder/2, 0);
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

      JDialog getPopupForPC() {
         return popupForPC;
     }
 }
