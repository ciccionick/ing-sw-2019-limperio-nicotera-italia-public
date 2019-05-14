package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

 class RightPanel extends JPanel {

    private MainFrame mainFrame;

     RightPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        int insetLeftRight = (int) (mainFrame.getFrame().getSize().getWidth()/96);
        int insetBottom = (int) (mainFrame.getFrame().getSize().getHeight()/27);

        PanelOfPlayers panelOfPlayers = new PanelOfPlayers(mainFrame);
        GridBagConstraints gbcPanelOfPlayers = new GridBagConstraints();
        gbcPanelOfPlayers.gridx = 0;
        gbcPanelOfPlayers.gridy=0;
        gbcPanelOfPlayers.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);
        this.add(panelOfPlayers, gbcPanelOfPlayers);



        PanelOfActions panelOfActions = new PanelOfActions(mainFrame);
        GridBagConstraints gbcPanelOfActions = new GridBagConstraints();
        gbcPanelOfActions.gridx = 0;
        gbcPanelOfActions.gridy=2;
        gbcPanelOfActions.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);

        this.add(panelOfActions, gbcPanelOfActions);
    }
}
