package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseAPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToSelectionPlayerToAttackWithTerminator;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of a dialog useful to let the player choose another player.
 * @author Pietro L'Imperio.
 */
class PopupToChooseAPlayer {

    /**
     * Event received by server
     */
    private ServerEvent event;
    /**
     * Dialog created in the constructor.
     */
    private JDialog dialog;

    /**
     * Constructor that creates a dialog differentiate according to the type of event is handling.
     * @param mainFrame Reference of main frame.
     * @param receivedEvent Event received by the server.
     */
    PopupToChooseAPlayer(MainFrame mainFrame, ServerEvent receivedEvent) {
        this.event = receivedEvent;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setUndecorated(true);
        dialog.setAutoRequestFocus(true);
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel contentPane = new JPanel(new GridBagLayout());
        dialog.getContentPane().add(contentPane);

        int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 10);
        int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10);

        contentPane.setBorder(new EmptyBorder(topBottomBorder,leftRightBorder,topBottomBorder,leftRightBorder));

        ListenerForButtonPlayers listenerForButtonPlayers = new ListenerForButtonPlayers(mainFrame,dialog,event);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder);


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
        if(receivedEvent.isRequestToChooseAPlayer() && ((RequestToChooseAPlayer) receivedEvent).isCanRefuse()){
            JButton buttonName = new JButton("NO ONE");
            buttonName.setActionCommand(buttonName.getText());
            buttonName.addActionListener(listenerForButtonPlayers);
            contentPane.add(buttonName, gbc);
        }

        dialog.pack();
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
        dialog.setVisible(true);
    }

}
