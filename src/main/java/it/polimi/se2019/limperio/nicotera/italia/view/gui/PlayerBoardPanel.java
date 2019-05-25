package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class PlayerBoardPanel extends JPanel {

    private MainFrame mainFrame;

    private PlayerBoardView playerBoardViewed;
    private JLabel cell11;

     PlayerBoardPanel(MainFrame mainFrame, PlayerBoardView playerBoardView) {
        this.mainFrame = mainFrame;
        this.playerBoardViewed = playerBoardView;
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
           case PURPLE:
                folderPath = "resources/playerboards/purple/";
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
        cell07.setToolTipText("Tap and hold to see possibly marks");
        ListenerForMarksBoard listenerForMarksBoard = new ListenerForMarksBoard(cell07);
        cell07.addMouseListener(listenerForMarksBoard);
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

        cell11 = new JLabel("");
        GridBagConstraints gbcCell11 = new GridBagConstraints();
        gbcCell11.gridx = 1;
        gbcCell11.gridy = 1;
        gbcCell11.gridwidth = 12;
        imageIcon = new ImageIcon(folderPath.concat("cell11.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthOfMiddleCells*12,heightOfCell,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell11.setIcon(imageIcon);
        cell11.setToolTipText("Tap and hold to see possibly damage");
        ListenerForDamageBoard listenerForDamageBoard = new ListenerForDamageBoard(cell11);
        cell11.addMouseListener(listenerForDamageBoard);
        add(cell11, gbcCell11);

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



    PlayerBoardView getPlayerBoardViewed() {
      return playerBoardViewed;
   }

   class ListenerForDamageBoard implements MouseListener{

        private JLabel damageBoard;
        private PopupForDamageMarks popupForDamage = null;


        ListenerForDamageBoard(JLabel damageBoard) {

          this.damageBoard = damageBoard;
       }

       @Override
       public void mouseClicked(MouseEvent e) {

       }

       @Override
       public void mousePressed(MouseEvent e) {
           if(!(mainFrame.getLeftPanel().getPlayerBoardView().getDamages().isEmpty())) {
              popupForDamage = new PopupForDamageMarks(damageBoard, mainFrame, true);
           }
       }

       @Override
       public void mouseReleased(MouseEvent e) {
         if(popupForDamage!=null)
            popupForDamage.getPanelForDamage().setVisible(false);
       }

       @Override
       public void mouseEntered(MouseEvent e) {

       }

       @Override
       public void mouseExited(MouseEvent e) {

       }
    }

    class ListenerForMarksBoard implements MouseListener{
         private JLabel marksBoard;
         private PopupForDamageMarks popupForDamageMarks = null;

        ListenerForMarksBoard(JLabel marksBoard) {
          this.marksBoard = marksBoard;
       }

       @Override
       public void mouseClicked(MouseEvent e) {

       }

       @Override
       public void mousePressed(MouseEvent e) {
          if(!mainFrame.getLeftPanel().getPlayerBoardView().getMarks().isEmpty())
             popupForDamageMarks = new PopupForDamageMarks(marksBoard, mainFrame, false);

       }

       @Override
       public void mouseReleased(MouseEvent e) {
         if(popupForDamageMarks!=null)
            popupForDamageMarks.getPanelForDamage().setVisible(false);

       }

       @Override
       public void mouseEntered(MouseEvent e) {

       }

       @Override
       public void mouseExited(MouseEvent e) {

       }
    }


 }
