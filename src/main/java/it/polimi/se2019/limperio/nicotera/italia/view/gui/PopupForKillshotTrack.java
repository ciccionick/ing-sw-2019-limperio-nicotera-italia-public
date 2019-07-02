package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfDeathToken;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of the dialog that show tokens of death on the killshot track.
 * @author Pietro L'Imperio
 */
 class PopupForKillshotTrack {
    /**
     * Dialog that is created in the constructor.
     */
    private JDialog dialog;
    /**
     * JPanel added to the content pane of the dialog.
     */
    private JPanel contentPane;

     /**
      * Constructor that create the dialog for tokens of death.
      * @param mainFrame Reference of main frame.
      * @param frenzyMode It's true if the dialog has to be created to show the tokens of death relative to death happened during the frenzy turn, false otherwise.
      * @param location The point where the dialog has to be created.
      */
     PopupForKillshotTrack(MainFrame mainFrame,  boolean frenzyMode, Point location) {

        dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.setAutoRequestFocus(false);
        dialog.setAlwaysOnTop(false);
         contentPane = new JPanel(new GridBagLayout());
        contentPane.setBackground(Color.DARK_GRAY);
        dialog.setContentPane(contentPane);
        int widthOfLabel = mainFrame.getKillshotTrackPanel().getSkull1().getWidth();
        int heightOfLabel = mainFrame.getKillshotTrackPanel().getSkull1().getHeight();

        int xOffset = widthOfLabel/20;
        int yOffset = (int) (heightOfLabel/2.6);

        if(frenzyMode){
            widthOfLabel = mainFrame.getKillshotTrackPanel().getSkull2().getWidth();
            heightOfLabel = mainFrame.getKillshotTrackPanel().getSkull2().getHeight();
        }

        String folderPath = "/playerboards/damage/";
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, mainFrame.getFrame().getWidth()/120);

        ArrayList<ArrayList<ColorOfDeathToken>> listOfToken = mainFrame.getRemoteView().getKillshotTrackView().getTokensOfDeath();
        ArrayList<ColorOfDeathToken> listOfTokenForFrenzyKillShoot = mainFrame.getRemoteView().getKillshotTrackView().getTokenOfFrenzyMode();
        if(!frenzyMode)
            widthOfLabel = widthOfLabel/12;
        heightOfLabel = heightOfLabel/3;
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
                ImageIcon icon = new ImageIcon(mainFrame.getResource(path.concat(".png")));
                Image image = icon.getImage().getScaledInstance(widthOfLabel, heightOfLabel, Image.SCALE_SMOOTH);
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
                ImageIcon icon = new ImageIcon(mainFrame.getResource(path));
                Image image = icon.getImage().getScaledInstance(widthOfLabel, heightOfLabel, Image.SCALE_SMOOTH);
                icon = new ImageIcon(image);
                damageIcon.setIcon(icon);
                contentPane.add(damageIcon, gbc);
                gbc.gridx++;
            }
        }
        dialog.pack();
        dialog.setLocation((int)location.getX()+xOffset,(int)location.getY()+yOffset);
        dialog.setVisible(true);
    }

    public JDialog getDialog() {
        return dialog;
    }
}
