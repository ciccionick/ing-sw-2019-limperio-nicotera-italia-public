package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class PopupForDamageMarks {
    private JDialog dialog;

     PopupForDamageMarks(JLabel labelBoard, MainFrame mainFrame, boolean forDamage) {
         dialog = new JDialog(mainFrame.getFrame());
         JPanel contentPane = new JPanel(new GridBagLayout());
         dialog.setContentPane(contentPane);
         int yOffset = 0;
         if(!forDamage)
             yOffset=10;
         Point location = SwingUtilities.convertPoint(labelBoard, 0,0, mainFrame.getFrame());
         dialog.setLocation((int)location.getX()-10, (int)location.getY()-yOffset);
         dialog.setUndecorated(true);
         dialog.setAutoRequestFocus(false);
         contentPane.setBackground(Color.DARK_GRAY);

         PlayerBoardView currentPlayerBoardView = mainFrame.getLeftPanel().getPlayerBoardView();
         String folderPath = "resources/playerboards/damage/";
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.insets = new Insets(0, 0, 0, 0);
         ArrayList<ColorOfFigure_Square> listOfColors;
         int width;
         int height;
         if(forDamage) {
             listOfColors = currentPlayerBoardView.getDamages();
             width = labelBoard.getWidth()/12;
             height = (int) (labelBoard.getHeight()/1.7);
         }
         else {
             listOfColors = currentPlayerBoardView.getMarks();
             width = labelBoard.getWidth()/6;
             height = (int) (labelBoard.getHeight()/1.7);
         }
         for(ColorOfFigure_Square color : listOfColors){
             JLabel damageIcon = new JLabel();
             ImageIcon icon = new ImageIcon(folderPath.concat(color.toString().toLowerCase()).concat(".png"));
             Image image = icon.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);
             damageIcon.setIcon(icon);
             contentPane.add(damageIcon,gbc);
             gbc.gridx++;
         }
         dialog.pack();
         dialog.setVisible(true);
     }

     JDialog getPanelForDamage() {
        return dialog;
    }
}
