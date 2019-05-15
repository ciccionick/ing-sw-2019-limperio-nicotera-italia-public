package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import java.awt.*;

 class PlayerBoardPanel extends JPanel {

    private MainFrame mainFrame;


     PlayerBoardPanel(MainFrame mainFrame, PlayerBoardView playerBoardView) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        int heightOfCell = (int) (mainFrame.getFrame().getSize().getHeight()/19.42);
        int widthOfFirstCell = (int) (mainFrame.getFrame().getSize().getWidth()/33.68);
        int widthOfMiddleCells = (int) (mainFrame.getFrame().getSize().getWidth()/49.23);
        int widthOfLastCell = (int) (mainFrame.getFrame().getSize().getWidth()/11.92);

        String folderPath = null;

        switch (playerBoardView.getColorOfPlayer()){
            case BLUE:
                folderPath = "resources/playerboards/blue/";
                break;
            case GREEN:
                folderPath = "resources/playerboards/green/";
                break;
            case GREY:
                folderPath = "resources/playerboards/grey/";
                break;
            case RED:
                folderPath = "resources/playerboards/red/";
                break;
            case YELLOW:
                folderPath = "resources/playerboards/yellow/";
                break;
            default:
                throw new IllegalArgumentException();
        }

        JLabel cell00 = new JLabel("");
        GridBagConstraints gbcCell00 = new GridBagConstraints();
        gbcCell00.gridx = 0;
        gbcCell00.gridy = 0;
        gbcCell00.gridheight = 3;
        ImageIcon imageIcon = new ImageIcon(folderPath.concat("cell00.png"));
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newimg = image.getScaledInstance(widthOfFirstCell,heightOfCell*gbcCell00.gridheight,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell00.setIcon(imageIcon);
        add(cell00, gbcCell00);

        JLabel cell01 = new JLabel("");
        GridBagConstraints gbcCell01 = new GridBagConstraints();
        gbcCell01.gridx = 1;
        gbcCell01.gridy = 0;
        gbcCell01.gridwidth = 6;
        imageIcon = new ImageIcon(folderPath.concat("cell01.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell01.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell01.setIcon(imageIcon);
        add(cell01, gbcCell01);

        JLabel cell07 = new JLabel("");
        GridBagConstraints gbcCell07 = new GridBagConstraints();
        gbcCell07.gridx = 7;
        gbcCell07.gridy = 0;
        gbcCell07.gridwidth = 6;
        imageIcon = new ImageIcon(folderPath.concat("cell07.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell07.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell07.setIcon(imageIcon);
        add(cell07, gbcCell07);

        JLabel cell013 = new JLabel("");
        GridBagConstraints gbcCell013 = new GridBagConstraints();
        gbcCell013.gridx = 13;
        gbcCell013.gridy = 0;
        gbcCell013.gridheight = 3;
        imageIcon = new ImageIcon(folderPath.concat("cell013.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfLastCell,heightOfCell*gbcCell013.gridheight,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell013.setIcon(imageIcon);
        add(cell013, gbcCell013);

        JLabel cell11 = new JLabel("");
        GridBagConstraints gbcCell11 = new GridBagConstraints();
        gbcCell11.gridx = 1;
        gbcCell11.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell11.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell11.setIcon(imageIcon);
        add(cell11, gbcCell11);

        JLabel cell12 = new JLabel("");
        GridBagConstraints gbcCell12 = new GridBagConstraints();
        gbcCell12.gridx = 2;
        gbcCell12.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell12.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell12.setIcon(imageIcon);
        add(cell12, gbcCell12);

        JLabel cell13 = new JLabel("");
        GridBagConstraints gbcCell13 = new GridBagConstraints();
        gbcCell13.gridx = 3;
        gbcCell13.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell13.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell13.setIcon(imageIcon);
        add(cell13, gbcCell13);

        JLabel cell14 = new JLabel("");
        GridBagConstraints gbcCell14 = new GridBagConstraints();
        gbcCell14.gridx = 4;
        gbcCell14.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell14.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell14.setIcon(imageIcon);
        add(cell14, gbcCell14);

        JLabel cell15 = new JLabel("");
        GridBagConstraints gbcCell15 = new GridBagConstraints();
        gbcCell15.gridx = 5;
        gbcCell15.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell15.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell15.setIcon(imageIcon);
        add(cell15, gbcCell15);

        JLabel cell16 = new JLabel("");
        GridBagConstraints gbcCell16 = new GridBagConstraints();
        gbcCell16.gridx = 6;
        gbcCell16.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell16.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell16.setIcon(imageIcon);
        add(cell16, gbcCell16);

        JLabel cell17 = new JLabel("");
        GridBagConstraints gbcCell17 = new GridBagConstraints();
        gbcCell17.gridx = 7;
        gbcCell17.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell17.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell17.setIcon(imageIcon);
        add(cell17, gbcCell17);

        JLabel cell18 = new JLabel("");
        GridBagConstraints gbcCell18 = new GridBagConstraints();
        gbcCell18.gridx = 8;
        gbcCell18.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell18.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell18.setIcon(imageIcon);
        add(cell18, gbcCell18);

        JLabel cell19 = new JLabel("");
        GridBagConstraints gbcCell19 = new GridBagConstraints();
        gbcCell19.gridx = 9;
        gbcCell19.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell19.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell19.setIcon(imageIcon);
        add(cell19, gbcCell19);

        JLabel cell110 = new JLabel("");
        GridBagConstraints gbcCell110 = new GridBagConstraints();
        gbcCell110.gridx = 10;
        gbcCell110.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell110.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell110.setIcon(imageIcon);
        add(cell110, gbcCell110);

        JLabel cell111 = new JLabel("");
        GridBagConstraints gbcCell111 = new GridBagConstraints();
        gbcCell111.gridx = 11;
        gbcCell111.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell111.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell111.setIcon(imageIcon);
        add(cell111, gbcCell111);

        JLabel cell112 = new JLabel("");
        GridBagConstraints gbcCell112 = new GridBagConstraints();
        gbcCell112.gridx = 12;
        gbcCell112.gridy = 1;
        imageIcon = new ImageIcon(folderPath.concat("cell112.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell112.setIcon(imageIcon);
        add(cell112, gbcCell112);

        JLabel cell21 = new JLabel("");
        GridBagConstraints gbcCell21 = new GridBagConstraints();
        gbcCell21.gridx = 1;
        gbcCell21.gridy = 2;
        gbcCell21.gridwidth = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell21.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell21.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell21.setIcon(imageIcon);
        add(cell21, gbcCell21);

        JLabel cell23 = new JLabel("");
        GridBagConstraints gbcCell23 = new GridBagConstraints();
        gbcCell23.gridx = 3;
        gbcCell23.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell23.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell23.setIcon(imageIcon);
        add(cell23, gbcCell23);

        JLabel cell24 = new JLabel("");
        GridBagConstraints gbcCell24 = new GridBagConstraints();
        gbcCell24.gridx = 4;
        gbcCell24.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell24.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell24.setIcon(imageIcon);
        add(cell24, gbcCell24);

        JLabel cell25 = new JLabel("");
        GridBagConstraints gbcCell25 = new GridBagConstraints();
        gbcCell25.gridx = 5;
        gbcCell25.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell25.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell25.setIcon(imageIcon);
        add(cell25, gbcCell25);


        JLabel cell26 = new JLabel("");
        GridBagConstraints gbcCell26 = new GridBagConstraints();
        gbcCell26.gridx = 6;
        gbcCell26.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell26.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell26.setIcon(imageIcon);
        add(cell26, gbcCell26);

        JLabel cell27 = new JLabel("");
        GridBagConstraints gbcCell27 = new GridBagConstraints();
        gbcCell27.gridx = 7;
        gbcCell27.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell27.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell27.setIcon(imageIcon);
        add(cell27, gbcCell27);

        JLabel cell28 = new JLabel("");
        GridBagConstraints gbcCell28 = new GridBagConstraints();
        gbcCell28.gridx = 8;
        gbcCell28.gridy = 2;
        imageIcon = new ImageIcon(folderPath.concat("cell28.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell28.setIcon(imageIcon);
        add(cell28, gbcCell28);

        JLabel cell29 = new JLabel("");
        GridBagConstraints gbcCell29 = new GridBagConstraints();
        gbcCell29.gridx = 9;
        gbcCell29.gridy = 2;
        gbcCell29.gridwidth = 4;
        imageIcon = new ImageIcon(folderPath.concat("cell29.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell29.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell29.setIcon(imageIcon);
        add(cell29, gbcCell29);
    }
}
