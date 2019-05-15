package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

class MapPanel extends JPanel {

    private MainFrame mainFrame;
    private JLabel cell00;
    private JLabel cell01;
    private JLabel cell02;
    private JLabel cell03;
    private JLabel cell10;
    private JLabel cell11;
    private JLabel cell12;
    private JLabel cell13;
    private JLabel cell20;
    private JLabel cell21;
    private JLabel cell22;
    private JLabel cell23;
    private HashMap<String, JLabel> hashMapForCell = new HashMap<>();




     MapPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.DARK_GRAY);
        int dim;
        if(mainFrame.getFrame().getSize().getWidth()/mainFrame.getFrame().getSize().getHeight()<1.80)
            dim = (int) ((int) mainFrame.getFrame().getSize().getWidth()/8.34);
        else
            dim = (int) (mainFrame.getFrame().getSize().getHeight()/4.70);

        int typeOfMap = mainFrame.getRemoteView().getMapView().getTypeOfMap();
        String folderPath;
        switch (typeOfMap){
           case 1:
              folderPath = "resources/board/maps/map1/";
              break;
           case 2:
              folderPath = "resources/board/maps/map2/";
              break;
           case 3:
              folderPath = "resources/board/maps/map3/";
              break;
           case 4:
              folderPath = "resources/board/maps/map4/";
              break;
           default:
              folderPath = "resources/board/maps/map1/";
        }


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        ImageIcon imageIcon = new ImageIcon(folderPath.concat("00.png"));
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell00 = new JLabel(imageIcon);
        this.add(cell00,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        imageIcon = new ImageIcon(folderPath.concat("01.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell01 = new JLabel(imageIcon);
        this.add(cell01,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        imageIcon = new ImageIcon(folderPath.concat("10.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell10 = new JLabel(imageIcon);
        this.add(cell10,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        imageIcon = new ImageIcon(folderPath.concat("02.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell02 = new JLabel(imageIcon);
        this.add(cell02,gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        imageIcon = new ImageIcon(folderPath.concat("03.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell03 = new JLabel(imageIcon);
        this.add(cell03,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        imageIcon = new ImageIcon(folderPath.concat("11.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell11 = new JLabel(imageIcon);
        this.add(cell11,gbc);

        gbc.gridx=2;
        gbc.gridy=1;
        imageIcon = new ImageIcon(folderPath.concat("12.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell12 = new JLabel(imageIcon);
        this.add(cell12,gbc);

        gbc.gridx=3;
        gbc.gridy=1;
        imageIcon = new ImageIcon(folderPath.concat("13.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell13 = new JLabel(imageIcon);
        this.add(cell13,gbc);


        gbc.gridx=0;
        gbc.gridy=2;
        imageIcon = new ImageIcon(folderPath.concat("20.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell20 = new JLabel(imageIcon);
        this.add(cell20,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        imageIcon = new ImageIcon(folderPath.concat("21.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell21 = new JLabel(imageIcon);
        this.add(cell21,gbc);

        gbc.gridx=2;
        gbc.gridy=2;
        imageIcon = new ImageIcon(folderPath.concat("22.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell22 = new JLabel(imageIcon);
        this.add(cell22,gbc);



        gbc.gridx=3;
        gbc.gridy=2;
        imageIcon = new ImageIcon(folderPath.concat("23.png"));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell23 = new JLabel(imageIcon);
        this.add(cell23,gbc);

        createHashMap();
    }

      private void createHashMap(){
         hashMapForCell.put("cell00", cell00);
         hashMapForCell.put("cell01", cell01);
         hashMapForCell.put("cell02", cell02);
         hashMapForCell.put("cell03", cell03);
         hashMapForCell.put("cell10", cell10);
         hashMapForCell.put("cell11", cell11);
         hashMapForCell.put("cell12", cell12);
         hashMapForCell.put("cell13", cell13);
         hashMapForCell.put("cell20", cell20);
         hashMapForCell.put("cell21", cell21);
         hashMapForCell.put("cell22", cell22);
         hashMapForCell.put("cell23", cell23);
      }

   public HashMap<String, JLabel> getHashMapForCell() {
      return hashMapForCell;
   }
}
