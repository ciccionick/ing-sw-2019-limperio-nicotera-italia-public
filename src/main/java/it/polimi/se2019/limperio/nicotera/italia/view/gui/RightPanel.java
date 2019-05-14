package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {

    private JFrame mainFrame;

    public RightPanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        int insetLeftRight = (int) (mainFrame.getSize().getWidth()/96);
        int insetBottom = (int) (mainFrame.getSize().getHeight()/27);

        PanelOfPlayers panelOfPlayers = new PanelOfPlayers(mainFrame);
        GridBagConstraints gbc_panelOfPlayers = new GridBagConstraints();
        gbc_panelOfPlayers.gridx = 0;
        gbc_panelOfPlayers.gridy=0;
        gbc_panelOfPlayers.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);
        this.add(panelOfPlayers, gbc_panelOfPlayers);



        PanelOfActions panelOfActions = new PanelOfActions(mainFrame);
        GridBagConstraints gbc_panelOfActions = new GridBagConstraints();
        gbc_panelOfActions.gridx = 0;
        gbc_panelOfActions.gridy=2;
        gbc_panelOfActions.insets = new Insets(0, insetLeftRight, insetBottom, insetLeftRight);

        this.add(panelOfActions, gbc_panelOfActions);
    }
}
