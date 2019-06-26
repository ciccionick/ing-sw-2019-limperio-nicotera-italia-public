package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToDiscard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestSelectionWeaponToReload;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class PanelForWeapons {
    private JPanel contentPane = new JPanel();
    private MainFrame mainFrame;
    private JLabel weapon1;
    private JLabel weapon2;
    private JLabel weapon3;
    private ServerEvent event;


     PanelForWeapons(MainFrame mainFrame, ArrayList<ServerEvent.AliasCard> listOfWeapons, ServerEvent receivedEvent, PopupForChooseWeaponCard popupForChooseW) {
         this.mainFrame = mainFrame;
         contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
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


        String folderPath = "resources/weapons/";


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


        weapon1 = new JLabel("");
        ImageIcon imageIcon;
        if(listOfWeapons!=null && listOfWeapons.isEmpty()) {
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(20, 20, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = gridy;
            this.getContentPane().add(weapon1, gbcWeapon1);
        }
        else if(listOfWeapons != null){
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(0).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(20  , 20, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = gridy;
            this.getContentPane().add(weapon1, gbcWeapon1);
            weapon1.addMouseListener(new WeaponListener(listOfWeapons.get(0).getName(), popupForChooseW));

            JTextArea descriptionOfWeapon1 = new JTextArea();
            descriptionOfWeapon1.setBackground(SystemColor.menu);
            descriptionOfWeapon1.setLineWrap(false);
            descriptionOfWeapon1.setEditable(false);
            descriptionOfWeapon1.setText(listOfWeapons.get(0).getDescription());
            GridBagConstraints gbcDescriptionOfWeapon1 = new GridBagConstraints();
            gbcDescriptionOfWeapon1.insets = new Insets(5, 20, 10, 15);
            gbcDescriptionOfWeapon1.anchor = GridBagConstraints.CENTER;
            gbcDescriptionOfWeapon1.fill = GridBagConstraints.BOTH;
            gbcDescriptionOfWeapon1.gridx = 0;
            gbcDescriptionOfWeapon1.gridy = gridy+1;
            this.getContentPane().add(descriptionOfWeapon1, gbcDescriptionOfWeapon1);

        }



        weapon2 = new JLabel("");

        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        if(listOfWeapons!=null && listOfWeapons.size()<2){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(20, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = gridy;
            this.getContentPane().add(weapon2, gbcWeapon2);
        }
        else if(listOfWeapons!=null) {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(1).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(20, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = gridy;
            this.getContentPane().add(weapon2, gbcWeapon2);
            weapon2.addMouseListener(new WeaponListener(listOfWeapons.get(1).getName(),popupForChooseW));


            JTextArea descriptionForWeapon2 = new JTextArea();
            descriptionForWeapon2.setBackground(SystemColor.menu);
            descriptionForWeapon2.setLineWrap(false);
            descriptionForWeapon2.setEditable(false);
            descriptionForWeapon2.setText(listOfWeapons.get(1).getDescription());
            GridBagConstraints gbcDescriptionForWeapon2 = new GridBagConstraints();
            gbcDescriptionForWeapon2.insets = new Insets(5, 0, 10, 15);
            gbcDescriptionForWeapon2.fill = GridBagConstraints.BOTH;
            gbcDescriptionForWeapon2.anchor = GridBagConstraints.CENTER;
            gbcDescriptionForWeapon2.gridx = 1;
            gbcDescriptionForWeapon2.gridy = gridy+1;
            this.getContentPane().add(descriptionForWeapon2, gbcDescriptionForWeapon2);
        }


        weapon3 = new JLabel("");
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        if(listOfWeapons!=null && listOfWeapons.size()<3){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(20, 0, 5, 20);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = gridy;
            this.getContentPane().add(weapon3, gbcWeapon3);

        }
        else if(listOfWeapons!=null){
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(2).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(20, 0, 20, 20);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = gridy;
            this.getContentPane().add(weapon3, gbcWeapon3);
            weapon3.addMouseListener(new WeaponListener(listOfWeapons.get(2).getName(),popupForChooseW));


            JTextArea descriptionWeapon3 = new JTextArea();
            descriptionWeapon3.setBackground(SystemColor.menu);
            descriptionWeapon3.setLineWrap(false);
            descriptionWeapon3.setEditable(false);
            descriptionWeapon3.setText(listOfWeapons.get(2).getDescription());
            GridBagConstraints gbcDescriptionWeapon3 = new GridBagConstraints();
            gbcDescriptionWeapon3.insets = new Insets(5, 0, 10, 20);
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
            buttonToStopReload.addActionListener(new WeaponListener(null, popupForChooseW));
            buttonToStopReload.setActionCommand("ReloadRejected");
            contentPane.add(buttonToStopReload,gbcButton);
        }

    }

     JPanel getContentPane() {
        return contentPane;
    }

    class WeaponListener implements MouseListener, ActionListener {
        private String nameOfWeaponCard;
        private PopupForChooseWeaponCard popup;

         WeaponListener(String nameOfWeaponCard, PopupForChooseWeaponCard popup) {
            this.nameOfWeaponCard = nameOfWeaponCard;
            this.popup = popup;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
             if(popup!=null) {
                 if(event.isRequestForChooseAWeaponToCatch()) {
                     SelectionWeaponToCatch newEvent = new SelectionWeaponToCatch("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                     newEvent.setNameOfWeaponCard(nameOfWeaponCard);
                     popup.getPopupForChooseW().setVisible(false);
                     mainFrame.getRemoteView().notify(newEvent);
                 }
                 if(event.isRequestToDiscardWeaponCard()){
                     SelectionWeaponToDiscard selectionWeaponToDiscard = new SelectionWeaponToDiscard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(), nameOfWeaponCard, popup.getNameOfCardToStoreForDiscardEvent());
                     popup.getPopupForChooseW().setVisible(false);
                     mainFrame.getRemoteView().notify(selectionWeaponToDiscard);
                 }
                 if(event.isRequestSelectionWeaponToReload()){
                     SelectionWeaponToReload selectionWeaponToReload = new SelectionWeaponToReload("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                     selectionWeaponToReload.setNameOfWeaponCardToReload(nameOfWeaponCard);
                     popup.getPopupForChooseW().setVisible(false);
                     mainFrame.getRemoteView().notify(selectionWeaponToReload);
                 }
             }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //not implemented
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //not implemented
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //not implemented
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //not implemented
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SelectionWeaponToReload selectionWeaponToReload = new SelectionWeaponToReload("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
            selectionWeaponToReload.setNameOfWeaponCardToReload(null);
            popup.getPopupForChooseW().setVisible(false);
            mainFrame.getRemoteView().notify(selectionWeaponToReload);

        }
    }


}
