package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.AmmoTile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of the dialog to show who and what is on a normal square.
 * @author Pietro L'Imperio
 */
class PopupForNormalSquare extends PopupForSquare {

    /**
     * The number that represent which type of ammo tile is on a square to read the correct image file.
     */
    private int typeOfAmmoTile;


    /**
     * Constructor of the class that create the dialog.
     * @param nicknamesOfPlayersOnThisSquare List of nickname of the players on the square.
     * @param ammoTile The ammo tile on the square.
     * @param mainFrame Reference of main frame.
     * @param positionOfSquare The point that represent the location of the square.
     */
     PopupForNormalSquare(ArrayList<String> nicknamesOfPlayersOnThisSquare, AmmoTile ammoTile, MainFrame mainFrame, Point positionOfSquare)  {
         Dimension dimensionOfFrame = mainFrame.getFrame().getSize();
         Point positionOfPanel = mainFrame.getMapPanel().getLocation();
         String folderPath = "/board/ammotiles/";
         popup.setAutoRequestFocus(true);
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
         int topInset = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);
         int bottomInset = topInset/2;
         int leftRightInset = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);
         GridBagConstraints gbcTextForPlayers = new GridBagConstraints();
         JLabel textForPlayers;
         if(nicknamesOfPlayersOnThisSquare.isEmpty()){
             textForPlayers = new JLabel("<html> No players <br/> on this square! </html>");
             gbcTextForPlayers.insets = new Insets(topInset, leftRightInset, 0, leftRightInset);
             gbcTextForPlayers.gridx = 0;
             gbcTextForPlayers.gridy = 0;
         }
         else {
             textForPlayers = new JLabel("<html>Players on this <br/> square: </html>");
             textForPlayers.setHorizontalAlignment(SwingConstants.CENTER);
             gbcTextForPlayers.insets = new Insets(0, leftRightInset, bottomInset, leftRightInset);
             gbcTextForPlayers.gridx = 0;
             gbcTextForPlayers.gridy = 0;
         }
         panelForPlayers.add(textForPlayers, gbcTextForPlayers);

         addListOfPlayersInSquare(panelForPlayers, nicknamesOfPlayersOnThisSquare, mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer(), mainFrame );


         JPanel panelForAmmoTile = new JPanel();
         panelForAmmoTile.setBackground(SystemColor.menu);
         contentPane.add(panelForAmmoTile);
         panelForAmmoTile.setLayout(new BorderLayout());



         if (ammoTile != null) {
             folderPath = folderPath.concat(String.valueOf(ammoTile.getTypeOfAmmoTile())).concat(".png");
             typeOfAmmoTile =ammoTile.getTypeOfAmmoTile();
         } else {
             folderPath = folderPath.concat("none.png");
             typeOfAmmoTile = 0;
         }

         JLabel labelForAmmotile = new JLabel("");
         ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(folderPath));
         if (typeOfAmmoTile ==0)
             labelForAmmotile.setEnabled(false);
         java.awt.Image image = imageIcon.getImage().getScaledInstance(popup.getWidth()/3, popup.getHeight()/3,  java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(image);
         labelForAmmotile.setIcon(imageIcon);
         labelForAmmotile.setHorizontalAlignment(SwingConstants.CENTER);
         panelForAmmoTile.add(labelForAmmotile);
     }
}
