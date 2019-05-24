package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.*;

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
        dialog.setSize((int)(frame.getWidth()/4.2), (int)(frame.getHeight()/3.6));
        int xPosition = (frame.getX()+frame.getWidth()- dialog.getWidth())/2;
        int yPosition = (frame.getY()+frame.getHeight()- dialog.getHeight())/2;
        dialog.setLocation(xPosition,yPosition);
        dialog.getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(SystemColor.menu);
        contentPanel.setLayout(new GridBagLayout());
        dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        JTextArea message;
        if(mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()))
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

        if(mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved())&&receivedEvent.isRequestForDrawTwoPowerUpCardsEvent()) {
            button = new JButton("DRAW");
            button.setActionCommand("DRAW");
        }
        else{
            button = new JButton("OK");
            button.setActionCommand("OK");
        }
        button.addActionListener(new DrawOkButtonListener(dialog,mainFrame));
        buttonPanel.add(button);
        dialog.getRootPane().setDefaultButton(button);
        dialog.setVisible(true);
    }

     JDialog getDialog() {
       return dialog;
    }

     JButton getButton() {
       return button;
    }
 }

