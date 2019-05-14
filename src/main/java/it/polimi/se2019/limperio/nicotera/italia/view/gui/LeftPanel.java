package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Ammo;
import it.polimi.se2019.limperio.nicotera.italia.model.ColorOfCard_Ammo;
import it.polimi.se2019.limperio.nicotera.italia.view.PlayerBoardView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

 class LeftPanel extends JPanel {

    private MainFrame mainFrame;

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



        PlayerBoardPanel playerBoardPanel = new PlayerBoardPanel(mainFrame, playerBoardView);
        GridBagConstraints gbcPlayerBoard = new GridBagConstraints();
        gbcPlayerBoard.insets = new Insets(0, (int)(mainFrame.getFrame().getSize().getWidth()/192), (int)(mainFrame.getFrame().getSize().getHeight()/216), (int)(mainFrame.getFrame().getSize().getWidth()/192));
        gbcPlayerBoard.anchor = GridBagConstraints.NORTHWEST;
        gbcPlayerBoard.gridx = 0;
        gbcPlayerBoard.gridy = 0;
        gbcPlayerBoard.gridwidth = 3;
        this.add(playerBoardPanel, gbcPlayerBoard);


        int insetTop = (int) (mainFrame.getFrame().getSize().getHeight()/108);
        int insetLeft = (int) (mainFrame.getFrame().getSize().getWidth()/38.4);
        int insetRight = insetLeft/10;
        int insetBottom = insetTop/2;

        String weaponFolderPath = "resources/weapons/";

        JLabel weapon1 = new JLabel("");
        ImageIcon imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),1)));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(widthCard, heightCard,  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon1.setIcon(imageIcon);
        GridBagConstraints gbcWeapon1 = new GridBagConstraints();
        gbcWeapon1.insets = new Insets(insetTop, insetLeft*2, insetBottom, insetRight);
        gbcWeapon1.gridx = 0;
        gbcWeapon1.gridy = 1;
        gbcWeapon1.fill = GridBagConstraints.BOTH;
        this.add(weapon1, gbcWeapon1);


        JLabel weapon2 = new JLabel("");
        imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),2)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon2.setIcon(imageIcon);
        GridBagConstraints gbcWeapon2 = new GridBagConstraints();
        gbcWeapon2.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcWeapon2.gridx = 1;
        gbcWeapon2.gridy = 1;
        gbcWeapon2.fill = GridBagConstraints.BOTH;
        this.add(weapon2, gbcWeapon2);

        JLabel weapon3 = new JLabel("");
        imageIcon = new ImageIcon(weaponFolderPath.concat(getNameOfWeaponCard(playerBoardView.getWeaponCardDeck(),3)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        weapon3.setIcon(imageIcon);
        GridBagConstraints gbcWeapon3 = new GridBagConstraints();
        gbcWeapon3.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcWeapon3.gridx = 2;
        gbcWeapon3.gridy = 1;
        gbcWeapon3.fill = GridBagConstraints.BOTH;
        this.add(weapon3, gbcWeapon3);

        insetTop=insetTop*2;

        String powerUpFolderPath = "resources/powerupcards/";


        JLabel powerCard1 = new JLabel("");
        imageIcon = new ImageIcon(powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 1)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard1.setIcon(imageIcon);
        GridBagConstraints gbcPowerUp1 = new GridBagConstraints();
        gbcPowerUp1.insets = new Insets(insetTop, insetLeft*2, insetBottom, insetRight);
        gbcPowerUp1.gridx = 0;
        gbcPowerUp1.gridy = 2;
        gbcPowerUp1.fill = GridBagConstraints.BOTH;
        this.add(powerCard1, gbcPowerUp1);

        JLabel powerCard2 = new JLabel("");
        imageIcon = new ImageIcon(powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 2)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard2.setIcon(imageIcon);
        GridBagConstraints gbcPowerUp2 = new GridBagConstraints();
        gbcPowerUp2.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcPowerUp2.gridx = 1;
        gbcPowerUp2.gridy = 2;
        gbcPowerUp2.fill = GridBagConstraints.BOTH;
        this.add(powerCard2, gbcPowerUp2);

        JLabel powerCard3 = new JLabel("");
        imageIcon = new ImageIcon(powerUpFolderPath.concat(getNameOfPowerUpCard(playerBoardView.getPowerUpCardsDeck(), 3)));
        image = imageIcon.getImage();
        newimg = image.getScaledInstance(widthCard, heightCard , java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        powerCard3.setIcon(imageIcon);
        GridBagConstraints gbcPowerUp3 = new GridBagConstraints();
        gbcPowerUp3.insets = new Insets(insetTop, insetLeft, insetBottom, insetRight);
        gbcPowerUp3.gridx = 2;
        gbcPowerUp3.gridy = 2;
        gbcPowerUp3.fill = GridBagConstraints.BOTH;
        this.add(powerCard3, gbcPowerUp3);

        String space = "      ";

        JLabel blueAmmo = new JLabel("");
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
        gbcBlueAmmo.gridy = 3;
        this.add(blueAmmo, gbcBlueAmmo);

        JLabel redAmmo = new JLabel();
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
        gbcRedAmmo.gridy = 4;
        this.add(redAmmo, gbcRedAmmo);

        JLabel yellowAmmo = new JLabel();
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
        gbcYellowAmmo.gridy = 5;
        this.add(yellowAmmo, gbcYellowAmmo);
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
}
