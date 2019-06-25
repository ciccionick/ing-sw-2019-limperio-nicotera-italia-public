package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAnEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

 class PopupForChooseEffect {

    private JDialog dialog;
    private ArrayList<JButton> effectButtons = new ArrayList<>();



     PopupForChooseEffect(MainFrame mainFrame, RequestToChooseAnEffect message) {
        this.dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(false);
        dialog.addWindowListener(new ListenerForPopupToChooseEffect(mainFrame));
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
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

        JTextArea text = new JTextArea(message.getMessageForInvolved());
        text.setEditable(false);
        text.setBackground(SystemColor.menu);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(20, 20, 20, 20);
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        gbcText.gridwidth = numOfAllEffects;
        if(message.isOneEffectAlreadyChosen())
            gbcText.gridwidth++;
        contentPane.add(text, gbcText);

        ListenerForEffects listenerForEffects = new ListenerForEffects(mainFrame,weaponCard);
         GridBagConstraints gbcEffectButton = new GridBagConstraints();
         GridBagConstraints gbcDescription = new GridBagConstraints();
         gbcEffectButton.gridy=1;
         gbcEffectButton.gridx = 0;
         gbcEffectButton.anchor = GridBagConstraints.CENTER;
         gbcEffectButton.insets = new Insets(10, 0, 5, 10);
         gbcDescription.insets = new Insets(5, 5, 0, 10);
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
            gbcEndActionButton.insets = new Insets(0, 0, 5, 10);
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
             if(e.getActionCommand().equals("0") || getEffectButtons().get(numOfEffect-1).isEnabled()){
                 RequestToUseEffect requestToUseEffect = new RequestToUseEffect("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                 requestToUseEffect.setNumOfEffect(numOfEffect);
                 dialog.setVisible(false);
                 mainFrame.getRemoteView().notify(requestToUseEffect);


             }

        }
    }

     private  class ListenerForPopupToChooseEffect implements WindowListener {
         MainFrame mainFrame;
          ListenerForPopupToChooseEffect(MainFrame mainFrame) {
              this.mainFrame = mainFrame;
         }

         @Override
         public void windowOpened(WindowEvent e) {
            //not implemented
         }

         @Override
         public void windowClosing(WindowEvent e) {
             dialog.setVisible(false);
             mainFrame.updatePanelOfAction();
         }

         @Override
         public void windowClosed(WindowEvent e) {
            //not implemented
         }

         @Override
         public void windowIconified(WindowEvent e) {
            //not implemented
         }

         @Override
         public void windowDeiconified(WindowEvent e) {
            //not implemented
         }

         @Override
         public void windowActivated(WindowEvent e) {
            //not implemented
         }

         @Override
         public void windowDeactivated(WindowEvent e) {
            //not implemented
         }
     }
 }

