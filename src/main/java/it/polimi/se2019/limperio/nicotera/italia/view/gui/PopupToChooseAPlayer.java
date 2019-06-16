package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ChoosePlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToSelectionPlayerToAttackWithTerminator;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PopupToChooseAPlayer {

    private MainFrame mainFrame;
    private ServerEvent event;
    private JDialog dialog;

    PopupToChooseAPlayer(MainFrame mainFrame, ServerEvent receivedEvent) {
        this.mainFrame = mainFrame;
        this.event = receivedEvent;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel contentPane = new JPanel(new GridBagLayout());
        dialog.getContentPane().add(contentPane);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        ListenerForButtonPlayers listenerForButtonPlayers = new ListenerForButtonPlayers();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);


        JTextArea text = new JTextArea(event.getMessageForInvolved());
        text.setEditable(false);
        text.setBackground(SystemColor.menu);
        contentPane.add(text, gbc);
        gbc.gridy++;
        ArrayList<String> attackablePlayers = null;
        if (receivedEvent.isRequestToChooseAPlayer())
            attackablePlayers = ((RequestToChooseAPlayer) receivedEvent).getNameOfPlayers();
        if (receivedEvent.isRequestToSelectionPlayerToAttackWithTerminator())
            attackablePlayers = ((RequestToSelectionPlayerToAttackWithTerminator) receivedEvent).getNicknamesOfPlayersAttachable();
        for (String name : attackablePlayers) {
            JButton buttonName = new JButton(name);
            buttonName.setActionCommand(name);
            buttonName.addActionListener(listenerForButtonPlayers);
            contentPane.add(buttonName, gbc);
            gbc.gridy++;
        }

        dialog.pack();
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
        dialog.setVisible(true);
    }


    private class ListenerForButtonPlayers implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (event.isRequestToSelectionPlayerToAttackWithTerminator()) {
                TerminatorShootEvent terminatorShootEvent = new TerminatorShootEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                terminatorShootEvent.setNicknamePlayerToAttack(e.getActionCommand());
                mainFrame.getRemoteView().notify(terminatorShootEvent);

            }
            if (event.isRequestToChooseAPlayer() && ((RequestToChooseAPlayer) event).isToUseTargeting()) {
                ChoosePlayer newEvent = new ChoosePlayer("", event.getNicknameInvolved());
                newEvent.setToTargeting(true);
                newEvent.setNameOfPlayer(e.getActionCommand());
                mainFrame.getRemoteView().notify(newEvent);
                dialog.setVisible(false);
            } else if (((RequestToChooseAPlayer) event).isChoosePlayerForNewton()) {
                ChoosePlayer newEvent = new ChoosePlayer("", event.getNicknameInvolved());
                newEvent.setToNewton(true);
                newEvent.setNameOfPlayer(e.getActionCommand());
                mainFrame.getRemoteView().notify(newEvent);
                dialog.setVisible(false);
            }
            dialog.setVisible(false);
        }
    }
}
