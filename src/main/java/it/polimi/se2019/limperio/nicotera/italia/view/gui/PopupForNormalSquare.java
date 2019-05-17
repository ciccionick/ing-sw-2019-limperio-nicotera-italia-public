package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.AmmoTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class PopupForNormalSquare extends PopupForSquare {

    private int typeOfAmmo;



     PopupForNormalSquare(ArrayList<String> nicknamesOfPlayersOnThisSquare, AmmoTile ammoTile, Dimension dimensionOfFrame, Point positionOfPanel, Point positionOfSquare)  {
         String folderPath = "resources/board/ammotiles/";
         popup.setAutoRequestFocus(false);
         popup.setUndecorated(true);
         popup.getContentPane().setBackground(SystemColor.menu);
         popup.setBackground(SystemColor.menu);
         popup.setResizable(false);
         int dim = (int) (dimensionOfFrame.width/8.34);
         popup.setBounds(positionOfPanel.x + positionOfSquare.x, (int) (positionOfPanel.y+positionOfSquare.y+dimensionOfFrame.getHeight()/36), dim, dim);
         contentPane.setBackground(SystemColor.menu);
         popup.getContentPane().add(contentPane);

         contentPane.setLayout(new GridLayout(1, 2));


         JPanel panelForPlayers = new JPanel();
         panelForPlayers.setBackground(SystemColor.menu);
         panelForPlayers.setLayout(new GridBagLayout());
         contentPane.add(panelForPlayers);



         GridBagConstraints gbcTextForPlayers = new GridBagConstraints();
         JLabel textForPlayers;
         if(nicknamesOfPlayersOnThisSquare.isEmpty()){
             textForPlayers = new JLabel("<html> No players <br/> on this square! </html>");
             gbcTextForPlayers.insets = new Insets(20, 20, 0, 20);
             gbcTextForPlayers.gridx = 0;
             gbcTextForPlayers.gridy = 0;
         }
         else {
             textForPlayers = new JLabel("<html>Players on this <br/> square: </html>");
             textForPlayers.setHorizontalAlignment(SwingConstants.CENTER);
             gbcTextForPlayers.insets = new Insets(0, 20, 10, 20);
             gbcTextForPlayers.gridx = 0;
             gbcTextForPlayers.gridy = 0;
         }
         panelForPlayers.add(textForPlayers, gbcTextForPlayers);





         if (!(nicknamesOfPlayersOnThisSquare.isEmpty())) {
             labelPlayer1 = new JLabel(nicknamesOfPlayersOnThisSquare.get(0));
             GridBagConstraints gbcLabelPlayer1 = new GridBagConstraints();
             gbcLabelPlayer1.insets = new Insets(5,20, 0, 20);
             gbcLabelPlayer1.gridx = 0;
             gbcLabelPlayer1.gridy = 1;
             labelPlayer1.setHorizontalAlignment(SwingConstants.CENTER);
             panelForPlayers.add(labelPlayer1, gbcLabelPlayer1 );
         }


         if (nicknamesOfPlayersOnThisSquare.size() > 1) {
             labelPlayer2 = new JLabel(nicknamesOfPlayersOnThisSquare.get(1));
             labelPlayer2.setHorizontalAlignment(SwingConstants.CENTER);
             GridBagConstraints gbcLabelPlayer2 = new GridBagConstraints();
             gbcLabelPlayer2.insets = new Insets(5,20, 0, 20);
             gbcLabelPlayer2.gridx = 0;
             gbcLabelPlayer2.gridy = 2;
             panelForPlayers.add(labelPlayer2, gbcLabelPlayer2);
         }

         if (nicknamesOfPlayersOnThisSquare.size() > 2) {
             labelPlayer3 = new JLabel(nicknamesOfPlayersOnThisSquare.get(2));
             labelPlayer3.setHorizontalAlignment(SwingConstants.CENTER);
             GridBagConstraints gbcLabelPlayer3 = new GridBagConstraints();
             gbcLabelPlayer3.insets = new Insets(5,20, 0, 20);
             gbcLabelPlayer3.gridx = 0;
             gbcLabelPlayer3.gridy = 3;
             panelForPlayers.add(labelPlayer3, gbcLabelPlayer3);
         }

         if (nicknamesOfPlayersOnThisSquare.size() > 3) {
             labelPlayer4 = new JLabel(nicknamesOfPlayersOnThisSquare.get(3));
             labelPlayer4.setHorizontalAlignment(SwingConstants.CENTER);
             GridBagConstraints gbcLabelPlayer4 = new GridBagConstraints();
             gbcLabelPlayer4.insets = new Insets(5,20, 0, 20);
             gbcLabelPlayer4.gridx = 0;
             gbcLabelPlayer4.gridy = 4;
             panelForPlayers.add(labelPlayer4, gbcLabelPlayer4);
         }

         if (nicknamesOfPlayersOnThisSquare.size() > 4) {
             labelPlayer5 = new JLabel(nicknamesOfPlayersOnThisSquare.get(4));
             labelPlayer5.setHorizontalAlignment(SwingConstants.CENTER);
             GridBagConstraints gbcLabelPlayer5 = new GridBagConstraints();
             gbcLabelPlayer5.insets = new Insets(5,20, 0, 20);
             gbcLabelPlayer5.gridx = 0;
             gbcLabelPlayer5.gridy = 5;
             panelForPlayers.add(labelPlayer5, gbcLabelPlayer5);
         }

         JPanel panelForAmmoTile = new JPanel();
         panelForAmmoTile.setBackground(SystemColor.menu);
         contentPane.add(panelForAmmoTile);
         panelForAmmoTile.setLayout(new BorderLayout());



         if (ammoTile != null) {
             folderPath = folderPath.concat(String.valueOf(ammoTile.getTypeOfAmmoTile())).concat(".png");
             typeOfAmmo=ammoTile.getTypeOfAmmoTile();
         } else {
             folderPath = folderPath.concat("none.png");
             typeOfAmmo = 0;
         }

         JLabel labelForAmmotile = new JLabel("");
         ImageIcon imageIcon = new ImageIcon(folderPath);
         java.awt.Image image = imageIcon.getImage();
         java.awt.Image newimg = image.getScaledInstance(popup.getWidth()/3, popup.getHeight()/3,  java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         labelForAmmotile.setIcon(imageIcon);
         labelForAmmotile.setHorizontalAlignment(SwingConstants.CENTER);
         panelForAmmoTile.add(labelForAmmotile);


     }


}
