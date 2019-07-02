package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ClientEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons concerning the choose of a particular action that terminator has to do.
 * @author Pietro L'Imperio
 */
  public class ListenerForButtonOfTerminator implements ActionListener {

    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;
    /**
     * The dialog where happens the event that call this listener.
     */
    private JDialog dialog;

    /**
     * Constructor that initialize class fields.
     * @param mainFrame The reference of main frame.
     * @param dialog The reference of dialog.
     */
        ListenerForButtonOfTerminator(MainFrame mainFrame, JDialog dialog) {
            this.mainFrame = mainFrame;
            this.dialog = dialog;
        }

    /**
     * Creates an event (ClientEvent) in according to the button pressed and the calls the notify of the remote view.
     */
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
