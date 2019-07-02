package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.ChoosePlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons concerning the choose of a player in three different occasion.
 * The first is when the terminator has to attack someone he can see.
 * The second is when someone needs to choose a player to use Targeting scope on him.
 * The third one is when someone has to choose a player to attack him with some weapon.
 * @author Pietro L'Imperio
 */
 class ListenerForButtonPlayers implements ActionListener {

    /**
     * The reference of the main frame.
     */
     private MainFrame mainFrame;
    /**
     * The dialog where happens the event that call this listener.
     */
     private JDialog dialog;
    /**
     * The event received by server side that handles the creation of the dialog where the choose happens.
     */
    private ServerEvent event;


    /**
     * Constructor that initialize class fields.
     * @param mainFrame The reference of main frame.
     * @param dialog The reference of dialog.
     * @param event The reference of the event.
     */
      ListenerForButtonPlayers(MainFrame mainFrame, JDialog dialog, ServerEvent event) {
         this.mainFrame = mainFrame;
         this.dialog = dialog;
         this.event = event;
     }

    /**
     * Creates an event in according to the event received by server side with the information caught by the buttons that has as action command the nickname of the players available.
     * At the end calls the notify of the remote view.
     */
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

