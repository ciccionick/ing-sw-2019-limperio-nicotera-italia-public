package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


class PopupForSquare {

    JLabel labelPlayer1;
    JLabel labelPlayer2;
    JLabel labelPlayer3;
    JLabel labelPlayer4;
    JLabel labelPlayer5;


    JDialog popup = new JDialog();
    final JPanel contentPane = new JPanel();


    void addListOfPlayersInSquare(JPanel panelForPlayers, ArrayList<String> nicknamesOfPlayersOnThisSquare){
       GridBagConstraints gbcTextForPlayers = new GridBagConstraints();
       JLabel textForPlayers;
       if (nicknamesOfPlayersOnThisSquare.isEmpty()) {
          textForPlayers = new JLabel("<html> No players <br/> on this square! </html>");
          gbcTextForPlayers.insets = new Insets(20, 0, 0, 0);
          gbcTextForPlayers.gridx = 0;
          gbcTextForPlayers.gridy = 0;
       } else {
          textForPlayers = new JLabel("<html>Players on this <br/> square: </html>");
          textForPlayers.setHorizontalAlignment(SwingConstants.CENTER);
          gbcTextForPlayers.insets = new Insets(0, 0, 10, 0);
          gbcTextForPlayers.gridx = 0;
          gbcTextForPlayers.gridy = 0;
       }
       panelForPlayers.add(textForPlayers, gbcTextForPlayers);

       GridBagConstraints gbcLabelPlayer = new GridBagConstraints();

       if (!(nicknamesOfPlayersOnThisSquare.isEmpty())) {
          labelPlayer1 = new JLabel(nicknamesOfPlayersOnThisSquare.get(0));
          gbcLabelPlayer.insets = new Insets(5, 0, 0, 0);
          gbcLabelPlayer.gridx = 0;
          gbcLabelPlayer.gridy = 1;
          labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
          panelForPlayers.add(labelPlayer1, gbcLabelPlayer);
       }


       if (nicknamesOfPlayersOnThisSquare.size() > 1) {
          labelPlayer2 = new JLabel(nicknamesOfPlayersOnThisSquare.get(1));
          labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 2;
          panelForPlayers.add(labelPlayer2, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 2) {
          labelPlayer3 = new JLabel(nicknamesOfPlayersOnThisSquare.get(2));
          labelPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 3;
          panelForPlayers.add(labelPlayer3, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 3) {
          labelPlayer4 = new JLabel(nicknamesOfPlayersOnThisSquare.get(3));
          labelPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 4;
          panelForPlayers.add(labelPlayer4, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 4) {
          labelPlayer5 = new JLabel(nicknamesOfPlayersOnThisSquare.get(4));
          labelPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 5;
          panelForPlayers.add(labelPlayer5, gbcLabelPlayer);
       }
    }

    JDialog getPopup() {
        return popup;
    }
}
