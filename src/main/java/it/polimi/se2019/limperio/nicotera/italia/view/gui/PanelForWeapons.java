package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of a panel that shows weapons. This panel is used in the popup for spawn square, in the popup to grab or discard weapon and in the popup to reload weapons.
 * @author Pietro L'Imperio.
 */
class PanelForWeapons {
    /**
     * The JPanel where weapons are shown.
     */
    private JPanel contentPane = new JPanel();
    /**
     * The JLabel that represents the first of the maximum three weapons.
     */
    private JLabel weapon1;
    /**
     * The JLabel that represents the second of the maximum three weapons.
     */
    private JLabel weapon2;
    /**
     * The JLabel that represents the third of the maximum three weapons.
     */
    private JLabel weapon3;
    /**
     * The event received by the server.
     */
    private ServerEvent event;

    /**
     * The constructor of the class where the content panel is created.
     * @param mainFrame The reference of the main frame.
     * @param listOfWeapons The list of weapons to show.
     * @param receivedEvent The event received by server
     * @param popupForChooseW The popup that contains the dialog that add to his contentPane the panel created in this constructor.
     */
     PanelForWeapons(MainFrame mainFrame, ArrayList<ServerEvent.AliasCard> listOfWeapons, ServerEvent receivedEvent, PopupForChooseWeaponCard popupForChooseW) {
         Font font = new Font(Font.SANS_SERIF,Font.PLAIN,10);
         int topBottomBorder  = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 50);
         int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 50);
         if(event!=null && event.isRequestSelectionWeaponToReload())
            contentPane.setBorder(new EmptyBorder(topBottomBorder , leftRightBorder, topBottomBorder, leftRightBorder));
        int widthCard;
        int heightCard;
        Dimension dimensionOfFrame = mainFrame.getFrame().getSize();

        if(dimensionOfFrame.getWidth()/dimensionOfFrame.getHeight()<1.80) {
            widthCard = (int) (dimensionOfFrame.getWidth()/12);
            heightCard = (int) (widthCard*1.70);
        }
        else
        {
            heightCard = (int) (dimensionOfFrame.getHeight()/3);
            widthCard = (int) (heightCard*0.59);
        }

        if(listOfWeapons==null){
            this.event = receivedEvent;
            if(receivedEvent.isRequestForChooseAWeaponToCatch())
                listOfWeapons = ((RequestForChooseAWeaponToCatch)receivedEvent).getWeaponsAvailableToCatch();
            if(receivedEvent.isRequestSelectionWeaponToReload())
                listOfWeapons = ((RequestSelectionWeaponToReload)receivedEvent).getWeaponCardsAffordable();
            if(receivedEvent.isRequestToDiscardWeaponCard())
                listOfWeapons = mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck();
        }


        String folderPath = "/weapons/";


        this.getContentPane().setLayout(new GridBagLayout());
        int gridy;
        if(receivedEvent!=null && receivedEvent.isRequestSelectionWeaponToReload()){
            GridBagConstraints gbcText = new GridBagConstraints();
            gbcText.gridx = 0;
            gbcText.gridy = 0;
            if(receivedEvent.isRequestSelectionWeaponToReload())
                gbcText.gridwidth = 4;
            else
                gbcText.gridwidth = 3;
            JTextArea text = new JTextArea();
            text.setText(receivedEvent.getMessageForInvolved());
            text.setBackground(SystemColor.menu);
            text.setEditable(false);
            contentPane.add(text,gbcText);
            gridy=1;
        }
        else
            gridy=0;

        int insetTopForWeapon = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
        int insetTopForDescription = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 5);
        int insetBottomForWeapon = insetTopForWeapon/4;
        int insetBottomForDescription = insetTopForDescription*2;
        int insetLeftForWeapon = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);
        int insetRigthForWeapon = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 15);

        weapon1 = new JLabel("");
        ImageIcon imageIcon;
        if(listOfWeapons!=null && listOfWeapons.isEmpty()) {
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("noCard.png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(insetTopForWeapon, insetLeftForWeapon, insetBottomForWeapon, insetRigthForWeapon);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = gridy;
            this.getContentPane().add(weapon1, gbcWeapon1);
        }
        else if(listOfWeapons != null){
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat(listOfWeapons.get(0).getName()).concat(".png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(insetTopForWeapon  , insetLeftForWeapon, insetBottomForWeapon, insetRigthForWeapon);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = gridy;
            this.getContentPane().add(weapon1, gbcWeapon1);
            weapon1.addMouseListener(new ListenerForChooseOfWeapon(listOfWeapons.get(0).getName(), popupForChooseW, mainFrame, receivedEvent));

            JTextArea descriptionOfWeapon1 = new JTextArea();
            descriptionOfWeapon1.setBackground(SystemColor.menu);
            descriptionOfWeapon1.setLineWrap(false);
            descriptionOfWeapon1.setEditable(false);
            descriptionOfWeapon1.setText(listOfWeapons.get(0).getDescription());
            descriptionOfWeapon1.setFont(font);
            GridBagConstraints gbcDescriptionOfWeapon1 = new GridBagConstraints();
            gbcDescriptionOfWeapon1.insets = new Insets(insetTopForDescription, insetLeftForWeapon, insetBottomForDescription, insetRigthForWeapon);
            gbcDescriptionOfWeapon1.anchor = GridBagConstraints.CENTER;
            gbcDescriptionOfWeapon1.fill = GridBagConstraints.BOTH;
            gbcDescriptionOfWeapon1.gridx = 0;
            gbcDescriptionOfWeapon1.gridy = gridy+1;
            this.getContentPane().add(descriptionOfWeapon1, gbcDescriptionOfWeapon1);

        }



        weapon2 = new JLabel("");

        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        if(listOfWeapons!=null && listOfWeapons.size()<2){
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("noCard.png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(insetTopForWeapon, 0, insetBottomForWeapon, insetRigthForWeapon);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = gridy;
            this.getContentPane().add(weapon2, gbcWeapon2);
        }
        else if(listOfWeapons!=null) {
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat(listOfWeapons.get(1).getName()).concat(".png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(insetTopForWeapon, 0, insetBottomForWeapon, insetRigthForWeapon);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = gridy;
            this.getContentPane().add(weapon2, gbcWeapon2);
            weapon2.addMouseListener(new ListenerForChooseOfWeapon(listOfWeapons.get(1).getName(),popupForChooseW, mainFrame, receivedEvent));


            JTextArea descriptionForWeapon2 = new JTextArea();
            descriptionForWeapon2.setBackground(SystemColor.menu);
            descriptionForWeapon2.setLineWrap(false);
            descriptionForWeapon2.setEditable(false);
            descriptionForWeapon2.setText(listOfWeapons.get(1).getDescription());
            descriptionForWeapon2.setFont(font);
            GridBagConstraints gbcDescriptionForWeapon2 = new GridBagConstraints();
            gbcDescriptionForWeapon2.insets = new Insets(insetTopForDescription, 0, insetBottomForDescription, insetRigthForWeapon);
            gbcDescriptionForWeapon2.fill = GridBagConstraints.BOTH;
            gbcDescriptionForWeapon2.anchor = GridBagConstraints.CENTER;
            gbcDescriptionForWeapon2.gridx = 1;
            gbcDescriptionForWeapon2.gridy = gridy+1;
            this.getContentPane().add(descriptionForWeapon2, gbcDescriptionForWeapon2);
        }


        weapon3 = new JLabel("");
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        if(listOfWeapons!=null && listOfWeapons.size()<3){
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("noCard.png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(insetTopForWeapon, 0, insetBottomForWeapon, insetLeftForWeapon);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = gridy;
            this.getContentPane().add(weapon3, gbcWeapon3);

        }
        else if(listOfWeapons!=null){
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat(listOfWeapons.get(2).getName()).concat(".png")));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(insetTopForWeapon, 0, insetBottomForWeapon, insetLeftForWeapon);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = gridy;
            this.getContentPane().add(weapon3, gbcWeapon3);
            weapon3.addMouseListener(new ListenerForChooseOfWeapon(listOfWeapons.get(2).getName(),popupForChooseW, mainFrame, receivedEvent));


            JTextArea descriptionWeapon3 = new JTextArea();
            descriptionWeapon3.setBackground(SystemColor.menu);
            descriptionWeapon3.setLineWrap(false);
            descriptionWeapon3.setEditable(false);
            descriptionWeapon3.setText(listOfWeapons.get(2).getDescription());
            descriptionWeapon3.setFont(font);
            GridBagConstraints gbcDescriptionWeapon3 = new GridBagConstraints();
            gbcDescriptionWeapon3.insets = new Insets(insetTopForDescription, 0, insetBottomForDescription, insetLeftForWeapon);
            gbcDescriptionWeapon3.fill = GridBagConstraints.BOTH;
            gbcDescriptionWeapon3.anchor = GridBagConstraints.CENTER;
            gbcDescriptionWeapon3.gridx = 2;
            gbcDescriptionWeapon3.gridy = gridy+1;
            this.getContentPane().add(descriptionWeapon3, gbcDescriptionWeapon3);

        }

        if(receivedEvent!=null && event.isRequestSelectionWeaponToReload()){
            JButton buttonToStopReload = new JButton();
            buttonToStopReload.setText("Don't want to reload");
            GridBagConstraints gbcButton = new GridBagConstraints();
            gbcButton.gridx = 4;
            gbcButton.gridy = 1;
            buttonToStopReload.addActionListener(new ListenerForChooseOfWeapon(null, popupForChooseW, mainFrame, receivedEvent));
            buttonToStopReload.setActionCommand("ReloadRejected");
            contentPane.add(buttonToStopReload,gbcButton);
        }
    }

     JPanel getContentPane() {
        return contentPane;
    }

}
