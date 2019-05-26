package it.polimi.se2019.limperio.nicotera.italia.view.gui;



import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class KillshotTrackPanel extends JPanel {

     private MainFrame mainFrame;

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

         JLabel skull1 = new JLabel("");
         ListenerForKillshotBoard listenerForPrincipalBoard = new ListenerForKillshotBoard(mainFrame,skull1);

         GridBagConstraints gbcSkull1 = new GridBagConstraints();
         gbcSkull1.gridx = 0;
         gbcSkull1.gridy = 0;
         gbcSkull1.insets = new Insets(insetTop, insetLeft, 0, 0);
         gbcSkull1.anchor = GridBagConstraints.WEST;
         ImageIcon imageIcon = new ImageIcon(folderPath.concat("cell0.png"));
         java.awt.Image image = imageIcon.getImage(); // transform it
         java.awt.Image newimg = image.getScaledInstance(widthSkull * 8, heightSkull, java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         skull1.setIcon(imageIcon);
         skull1.addMouseListener(listenerForPrincipalBoard);
         add(skull1, gbcSkull1);

         JLabel skull2 = new JLabel("");
         GridBagConstraints gbcSkull2 = new GridBagConstraints();
         gbcSkull2.gridx = 1;
         gbcSkull2.gridy = 0;
         gbcSkull2.insets = new Insets(insetTop, 0, 0, 0);
         imageIcon = new ImageIcon(folderPath.concat("cell1.png"));
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         skull2.setIcon(imageIcon);
         add(skull2, gbcSkull2);

         JLabel skull3 = new JLabel("");
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
         gbcSemaphore.insets = new Insets(insetTop, 150, 0,0);
         String path;
         if(mainFrame.getRemoteView().isMyTurn())
            path = folderPath.concat("green.png");
         else
            path = folderPath.concat("red.png");
         imageIcon = new ImageIcon(path);
         image = imageIcon.getImage();
         newimg = image.getScaledInstance(widthSkull, heightSkull, java.awt.Image.SCALE_SMOOTH);
         imageIcon = new ImageIcon(newimg);
         semaphore.setIcon(imageIcon);
         add(semaphore,gbcSemaphore);

      }

      class ListenerForKillshotBoard implements MouseListener{

         private MainFrame mainFrame;
         private JLabel label;
         private PopupForKillshotTrack popupForKillshotTrack;

         public ListenerForKillshotBoard(MainFrame mainFrame, JLabel label) {
            this.mainFrame = mainFrame;
            this.label = label;
         }

         @Override
         public void mouseClicked(MouseEvent e) {

         }

         @Override
         public void mousePressed(MouseEvent e) {
            popupForKillshotTrack = new PopupForKillshotTrack(mainFrame, label);
         }

         @Override
         public void mouseReleased(MouseEvent e) {
            if(popupForKillshotTrack!=null)
               popupForKillshotTrack.getDialog().setVisible(false);

         }

         @Override
         public void mouseEntered(MouseEvent e) {

         }

         @Override
         public void mouseExited(MouseEvent e) {

         }
      }

 }
