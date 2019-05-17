package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

 class DialogForRequestToDraw2PC {

    private JDialog dialog;
    private MainFrame mainFrame;

     DialogForRequestToDraw2PC(MainFrame mainFrame, ServerEvent receivedEvent) {
        this.mainFrame = mainFrame;
        dialog = new JDialog();
        JFrame frame = mainFrame.getFrame();
        dialog.setAlwaysOnTop(true);
        dialog.setUndecorated(true);
        dialog.setSize((int)(frame.getWidth()/4.2), (int)(frame.getHeight()/3.6));
        int xPosition = (frame.getX()+frame.getWidth()- dialog.getWidth())/2;
        int yPosition = (frame.getY()+frame.getHeight()- dialog.getHeight())/2;
        dialog.setLocation(xPosition,yPosition);
        dialog.getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(SystemColor.menu);
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(70, 5, 5, 5));
        dialog.getContentPane().add(contentPanel, BorderLayout.CENTER);
        JTextArea message;
        if(mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved()))
            message = new JTextArea(receivedEvent.getMessageForInvolved());
        else
            message = new JTextArea(receivedEvent.getMessageForOthers());
        message.setEditable(false);
        message.setLineWrap(true);
        message.setBackground(SystemColor.menu);
        message.setColumns(35);
        message.setFont(new Font("Serif", Font.PLAIN, 16));
        contentPanel.add(message);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton button;
        if(mainFrame.getRemoteView().getNetworkHandler().getClient().getNickname().equals(receivedEvent.getNicknameInvolved())) {
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
}

