package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

 public class RightPanel {

    private MainFrame mainFrame;
    private JPanel panel;
    private PanelOfPlayers panelOfPlayers;
    private PanelOfActions panelOfActions;
    private int insetLeftRight;
    private int insetBottom;
    private GridBagConstraints gbcPanelOfPlayers;


     RightPanel(MainFrame mainFrame) {
         panel = new JPanel();
        this.mainFrame = mainFrame;
        this.panel.setBackground(Color.DARK_GRAY);

            this.panel.setLayout(new GridBagLayout());
            insetLeftRight = (int) (mainFrame.getFrame().getSize().getWidth() / 96);
            insetBottom = (int) (mainFrame.getFrame().getSize().getHeight() / 27);

            panelOfPlayers = new PanelOfPlayers(mainFrame);
            gbcPanelOfPlayers = new GridBagConstraints();
            gbcPanelOfPlayers.gridx = 0;
            gbcPanelOfPlayers.gridy = 0;
            gbcPanelOfPlayers.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);
            this.panel.add(panelOfPlayers, gbcPanelOfPlayers);


            panelOfActions = new PanelOfActions(mainFrame);
            GridBagConstraints gbcPanelOfActions = new GridBagConstraints();
            gbcPanelOfActions.gridx = 0;
            gbcPanelOfActions.gridy = 2;
            gbcPanelOfActions.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);

            this.panel.add(panelOfActions, gbcPanelOfActions);

    }

     PanelOfPlayers getPanelOfPlayers() {
       return panelOfPlayers;
    }

     GridBagConstraints getGbcPanelOfPlayers() {
         return gbcPanelOfPlayers;
     }

     public void setPanelOfPlayers(PanelOfPlayers panelOfPlayers) {
         this.panelOfPlayers = panelOfPlayers;
     }

     JPanel getPanel() {
         return panel;
     }

     public PanelOfActions getPanelOfActions() {
       return panelOfActions;
    }

 }
