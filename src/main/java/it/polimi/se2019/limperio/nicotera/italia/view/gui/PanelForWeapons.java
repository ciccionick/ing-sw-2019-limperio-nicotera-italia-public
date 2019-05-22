package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionWeaponToDiscard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

 class PanelForWeapons {
    private JPanel contentPane = new JPanel();
    private MainFrame mainFrame;
    private JLabel weapon1;
    private JLabel weapon2;
    private JLabel weapon3;


     PanelForWeapons(MainFrame mainFrame, ArrayList<ServerEvent.AliasCard> listOfWeapons, PopupForChooseWeaponCard popupForChooseW) {

         this.mainFrame = mainFrame;
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


        String folderPath = "resources/weapons/";


        this.getContentPane().setLayout(new GridBagLayout());


        weapon1 = new JLabel("");
        ImageIcon imageIcon;
        if(listOfWeapons.isEmpty()) {
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(0, 0, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = 0;
            this.getContentPane().add(weapon1, gbcWeapon1);
        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(0).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(0, 0, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = 0;
            this.getContentPane().add(weapon1, gbcWeapon1);
            weapon1.addMouseListener(new WeaponListener(listOfWeapons.get(0).getName(), popupForChooseW));

            JTextArea descriptionOfWeapon1 = new JTextArea();
            descriptionOfWeapon1.setBackground(SystemColor.menu);
            descriptionOfWeapon1.setLineWrap(true);
            descriptionOfWeapon1.setEditable(false);
            descriptionOfWeapon1.setText(listOfWeapons.get(0).getDescription());
            descriptionOfWeapon1.setTabSize(10);
            GridBagConstraints gbcDescriptionOfWeapon1 = new GridBagConstraints();
            gbcDescriptionOfWeapon1.insets = new Insets(5, 0, 0, 15);
            gbcDescriptionOfWeapon1.fill = GridBagConstraints.BOTH;
            gbcDescriptionOfWeapon1.gridx = 0;
            gbcDescriptionOfWeapon1.gridy = 1;
            this.getContentPane().add(descriptionOfWeapon1, gbcDescriptionOfWeapon1);

        }



        weapon2 = new JLabel("");

        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        if(listOfWeapons.size()<2){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(0, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = 0;
            this.getContentPane().add(weapon2, gbcWeapon2);
        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(1).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(0, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = 0;
            this.getContentPane().add(weapon2, gbcWeapon2);
            weapon2.addMouseListener(new WeaponListener(listOfWeapons.get(1).getName(),popupForChooseW));


            JTextArea descriptionForWeapon2 = new JTextArea();
            descriptionForWeapon2.setBackground(SystemColor.menu);
            descriptionForWeapon2.setLineWrap(true);
            descriptionForWeapon2.setEditable(false);
            descriptionForWeapon2.setText(listOfWeapons.get(1).getDescription());
            GridBagConstraints gbcDescriptionForWeapon2 = new GridBagConstraints();
            descriptionForWeapon2.setWrapStyleWord(true);
            descriptionForWeapon2.setColumns(10);
            gbcDescriptionForWeapon2.insets = new Insets(0, 0, 0, 15);
            gbcDescriptionForWeapon2.fill = GridBagConstraints.BOTH;
            gbcDescriptionForWeapon2.gridx = 1;
            gbcDescriptionForWeapon2.gridy = 1;
            this.getContentPane().add(descriptionForWeapon2, gbcDescriptionForWeapon2);
        }


        weapon3 = new JLabel("");
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        if(listOfWeapons.size()<3){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(0, 0, 5, 15);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = 0;
            this.getContentPane().add(weapon3, gbcWeapon3);

        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapons.get(2).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(0, 0, 5, 15);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = 0;
            this.getContentPane().add(weapon3, gbcWeapon3);
            weapon3.addMouseListener(new WeaponListener(listOfWeapons.get(2).getName(),popupForChooseW));


            JTextArea descriptionWeapon3 = new JTextArea();
            descriptionWeapon3.setBackground(SystemColor.menu);
            descriptionWeapon3.setLineWrap(true);
            descriptionWeapon3.setEditable(false);
            descriptionWeapon3.setText(listOfWeapons.get(2).getDescription());
            GridBagConstraints gbcDescriptionWeapon3 = new GridBagConstraints();
            gbcDescriptionWeapon3.insets = new Insets(5, 0, 0, 15);
            gbcDescriptionWeapon3.fill = GridBagConstraints.BOTH;
            gbcDescriptionWeapon3.gridx = 2;
            gbcDescriptionWeapon3.gridy = 1;
            this.getContentPane().add(descriptionWeapon3, gbcDescriptionWeapon3);

        }

    }

     JPanel getContentPane() {
        return contentPane;
    }

    class WeaponListener implements MouseListener{
        private String nameOfWeaponCard;
        private PopupForChooseWeaponCard popup = null;

         WeaponListener(String nameOfWeaponCard, PopupForChooseWeaponCard popup) {
            this.nameOfWeaponCard = nameOfWeaponCard;
            this.popup = popup;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
             if(popup!=null) {
                 if(popup.getNameOfCardToStoreForDiscardEvent()==null) {
                     SelectionWeaponToCatch newEvent = new SelectionWeaponToCatch("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                     newEvent.setNameOfWeaponCard(nameOfWeaponCard);
                     popup.getPopupForChooseW().setVisible(false);
                     mainFrame.getRemoteView().notify(newEvent);
                 }
                 else{
                     SelectionWeaponToDiscard selectionWeaponToDiscard = new SelectionWeaponToDiscard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(), nameOfWeaponCard, popup.getNameOfCardToStoreForDiscardEvent());
                     popup.getPopupForChooseW().setVisible(false);
                     mainFrame.getRemoteView().notify(selectionWeaponToDiscard);
                 }
             }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }





}
