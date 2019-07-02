package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The central panel in the border layout of the main JFrame.
 * @author Pietro L'Imperio.
 */
class MapPanel extends JPanel {

    /**
     * The reference with main frame
     */
    private transient  MainFrame mainFrame;
    /**
     * The JLabel representing the cell with row 0 and column 0 of the matrix of squares.
     */
    private JLabel cell00;
    /**
     * The JLabel representing the cell with row 0 and column 1 of the matrix of squares.
     */
    private JLabel cell01;
    /**
     * The JLabel representing the cell with row 0 and column 2 of the matrix of squares.
     */
    private JLabel cell02;
    /**
     * The JLabel representing the cell with row 0 and column 3 of the matrix of squares.
     */
    private JLabel cell03;
    /**
     * The JLabel representing the cell with row 1 and column 0 of the matrix of squares.
     */
    private JLabel cell10;
    /**
     * The JLabel representing the cell with row 1 and column 1 of the matrix of squares.
     */
    private JLabel cell11;
    /**
     * The JLabel representing the cell with row 1 and column 2 of the matrix of squares.
     */
    private JLabel cell12;
    /**
     * The JLabel representing the cell with row 1 and column 3 of the matrix of squares.
     */
    private JLabel cell13;
    /**
     * The JLabel representing the cell with row 2 and column 0 of the matrix of squares.
     */
    private JLabel cell20;
    /**
     * The JLabel representing the cell with row 2 and column 1 of the matrix of squares.
     */
    private JLabel cell21;
    /**
     * The JLabel representing the cell with row 2 and column 2 of the matrix of squares.
     */
    private JLabel cell22;
    /**
     * The JLabel representing the cell with row 2 and column 3 of the matrix of squares.
     */
    private JLabel cell23;
    /**
     * The list of dialogs that represents the figure of players in the squares.
     */
    private ArrayList<JDialog> dialogForFigure = new ArrayList<>();
    /**
     * Tha hash map that store the correspondence between the name of the variable and the JLabel.
     */
    private HashMap<String, JLabel> hashMapForCell = new HashMap<>();


    /**
     * Constructor of map panel that creates the panel adding the JLabel of the squares in a GridBag layout.
     * @param mainFrame The reference of the main frame.
     */
     MapPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setBackground(Color.DARK_GRAY);
        int dim;
        if(mainFrame.getFrame().getSize().getWidth()/mainFrame.getFrame().getSize().getHeight()<1.80)
            dim = ((int) mainFrame.getFrame().getSize().getWidth()/9);
        else
            dim = (int) (mainFrame.getFrame().getSize().getHeight()/5);

        int typeOfMap = mainFrame.getRemoteView().getMapView().getTypeOfMap();
        String folderPath;
        switch (typeOfMap){
           case 1:
              folderPath = "/board/maps/map1/";
              break;
           case 2:
              folderPath = "/board/maps/map2/";
              break;
           case 3:
              folderPath = "/board/maps/map3/";
              break;
           case 4:
              folderPath = "/board/maps/map4/";
              break;
           default:
              folderPath = "/board/maps/map1/";
        }

        String tip = "Tap and hold to see what there is in the square";

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=0;
        gbc.gridy=0;
        ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("00.png")));
        java.awt.Image image = imageIcon.getImage();
        java.awt.Image newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell00 = new JLabel(imageIcon);
        cell00.setToolTipText(tip);
        this.add(cell00,gbc);

         gbc.gridx=1;
         gbc.gridy=0;
         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("01.png")));

         image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell01 = new JLabel(imageIcon);
        this.add(cell01,gbc);

        gbc.gridx=2;
        gbc.gridy=0;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("02.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell02 = new JLabel(imageIcon);
        this.add(cell02,gbc);


         gbc.gridx=3;
         gbc.gridy=0;
         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("03.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell03 = new JLabel(imageIcon);
        this.add(cell03,gbc);

         gbc.gridx=0;
         gbc.gridy=1;
         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("10.png")));

         image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell10 = new JLabel(imageIcon);
        this.add(cell10,gbc);


         gbc.gridx=1;
         gbc.gridy=1;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("11.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell11 = new JLabel(imageIcon);
        this.add(cell11,gbc);


         gbc.gridx=2;
         gbc.gridy=1;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("12.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell12 = new JLabel(imageIcon);
        this.add(cell12,gbc);


         gbc.gridx=3;
         gbc.gridy=1;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("13.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell13 = new JLabel(imageIcon);
        this.add(cell13,gbc);



         gbc.gridx=0;

         gbc.gridy=2;
        imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("20.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell20 = new JLabel(imageIcon);
        this.add(cell20,gbc);


         gbc.gridx=1;
         gbc.gridy=2;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("21.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell21 = new JLabel(imageIcon);
        this.add(cell21,gbc);


         gbc.gridx=2;
         gbc.gridy=2;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("22.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell22 = new JLabel(imageIcon);
        this.add(cell22,gbc);


         gbc.gridx=3;
         gbc.gridy=2;

         imageIcon = new ImageIcon(mainFrame.getResource(folderPath.concat("23.png")));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(dim,dim,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        cell23 = new JLabel(imageIcon);
        this.add(cell23,gbc);
         createHashMap();
         addMouseListenerToCells();
     }


    /**
     * Adds for each JLabel a listener of square.
     */
    private void addMouseListenerToCells() {
      cell00.addMouseListener(new ListenerOfSquare(0, 0, mainFrame,this));
      cell01.addMouseListener(new ListenerOfSquare(0, 1, mainFrame,this));
      cell02.addMouseListener(new ListenerOfSquare(0, 2, mainFrame,this));
      cell03.addMouseListener(new ListenerOfSquare(0, 3, mainFrame,this));
      cell10.addMouseListener(new ListenerOfSquare(1, 0, mainFrame,this));
      cell11.addMouseListener(new ListenerOfSquare(1, 1, mainFrame,this));
      cell12.addMouseListener(new ListenerOfSquare(1, 2, mainFrame,this));
      cell13.addMouseListener(new ListenerOfSquare(1, 3, mainFrame,this));
      cell20.addMouseListener(new ListenerOfSquare(2, 0, mainFrame,this));
      cell21.addMouseListener(new ListenerOfSquare(2, 1, mainFrame,this));
      cell22.addMouseListener(new ListenerOfSquare(2, 2, mainFrame,this));
      cell23.addMouseListener(new ListenerOfSquare(2, 3, mainFrame,this));

   }

    /**
     * Creates the hash map for correspondence between the name of the JLabel and the name of the variables.
     */
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

    /**
     * Makes enable only the JLabels of the squares contained  in the list of selectable squares.
     * @param selectableSquares The list of selectable squares.
     */
     void updateEnableSquares(ArrayList<Square> selectableSquares) {
         for(JLabel label : hashMapForCell.values()){
             label.setEnabled(false);
         }
         for(Square square : selectableSquares){
             hashMapForCell.get("cell".concat(String.valueOf(square.getRow())).concat(String.valueOf(square.getColumn()))).setEnabled(true);
         }
    }

    /**
     * Creates the dialog for each figure of player and put them in the right place on the map.
     * @param mainFrame The reference of the main frame.
     */
    void addFigureOnSquare(MainFrame mainFrame) {
         ArrayList<Square> listOfSquares = mainFrame.getRemoteView().getMapView().getListOfSquareAsArrayList();
        for(JDialog dialog : dialogForFigure){
             dialog.setVisible(false);
         }
         dialogForFigure = new ArrayList<>();
         JPanel panel;
         String cell;
         String color;
         JLabel label;
         ImageIcon icon;
         Image image;
         int yOffset = (int) (cell00.getHeight()*0.1);
         int xOffset = (int) (cell00.getWidth()*0.1);
         int width = (int) (cell00.getWidth()/3.5);
         int height = (int) (cell00.getHeight()/2.5);
         for(Square square : listOfSquares){
             cell = "cell";
             if(!square.getNicknamesOfPlayersOnThisSquare().isEmpty()){
                 JDialog dialog;
                 cell = cell.concat(String.valueOf(square.getRow())).concat(String.valueOf(square.getColumn()));
                 dialog = new JDialog(mainFrame.getFrame());
                 dialogForFigure.add(dialog);
                 dialog.setUndecorated(true);
                 dialog.setResizable(false);
                 dialog.setAlwaysOnTop(false);
                 dialog.setAutoRequestFocus(false);
                 panel = new JPanel(new GridBagLayout());
                 dialog.getContentPane().add(panel);
                 GridBagConstraints gbc = new GridBagConstraints();
                 gbc.gridx = 0;
                 gbc.gridy = 0;
                 for(String name : square.getNicknamesOfPlayersOnThisSquare()){
                     color = mainFrame.getRemoteView().getPlayerBoardViewOfThisPlayer(name).getColorOfPlayer().toString().toLowerCase();
                     icon = new ImageIcon(mainFrame.getResource("/figure/" + color +".png"));
                     image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                     icon = new ImageIcon(image);
                     label = new JLabel(icon);
                     label.setToolTipText(name);
                     panel.add(label,gbc);
                     if(gbc.gridx<2)
                         gbc.gridx++;
                     else{
                         gbc.gridx=0;
                         gbc.gridy=1;
                     }
                 }
                 dialog.pack();
                 Point location = hashMapForCell.get(cell).getLocationOnScreen();
                 dialog.setLocation((int)location.getX()+xOffset,(int)location.getY()+yOffset);
                 dialog.setVisible(true);
             }
         }
    }

    ArrayList<JDialog> getDialogForFigure() {
        return dialogForFigure;
    }

    HashMap<String, JLabel> getHashMapForCell() {
        return hashMapForCell;
    }
}
