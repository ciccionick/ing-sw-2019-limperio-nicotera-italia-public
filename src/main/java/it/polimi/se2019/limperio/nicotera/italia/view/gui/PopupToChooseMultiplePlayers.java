package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionMultiplePlayers;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseMultiplePlayers;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Handles the creation of a dialog to let the player choose multiple players.
 * @author Pietro L'Imperio.
 */
class PopupToChooseMultiplePlayers {
    /**
     * Dialog created in the constructor.
     */
    private JDialog dialog;
    /**
     * The list of check box to choose players.
     */
    private ArrayList<JCheckBox> listOfCheckBox = new ArrayList<>();
    /**
     * The list of nicknames of the players chosen through the check boxes.
     */
    private ArrayList<String> selectedNames = new ArrayList<>();

    /**
     * Constructor where the dialog is created.
     * @param receivedEvent Event received by server.
     * @param mainFrame Reference of main frame.
     */
     PopupToChooseMultiplePlayers(ServerEvent receivedEvent, MainFrame mainFrame) {
         dialog = new JDialog(mainFrame.getFrame());
         dialog.setUndecorated(true);
         dialog.setAutoRequestFocus(true);
         JPanel contentPanel = new JPanel(new GridBagLayout());
         dialog.getContentPane().add(contentPanel);
         int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 50);
         int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 50);
         contentPanel.setBorder(new EmptyBorder( topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));

         ArrayList<String> namesOfPlayers = null;
         if(receivedEvent.isRequestToChooseMultiplePlayers())
             namesOfPlayers =(((RequestToChooseMultiplePlayers)receivedEvent).getNamesOfPlayers());

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.insets = new Insets(topBottomBorder/5, leftRightBorder/10, topBottomBorder/10, leftRightBorder/10);

         JTextArea text = new JTextArea(receivedEvent.getMessageForInvolved());
         text.setBackground(SystemColor.menu);
         text.setEditable(false);
         contentPanel.add(text, gbc);



         ListenerForCheckBoxPlayers listenerForCheckBoxPlayers = new ListenerForCheckBoxPlayers(((RequestToChooseMultiplePlayers)receivedEvent).getNumOfMaxPlayersToChoose(),this);

         for(String nickname : namesOfPlayers){
             JCheckBox checkBox = new JCheckBox(nickname);
             checkBox.setSelected(false);
             checkBox.setActionCommand(nickname);
             checkBox.addActionListener(listenerForCheckBoxPlayers);
             gbc.gridy++;
             listOfCheckBox.add(checkBox);
             contentPanel.add(checkBox,gbc);
         }

         JButton button = new JButton("Confirm");
         button.setActionCommand(button.getText());
         button.addActionListener(e -> {
             SelectionMultiplePlayers selectionMultiplePlayers = new SelectionMultiplePlayers("", receivedEvent.getNicknameInvolved());
             selectionMultiplePlayers.setNamesOfPlayers(selectedNames);
             mainFrame.getRemoteView().notify(selectionMultiplePlayers);
             dialog.setVisible(false);
         });
         gbc.gridy++;
         contentPanel.add(button,gbc);

         dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
         dialog.setVisible(true);
     }

     ArrayList<JCheckBox> getListOfCheckBox() {
        return listOfCheckBox;
    }

     ArrayList<String> getSelectedNames() {
        return selectedNames;
    }
}
