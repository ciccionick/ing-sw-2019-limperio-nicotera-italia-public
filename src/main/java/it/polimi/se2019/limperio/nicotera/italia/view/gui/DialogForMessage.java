package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestChooseActionForTerminator;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DialogForMessage {

     private JDialog dialog;
     private JButton button;

     DialogForMessage(MainFrame mainFrame, ServerEvent receivedEvent) {
         //this.mainFrame = mainFrame;
         dialog = new JDialog(mainFrame.getFrame());
         dialog.setModal(true);

         dialog.setLocationRelativeTo(mainFrame.getFrame());
         JFrame frame = mainFrame.getFrame();
         dialog.setUndecorated(true);
         dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
         dialog.setSize((int) (frame.getWidth() / 4.2), (int) (frame.getHeight() / 3.6));
         int xPosition = (frame.getX() + frame.getWidth() - dialog.getWidth()) / 2;
         int yPosition = (frame.getY() + frame.getHeight() - dialog.getHeight()) / 2;
         dialog.setLocation(xPosition, yPosition);
         dialog.getContentPane().setLayout(new BorderLayout());
         JPanel contentPanel = new JPanel();
         contentPanel.setBackground(SystemColor.menu);
         contentPanel.setLayout(new GridBagLayout());
         dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
         JTextArea message;
         if (mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()))
             message = new JTextArea(receivedEvent.getMessageForInvolved());
         else
             message = new JTextArea(receivedEvent.getMessageForOthers());
         message.setEditable(false);
         message.setLineWrap(true);
         message.setBackground(SystemColor.menu);
         message.setColumns(34);
         message.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
         GridBagConstraints gbcMessage = new GridBagConstraints();
         gbcMessage.gridx = 0;
         gbcMessage.gridy = 0;
         contentPanel.add(message, gbcMessage);
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
         dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

         if (receivedEvent.isRequestToChooseTerminatorAction()) {
             ListenerForButtonOfTerminator listenerForButtonOfTerminator = new ListenerForButtonOfTerminator(mainFrame);
             buttonPanel.setLayout(new GridBagLayout());
             JButton buttonMove = new JButton("Move");
             buttonMove.setActionCommand(buttonMove.getText());
             buttonMove.addActionListener(listenerForButtonOfTerminator);
             JButton buttonShoot = new JButton("Shoot");
             buttonShoot.addActionListener(listenerForButtonOfTerminator);
             buttonShoot.setActionCommand(buttonShoot.getText());
             JButton buttonGoOn = new JButton("Go on");
             buttonGoOn.addActionListener(listenerForButtonOfTerminator);
             buttonGoOn.setActionCommand(buttonGoOn.getText());
             buttonMove.setEnabled(((RequestChooseActionForTerminator) receivedEvent).isTerminatorCanMove());
             buttonShoot.setEnabled(((RequestChooseActionForTerminator) receivedEvent).isTerminatorCanShoot());
             buttonGoOn.setEnabled(!buttonShoot.isEnabled());
             GridBagConstraints gbcButtonMove = new GridBagConstraints();
             GridBagConstraints gbcButtonShoot = new GridBagConstraints();
             GridBagConstraints gbcButtonGoOn = new GridBagConstraints();
             gbcButtonMove.gridx = 0;
             gbcButtonMove.gridy = 0;
             gbcButtonShoot.gridx = 1;
             gbcButtonShoot.gridy = 0;
             gbcButtonGoOn.gridx = 2;
             gbcButtonGoOn.gridy = 0;
             Insets insets = new Insets(20, 20, 20, 20);
             gbcButtonMove.insets = insets;
             gbcButtonShoot.insets = insets;
             gbcButtonGoOn.insets = insets;
             buttonPanel.add(buttonMove, gbcButtonMove);
             buttonPanel.add(buttonShoot, gbcButtonShoot);
             buttonPanel.add(buttonGoOn, gbcButtonGoOn);

         } else {
             if (mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()) && receivedEvent.isRequestForDrawTwoPowerUpCardsEvent()) {
                 button = new JButton("DRAW");
                 button.setActionCommand("DRAW");
             } else {
                 button = new JButton("OK");
                 button.setActionCommand("OK");
             }

             button.addActionListener(new DrawOkButtonListener(dialog, mainFrame));
             buttonPanel.add(button);
             dialog.getRootPane().setDefaultButton(button);
         }
         dialog.setVisible(true);
     }

     JDialog getDialog() {
         return dialog;
     }

     JButton getButton() {
         return button;
     }

     class ListenerForButtonOfTerminator implements ActionListener{

         private MainFrame mainFrame;

          ListenerForButtonOfTerminator(MainFrame mainFrame) {
             this.mainFrame = mainFrame;
         }

         @Override
         public void actionPerformed(ActionEvent e) {
              String nickname = mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer();
              ClientEvent requestToDoActionWithTerminator = new ClientEvent("", nickname);
              if(e.getActionCommand().equals("Move")){
                  requestToDoActionWithTerminator.setRequestToMoveTerminator(true);
              }
              if(e.getActionCommand().equals("Shoot")){
                  requestToDoActionWithTerminator.setRequestToShootWithTerminator(true);
             }
             if(e.getActionCommand().equals("Go on")){
                requestToDoActionWithTerminator.setRequestToGoOn(true);
             }
             dialog.setVisible(false);
             mainFrame.getRemoteView().notify(requestToDoActionWithTerminator);

         }
     }


 }

