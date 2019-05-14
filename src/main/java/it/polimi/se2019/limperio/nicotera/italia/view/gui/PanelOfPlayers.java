package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

public class PanelOfPlayers extends JPanel {

    private JFrame mainFrame;

    public PanelOfPlayers(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);
        int insetTopForText;
        int insetTopForButtons;
        int insetRigthForLabel = (int) (mainFrame.getSize().getWidth()/64);

        insetTopForText = (int) (mainFrame.getSize().getHeight()/108);
        insetTopForButtons = 2*insetTopForText;

        JLabel text = new JLabel("Players in game (in order) :");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_text = new GridBagConstraints();
        gbc_text.fill = GridBagConstraints.BOTH;
        gbc_text.insets = new Insets(0, 0, insetTopForText, 0);
        gbc_text.gridwidth = 3;
        gbc_text.gridx = 0;
        gbc_text.gridy = 0;
        add(text, gbc_text);

        JLabel labelNickname = new JLabel("Nickname: ");
        labelNickname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelNickname.setForeground(Color.WHITE);

        GridBagConstraints gbc_labelNickname = new GridBagConstraints();
        gbc_labelNickname.insets = new Insets(insetTopForButtons, 0, 0, insetRigthForLabel );
        gbc_labelNickname.gridx = 0;
        gbc_labelNickname.gridy = 1;
        gbc_labelNickname.anchor = GridBagConstraints.CENTER;
        add(labelNickname, gbc_labelNickname);

        JLabel labelPB = new JLabel("PB: ");
        labelPB.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPB.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPB = new GridBagConstraints();
        gbc_labelPB.insets = new Insets(insetTopForButtons, 0, 0, insetRigthForLabel);
        gbc_labelPB.gridx = 1;
        gbc_labelPB.gridy = 1;
        gbc_labelPB.anchor = GridBagConstraints.CENTER;
        add(labelPB, gbc_labelPB);

        JLabel labelMap = new JLabel("MAP: ");
        labelMap.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelMap.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelMap = new GridBagConstraints();
        gbc_labelMap.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelMap.gridx = 2;
        gbc_labelMap.gridy = 1;
        gbc_labelMap.anchor = GridBagConstraints.CENTER;
        add(labelMap, gbc_labelMap);


        JLabel labelPlayer1 = new JLabel("Player1");
        labelPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPlayer1.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPlayer1 = new GridBagConstraints();
        gbc_labelPlayer1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelPlayer1.anchor = GridBagConstraints.WEST;
        gbc_labelPlayer1.gridx = 0;
        gbc_labelPlayer1.gridy = 2;
        add(labelPlayer1, gbc_labelPlayer1);

        JLabel labelPlayer2 = new JLabel("Player2");
        labelPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPlayer2.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPlayer2 = new GridBagConstraints();
        gbc_labelPlayer2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelPlayer2.anchor = GridBagConstraints.WEST;
        gbc_labelPlayer2.gridx = 0;
        gbc_labelPlayer2.gridy = 3;
        add(labelPlayer2, gbc_labelPlayer2);

        JLabel labelPlayer3 = new JLabel("Player3");
        labelPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPlayer3.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPlayer3 = new GridBagConstraints();
        gbc_labelPlayer3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelPlayer3.anchor = GridBagConstraints.WEST;
        gbc_labelPlayer3.gridx = 0;
        gbc_labelPlayer3.gridy = 4;
        add(labelPlayer3, gbc_labelPlayer3);

        JLabel labelPlayer4 = new JLabel("Player4");
        labelPlayer4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPlayer4.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPlayer4 = new GridBagConstraints();
        gbc_labelPlayer4.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelPlayer4.anchor = GridBagConstraints.WEST;
        gbc_labelPlayer4.gridx = 0;
        gbc_labelPlayer4.gridy = 5;
        add(labelPlayer4, gbc_labelPlayer4);

        JLabel labelPlayer5 = new JLabel("Player5");
        labelPlayer5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        labelPlayer5.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelPlayer5 = new GridBagConstraints();
        gbc_labelPlayer5.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelPlayer5.anchor = GridBagConstraints.WEST;
        gbc_labelPlayer5.gridx = 0;
        gbc_labelPlayer5.gridy = 6;
        add(labelPlayer5, gbc_labelPlayer5);

        JLabel labelAll = new JLabel("All");
        labelAll.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelAll.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelAll = new GridBagConstraints();
        gbc_labelAll.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelAll.anchor = GridBagConstraints.WEST;
        gbc_labelAll.gridx = 0;
        gbc_labelAll.gridy = 7;
        add(labelAll, gbc_labelAll);

        JLabel labelNone = new JLabel("None");
        labelNone.setFont(new Font("Tahoma", Font.BOLD, 18));
        labelNone.setForeground(Color.WHITE);
        GridBagConstraints gbc_labelNone = new GridBagConstraints();
        gbc_labelNone.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_labelNone.anchor = GridBagConstraints.WEST;
        gbc_labelNone.gridx = 0;
        gbc_labelNone.gridy = 8;
        add(labelNone, gbc_labelNone);

        JToggleButton buttonPB1 = new JToggleButton(" ");
        buttonPB1.setSelected(true);
        buttonPB1.setBackground(Color.BLACK);;
        GridBagConstraints gbc_buttonPB1 = new GridBagConstraints();
        gbc_buttonPB1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonPB1.anchor = GridBagConstraints.WEST;
        gbc_buttonPB1.gridx = 1;
        gbc_buttonPB1.gridy = 2;
        add(buttonPB1, gbc_buttonPB1);

        JToggleButton buttonPB2 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonPB2 = new GridBagConstraints();
        buttonPB2.setBackground(Color.BLACK);;
        gbc_buttonPB2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonPB2.anchor = GridBagConstraints.WEST;
        gbc_buttonPB2.gridx = 1;
        gbc_buttonPB2.gridy = 3;
        add(buttonPB2, gbc_buttonPB2);

        JToggleButton buttonPB3 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonPB3 = new GridBagConstraints();
        buttonPB3.setBackground(Color.BLACK);;
        gbc_buttonPB3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonPB3.anchor = GridBagConstraints.WEST;
        gbc_buttonPB3.gridx = 1;
        gbc_buttonPB3.gridy = 4;
        add(buttonPB3, gbc_buttonPB3);

        JToggleButton buttonPB4 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonPB4 = new GridBagConstraints();
        buttonPB4.setBackground(Color.BLACK);;
        gbc_buttonPB4.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonPB4.anchor = GridBagConstraints.WEST;
        gbc_buttonPB4.gridx = 1;
        gbc_buttonPB4.gridy = 5;
        add(buttonPB4, gbc_buttonPB4);

        JToggleButton buttonPB5 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonPB5 = new GridBagConstraints();
        buttonPB5.setBackground(Color.BLACK);;
        gbc_buttonPB5.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonPB5.anchor = GridBagConstraints.WEST;
        gbc_buttonPB5.gridx = 1;
        gbc_buttonPB5.gridy = 6;
        add(buttonPB5, gbc_buttonPB5);



        ButtonGroup pbButtonGroup = new ButtonGroup();
        pbButtonGroup.add(buttonPB1);
        pbButtonGroup.add(buttonPB2);
        pbButtonGroup.add(buttonPB3);
        pbButtonGroup.add(buttonPB4);
        pbButtonGroup.add(buttonPB5);


        JToggleButton buttonM1 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonM1 = new GridBagConstraints();
        buttonM1.setBackground(Color.BLACK);;
        gbc_buttonM1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonM1.gridx = 2;
        gbc_buttonM1.gridy = 2;
        add(buttonM1, gbc_buttonM1);

        JToggleButton buttonM2 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonM2 = new GridBagConstraints();
        buttonM2.setBackground(Color.BLACK);;
        gbc_buttonM2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonM2.gridx = 2;
        gbc_buttonM2.gridy = 3;
        add(buttonM2, gbc_buttonM2);

        JToggleButton buttonM3 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonM3 = new GridBagConstraints();
        buttonM3.setBackground(Color.BLACK);;
        gbc_buttonM3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonM3.gridx = 2;
        gbc_buttonM3.gridy = 4;
        add(buttonM3, gbc_buttonM3);

        JToggleButton buttonM4 = new JToggleButton(" ");
        GridBagConstraints gbc_buttonM4 = new GridBagConstraints();
        buttonM4.setBackground(Color.BLACK);;
        gbc_buttonM4.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonM4.gridx = 2;
        gbc_buttonM4.gridy = 5;
        add(buttonM4, gbc_buttonM4);

        JToggleButton buttonM5 = new JToggleButton(" ");
        buttonM5.setBackground(Color.BLACK);;
        GridBagConstraints gbc_buttonM5 = new GridBagConstraints();
        gbc_buttonM5.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonM5.gridx = 2;
        gbc_buttonM5.gridy = 6;
        add(buttonM5, gbc_buttonM5);

        JToggleButton buttonMAll = new JToggleButton(" ");
        GridBagConstraints gbc_buttonMAll = new GridBagConstraints();
        buttonMAll.setBackground(Color.BLACK);;
        gbc_buttonMAll.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonMAll.gridx = 2;
        gbc_buttonMAll.gridy = 7;
        add(buttonMAll, gbc_buttonMAll);

        JToggleButton buttonMNone = new JToggleButton(" ");
        buttonMNone.setSelected(true);
        buttonMNone.setBackground(Color.BLACK);;
        GridBagConstraints gbc_buttonMNone = new GridBagConstraints();
        gbc_buttonMNone.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbc_buttonMNone.gridx = 2;
        gbc_buttonMNone.gridy = 8;
        add(buttonMNone, gbc_buttonMNone);

        ButtonGroup mapButtonGroup = new ButtonGroup();
        mapButtonGroup.add(buttonM1);
        mapButtonGroup.add(buttonM2);
        mapButtonGroup.add(buttonM3);
        mapButtonGroup.add(buttonM4);
        mapButtonGroup.add(buttonM5);
        mapButtonGroup.add(buttonMAll);
        mapButtonGroup.add(buttonMNone);
    }
}
