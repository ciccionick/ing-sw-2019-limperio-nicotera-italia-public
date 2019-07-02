package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;
import javax.swing.*;
import java.awt.*;

/**
 * Handles the creation of the player board panel to add it on the left panel.
 * @author Pietro L'Imperio
 */
class PlayerBoardPanel extends JPanel {

    /**
     * Reference of the main frame.
     */
    private MainFrame mainFrame;
    /**
     * Dialog that shows the damage on player board.
     */
    private JDialog dialogForDamage;
    /**
     * Dialog that shows marks on player board.
     */
    private JDialog dialogForMarks;
    /**
     * The player board view from where take the right information of the player board to view.
     */
    private PlayerBoardView playerBoardViewed;
    /**
     * JLabel that represent the piece of player board where there would be damages.
     */
    private JLabel cell11;
    /**
     * JLabel that represent the piece of player board where there would be marks.
     */
    private JLabel cell07;


    /**
     * Constructor of the class where player board panel is created.
     * @param mainFrame Reference of the main frame.
     * @param playerBoardView Reference of the player board view.
     */
     PlayerBoardPanel(MainFrame mainFrame, PlayerBoardView playerBoardView) {
        this.mainFrame = mainFrame;
        this.playerBoardViewed = playerBoardView;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);

        int heightOfCell = (int) (mainFrame.getFrame().getSize().getHeight()/19.42);
        int widthOfFirstCell = (int) (mainFrame.getFrame().getSize().getWidth()/33.68);
        int widthOfMiddleCells = (int) (mainFrame.getFrame().getSize().getWidth()/49.23);
        int widthOfLastCell = (int) (mainFrame.getFrame().getSize().getWidth()/11.92);

        String folderPath;

        switch (playerBoardView.getColorOfPlayer()){
            case BLUE:
                folderPath = "/playerboards/blue/";
                break;
            case GREEN:
                folderPath = "/playerboards/green/";
                break;
            case GREY:
                folderPath = "/playerboards/grey/";
                break;
           case PURPLE:
                folderPath = "/playerboards/purple/";
                break;
            case YELLOW:
                folderPath = "/playerboards/yellow/";
                break;
            default:
                throw new IllegalArgumentException();
        }

        if(playerBoardView.isFrenzyPlayerBoard())
            folderPath = folderPath.concat("frenzy/");

        JLabel cell00 = new JLabel("");
        GridBagConstraints gbcCell00 = new GridBagConstraints();
        gbcCell00.gridx = 0;
        gbcCell00.gridy = 0;
        gbcCell00.gridheight = 3;
        String pathForAction = folderPath;
        if(playerBoardView.isFrenzyActionBar() && !playerBoardView.isFrenzyPlayerBoard())
            pathForAction = pathForAction.concat("frenzy/");
        ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(pathForAction.concat("cell00.png")));
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newimg = image.getScaledInstance(widthOfFirstCell,heightOfCell*gbcCell00.gridheight,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell00.setIcon(imageIcon);
        add(cell00, gbcCell00);

        JLabel cell01 = new JLabel("");
        GridBagConstraints gbcCell01 = new GridBagConstraints();
        gbcCell01.gridx = 1;
        gbcCell01.gridy = 0;
        if(playerBoardView.isFrenzyPlayerBoard())
            gbcCell01.gridwidth=7;
        else
            gbcCell01.gridwidth = 6;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell01.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell01.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell01.setIcon(imageIcon);
        add(cell01, gbcCell01);


        cell07 = new JLabel("");
        GridBagConstraints gbcCell07 = new GridBagConstraints();
        if(playerBoardView.isFrenzyPlayerBoard()){
            gbcCell07.gridx = 8;
            gbcCell07.gridwidth = 5;
        }
        else {
            gbcCell07.gridx = 7;
            gbcCell07.gridwidth = 6;
        }
        gbcCell07.gridy = 0;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell07.png")));
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
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell013.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfLastCell,heightOfCell*gbcCell013.gridheight,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell013.setIcon(imageIcon);
        add(cell013, gbcCell013);

        cell11 = new JLabel("");
        GridBagConstraints gbcCell11 = new GridBagConstraints();
        gbcCell11.gridx = 1;
        gbcCell11.gridy = 1;
        gbcCell11.gridwidth = 12;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell11.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*12,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell11.setIcon(imageIcon);
        add(cell11, gbcCell11);


        JLabel cell21 = new JLabel("");
        GridBagConstraints gbcCell21 = new GridBagConstraints();
        gbcCell21.gridx = 1;
        gbcCell21.gridy = 2;
        if(playerBoardView.isFrenzyPlayerBoard())
            gbcCell21.gridwidth = 3;
        else
            gbcCell21.gridwidth = 2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell21.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell21.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell21.setIcon(imageIcon);
        add(cell21, gbcCell21);

        if(!playerBoardView.isFrenzyPlayerBoard()) {
            JLabel cell23 = new JLabel("");
            GridBagConstraints gbcCell23 = new GridBagConstraints();
            gbcCell23.gridx = 3;
            gbcCell23.gridy = 2;
            imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell23.png")));
            image = imageIcon.getImage();
            newimg = image.getScaledInstance(widthOfMiddleCells, heightOfCell, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            if (getPlayerBoardViewed().getScoreBarForNormalMode().size() < 6)
                cell23.setEnabled(false);
            cell23.setIcon(imageIcon);
            add(cell23, gbcCell23);
        }

        JLabel cell24 = new JLabel("");
        GridBagConstraints gbcCell24 = new GridBagConstraints();
        gbcCell24.gridx = 4;
        gbcCell24.gridy = 2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell24.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell24.setIcon(imageIcon);
         if(getPlayerBoardViewed().getScoreBarForNormalMode().size()<4 && !playerBoardViewed.isFrenzyPlayerBoard() || playerBoardViewed.isFrenzyPlayerBoard() && playerBoardViewed.getScoreBarForFrenzyMode().size()<4)
             cell24.setEnabled(false);
         add(cell24, gbcCell24);

        JLabel cell25 = new JLabel("");
        GridBagConstraints gbcCell25 = new GridBagConstraints();
        gbcCell25.gridx = 5;
        gbcCell25.gridy = 2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell25.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell25.setIcon(imageIcon);
         if(getPlayerBoardViewed().getScoreBarForNormalMode().size()<3 && !playerBoardViewed.isFrenzyPlayerBoard() || playerBoardViewed.isFrenzyPlayerBoard() && playerBoardViewed.getScoreBarForFrenzyMode().size()<3)
             cell25.setEnabled(false);

         add(cell25, gbcCell25);


        JLabel cell26 = new JLabel("");
        GridBagConstraints gbcCell26 = new GridBagConstraints();
        gbcCell26.gridx = 6;
        gbcCell26.gridy = 2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell26.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell26.setIcon(imageIcon);
         if(getPlayerBoardViewed().getScoreBarForNormalMode().size()<2 && !playerBoardViewed.isFrenzyPlayerBoard() || playerBoardViewed.isFrenzyPlayerBoard() && playerBoardViewed.getScoreBarForFrenzyMode().size()<2)
             cell26.setEnabled(false);

         add(cell26, gbcCell26);

        JLabel cell27 = new JLabel("");
        GridBagConstraints gbcCell27 = new GridBagConstraints();
        gbcCell27.gridx = 7;
        gbcCell27.gridy = 2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell27.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell27.setIcon(imageIcon);
         if(getPlayerBoardViewed().getScoreBarForNormalMode().isEmpty() && !playerBoardViewed.isFrenzyPlayerBoard() || playerBoardViewed.isFrenzyPlayerBoard() && playerBoardViewed.getScoreBarForFrenzyMode().isEmpty())
             cell27.setEnabled(false);

         add(cell27, gbcCell27);

         if(!playerBoardView.isFrenzyPlayerBoard()) {
             JLabel cell28 = new JLabel("");
             GridBagConstraints gbcCell28 = new GridBagConstraints();
             gbcCell28.gridx = 8;
             gbcCell28.gridy = 2;
             imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell28.png")));
             image = imageIcon.getImage();
             newimg = image.getScaledInstance(widthOfMiddleCells, heightOfCell, java.awt.Image.SCALE_SMOOTH);
             imageIcon = new ImageIcon(newimg);
             cell28.setIcon(imageIcon);
             if (getPlayerBoardViewed().getScoreBarForNormalMode().isEmpty())
                 cell28.setEnabled(false);
             add(cell28, gbcCell28);
         }

        JLabel cell29 = new JLabel("");
        GridBagConstraints gbcCell29 = new GridBagConstraints();
        if(playerBoardView.isFrenzyPlayerBoard())
            gbcCell29.gridx=8;
        else
            gbcCell29.gridx = 9;
        gbcCell29.gridy = 2;
        if(playerBoardView.isFrenzyPlayerBoard())
            gbcCell29.gridwidth = 5;
        else
            gbcCell29.gridwidth = 4;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("cell29.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*gbcCell29.gridwidth,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell29.setIcon(imageIcon);
        add(cell29, gbcCell29);
     }

    /**
     * Calls the constructor of the class that creates the dialog for show damage.
     */
    void addDialogForDamage(){
        dialogForDamage = new PopupForDamageMarks( mainFrame, true, cell11.getLocationOnScreen()).getDialog();
    }

    /**
     * Calls the constructor of the class that creates the dialog for show marks.
     */
    void addDialogForMarks(){
         dialogForMarks = new PopupForDamageMarks(mainFrame, false, cell07.getLocationOnScreen()).getDialog();
    }

    PlayerBoardView getPlayerBoardViewed() {
      return playerBoardViewed;
   }

    JLabel getCell11() {
        return cell11;
    }

    JLabel getCell07() {
        return cell07;
    }

    JDialog getDialogForDamage() {
        return dialogForDamage;
    }

    JDialog getDialogForMarks() {
        return dialogForMarks;
    }


 }
