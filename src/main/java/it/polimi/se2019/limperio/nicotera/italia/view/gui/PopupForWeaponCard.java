package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Handles the creation of a dialog that show information about a weapon card.
 * @author Pietro L'Imperio
 */
 class PopupForWeaponCard {
    /**
     * Dialog created in the constructor.
     */
    private JDialog dialog;

    /**
     * Constructor where the dialog is created.
     * @param mainFrame Reference of main frame.
     * @param numOfCard Number that represent the position in the deck of the card shown in the dialog.
     */
     PopupForWeaponCard(MainFrame mainFrame, int numOfCard) {
        this.dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.setAutoRequestFocus(true);
        JPanel contentPanel = new JPanel(new GridBagLayout());
        dialog.getContentPane().add(contentPanel);


        int widthCard;
        int heightCard;

        if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
            widthCard = (int) (mainFrame.getFrame().getSize().getWidth() / 12);
            heightCard = (int) (widthCard * 1.70);
        } else {
            heightCard = (int) (mainFrame.getFrame().getSize().getHeight() / 3);
            widthCard = (int) (heightCard * 0.59);
        }

        ServerEvent.AliasCard weaponCard = mainFrame.getLeftPanel().getPlayerBoardView().getWeaponCardDeck().get(numOfCard-1);
        ImageIcon icon;
        Image image;
        String folderPath = "/weapons/";
        int topBottomInset = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 10);
        int leftRightInset = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(topBottomInset,leftRightInset,topBottomInset,leftRightInset);

        JLabel labelCard = new JLabel();
        icon = new ImageIcon(mainFrame.getResource(folderPath.concat(weaponCard.getName()).concat(".png")));
        image = icon.getImage().getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
        labelCard.setIcon(new ImageIcon(image));
        contentPanel.add(labelCard,gbc);

        gbc.gridy++;

        JCheckBox checkBoxLoad = new JCheckBox("Load");
        checkBoxLoad.setEnabled(false);
        checkBoxLoad.setSelected(weaponCard.isLoaded());
        contentPanel.add(checkBoxLoad,gbc);

        JPanel panelForDescriptionOfEffects = new JPanel(new GridBagLayout());
        gbc.gridy=0;
        gbc.gridx =1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(panelForDescriptionOfEffects, gbc);

        JLabel labelNameOfEffect;
        JTextArea descriptionOfEffect;


        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill = 0;
        String description;
        for(String nameOfEffect : weaponCard.getNameOfEffects()){
            if(!nameOfEffect.equals("")){
                labelNameOfEffect = new JLabel();
                labelNameOfEffect.setText(nameOfEffect);
                panelForDescriptionOfEffects.add(labelNameOfEffect,gbc);
                gbc.gridy++;
                description = weaponCard.getDescriptionOfEffects().get(weaponCard.getNameOfEffects().indexOf(nameOfEffect));
                descriptionOfEffect = new JTextArea();
                descriptionOfEffect.setEditable(false);
                descriptionOfEffect.setBackground(SystemColor.menu);
                descriptionOfEffect.setText(description);
                panelForDescriptionOfEffects.add(descriptionOfEffect, gbc);
                gbc.gridy++;
            }
        }

        dialog.pack();
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

        dialog.setVisible(true);
    }

     JDialog getDialog() {
        return dialog;
    }
}
