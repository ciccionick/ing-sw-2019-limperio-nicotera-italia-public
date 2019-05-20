package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

 class RightPanel extends JPanel {

    private MainFrame mainFrame;

    private JPanel panelOfPlayers;
    private JPanel panelOfActions;
    private int insetLeftRight;
    private int insetBottom;

     RightPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        insetLeftRight = (int) (mainFrame.getFrame().getSize().getWidth()/96);
        insetBottom = (int) (mainFrame.getFrame().getSize().getHeight()/27);

        panelOfPlayers = new PanelOfPlayers(mainFrame);
        GridBagConstraints gbcPanelOfPlayers = new GridBagConstraints();
        gbcPanelOfPlayers.gridx = 0;
        gbcPanelOfPlayers.gridy=0;
        gbcPanelOfPlayers.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);
        this.add(panelOfPlayers, gbcPanelOfPlayers);



        panelOfActions = new PanelOfActions(mainFrame);
        GridBagConstraints gbcPanelOfActions = new GridBagConstraints();
        gbcPanelOfActions.gridx = 0;
        gbcPanelOfActions.gridy=2;
        gbcPanelOfActions.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);

        this.add(panelOfActions, gbcPanelOfActions);
    }

    public JPanel getPanelOfPlayers() {
       return panelOfPlayers;
    }

    public void setPanelOfPlayers(JPanel panelOfPlayers) {
       this.panelOfPlayers = panelOfPlayers;
    }

     JPanel getPanelOfActions() {
       return panelOfActions;
    }

     int getInsetLeftRight() {
       return insetLeftRight;
    }

     int getInsetBottom() {
       return insetBottom;
    }
 }
