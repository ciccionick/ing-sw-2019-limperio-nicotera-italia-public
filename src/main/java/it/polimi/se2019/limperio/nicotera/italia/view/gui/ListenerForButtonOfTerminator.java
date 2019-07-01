package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


  public class ListenerForButtonOfTerminator implements ActionListener {

        private MainFrame mainFrame;
        private JDialog dialog;

        ListenerForButtonOfTerminator(MainFrame mainFrame, JDialog dialog) {
            this.mainFrame = mainFrame;
            this.dialog = dialog;
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
