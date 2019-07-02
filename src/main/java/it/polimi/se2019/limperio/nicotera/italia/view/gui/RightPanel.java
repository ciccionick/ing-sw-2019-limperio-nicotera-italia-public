package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Handles the creation of the panel that is added on the right side in the border layout of the main JFrame.
 * @author Pietro L'Imperio.
 */
 public class RightPanel {

    /**
     * JPanel created in the constructor.
     */
    private JPanel panel;
    /**
     * JPanel that is added on the upper side of the right panel containing all of the players in game and the buttons to see their player boards.
     */
    private PanelOfPlayers panelOfPlayers;
    /**
     * JPanel added on the lower side of the right panel containing all of the buttons to choose which action do.
     */
    private PanelOfActions panelOfActions;
    /**
     * Left and right inset for the constraints of the panel.
     */
    private int insetLeftRight;
    /**
     * Bottom inset for the constraints of the panel.
     */
    private int insetBottom;
    /**
     * Grid bag constraints used for add panels to main panel.
     */
    private GridBagConstraints gbcPanelOfPlayers;


    /**
     * Constructor of Right panel
     * @param mainFrame Reference of main frame.
     */
     RightPanel(MainFrame mainFrame) {
         panel = new JPanel();
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

      void setPanelOfPlayers(PanelOfPlayers panelOfPlayers) {
         this.panelOfPlayers = panelOfPlayers;
     }

     JPanel getPanel() {
         return panel;
     }

     public PanelOfActions getPanelOfActions() {
       return panelOfActions;
    }

 }
