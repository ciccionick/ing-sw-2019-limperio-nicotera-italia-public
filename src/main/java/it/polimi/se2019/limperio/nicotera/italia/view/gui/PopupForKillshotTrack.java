package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 class PopupForKillshotTrack {
    private JDialog dialog;
    private JPanel contentPane;
    private MainFrame mainFrame;

     PopupForKillshotTrack(MainFrame mainFrame, JLabel labelBoard) {
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.setAutoRequestFocus(false);
         contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(Color.GRAY);
        dialog.setContentPane(contentPane);
        int yOffset=labelBoard.getHeight()/4;
        int xOffset = labelBoard.getWidth()/25;

        Point location = SwingUtilities.convertPoint(labelBoard, 0, 0, mainFrame.getFrame());
        dialog.setLocation((int)location.getX()+xOffset, (int)location.getY()+yOffset);

        String folderPath = "resources/playerboards/damage/";
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 10);

        ArrayList<ArrayList<ColorOfDeathToken>> listOfToken = mainFrame.getRemoteView().getKillshotTrackView().getTokensOfDeath();
        int width = labelBoard.getWidth()/12;
        int height = (int) (labelBoard.getHeight()/3);
        int numOfToken;
        ColorOfDeathToken color;
        for(ArrayList<ColorOfDeathToken> token : listOfToken){
            numOfToken=token.size();
            color = token.get(0);
            JLabel damageIcon = new JLabel();
            ImageIcon icon = new ImageIcon(folderPath.concat(color.toString().toLowerCase()).concat(String.valueOf(numOfToken)).concat(".png"));
            Image image = icon.getImage().getScaledInstance(width,height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            damageIcon.setIcon(icon);
            contentPane.add(damageIcon,gbc);
            gbc.gridx++;
        }
        dialog.pack();
        dialog.setVisible(true);
    }

    public JDialog getDialog() {
        return dialog;
    }
}
