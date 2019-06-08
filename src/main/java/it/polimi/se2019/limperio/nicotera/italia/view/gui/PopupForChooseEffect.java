package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAnEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 class PopupForChooseEffect {

    private JDialog dialog;
    private ArrayList<JButton> effectButtons = new ArrayList<>();



     PopupForChooseEffect(MainFrame mainFrame, RequestToChooseAnEffect message) {
        this.dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        JPanel contentPane = new JPanel(new GridBagLayout());
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

        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setText(message.getMessageForInvolved());
        text.setBackground(SystemColor.menu);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(20, 20, 20, 20);
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        gbcText.gridwidth = numOfAllEffects;
        if(message.isOneEffectAlreadyChoosen())
            gbcText.gridwidth++;
        contentPane.add(text, gbcText);

        ListenerForEffects listenerForEffects = new ListenerForEffects(mainFrame,weaponCard);

        for(int i = 0 ; i<4 ; i++){
            if(weaponCard.getNameOfEffects().size()>i && !weaponCard.getNameOfEffects().get(i).equals("")){
                JButton effectButton = new JButton(weaponCard.getNameOfEffects().get(i));
                effectButtons.add(effectButton);
                GridBagConstraints gbcEffectButton = new GridBagConstraints();
                gbcEffectButton.insets = new Insets(0, 0, 5, 10);
                gbcEffectButton.gridx = i;
                gbcEffectButton.gridy = 1;
                if(!message.getUsableEffects().contains(i+1))
                    effectButton.setEnabled(false);
                effectButton.setActionCommand(String.valueOf(i+1));
                effectButton.addActionListener(listenerForEffects);
                contentPane.add (effectButton, gbcEffectButton);

                JTextArea descriptionOfEffect = new JTextArea();
                descriptionOfEffect.setText(weaponCard.getDescriptionOfEffects().get(i));
                descriptionOfEffect.setEditable(false);
                descriptionOfEffect.setBackground(SystemColor.menu);
                descriptionOfEffect.setLineWrap(true);
                gbcEffectButton.gridy=2;
                gbcEffectButton.insets.bottom = 20;
                contentPane.add(descriptionOfEffect,gbcEffectButton);
            }
        }

        if(message.isOneEffectAlreadyChoosen()){
            JButton endActionButton = new JButton("End action");
            contentPane.add(endActionButton);
            GridBagConstraints gbcEndActionButton = new GridBagConstraints();
            gbcEndActionButton.insets = new Insets(0, 0, 5, 10);
            gbcEndActionButton.gridx = 4;
            gbcEndActionButton.gridy = 1;
            if(!message.isOneEffectAlreadyChoosen())
                endActionButton.setEnabled(false);
            endActionButton.setActionCommand("End Action");
            endActionButton.addActionListener(listenerForEffects);
            contentPane.add (endActionButton, gbcEndActionButton);
        }


        dialog.pack();
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

    }

     private ArrayList<JButton> getEffectButtons() {
        return effectButtons;
    }

      JDialog getDialog() {
         return dialog;
     }

     private class ListenerForEffects implements ActionListener {
        MainFrame mainFrame;
        ServerEvent.AliasCard weaponCard;
         ListenerForEffects(MainFrame mainFrame, ServerEvent.AliasCard weaponCard) {
             this.mainFrame = mainFrame;
             this.weaponCard = weaponCard;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
             int numOfEffect = Integer.parseInt(e.getActionCommand());
             if(getEffectButtons().get(numOfEffect-1).isEnabled()){

                 System.out.println("Stampo");
                 dialog.setVisible(false);


             }

        }
    }
}
