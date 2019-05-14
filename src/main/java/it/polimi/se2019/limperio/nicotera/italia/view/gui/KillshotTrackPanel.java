package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;

class KillshotTrackPanel extends JPanel {

     private JFrame mainFrame;

      KillshotTrackPanel(JFrame mainFrame) {
         this.mainFrame = mainFrame;

         this.setBackground(Color.DARK_GRAY);
         this.setLayout(new GridBagLayout());
         int widthSkullFrom1To8;
         int widthSkull9;
         int widthSkull10;
         int heightSkullFrom1To8;
         int heightSkull9;
         int heightSkull10;
         int insetLeft;
         int insetTop;

         if(mainFrame.getSize().getWidth()/mainFrame.getSize().getHeight()<1.80) {
             widthSkullFrom1To8 = (int) (mainFrame.getSize().getWidth()/34.9);
             heightSkullFrom1To8 = (int) (widthSkullFrom1To8*2.32);
             widthSkull9 = (int) (mainFrame.getSize().getWidth()/29);
             heightSkull9 = (int) (widthSkull9*1.93);
             widthSkull10 = (int) (mainFrame.getSize().getWidth()/22.06);
             heightSkull10 = (int) (widthSkull10 * 1.47);
         }
         else
         {
             heightSkullFrom1To8 = (int) (mainFrame.getSize().getHeight()/8.43);
             widthSkullFrom1To8 = (int) (heightSkullFrom1To8*0.429);
             heightSkull9 = (int) (mainFrame.getSize().getHeight()/8.43);
             widthSkull9 = (int) (heightSkull9/1.93);
             heightSkull10 =(int)( mainFrame.getSize().getHeight()/8.43);
             widthSkull10 = (int) (heightSkull10/1.47);
         }

         insetLeft = (int) (mainFrame.getSize().getWidth()/4.8);
         insetTop = (int) (mainFrame.getSize().getHeight()/21.6);


         JLabel skull1 = new JLabel("");

         GridBagConstraints gbcSkull1 = new GridBagConstraints();
         gbcSkull1.gridx = 0;
         gbcSkull1.gridy = 0;
         gbcSkull1.insets = new Insets(insetTop, insetLeft, 0, 0);
         gbcSkull1.anchor = GridBagConstraints.WEST;
         ImageIcon imageIcon = new ImageIcon("resources/board/killshottrack/skull1.png"); // load the image to a imageIcon
         java.awt.Image image = imageIcon.getImage(); // transform it
         java.awt.Image newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull1.setIcon(imageIcon);
         add(skull1, gbcSkull1);

         JLabel skull2 = new JLabel("");
         GridBagConstraints gbcSkull2 = new GridBagConstraints();
         gbcSkull2.gridx = 1;
         gbcSkull2.gridy = 0;
         gbcSkull2.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull2.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull2.setIcon(imageIcon);
         add(skull2, gbcSkull2);

         JLabel skull3 = new JLabel("");
         GridBagConstraints gbcSkull3 = new GridBagConstraints();
         gbcSkull3.gridx = 2;
         gbcSkull3.gridy = 0;
         gbcSkull3.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull3.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull3.setIcon(imageIcon);
         add(skull3, gbcSkull3);

         JLabel skull4 = new JLabel("");
         GridBagConstraints gbcSkull4 = new GridBagConstraints();
         gbcSkull4.gridx = 3;
         gbcSkull4.gridy = 0;
         gbcSkull4.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull4.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull4.setIcon(imageIcon);
         add(skull4, gbcSkull4);


         JLabel skull5 = new JLabel("");
         GridBagConstraints gbcSkull5 = new GridBagConstraints();
         gbcSkull5.gridx = 4;
         gbcSkull5.gridy = 0;
         gbcSkull5.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull5.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull5.setIcon(imageIcon);
         add(skull5, gbcSkull5);

         JLabel skull6 = new JLabel("");
         GridBagConstraints gbcSkull6 = new GridBagConstraints();
         gbcSkull6.gridx = 5;
         gbcSkull6.gridy = 0;
         gbcSkull6.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull6.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull6.setIcon(imageIcon);
         add(skull6, gbcSkull6);

         JLabel skull7 = new JLabel("");
         GridBagConstraints gbcSkull7 = new GridBagConstraints();
         gbcSkull7.gridx = 6;
         gbcSkull7.gridy = 0;
         gbcSkull7.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull7.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull7.setIcon(imageIcon);
         add(skull7, gbcSkull7);

         JLabel skull8 = new JLabel("");
         GridBagConstraints gbcSkull8 = new GridBagConstraints();
         gbcSkull8.gridx = 7;
         gbcSkull8.gridy = 0;
         gbcSkull8.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull8.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkullFrom1To8, heightSkullFrom1To8,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull8.setIcon(imageIcon);
         add(skull8, gbcSkull8);

         JLabel skull9 = new JLabel("");
         GridBagConstraints gbcSkull9 = new GridBagConstraints();
         gbcSkull9.gridx = 8;
         gbcSkull9.gridy = 0;
         gbcSkull9.insets = new Insets(insetTop,0,0,0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull9.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkull9, heightSkull9,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull9.setIcon(imageIcon);
         add(skull9, gbcSkull9);

         JLabel skull10 = new JLabel("");
         GridBagConstraints gbcSkull10 = new GridBagConstraints();
         gbcSkull10.gridx = 9;
         gbcSkull10.gridy = 0;
         gbcSkull10.insets = new Insets(insetTop, 0, 0, 0);
         imageIcon = new ImageIcon("resources/board/killshottrack/skull10.png"); // load the image to a imageIcon
         image = imageIcon.getImage(); // transform it
         newimg = image.getScaledInstance(widthSkull10, heightSkull10,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
         imageIcon = new ImageIcon(newimg);
         skull10.setIcon(imageIcon);
         add(skull10, gbcSkull10);
     }
 }
