package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 class PopupForKillshotTrack {
    private JDialog dialog;
    private JPanel contentPane;
    private MainFrame mainFrame;

     PopupForKillshotTrack(MainFrame mainFrame, JLabel labelBoard, boolean frenzyMode) {
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.setAutoRequestFocus(false);
         contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(Color.DARK_GRAY);
        dialog.setContentPane(contentPane);
        int yOffset= (int) (labelBoard.getHeight()/3.5);
        int xOffset = labelBoard.getWidth()/28;

        Point location = SwingUtilities.convertPoint(labelBoard, 0, 0, mainFrame.getFrame());
        dialog.setLocation((int)location.getX()+xOffset, (int)location.getY()+yOffset);

        String folderPath = "resources/playerboards/damage/";
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 16);

        ArrayList<ArrayList<ColorOfDeathToken>> listOfToken = mainFrame.getRemoteView().getKillshotTrackView().getTokensOfDeath();
        ArrayList<ColorOfDeathToken> listOfTokenForFrenzyKillShoot = mainFrame.getRemoteView().getKillshotTrackView().getTokenOfFrenzyMode();
        int width;
        if(frenzyMode)
            width = labelBoard.getWidth();
        else
            width = labelBoard.getWidth()/12;
        int height = (labelBoard.getHeight()/3);
        int numOfToken;
        ColorOfDeathToken firstColor;
        ColorOfDeathToken secondColor;
        String path;
        if(!frenzyMode) {
            for (ArrayList<ColorOfDeathToken> token : listOfToken) {
                numOfToken = token.size();
                if (numOfToken == 1) {
                    firstColor = token.get(0);
                    path = folderPath.concat(firstColor.toString().toLowerCase());
                } else {
                    firstColor = token.get(0);
                    secondColor = token.get(1);
                    path = folderPath.concat(firstColor.toString().toLowerCase()).concat(" ").concat(secondColor.toString().toLowerCase());
                }
                JLabel damageIcon = new JLabel();
                ImageIcon icon = new ImageIcon(path.concat(".png"));
                Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
                damageIcon.setIcon(icon);
                contentPane.add(damageIcon, gbc);
                gbc.gridx++;
            }
        }
        else{
            for(ColorOfDeathToken token : listOfTokenForFrenzyKillShoot){
                path = folderPath.concat(token.toString().toLowerCase()).concat(".png");
                JLabel damageIcon = new JLabel();
                ImageIcon icon = new ImageIcon(path);
                Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
                damageIcon.setIcon(icon);
                contentPane.add(damageIcon, gbc);
                gbc.gridx++;
            }
        }
        dialog.pack();
        dialog.setVisible(true);
    }

    public JDialog getDialog() {
        return dialog;
    }
}
