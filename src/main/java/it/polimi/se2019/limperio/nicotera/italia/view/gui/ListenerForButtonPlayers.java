package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ChoosePlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class ListenerForButtonPlayers implements ActionListener {

     private MainFrame mainFrame;
     private JDialog dialog;
     private ServerEvent event;

      ListenerForButtonPlayers(MainFrame mainFrame, JDialog dialog, ServerEvent event) {
         this.mainFrame = mainFrame;
         this.dialog = dialog;
         this.event = event;
     }

     @Override
        public void actionPerformed(ActionEvent e) {
            if (event.isRequestToSelectionPlayerToAttackWithTerminator()) {
                TerminatorShootEvent terminatorShootEvent = new TerminatorShootEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                terminatorShootEvent.setNicknamePlayerToAttack(e.getActionCommand());
                mainFrame.getRemoteView().notify(terminatorShootEvent);
                dialog.setVisible(false);
            }
            if (event.isRequestToChooseAPlayer() && ((RequestToChooseAPlayer) event).isToUseTargeting()) {
                ChoosePlayer newEvent = new ChoosePlayer("", event.getNicknameInvolved());
                newEvent.setToTargeting(true);
                newEvent.setNameOfPlayer(e.getActionCommand());
                mainFrame.getRemoteView().notify(newEvent);
                dialog.setVisible(false);
            } else if (!event.isRequestToSelectionPlayerToAttackWithTerminator() && ((RequestToChooseAPlayer)event).isChoosePlayerForNewton()) {
                ChoosePlayer newEvent = new ChoosePlayer("", event.getNicknameInvolved());
                newEvent.setToNewton(true);
                newEvent.setNameOfPlayer(e.getActionCommand());
                mainFrame.getRemoteView().notify(newEvent);
                dialog.setVisible(false);
            }
            else if(event.isRequestToChooseAPlayer() && ((RequestToChooseAPlayer)event).isChoosePlayerForAttack()){
                ChoosePlayer newEvent = new ChoosePlayer("", event.getNicknameInvolved());
                newEvent.setForAttack(true);
                if(!e.getActionCommand().equals("NO ONE"))
                    newEvent.setNameOfPlayer(e.getActionCommand());
                else
                    newEvent.setNameOfPlayer(null);
                mainFrame.getRemoteView().notify(newEvent);
                dialog.setVisible(false);
            }
            dialog.setVisible(false);
        }
    }

