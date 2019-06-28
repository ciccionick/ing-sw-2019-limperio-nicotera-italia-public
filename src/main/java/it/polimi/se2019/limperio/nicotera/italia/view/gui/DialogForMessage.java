package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestChooseActionForTerminator;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToSelectionPlayerToAttackWithTerminator;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

class DialogForMessage {

     private JDialog dialog;
     private JButton button;

     DialogForMessage(MainFrame mainFrame, ServerEvent receivedEvent) {
         dialog = new JDialog(mainFrame.getFrame());
         dialog.setLocationRelativeTo(mainFrame.getFrame());
         JFrame frame = mainFrame.getFrame();
         dialog.setUndecorated(true);
         dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
         dialog.getContentPane().setLayout(new BorderLayout());
         JPanel contentPanel = new JPanel();
         contentPanel.setBorder(new EmptyBorder(frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20), frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20)));
         contentPanel.setBackground(SystemColor.menu);
         contentPanel.setLayout(new GridBagLayout());
         dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
         JTextArea message;
         if (mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()))
             message = new JTextArea(receivedEvent.getMessageForInvolved());
         else
             message = new JTextArea(receivedEvent.getMessageForOthers());
         message.setEditable(false);
         message.setLineWrap(false);
         message.setBackground(SystemColor.menu);
         message.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
         GridBagConstraints gbcMessage = new GridBagConstraints();
         gbcMessage.gridx = 0;
         gbcMessage.gridy = 0;
         gbcMessage.insets = new Insets(frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 5), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10), frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 5), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10));
         contentPanel.add(message, gbcMessage);


         if(receivedEvent.isRequestToSelectionPlayerToAttackWithTerminator()){
             ArrayList<String> nicknames = ((RequestToSelectionPlayerToAttackWithTerminator)receivedEvent).getNicknamesOfPlayersAttachable();
             PanelForPlayersButton panelForPlayersButton = new PanelForPlayersButton(dialog, mainFrame, nicknames);
             dialog.getContentPane().add(panelForPlayersButton.getPanelButtons(), BorderLayout.SOUTH);
         }
         else {
             if (receivedEvent.isRequestToChooseTerminatorAction()) {
                 JPanel buttonPanel = new JPanel();
                 dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                 ListenerForButtonOfTerminator listenerForButtonOfTerminator = new ListenerForButtonOfTerminator(mainFrame,dialog);
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
                 Insets insets = new Insets(frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20), frame.getHeight()/mainFrame.resizeInFunctionOfFrame(true, 20), frame.getWidth()/mainFrame.resizeInFunctionOfFrame(false, 20));
                 gbcButtonMove.insets = insets;
                 gbcButtonShoot.insets = insets;
                 gbcButtonGoOn.insets = insets;
                 buttonPanel.add(buttonMove, gbcButtonMove);
                 buttonPanel.add(buttonShoot, gbcButtonShoot);
                 buttonPanel.add(buttonGoOn, gbcButtonGoOn);

             } else {
                 JPanel buttonPanel = new JPanel();
                 buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                 dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                 if (mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()) && (receivedEvent.isRequestForDrawTwoPowerUpCardsEvent()||receivedEvent.isRequestForDrawOnePowerUpCardEvent())) {
                     button = new JButton("DRAW");
                     button.setActionCommand("DRAW");
                 } else {
                     button = new JButton("OK");
                     button.setActionCommand("OK");
                 }
                 button.addActionListener(new ListenerDrawOkButton(dialog, mainFrame, 0));
                if(receivedEvent.isRequestForDrawOnePowerUpCardEvent())
                    button.addActionListener(new ListenerDrawOkButton(dialog, mainFrame,1));
                if(receivedEvent.isRequestForDrawTwoPowerUpCardsEvent())
                    button.addActionListener(new ListenerDrawOkButton(dialog, mainFrame,2));
                 buttonPanel.add(button);
                 dialog.getRootPane().setDefaultButton(button);
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

