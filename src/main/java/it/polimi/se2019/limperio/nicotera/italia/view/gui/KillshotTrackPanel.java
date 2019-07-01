package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Hanldes the creation and the update of the north panel in Border Layout of main frame where is located the killshot track.
 * @author Pietro L'Imperio
 */
class KillshotTrackPanel extends JPanel {

    /**
     * The reference of main frame.
     */
    private MainFrame mainFrame;
    /**
     * The dialog with tokens of death of the normal mode.
     */
    private JDialog dialogForNormalSkull = null;
    /**
     * The dialog with tokens of death of the frenzy mode.
     */
    private JDialog dialogForFrenzySkull = null;
    /**
     * The first part of the track (where there would be the tokens of death of the normal mode).
     */
    private JLabel skull1;
    /**
     * The second part of the track (where there would be the tokens of death of the frenzy mode).
     */
    private JLabel skull2;
    /**
     * The third part of the track.
     */
    private JLabel skull3;

    /**
     * Constructor of the Panel.
     * @param mainFrame The reference of the main frame.
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

        String folderPath = "/board/killshottrack/";

        skull1 = new JLabel("");
        GridBagConstraints gbcSkull1 = new GridBagConstraints();
        gbcSkull1.gridx = 0;
        gbcSkull1.gridy = 0;
        gbcSkull1.insets = new Insets(insetTop, insetLeft, 0, 0);
        gbcSkull1.anchor = GridBagConstraints.WEST;
        ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell0.png")));
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
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell1.png")));
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
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell2.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        skull3.setIcon(imageIcon);
        add(skull3, gbcSkull3);

        JLabel semaphore = new JLabel("");
        GridBagConstraints gbcSemaphore = new GridBagConstraints();
        gbcSemaphore.gridx = 3;
        gbcSemaphore.gridy = 0;
        int leftInset = (int) (mainFrame.getFrame().getWidth()/12.8);
        gbcSemaphore.insets = new Insets(insetTop, leftInset, 0, 0);
        String path;
        if (mainFrame.getRemoteView().isMyTurn())
            path = folderPath.concat("green.png");
        else
            path = folderPath.concat("red.png");
        imageIcon = new ImageIcon(mainFrame.getResource(path));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        semaphore.setIcon(imageIcon);
        add(semaphore, gbcSemaphore);

    }

    /**
     * Add the dialog with the tokens of death put on the track during the normal mode.
     */
   void addDialogForNormalKillshot() {
       if (dialogForNormalSkull == null)
           dialogForNormalSkull = new PopupForKillshotTrack(mainFrame, false, skull1.getLocationOnScreen()).getDialog();
   }

    /**
     * Add the dialog with the tokens of death put on the track during the frenzy mode.
     */
    void addDialogForFrenzyKillshot() {
        if (dialogForFrenzySkull == null)
            dialogForFrenzySkull = new PopupForKillshotTrack(mainFrame, true, skull2.getLocationOnScreen()).getDialog();
    }

    JDialog getDialogForNormalSkull() {
        return dialogForNormalSkull;
    }

    JDialog getDialogForFrenzySkull() {
        return dialogForFrenzySkull;
    }

    void setNullDialogForNormalSkull() {
        this.dialogForNormalSkull = null;
    }

    void setNullDialogForFrenzySkull() {
        this.dialogForFrenzySkull = null;
    }

    JLabel getSkull1() {
        return skull1;
    }

    JLabel getSkull2() {
        return skull2;
    }


}

