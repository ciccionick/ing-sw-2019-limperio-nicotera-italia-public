package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

 class LeftPanel extends JPanel {

    private MainFrame mainFrame;
    private PlayerBoardPanel playerBoardPanel;
    private JLabel weapon1;
    private JLabel weapon2;
    private JLabel weapon3;
    private JLabel powerCard1;
    private JLabel powerCard2;
    private JLabel powerCard3;
    private JLabel blueAmmo;
    private JLabel redAmmo;
    private JLabel yellowAmmo;


     LeftPanel(MainFrame mainFrame, PlayerBoardView playerBoardView) {
        this.mainFrame = mainFrame;
        this.setBackground(Color.DARK_GRAY);
        int widthCard;
        int heightCard;
        int widthHeightAmmo;

        if(mainFrame.getFrame().getSize().getWidth()/mainFrame.getFrame().getSize().getHeight()<1.80) {
            widthCard = (int) (mainFrame.getFrame().getSize().getWidth()/16);
            heightCard = (int) (widthCard*1.70);
            widthHeightAmmo = (int) (mainFrame.getFrame().getSize().getWidth()/64);
        }
        else
        {
            heightCard = (int) (mainFrame.getFrame().getSize().getHeight()/5.29);
            widthCard = (int) (heightCard*0.59);
            widthHeightAmmo = (int) (mainFrame.getFrame().getSize().getHeight()/36);
        }


        GridBagLayout gbcPanel = new GridBagLayout();
        this.setLayout(gbcPanel);



        playerBoardPanel = new PlayerBoardPanel(mainFrame, playerBoardView);
        GridBagConstraints gbcPlayerBoard = new GridBagConstraints();
        gbcPlayerBoard.insets = new Insets(0, (int)(mainFrame.getFrame().getSize().getWidth()/192), (int)(mainFrame.getFrame().getSize().getHeight()/216), (int)(mainFrame.getFrame().getSize().getWidth()/192));
        gbcPlayerBoard.anchor = GridBagConstraints.NORTHWEST;
        gbcPlayerBoard.gridx = 0;
        gbcPlayerBoard.gridy = 0;
        gbcPlayerBoard.gridwidth = 4;
        this.add(playerBoardPanel, gbcPlayerBoard);


        int insetTop = (int) (mainFrame.getFrame().getSize().getHeight()/108);
        int insetLeft = (int) (mainFrame.getFrame().getSize().getWidth()/38.4);
        int insetRight = insetLeft/10;
        int insetBottom = insetTop/2;

        String weaponFolderPath = "resources/weapons/";

        weapon1 = new JLabel("");
        ImageIcon imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),1)));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(widthCard, heightCard,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon1.setIcon(imageIcon);
        if(playerBoardView.getWeaponCardDeck().isEmpty())
           weapon1.setEnabled(false);
        GridBagConstraints gbcWeapon1 = new GridBagConstraints();
        gbcWeapon1.insets = new Insets(insetTop, insetLeft*2, insetBottom, insetRight);
        gbcWeapon1.gridx = 0;
        gbcWeapon1.gridy = 1;
        gbcWeapon1.fill = GridBagConstraints.BOTH;
        this.add(weapon1, gbcWeapon1);


        weapon2 = new JLabel("");
        imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),2)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon2.setIcon(imageIcon);
        if(playerBoardView.getWeaponCardDeck().size()<2)
           weapon2.setEnabled(false);
        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        gbcWeapon2.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcWeapon2.gridx = 1;
        gbcWeapon2.gridy = 1;
        gbcWeapon2.fill = GridBagConstraints.BOTH;
        this.add(weapon2, gbcWeapon2);

        weapon3 = new JLabel("");
        imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),3)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon3.setIcon(imageIcon);
        if(playerBoardView.getWeaponCardDeck().size()<3)
           weapon3.setEnabled(false);
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        gbcWeapon3.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcWeapon3.gridx = 2;
        gbcWeapon3.gridy = 1;
        gbcWeapon3.fill = GridBagConstraints.BOTH;
        this.add(weapon3, gbcWeapon3);


        JButton buttonW1 = new JButton("SELECT");
        GridBagConstraints gbcButtonW1 = new GridBagConstraints();
        if(weapon1.isEnabled()&&mainFrame.isHasToChooseAWeapon())
           buttonW1.setEnabled(true);
        else
           buttonW1.setEnabled(false);
        gbcButtonW1.insets = new Insets(insetTop/2, insetLeft*2, 0, 0);
        gbcButtonW1.gridx = 0;
        gbcButtonW1.gridy = 2;
        gbcButtonW1.fill = GridBagConstraints.BOTH;
        this.add(buttonW1, gbcButtonW1);


        JButton buttonW2 = new JButton("SELECT");
        GridBagConstraints gbcButtonW2 = new GridBagConstraints();
        if(buttonW2.isEnabled()&&mainFrame.isHasToChooseAWeapon())
           buttonW2.setEnabled(true);
        else
           buttonW2.setEnabled(false);
        gbcButtonW2.insets = new Insets(insetTop/2, insetLeft, 0, 0);
        gbcButtonW2.gridx = 1;
        gbcButtonW2.gridy = 2;
        gbcButtonW2.fill = GridBagConstraints.BOTH;
        this.add(buttonW2, gbcButtonW2);

        JButton buttonW3 = new JButton("SELECT");
        GridBagConstraints gbcButtonW3 = new GridBagConstraints();
        if(weapon3.isEnabled()&&mainFrame.isHasToChooseAWeapon())
           buttonW3.setEnabled(true);
        else
           buttonW3.setEnabled(false);
        gbcButtonW3.insets = new Insets(insetTop/2, insetLeft, 0, insetRight);
        gbcButtonW3.gridx = 2;
        gbcButtonW3.gridy = 2;
        gbcButtonW3.fill = GridBagConstraints.BOTH;
        this.add(buttonW3, gbcButtonW3);


        String powerUpFolderPath = "resources/powerupcards/";



        powerCard1 = new JLabel("");
        String path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 1));
        imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard1.setIcon(imageIcon);
        if (path.equals("resources/powerupcards/noCard.png"))
         powerCard1.setEnabled(false);
        GridBagConstraints gbcPowerUp1 = new GridBagConstraints();
        gbcPowerUp1.insets = new Insets(insetTop*2, insetLeft*2, insetBottom, insetRight);
        gbcPowerUp1.gridx = 0;
        gbcPowerUp1.gridy = 3;
        gbcPowerUp1.fill = GridBagConstraints.BOTH;
        this.add(powerCard1, gbcPowerUp1);
        ListenerForPowerUpCard listenerForPowerUpCard1 = new ListenerForPowerUpCard(powerCard1,mainFrame,1);
        powerCard1.addMouseListener(listenerForPowerUpCard1);

        powerCard2 = new JLabel("");
        path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 2));
        imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard2.setIcon(imageIcon);
        if (path.equals("resources/powerupcards/noCard.png"))
           powerCard2.setEnabled(false);
        GridBagConstraints gbcPowerUp2 = new GridBagConstraints();
        gbcPowerUp2.insets = new Insets(insetTop*2, insetLeft, insetBottom, insetRight);
        gbcPowerUp2.gridx = 1;
        gbcPowerUp2.gridy = 3;
        gbcPowerUp2.fill = GridBagConstraints.BOTH;
        this.add(powerCard2, gbcPowerUp2);
        ListenerForPowerUpCard listenerForPowerUpCard2 = new ListenerForPowerUpCard(powerCard2, mainFrame, 2);
        powerCard2.addMouseListener(listenerForPowerUpCard2);

        powerCard3 = new JLabel("");
        path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 3));
        imageIcon = new ImageIcon(path);
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard3.setIcon(imageIcon);
        if (path.equals("resources/powerupcards/noCard.png"))
           powerCard3.setEnabled(false);
        GridBagConstraints gbcPowerUp3 = new GridBagConstraints();
        gbcPowerUp3.insets = new Insets(insetTop*2, insetLeft, insetBottom, insetRight);
        gbcPowerUp3.gridx = 2;
        gbcPowerUp3.gridy = 3;
        gbcPowerUp3.fill = GridBagConstraints.BOTH;
        this.add(powerCard3, gbcPowerUp3);
        ListenerForPowerUpCard listenerForPowerUpCard3 = new ListenerForPowerUpCard(powerCard3, mainFrame, 3);
        powerCard3.addMouseListener(listenerForPowerUpCard3);



        JButton buttonPC1 = new JButton("SELECT");
        GridBagConstraints gbcButtonPC1 = new GridBagConstraints();
        gbcButtonPC1.insets = new Insets(insetTop/2, insetLeft*2, 0, 0);
        gbcButtonPC1.gridx = 0;
        gbcButtonPC1.gridy = 4;
        gbcButtonPC1.fill = GridBagConstraints.BOTH;
        if(!(powerCard1.isEnabled()))
           buttonPC1.setEnabled(false);
        else
           buttonPC1.setEnabled(checkStateOfButton(gbcPowerUp1));
        this.add(buttonPC1, gbcButtonPC1);
        buttonPC1.addActionListener(listenerForPowerUpCard1);

        JButton buttonPC2 = new JButton("SELECT");
        GridBagConstraints gbcButtonPC2 = new GridBagConstraints();
        gbcButtonPC2.insets = new Insets(insetTop/2, insetLeft, 0, 0);
        gbcButtonPC2.gridx = 1;
        gbcButtonPC2.gridy = 4;
        gbcButtonPC2.fill = GridBagConstraints.BOTH;
        if(!(powerCard2.isEnabled()))
           buttonPC2.setEnabled(false);
        else
           buttonPC2.setEnabled(checkStateOfButton(gbcPowerUp2));
        this.add(buttonPC2, gbcButtonPC2);
        buttonPC2.addActionListener(listenerForPowerUpCard2);


        JButton buttonPC3 = new JButton("SELECT");
        GridBagConstraints gbcButtonPC3 = new GridBagConstraints();
        gbcButtonPC3.insets = new Insets(insetTop/2, insetLeft, 0, insetRight);
        gbcButtonPC3.gridx = 2;
        gbcButtonPC3.gridy = 4;
        gbcButtonPC3.fill = GridBagConstraints.BOTH;
        if(!(powerCard3.isEnabled()))
           buttonPC3.setEnabled(false);
        else
           buttonPC3.setEnabled(checkStateOfButton(gbcPowerUp3));
        this.add(buttonPC3, gbcButtonPC3);
        buttonPC3.addActionListener(listenerForPowerUpCard3);


        String space = "      ";

        blueAmmo = new JLabel("");
        int numOfBlueAmmo;
        numOfBlueAmmo = getNumOfAmmo(playerBoardView.getAmmo(),ColorOfCard_Ammo.BLUE);
        blueAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        blueAmmo.setForeground(Color.WHITE);
        imageIcon = new ImageIcon("resources/playerboards/blueammo.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthHeightAmmo, widthHeightAmmo,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        blueAmmo.setIcon(imageIcon);
        blueAmmo.setText(space.concat(String.valueOf(numOfBlueAmmo)));
        GridBagConstraints gbcBlueAmmo = new GridBagConstraints();
        gbcBlueAmmo.insets = new Insets(insetTop*5/2, 0, insetBottom, insetRight);
        gbcBlueAmmo.gridx = 0;
        gbcBlueAmmo.gridy = 5;
        this.add(blueAmmo, gbcBlueAmmo);

        redAmmo = new JLabel();
        int numOfRedAmmo;
        numOfRedAmmo = getNumOfAmmo(playerBoardView.getAmmo(),ColorOfCard_Ammo.RED);
        imageIcon = new ImageIcon("resources/playerboards/redammo.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthHeightAmmo, widthHeightAmmo,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        redAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        redAmmo.setForeground(Color.WHITE);
        redAmmo.setIcon(imageIcon);
        redAmmo.setText(space.concat(String.valueOf(numOfRedAmmo)));
        GridBagConstraints gbcRedAmmo = new GridBagConstraints();
        gbcRedAmmo.insets = new Insets(insetTop/2, 0, insetBottom, insetRight);
        gbcRedAmmo.gridx = 0;
        gbcRedAmmo.gridy = 6;
        this.add(redAmmo, gbcRedAmmo);

        yellowAmmo = new JLabel();
        int numOfYellowAmmo;
        numOfYellowAmmo = getNumOfAmmo(playerBoardView.getAmmo(),ColorOfCard_Ammo.YELLOW);
        imageIcon = new ImageIcon("resources/playerboards/yellowammo.png");
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthHeightAmmo, widthHeightAmmo,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        yellowAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        yellowAmmo.setForeground(Color.WHITE);
        yellowAmmo.setIcon(imageIcon);
        yellowAmmo.setText(space.concat(String.valueOf(numOfYellowAmmo)));
        GridBagConstraints gbcYellowAmmo = new GridBagConstraints();
        gbcYellowAmmo.insets = new Insets(insetTop/2, 0, insetBottom, insetRight);
        gbcYellowAmmo.gridx = 0;
        gbcYellowAmmo.gridy = 7;
        this.add(yellowAmmo, gbcYellowAmmo);
    }

    private boolean checkStateOfButton(GridBagConstraints gbcPC) {
        if(mainFrame.hasToChoosePowerUpCard())
           return true;
        for(int i = 0; i<3; i++){
           if(gbcPC.gridx==i){
              switch (mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(i).getName()){
                 case "Newton":
                    return mainFrame.isCanUseNewton();
                 case "Tagback granade":
                    return mainFrame.isCanUseTagbackGranade();
                 case "Teleporter":
                    return mainFrame.isCanUseTeleporter();
                    default:
                       return false;
              }
           }
        }
      return false;
    }

    private int getNumOfAmmo(ArrayList<Ammo> ammo, ColorOfCard_Ammo color) {
        int counterOfAvailableAmmo=0;
        for (Ammo ammoItem : ammo){
            if (ammoItem.getColor()==color && ammoItem.isUsable()){
                counterOfAvailableAmmo++;
            }
        }
        return counterOfAvailableAmmo;
    }

    private String getNameOfWeaponCard(ArrayList<ServerEvent.AliasCard> weaponCardDeck, int position) {
        String nameOfCard;
        if(weaponCardDeck.size()<position)
            nameOfCard = "noCard.png";
        else
            nameOfCard = weaponCardDeck.get(position-1).getName().concat(".png");
        return nameOfCard;
    }

    private String getNameOfPowerUpCard(ArrayList<ServerEvent.AliasCard> powerUpCardsDeck, int position){
        String nameOfCard;
        if(powerUpCardsDeck.size()<position){
            nameOfCard ="noCard.png";
        }
        else
        {
            nameOfCard = powerUpCardsDeck.get(position-1).getName();
            if(powerUpCardsDeck.get(position-1).getColor()== ColorOfCard_Ammo.BLUE)
                nameOfCard= nameOfCard.concat(" blue.png");
            if(powerUpCardsDeck.get(position-1).getColor()== ColorOfCard_Ammo.RED)
                nameOfCard= nameOfCard.concat(" red.png");
            if(powerUpCardsDeck.get(position-1).getColor()== ColorOfCard_Ammo.YELLOW)
                nameOfCard= nameOfCard.concat(" yellow.png");
        }
        return nameOfCard;
    }

     PlayerBoardPanel getPlayerBoardPanel() {
       return playerBoardPanel;
    }


 }
