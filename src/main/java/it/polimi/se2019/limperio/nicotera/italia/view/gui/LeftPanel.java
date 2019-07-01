package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 class LeftPanel extends JPanel {

    private transient MainFrame mainFrame;
    private PlayerBoardPanel playerBoardPanel;
    private PlayerBoardView playerBoardView;
    private JLabel weapon1;
    private JLabel weapon2;
    private JLabel weapon3;
    private JLabel powerCard1;
    private JLabel powerCard2;
    private JLabel powerCard3;
    private JButton buttonPC1;
    private JButton buttonPC2;
    private JButton buttonPC3;
    private JButton buttonW1;
    private JButton buttonW2;
    private JButton buttonW3;
    private JLabel blueAmmo;
    private JLabel redAmmo;
    private JLabel yellowAmmo;


     LeftPanel(MainFrame mainFrame, PlayerBoardView playerBoardView) {
        this.mainFrame = mainFrame;
        this.playerBoardView = playerBoardView;
        String use = "USE";

        this.setBackground(Color.DARK_GRAY);
        int widthCard;
        int heightCard;
        int widthHeightAmmo;

        if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
           widthCard = (int) (mainFrame.getFrame().getSize().getWidth() / 16);
           heightCard = (int) (widthCard * 1.70);
           widthHeightAmmo = (int) (mainFrame.getFrame().getSize().getWidth() / 64);
        } else {
           heightCard = (int) (mainFrame.getFrame().getSize().getHeight() / 5.29);
           widthCard = (int) (heightCard * 0.59);
           widthHeightAmmo = (int) (mainFrame.getFrame().getSize().getHeight() / 36);
        }


        GridBagLayout gbcPanel = new GridBagLayout();
        this.setLayout(gbcPanel);


        playerBoardPanel = new PlayerBoardPanel(mainFrame, playerBoardView);


         GridBagConstraints gbcPlayerBoard = new GridBagConstraints();
        gbcPlayerBoard.insets = new Insets(0, (int) (mainFrame.getFrame().getSize().getWidth() / 192), (int) (mainFrame.getFrame().getSize().getHeight() / 216), (int) (mainFrame.getFrame().getSize().getWidth() / 192));
        gbcPlayerBoard.anchor = GridBagConstraints.NORTHWEST;
        gbcPlayerBoard.gridx = 0;
        gbcPlayerBoard.gridy = 0;
        gbcPlayerBoard.gridwidth = 4;
        this.add(playerBoardPanel, gbcPlayerBoard);

        if (playerBoardView.getNicknameOfPlayer().equals("terminator")) {
           JLabel labelTerminator = new JLabel();
           ImageIcon icon = new ImageIcon(mainFrame.getResource("/weapons/terminator.png"));
           Image image = icon.getImage().getScaledInstance(widthCard * 2, heightCard * 2, Image.SCALE_SMOOTH);
           icon = new ImageIcon(image);
           labelTerminator.setIcon(icon);
           GridBagConstraints gbcLabelTerminator = new GridBagConstraints();
           gbcLabelTerminator.gridx = 0;
           gbcLabelTerminator.gridy = 1;
           int topInset = (int) (mainFrame.getFrame().getHeight()/21.6);
           int leftInset = (int) (mainFrame.getFrame().getWidth()/7.68);
           gbcLabelTerminator.insets = new Insets(topInset, leftInset, 0, 0);
           gbcLabelTerminator.fill = GridBagConstraints.BOTH;
           this.add(labelTerminator, gbcLabelTerminator);
        }

         else {

           int insetTop = (int) (mainFrame.getFrame().getSize().getHeight() / 108);
           int insetLeft = (int) (mainFrame.getFrame().getSize().getWidth() / 38.4);
           int insetRight = insetLeft / 10;
           int insetBottom = insetTop / 2;

           String weaponFolderPath = "/weapons/";

           weapon1 = new JLabel("");
           ImageIcon imageIcon = new ImageIcon(mainFrame.getResource(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(), 1))));
           Image image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           weapon1.setIcon(imageIcon);
           if (playerBoardView.getWeaponCardDeck().isEmpty()|| !playerBoardView.getWeaponCardDeck().get(0).isLoaded())
              weapon1.setEnabled(false);
           GridBagConstraints gbcWeapon1 = new GridBagConstraints();
           gbcWeapon1.insets = new Insets(insetTop, insetLeft * 2, insetBottom, insetRight);
           gbcWeapon1.gridx = 0;
           gbcWeapon1.gridy = 1;
           gbcWeapon1.fill = GridBagConstraints.BOTH;
           weapon1.addMouseListener(new ListenerForWeaponCard(buttonW1, 1, mainFrame));
           this.add(weapon1, gbcWeapon1);


           weapon2 = new JLabel("");
           imageIcon = new ImageIcon(mainFrame.getResource(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(), 2))));
           image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           weapon2.setIcon(imageIcon);
           if (playerBoardView.getWeaponCardDeck().size() < 2 || !playerBoardView.getWeaponCardDeck().get(1).isLoaded())
              weapon2.setEnabled(false);
           GridBagConstraints gbcWeapon2 = new GridBagConstraints();
           gbcWeapon2.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
           gbcWeapon2.gridx = 1;
           gbcWeapon2.gridy = 1;
           gbcWeapon2.fill = GridBagConstraints.BOTH;
           weapon2.addMouseListener(new ListenerForWeaponCard(buttonW2, 2, mainFrame));
           this.add(weapon2, gbcWeapon2);

           weapon3 = new JLabel("");
           imageIcon = new ImageIcon(mainFrame.getResource(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(), 3))));
           image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           weapon3.setIcon(imageIcon);
           if (playerBoardView.getWeaponCardDeck().size() < 3 || !playerBoardView.getWeaponCardDeck().get(2).isLoaded())
              weapon3.setEnabled(false);
           GridBagConstraints gbcWeapon3 = new GridBagConstraints();
           gbcWeapon3.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
           gbcWeapon3.gridx = 2;
           gbcWeapon3.gridy = 1;
           gbcWeapon3.fill = GridBagConstraints.BOTH;
           weapon3.addMouseListener(new ListenerForWeaponCard(buttonW3,3, mainFrame));
           this.add(weapon3, gbcWeapon3);


           buttonW1 = new JButton(use);
           GridBagConstraints gbcButtonW1 = new GridBagConstraints();
           buttonW1.setEnabled(weapon1.isEnabled() && playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer()) && mainFrame.getRemoteView().getMyPlayerBoardView().isCanChooseWeapon1());
           gbcButtonW1.insets = new Insets(insetTop / 2, insetLeft * 2, 0, 0);
           gbcButtonW1.gridx = 0;
           gbcButtonW1.gridy = 2;
           gbcButtonW1.fill = GridBagConstraints.BOTH;
           buttonW1.addActionListener(new ListenerForWeaponCard(buttonW1, 1, mainFrame));
           this.add(buttonW1, gbcButtonW1);


           buttonW2 = new JButton(use);
           GridBagConstraints gbcButtonW2 = new GridBagConstraints();
           buttonW2.setEnabled(weapon2.isEnabled() && playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer()) && mainFrame.getRemoteView().getMyPlayerBoardView().isCanChooseWeapon2());
           gbcButtonW2.insets = new Insets(insetTop / 2, insetLeft, 0, 0);
           gbcButtonW2.gridx = 1;
           gbcButtonW2.gridy = 2;
           gbcButtonW2.fill = GridBagConstraints.BOTH;
           buttonW2.addActionListener(new ListenerForWeaponCard(buttonW2, 2, mainFrame));
           this.add(buttonW2, gbcButtonW2);

           buttonW3 = new JButton(use);
           GridBagConstraints gbcButtonW3 = new GridBagConstraints();
           buttonW3.setEnabled(weapon3.isEnabled() && playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer()) && mainFrame.getRemoteView().getMyPlayerBoardView().isCanChooseWeapon3());
           gbcButtonW3.insets = new Insets(insetTop / 2, insetLeft, 0, insetRight);
           gbcButtonW3.gridx = 2;
           gbcButtonW3.gridy = 2;
           gbcButtonW3.fill = GridBagConstraints.BOTH;
           buttonW3.addActionListener(new ListenerForWeaponCard(buttonW3, 3, mainFrame));
           this.add(buttonW3, gbcButtonW3);


           String powerUpFolderPath = "/powerupcards/";
           String pathOfNoCard = powerUpFolderPath.concat("noCard.png");

           powerCard1 = new JLabel("");
           String path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 1));
           imageIcon = new ImageIcon(mainFrame.getResource(path));
           image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           powerCard1.setIcon(imageIcon);
           if (path.equals(pathOfNoCard))
              powerCard1.setEnabled(false);
           GridBagConstraints gbcPowerUp1 = new GridBagConstraints();
           gbcPowerUp1.insets = new Insets(insetTop * 2, insetLeft * 2, insetBottom, insetRight);
           gbcPowerUp1.gridx = 0;
           gbcPowerUp1.gridy = 3;
           gbcPowerUp1.fill = GridBagConstraints.BOTH;
           this.add(powerCard1, gbcPowerUp1);
           ListenerForPowerUpCard listenerForPowerUpCard1 = new ListenerForPowerUpCard(powerCard1, mainFrame, 1);
           powerCard1.addMouseListener(listenerForPowerUpCard1);

           powerCard2 = new JLabel("");
           path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 2));
           imageIcon = new ImageIcon(mainFrame.getResource(path));
           image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           powerCard2.setIcon(imageIcon);
           if (path.equals(pathOfNoCard))
              powerCard2.setEnabled(false);
           GridBagConstraints gbcPowerUp2 = new GridBagConstraints();
           gbcPowerUp2.insets = new Insets(insetTop * 2, insetLeft, insetBottom, insetRight);
           gbcPowerUp2.gridx = 1;
           gbcPowerUp2.gridy = 3;
           gbcPowerUp2.fill = GridBagConstraints.BOTH;
           this.add(powerCard2, gbcPowerUp2);
           ListenerForPowerUpCard listenerForPowerUpCard2 = new ListenerForPowerUpCard(powerCard2, mainFrame, 2);
           powerCard2.addMouseListener(listenerForPowerUpCard2);

           powerCard3 = new JLabel("");
           path = powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 3));
           imageIcon = new ImageIcon(mainFrame.getResource(path));
           image = imageIcon.getImage().getScaledInstance(widthCard, heightCard, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           powerCard3.setIcon(imageIcon);
           if (path.equals(pathOfNoCard))
              powerCard3.setEnabled(false);
           GridBagConstraints gbcPowerUp3 = new GridBagConstraints();
           gbcPowerUp3.insets = new Insets(insetTop * 2, insetLeft, insetBottom, insetRight);
           gbcPowerUp3.gridx = 2;
           gbcPowerUp3.gridy = 3;
           gbcPowerUp3.fill = GridBagConstraints.BOTH;
           this.add(powerCard3, gbcPowerUp3);
           ListenerForPowerUpCard listenerForPowerUpCard3 = new ListenerForPowerUpCard(powerCard3, mainFrame, 3);
           powerCard3.addMouseListener(listenerForPowerUpCard3);


           buttonPC1 = new JButton(use);
           GridBagConstraints gbcButtonPC1 = new GridBagConstraints();
           gbcButtonPC1.insets = new Insets(insetTop / 2, insetLeft * 2, 0, 0);
           gbcButtonPC1.gridx = 0;
           gbcButtonPC1.gridy = 4;
           gbcButtonPC1.fill = GridBagConstraints.BOTH;
           if (!(powerCard1.isEnabled()) || !(playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())))
              buttonPC1.setEnabled(false);
           else
              buttonPC1.setEnabled(checkStateOfButton(gbcPowerUp1));
           this.add(buttonPC1, gbcButtonPC1);
           buttonPC1.addActionListener(listenerForPowerUpCard1);

           buttonPC2 = new JButton(use);
           GridBagConstraints gbcButtonPC2 = new GridBagConstraints();
           gbcButtonPC2.insets = new Insets(insetTop / 2, insetLeft, 0, 0);
           gbcButtonPC2.gridx = 1;
           gbcButtonPC2.gridy = 4;
           gbcButtonPC2.fill = GridBagConstraints.BOTH;
           if (!(powerCard2.isEnabled()) || !(playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())))
              buttonPC2.setEnabled(false);
           else
              buttonPC2.setEnabled(checkStateOfButton(gbcPowerUp2));
           this.add(buttonPC2, gbcButtonPC2);
           buttonPC2.addActionListener(listenerForPowerUpCard2);


           buttonPC3 = new JButton(use);
           GridBagConstraints gbcButtonPC3 = new GridBagConstraints();
           gbcButtonPC3.insets = new Insets(insetTop / 2, insetLeft, 0, insetRight);
           gbcButtonPC3.gridx = 2;
           gbcButtonPC3.gridy = 4;
           gbcButtonPC3.fill = GridBagConstraints.BOTH;
           if (!(powerCard3.isEnabled()) || !(playerBoardView.getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())))
              buttonPC3.setEnabled(false);
           else
              buttonPC3.setEnabled(checkStateOfButton(gbcPowerUp3));
           this.add(buttonPC3, gbcButtonPC3);
           buttonPC3.addActionListener(listenerForPowerUpCard3);


           String space = "      ";

           blueAmmo = new JLabel("");
           int numOfBlueAmmo;
           numOfBlueAmmo = getNumOfAmmo(playerBoardView.getAmmo(), ColorOfCard_Ammo.BLUE);
           blueAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
           blueAmmo.setForeground(Color.WHITE);
           imageIcon = new ImageIcon(mainFrame.getResource("/playerboards/blueammo.png"));
           image = imageIcon.getImage().getScaledInstance(widthHeightAmmo, widthHeightAmmo, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           blueAmmo.setIcon(imageIcon);
           blueAmmo.setText(space.concat(String.valueOf(numOfBlueAmmo)));
           GridBagConstraints gbcBlueAmmo = new GridBagConstraints();
           gbcBlueAmmo.insets = new Insets(insetTop * 5 / 2, 0, insetBottom, insetRight);
           gbcBlueAmmo.gridx = 0;
           gbcBlueAmmo.gridy = 5;
           this.add(blueAmmo, gbcBlueAmmo);

           redAmmo = new JLabel();
           int numOfRedAmmo;
           numOfRedAmmo = getNumOfAmmo(mainFrame.getRemoteView().getMyPlayerBoardView().getAmmo(), ColorOfCard_Ammo.RED);
           imageIcon = new ImageIcon(mainFrame.getResource("/playerboards/redammo.png"));
           image = imageIcon.getImage().getScaledInstance(widthHeightAmmo, widthHeightAmmo, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           redAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
           redAmmo.setForeground(Color.WHITE);
           redAmmo.setIcon(imageIcon);
           redAmmo.setText(space.concat(String.valueOf(numOfRedAmmo)));
           GridBagConstraints gbcRedAmmo = new GridBagConstraints();
           gbcRedAmmo.insets = new Insets(insetTop / 2, 0, insetBottom, insetRight);
           gbcRedAmmo.gridx = 0;
           gbcRedAmmo.gridy = 6;
           this.add(redAmmo, gbcRedAmmo);

           yellowAmmo = new JLabel();
           int numOfYellowAmmo;
           numOfYellowAmmo = getNumOfAmmo(playerBoardView.getAmmo(), ColorOfCard_Ammo.YELLOW);
           imageIcon = new ImageIcon(mainFrame.getResource("/playerboards/yellowammo.png"));
           image = imageIcon.getImage().getScaledInstance(widthHeightAmmo, widthHeightAmmo, java.awt.Image.SCALE_SMOOTH);
           imageIcon = new ImageIcon(image);
           yellowAmmo.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
           yellowAmmo.setForeground(Color.WHITE);
           yellowAmmo.setIcon(imageIcon);
           yellowAmmo.setText(space.concat(String.valueOf(numOfYellowAmmo)));
           GridBagConstraints gbcYellowAmmo = new GridBagConstraints();
           gbcYellowAmmo.insets = new Insets(insetTop / 2, 0, insetBottom, insetRight);
           gbcYellowAmmo.gridx = 0;
           gbcYellowAmmo.gridy = 7;
           this.add(yellowAmmo, gbcYellowAmmo);
        }
     }

    private boolean checkStateOfButton(GridBagConstraints gbcPC) {
        if(mainFrame.getRemoteView().getMyPlayerBoardView().isHasToChoosePowerUpCardForSpawn())
           return true;
        for(int i = 0; i<3; i++){
           if(gbcPC.gridx==i){
              switch (mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck().get(i).getName()){
                 case "Newton":
                    return mainFrame.getRemoteView().getMyPlayerBoardView().isCanUseNewton();
                 case "Teleporter":
                    return mainFrame.getRemoteView().getMyPlayerBoardView().isCanUseTeleporter();
                    default:
                       return false;
              }
           }
        }
      return false;
    }

     PlayerBoardView getPlayerBoardView() {
       return playerBoardView;
    }

     JButton getButtonPC1() {
       return buttonPC1;
    }

     JButton getButtonPC2() {
       return buttonPC2;
    }

     JButton getButtonPC3() {
       return buttonPC3;
    }

      JButton getButtonW1() {
         return buttonW1;
     }

      JButton getButtonW2() {
         return buttonW2;
     }

      JButton getButtonW3() {
         return buttonW3;
     }

     private int getNumOfAmmo(ArrayList<Ammo> ammo, ColorOfCard_Ammo color) {
        int counterOfAvailableAmmo=0;
        for (Ammo ammoItem : ammo){
            if (ammoItem.getColor().equals(color)&&ammoItem.isUsable()){
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
