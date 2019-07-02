package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Class inheritable by PopupForNormalSquare and PopupForSpawnSquare containing the method to add players to panel of players in the dialogs.
 */
class PopupForSquare {

    /**
     * Dialog created to show who and what there are in a specific square.
     */
    JDialog popup = new JDialog();
    /**
     * JPanel that contains all of the information shown in the dialog and add to its content pane.
     */
    final JPanel contentPane = new JPanel();

    /**
     * Adds to the panelForPlayers every player present in a specific square.
     * @param panelForPlayers Panel where are added players.
     * @param nicknamesOfPlayersOnThisSquare List of nicknames of the player present in a square.
     * @param myNickname Nickname of the player who is viewing the GUI
     * @param mainFrame Reference of main frame.
     */
    void addListOfPlayersInSquare(JPanel panelForPlayers, ArrayList<String> nicknamesOfPlayersOnThisSquare, String myNickname, MainFrame mainFrame){
         JLabel labelPlayer1;
         JLabel labelPlayer2;
         JLabel labelPlayer3;
         JLabel labelPlayer4;
         JLabel labelPlayer5;
        GridBagConstraints gbcTextForPlayers = new GridBagConstraints();
       JLabel textForPlayers;
       int topInset = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
       int leftInset = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 5);

       if (nicknamesOfPlayersOnThisSquare.isEmpty()) {
          textForPlayers = new JLabel("<html> No players <br/> on this square! </html>");
          gbcTextForPlayers.insets = new Insets(topInset, 0, 0, 0);
          gbcTextForPlayers.gridx = 0;
          gbcTextForPlayers.gridy = 0;
       } else {
          textForPlayers = new JLabel("<html>Players on this <br/> square: </html>");
          textForPlayers.setHorizontalAlignment(SwingConstants.CENTER);
          gbcTextForPlayers.insets = new Insets(0, 0, topInset/2, 0);
          gbcTextForPlayers.gridx = 0;
          gbcTextForPlayers.gridy = 0;
       }
       panelForPlayers.add(textForPlayers, gbcTextForPlayers);

       GridBagConstraints gbcLabelPlayer = new GridBagConstraints();

       if (!(nicknamesOfPlayersOnThisSquare.isEmpty())) {
          labelPlayer1 = new JLabel();
          if(nicknamesOfPlayersOnThisSquare.get(0).equals(myNickname))
              labelPlayer1.setText("Me");
          else
              labelPlayer1.setText(nicknamesOfPlayersOnThisSquare.get(0));
          gbcLabelPlayer.insets = new Insets(leftInset, 0, 0, 0);
          gbcLabelPlayer.gridx = 0;
          gbcLabelPlayer.gridy = 1;
          labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
          panelForPlayers.add(labelPlayer1, gbcLabelPlayer);
       }


       if (nicknamesOfPlayersOnThisSquare.size() > 1) {
           labelPlayer2 = new JLabel();
           if(nicknamesOfPlayersOnThisSquare.get(1).equals(myNickname))
               labelPlayer2.setText("Me");
           else
               labelPlayer2.setText(nicknamesOfPlayersOnThisSquare.get(1));
          labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 2;
          panelForPlayers.add(labelPlayer2, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 2) {
           labelPlayer3 = new JLabel();
           if(nicknamesOfPlayersOnThisSquare.get(2).equals(myNickname))
               labelPlayer3.setText("Me");
           else
               labelPlayer3.setText(nicknamesOfPlayersOnThisSquare.get(2));
          labelPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 3;
          panelForPlayers.add(labelPlayer3, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 3) {
           labelPlayer4 = new JLabel();
           if(nicknamesOfPlayersOnThisSquare.get(3).equals(myNickname))
               labelPlayer4.setText("Me");
           else
               labelPlayer4.setText(nicknamesOfPlayersOnThisSquare.get(3));
          labelPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 4;
          panelForPlayers.add(labelPlayer4, gbcLabelPlayer);
       }

       if (nicknamesOfPlayersOnThisSquare.size() > 4) {
           labelPlayer5 = new JLabel();
           if(nicknamesOfPlayersOnThisSquare.get(4).equals(myNickname))
               labelPlayer5.setText("Me");
           else
               labelPlayer5.setText(nicknamesOfPlayersOnThisSquare.get(4));
          labelPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
          gbcLabelPlayer.gridy = 5;
          panelForPlayers.add(labelPlayer5, gbcLabelPlayer);
       }
    }

    JDialog getPopup() {
        return popup;
    }
}
