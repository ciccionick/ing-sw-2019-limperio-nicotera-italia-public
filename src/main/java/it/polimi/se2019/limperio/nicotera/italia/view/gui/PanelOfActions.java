package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

public class PanelOfActions extends JPanel {

    private JFrame mainFrame;

    public PanelOfActions(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);

        JLabel text = new JLabel("Choose one action:");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_text = new GridBagConstraints();
        gbc_text.fill = GridBagConstraints.BOTH;
        gbc_text.insets = new Insets(0, 0, (int)(mainFrame.getSize().getHeight()/54), 0);
        gbc_text.gridwidth = 3;
        gbc_text.gridx = 0;
        gbc_text.gridy = 0;
        add(text, gbc_text);

        int insetTop = (int)(mainFrame.getSize().getHeight()/54);
        int insetBottom = insetTop/4;

        JButton buttonRun = new JButton("Run");
        buttonRun.setEnabled(false);
        buttonRun.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonRun.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_buttonRun = new GridBagConstraints();
        gbc_buttonRun.insets = new Insets(insetTop/2, 0, insetBottom, 0);
        gbc_buttonRun.gridx = 2;
        gbc_buttonRun.gridy = 1;
        add(buttonRun, gbc_buttonRun);

        JButton buttonCatch = new JButton("Catch");
        buttonCatch.setEnabled(false);
        buttonCatch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonCatch.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_buttonCatch = new GridBagConstraints();
        gbc_buttonCatch.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbc_buttonCatch.gridx = 2;
        gbc_buttonCatch.gridy = 2;
        add(buttonCatch, gbc_buttonCatch);

        JButton buttonShoot = new JButton("Shoot");
        buttonShoot.setEnabled(false);
        buttonShoot.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonShoot.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_buttonShoot = new GridBagConstraints();
        gbc_buttonShoot.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbc_buttonShoot.gridx = 2;
        gbc_buttonShoot.gridy = 3;
        add(buttonShoot, gbc_buttonShoot);

        JButton buttonReload = new JButton("Reload");
        buttonReload.setEnabled(false);
        buttonReload.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonReload.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_buttonReload = new GridBagConstraints();
        gbc_buttonReload.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbc_buttonReload.gridx = 2;
        gbc_buttonReload.gridy = 4;
        add(buttonReload, gbc_buttonReload);

        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setEnabled(false);
        buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        buttonCancel.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbc_buttonCancel = new GridBagConstraints();
        gbc_buttonCancel.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbc_buttonCancel.gridx = 2;
        gbc_buttonCancel.gridy = 5;
        add(buttonCancel, gbc_buttonCancel);

    }
}
