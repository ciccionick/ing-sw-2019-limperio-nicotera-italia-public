package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
class KillshotTrackPanel extends JPanel {

    /**
     *
     */
    private MainFrame mainFrame;
    /**
     *
     */
    private JDialog dialogForNormalSkull = null;
    /**
     *
     */
    private JDialog dialogForFrenzySkull = null;
    /**
     *
     */
    private JLabel skull1;
    /**
     *
     */
    private JLabel skull2;
    /**
     *
     */
    private JLabel skull3;

    /**
     *
     * @param mainFrame
     */
    KillshotTrackPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridBagLayout());
        int widthSkull;

        int heightSkull;

        int insetLeft;
        int insetTop;

        if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
            widthSkull = (int) (mainFrame.getFrame().getSize().getWidth() / 34.9);
            heightSkull = (int) (widthSkull * 2.32);
        } else {
            heightSkull = (int) (mainFrame.getFrame().getSize().getHeight() / 8.43);
            widthSkull = (int) (heightSkull * 0.429);
        }

        insetLeft = (int) (mainFrame.getFrame().getSize().getWidth() / 4.8);
        insetTop = (int) (mainFrame.getFrame().getSize().getHeight() / 21.6);

        String folderPath = "resources/board/killshottrack/";

        skull1 = new JLabel("");


        GridBagConstraints gbcSkull1 = new GridBagConstraints();
        gbcSkull1.gridx = 0;
        gbcSkull1.gridy = 0;
        gbcSkull1.insets = new Insets(insetTop, insetLeft, 0, 0);
        gbcSkull1.anchor = GridBagConstraints.WEST;
        ImageIcon imageIcon = new ImageIcon(folderPath.concat("cell0.png"));
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newimg = image.getScaledInstance(widthSkull * 8, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        skull1.setIcon(imageIcon);
        add(skull1, gbcSkull1);

        skull2 = new JLabel("");
        GridBagConstraints gbcSkull2 = new GridBagConstraints();
        gbcSkull2.gridx = 1;
        gbcSkull2.gridy = 0;
        gbcSkull2.insets = new Insets(insetTop, 0, 0, 0);
        imageIcon = new ImageIcon(folderPath.concat("cell1.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        skull2.setIcon(imageIcon);
        add(skull2, gbcSkull2);

        skull3 = new JLabel("");
        GridBagConstraints gbcSkull3 = new GridBagConstraints();
        gbcSkull3.gridx = 2;
        gbcSkull3.gridy = 0;
        gbcSkull3.insets = new Insets(insetTop, 0, 0, 0);
        imageIcon = new ImageIcon(folderPath.concat("cell2.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        skull3.setIcon(imageIcon);
        add(skull3, gbcSkull3);

        JLabel semaphore = new JLabel("");
        GridBagConstraints gbcSemaphore = new GridBagConstraints();
        gbcSemaphore.gridx = 3;
        gbcSemaphore.gridy = 0;
        gbcSemaphore.insets = new Insets(insetTop, 150, 0, 0);
        String path;
        if (mainFrame.getRemoteView().isMyTurn())
            path = folderPath.concat("green.png");
        else
            path = folderPath.concat("red.png");
        imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        semaphore.setIcon(imageIcon);
        add(semaphore, gbcSemaphore);

    }

    JDialog getDialogForNormalSkull() {
        return dialogForNormalSkull;
    }

     JDialog getDialogForFrenzySkull() {
        return dialogForFrenzySkull;
    }

     JLabel getSkull1() {
        return skull1;
    }

     JLabel getSkull2() {
        return skull2;
    }

    /**
     *
     */
   void addDialogForNormalKillshot() {
        dialogForNormalSkull = new PopupForKillshotTrack(mainFrame, false, skull1.getLocationOnScreen()).getDialog();
    }

    /**
     *
     */
    void addDialogForFrenzyKillshot(){
        dialogForFrenzySkull = new PopupForKillshotTrack(mainFrame, true, skull2.getLocationOnScreen()).getDialog();
    }
}

