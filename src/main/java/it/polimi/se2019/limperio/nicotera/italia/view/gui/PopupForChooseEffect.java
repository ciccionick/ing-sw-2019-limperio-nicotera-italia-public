package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAnEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 class PopupForChooseEffect {

    private JDialog dialog;
    private ArrayList<JButton> effectButtons = new ArrayList<>();



     PopupForChooseEffect(MainFrame mainFrame, RequestToChooseAnEffect message) {
        this.dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(false);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
                mainFrame.updatePanelOfAction();
            }});
        JPanel contentPane = new JPanel(new GridBagLayout());
        int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 50);
        int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 50);
        contentPane.setBorder(new EmptyBorder(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));
        dialog.getContentPane().add(contentPane);

        ServerEvent.AliasCard weaponCard = null;
        for (ServerEvent.AliasCard weapon : mainFrame.getRemoteView().getMyPlayerBoardView().getWeaponCardDeck()) {
            if (weapon.getName().equals(message.getNameOfCard()))
                weaponCard = weapon;
        }

        int numOfAllEffects = 0;
        if(weaponCard!=null) {
            for (String nameOfEffect : weaponCard.getNameOfEffects()) {
                if (!nameOfEffect.equals(""))
                    numOfAllEffects++;
            }
        }
        int leftInset = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20);
        int rightInset = leftInset;
        int bottomInset = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20);

        JTextArea text = new JTextArea(message.getMessageForInvolved());
        text.setEditable(false);
        text.setBackground(SystemColor.menu);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(0, leftInset   , bottomInset, rightInset);
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        gbcText.gridwidth = numOfAllEffects;
        if(message.isOneEffectAlreadyChosen())
            gbcText.gridwidth++;
        contentPane.add(text, gbcText);

        ListenerForChooseEffect listenerForEffects = new ListenerForChooseEffect(mainFrame,weaponCard, this);
         GridBagConstraints gbcEffectButton = new GridBagConstraints();
         GridBagConstraints gbcDescription = new GridBagConstraints();
         gbcEffectButton.gridy=1;
         gbcEffectButton.gridx = 0;
         gbcEffectButton.anchor = GridBagConstraints.CENTER;
         gbcEffectButton.insets = new Insets(bottomInset/2, 0, bottomInset/4, rightInset/2);
         gbcDescription.insets = new Insets(bottomInset/4, leftInset/4, 0, rightInset/2);
         gbcDescription.gridy = 2;
         gbcDescription.gridx = 0;
         for(int i = 0 ; i<4 ; i++){
            if(weaponCard.getNameOfEffects().size()>i && !weaponCard.getNameOfEffects().get(i).equals("")){
                JButton effectButton = new JButton(weaponCard.getNameOfEffects().get(i));
                effectButtons.add(effectButton);
                if(!message.getUsableEffects().contains(i+1))
                    effectButton.setEnabled(false);
                effectButton.setActionCommand(String.valueOf(i+1));
                effectButton.addActionListener(listenerForEffects);
                contentPane.add (effectButton, gbcEffectButton);
                JTextArea descriptionOfEffect = new JTextArea();
                descriptionOfEffect.setText(weaponCard.getDescriptionOfEffects().get(i));
                descriptionOfEffect.setEditable(false);
                descriptionOfEffect.setBackground(SystemColor.menu);
                descriptionOfEffect.setLineWrap(false);
                contentPane.add(descriptionOfEffect,gbcDescription);
                gbcEffectButton.gridx++;
                gbcDescription.gridx++;
            }
        }

        if(message.isOneEffectAlreadyChosen()){
            JButton endActionButton = new JButton("End action");
            contentPane.add(endActionButton);
            GridBagConstraints gbcEndActionButton = new GridBagConstraints();
            gbcEndActionButton.insets = new Insets(0, 0, bottomInset/4, rightInset/2);
            gbcEndActionButton.gridx = numOfAllEffects;
            gbcEndActionButton.gridy = 1;
            endActionButton.setActionCommand("0");
            endActionButton.addActionListener(listenerForEffects);
            contentPane.add (endActionButton, gbcEndActionButton);
        }


        dialog.pack();
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

    }

     ArrayList<JButton> getEffectButtons() {
        return effectButtons;
    }

      JDialog getDialog() {
         return dialog;
     }


 }

