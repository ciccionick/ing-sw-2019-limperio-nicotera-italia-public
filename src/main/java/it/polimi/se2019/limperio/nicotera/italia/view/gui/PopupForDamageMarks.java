package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfFigure_Square;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of the dialogs for damage and marks located on the player board panel
 */
class PopupForDamageMarks {
    /**
     * Dialog that is created in the constructor of the class.
     */
    private JDialog dialog;
    /**
     * JPanel added to the content panel of the dialog.
     */
    private JPanel contentPane;

    /**
     * Constructor of the class where the dialog is created.
     * @param mainFrame Reference of main frame.
     * @param forDamage It's true if the dialog has to be created for represent damage, false for marks.
     * @param location The point where the dialog has to be located.
     */
     PopupForDamageMarks(MainFrame mainFrame, boolean forDamage, Point location) {
         dialog = new JDialog(mainFrame.getFrame());
         contentPane = new JPanel(new GridBagLayout());
         dialog.setContentPane(contentPane);
         JLabel labelBoard;
         int yOffset;
         if(forDamage){
             labelBoard = mainFrame.getLeftPanel().getPlayerBoardPanel().getCell11();
             yOffset = labelBoard.getHeight()/4;
         }
         else {
             labelBoard = mainFrame.getLeftPanel().getPlayerBoardPanel().getCell07();
             yOffset = labelBoard.getHeight()/5;
         }

         dialog.setLocation((int)location.getX(), (int)location.getY()+yOffset);
         dialog.setUndecorated(true);
         dialog.setAutoRequestFocus(false);
         dialog.setAlwaysOnTop(false);
         contentPane.setBackground(Color.DARK_GRAY);

         PlayerBoardView currentPlayerBoardView = mainFrame.getLeftPanel().getPlayerBoardView();

         String folderPath = "/playerboards/damage/";
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.insets = new Insets(0, 0, 0, 0);
         ArrayList<ColorOfFigure_Square> listOfColors;
         int width;
         int height;
         if(forDamage) {
             listOfColors = currentPlayerBoardView.getDamages();
             width = labelBoard.getIcon().getIconWidth()/12;
             height = (int) (labelBoard.getIcon().getIconHeight()/1.7);
         }
         else {
             listOfColors = currentPlayerBoardView.getMarks();
             width = labelBoard.getWidth()/6;
             height = (int) (labelBoard.getHeight()/1.7);
         }
         for(ColorOfFigure_Square color : listOfColors){
             JLabel damageIcon = new JLabel();
             ImageIcon icon = new ImageIcon(mainFrame.getResource(folderPath.concat(color.toString().toLowerCase()).concat(".png")));
             Image image = icon.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
             icon = new ImageIcon(image);
             damageIcon.setIcon(icon);
             contentPane.add(damageIcon,gbc);
             gbc.gridx++;
         }
         dialog.pack();
         dialog.setVisible(true);
     }

     JDialog getDialog() {
        return dialog;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
