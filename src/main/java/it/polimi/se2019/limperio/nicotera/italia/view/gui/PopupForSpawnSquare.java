package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

 class PopupForSpawnSquare extends PopupForSquare {

     PopupForSpawnSquare(ArrayList<String> nicknamesOfPlayersOnThisSquare, Point locationOfFrame,  Dimension dimensionOfFrame, ArrayList<ServerEvent.AliasCard> listOfWeapon) {

        int widthCard;
        int heightCard;

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
        popup.setAutoRequestFocus(false);
        popup.setUndecorated(true);
        popup.setSize((int)((int)dimensionOfFrame.getWidth()/1.92), (int)((int)dimensionOfFrame.getHeight()/1.54));
        popup.setLocation((int) (locationOfFrame.getX() + dimensionOfFrame.getWidth() - popup.getWidth()) / 2,
                (int) (locationOfFrame.getY() + dimensionOfFrame.getHeight() - popup.getHeight()) / 2);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        popup.getContentPane().add(contentPane);
        contentPane.setLayout(new GridBagLayout());


        JPanel panelForPlayers = new JPanel();
        GridBagConstraints gbcPanelForPlayers = new GridBagConstraints();
        gbcPanelForPlayers.insets = new Insets(0, 0, 0, 40);
        gbcPanelForPlayers.fill = GridBagConstraints.BOTH;
        gbcPanelForPlayers.gridx = 0;
        gbcPanelForPlayers.gridy = 0;
        contentPane.add(panelForPlayers, gbcPanelForPlayers);
        panelForPlayers.setLayout(new GridBagLayout());

        addListOfPlayersInSquare(panelForPlayers, nicknamesOfPlayersOnThisSquare);


        JPanel panelForWeapons = new JPanel();
        GridBagConstraints gbcPanelForWeapons = new GridBagConstraints();
        gbcPanelForWeapons.insets = new Insets(5, 5, 0, 5);
        gbcPanelForWeapons.fill = GridBagConstraints.BOTH;
        gbcPanelForWeapons.gridx = 1;
        gbcPanelForWeapons.gridy = 0;
        contentPane.add(panelForWeapons, gbcPanelForWeapons);
        panelForWeapons.setLayout(new GridBagLayout());


        JLabel weapon1 = new JLabel("");
        ImageIcon imageIcon;
        if(listOfWeapon.isEmpty()) {
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(0, 0, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = 0;
            panelForWeapons.add(weapon1, gbcWeapon1);
        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapon.get(0).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon1.setIcon(imageIcon);
            GridBagConstraints gbcWeapon1 = new GridBagConstraints();
            gbcWeapon1.insets = new Insets(0, 0, 5, 15);
            gbcWeapon1.gridx = 0;
            gbcWeapon1.gridy = 0;
            panelForWeapons.add(weapon1, gbcWeapon1);

            JTextArea descriptionOfWeapon1 = new JTextArea();
            descriptionOfWeapon1.setBackground(SystemColor.menu);
            descriptionOfWeapon1.setLineWrap(true);
            descriptionOfWeapon1.setText(listOfWeapon.get(0).getDescription());
            descriptionOfWeapon1.setTabSize(10);
            GridBagConstraints gbcDescriptionOfWeapon1 = new GridBagConstraints();
            gbcDescriptionOfWeapon1.insets = new Insets(5, 0, 0, 15);
            gbcDescriptionOfWeapon1.fill = GridBagConstraints.BOTH;
            gbcDescriptionOfWeapon1.gridx = 0;
            gbcDescriptionOfWeapon1.gridy = 1;
            panelForWeapons.add(descriptionOfWeapon1, gbcDescriptionOfWeapon1);
        }



        JLabel weapon2 = new JLabel("");

        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        if(listOfWeapon.size()<2){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(0, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = 0;
            panelForWeapons.add(weapon2, gbcWeapon2);
        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapon.get(1).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon2.setIcon(imageIcon);
            gbcWeapon2.insets = new Insets(0, 0, 5, 15);
            gbcWeapon2.gridx = 1;
            gbcWeapon2.gridy = 0;
            panelForWeapons.add(weapon2, gbcWeapon2);


            JTextArea descriptionForWeapon2 = new JTextArea();
            descriptionForWeapon2.setBackground(SystemColor.menu);
            descriptionForWeapon2.setLineWrap(true);
            descriptionForWeapon2.setText(listOfWeapon.get(1).getDescription());
            GridBagConstraints gbcDescriptionForWeapon2 = new GridBagConstraints();
            descriptionForWeapon2.setWrapStyleWord(true);
            descriptionForWeapon2.setColumns(10);
            gbcDescriptionForWeapon2.insets = new Insets(0, 0, 0, 15);
            gbcDescriptionForWeapon2.fill = GridBagConstraints.BOTH;
            gbcDescriptionForWeapon2.gridx = 1;
            gbcDescriptionForWeapon2.gridy = 1;
            panelForWeapons.add(descriptionForWeapon2, gbcDescriptionForWeapon2);
        }


        JLabel weapon3 = new JLabel("");
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        if(listOfWeapon.size()<3){
            imageIcon = new ImageIcon(folderPath.concat("noCard.png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(0, 0, 5, 15);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = 0;
            panelForWeapons.add(weapon3, gbcWeapon3);
        }
        else {
            imageIcon = new ImageIcon(folderPath.concat(listOfWeapon.get(2).getName()).concat(".png"));
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            weapon3.setIcon(imageIcon);
            gbcWeapon3.insets = new Insets(0, 0, 5, 15);
            gbcWeapon3.gridx = 2;
            gbcWeapon3.gridy = 0;
            panelForWeapons.add(weapon3, gbcWeapon3);


            JTextArea descriptionWeapon3 = new JTextArea();
            descriptionWeapon3.setBackground(SystemColor.menu);
            descriptionWeapon3.setLineWrap(true);
            descriptionWeapon3.setText(listOfWeapon.get(2).getDescription());
            GridBagConstraints gbcDescriptionWeapon3 = new GridBagConstraints();
            gbcDescriptionWeapon3.insets = new Insets(5, 0, 0, 15);
            gbcDescriptionWeapon3.fill = GridBagConstraints.BOTH;
            gbcDescriptionWeapon3.gridx = 2;
            gbcDescriptionWeapon3.gridy = 1;
            panelForWeapons.add(descriptionWeapon3, gbcDescriptionWeapon3);

        }

    }

}
