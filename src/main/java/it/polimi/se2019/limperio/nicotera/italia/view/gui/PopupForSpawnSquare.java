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
        int topBottomInset = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
        int leftRightInset = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);

        contentPane.setBorder(new EmptyBorder(topBottomInset, leftRightInset, topBottomInset, leftRightInset));
        popup.getContentPane().add(contentPane);
        contentPane.setLayout(new GridBagLayout());


        JPanel panelForPlayers = new JPanel();
        GridBagConstraints gbcPanelForPlayers = new GridBagConstraints();
        gbcPanelForPlayers.insets = new Insets(0, 0, 0, leftRightInset*2);
        gbcPanelForPlayers.fill = GridBagConstraints.BOTH;
        gbcPanelForPlayers.gridx = 0;
        gbcPanelForPlayers.gridy = 0;
        contentPane.add(panelForPlayers, gbcPanelForPlayers);
        panelForPlayers.setLayout(new GridBagLayout());

        addListOfPlayersInSquare(panelForPlayers, nicknamesOfPlayersOnThisSquare, mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(), mainFrame);


        PanelForWeapons panelForWeapons = new PanelForWeapons(mainFrame, listOfWeapon, null, null);

        GridBagConstraints gbcPanelForWeapons = new GridBagConstraints();
        gbcPanelForWeapons.insets = new Insets(topBottomInset/4, leftRightInset/2, 0, leftRightInset/2);
        gbcPanelForWeapons.fill = GridBagConstraints.BOTH;
        gbcPanelForWeapons.gridx = 1;
        gbcPanelForWeapons.gridy = 0;
        contentPane.add(panelForWeapons.getContentPane(), gbcPanelForWeapons);

        popup.pack();
        popup.setLocation((int) (locationOfFrame.getX() + dimensionOfFrame.getWidth() - popup.getWidth()) / 2,
                (int) (locationOfFrame.getY() + dimensionOfFrame.getHeight() - popup.getHeight()) / 2);

     }

}
