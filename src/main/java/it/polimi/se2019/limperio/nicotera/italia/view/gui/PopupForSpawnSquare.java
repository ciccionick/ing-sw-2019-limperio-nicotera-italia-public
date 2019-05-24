package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

 class PopupForSpawnSquare extends PopupForSquare {

     PopupForSpawnSquare(ArrayList<String> nicknamesOfPlayersOnThisSquare, MainFrame mainFrame, ArrayList<ServerEvent.AliasCard> listOfWeapon) {

        Point locationOfFrame = mainFrame.getFrame().getLocation();
        Dimension dimensionOfFrame = mainFrame.getFrame().getSize();
        popup.setAutoRequestFocus(false);
        popup.setUndecorated(true);
        popup.setSize((int)((int)dimensionOfFrame.getWidth()/1.92), (int)((int)dimensionOfFrame.getHeight()/1.2));
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

        addListOfPlayersInSquare(panelForPlayers, nicknamesOfPlayersOnThisSquare, mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());


        PanelForWeapons panelForWeapons = new PanelForWeapons(mainFrame, listOfWeapon, null);

        GridBagConstraints gbcPanelForWeapons = new GridBagConstraints();
        gbcPanelForWeapons.insets = new Insets(5, 10, 0, 10);
        gbcPanelForWeapons.fill = GridBagConstraints.BOTH;
        gbcPanelForWeapons.gridx = 1;
        gbcPanelForWeapons.gridy = 0;
        contentPane.add(panelForWeapons.getContentPane(), gbcPanelForWeapons);


    }

}
