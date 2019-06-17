package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.SelectionMultiplePlayers;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToChooseMultiplePlayers;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PopupToChooseMultiplePlayers {
    private JDialog dialog;
    private MainFrame mainFrame;
    private ServerEvent serverEvent;
    private ArrayList<JCheckBox> listOfCheckBox = new ArrayList<>();
    private ArrayList<String> selectedNames = new ArrayList<>();

     PopupToChooseMultiplePlayers(ServerEvent receivedEvent, MainFrame mainFrame) {
         dialog = new JDialog(mainFrame.getFrame());
         dialog.setUndecorated(true);
         JPanel contentPanel = new JPanel(new GridBagLayout());
         dialog.getContentPane().add(contentPanel);
         contentPanel.setBorder(new EmptyBorder( 50, 50, 50, 50));

         ArrayList<String> namesOfPlayers = null;
         if(receivedEvent.isRequestToChooseMultiplePlayers())
             namesOfPlayers =(((RequestToChooseMultiplePlayers)receivedEvent).getNamesOfPlayers());

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.insets = new Insets(10, 5, 5, 5);

         JTextArea text = new JTextArea(receivedEvent.getMessageForInvolved());
         text.setBackground(SystemColor.menu);
         text.setEditable(false);
         contentPanel.add(text, gbc);



         ListenerForCheckBoxPlayers listenerForCheckBoxPlayers = new ListenerForCheckBoxPlayers(((RequestToChooseMultiplePlayers)receivedEvent).getNumOfMaxPlayersToChoose());

         for(String nickname : namesOfPlayers){
             JCheckBox checkBox = new JCheckBox(nickname);
             checkBox.setSelected(false);
             checkBox.setActionCommand(nickname);
             checkBox.addActionListener(listenerForCheckBoxPlayers);
             gbc.gridy++;
             listOfCheckBox.add(checkBox);
             contentPanel.add(checkBox,gbc);
         }

         ListenerForConfirmButton listenerForConfirmButton = new ListenerForConfirmButton(receivedEvent,mainFrame);
         JButton button = new JButton("Confirm");
         button.setActionCommand(button.getText());
         button.addActionListener(listenerForConfirmButton);
         gbc.gridy++;
         contentPanel.add(button,gbc);

         dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
         dialog.setVisible(true);


     }

    private class ListenerForCheckBoxPlayers implements ActionListener {
        private int numOfButtonsSelected = 0;
        private ArrayList<String> nameOfPlayers = new ArrayList<>();
        private int namOfMaxCheckBoxSelectable;

        ListenerForCheckBoxPlayers(int numOfMaxCheckBoxSelectionable) {
            this.namOfMaxCheckBoxSelectable = numOfMaxCheckBoxSelectionable;
        }

        private JCheckBox getCheckBoxPressed(String actionCommand) {
            for (JCheckBox checkBox : listOfCheckBox) {
                if (checkBox.getActionCommand().equals(actionCommand))
                    return checkBox;
            }
            throw new IllegalArgumentException();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (numOfButtonsSelected == namOfMaxCheckBoxSelectable) {
                for (JCheckBox checkBox : listOfCheckBox) {
                    if (checkBox.isSelected() && !checkBox.getActionCommand().equals(e.getActionCommand())) {
                        checkBox.setSelected(false);
                        selectedNames.add(e.getActionCommand());
                        selectedNames.remove(checkBox.getActionCommand());
                        break;
                    }
                }
            } else {
                selectedNames.add(e.getActionCommand());
                numOfButtonsSelected++;
            }
        }
    }



    private class ListenerForConfirmButton implements ActionListener{

         private ServerEvent serverEvent;
         private MainFrame mainFrame;

         ListenerForConfirmButton(ServerEvent receivedEvent, MainFrame mainFrame) {
            this.serverEvent = receivedEvent;
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SelectionMultiplePlayers selectionMultiplePlayers = new SelectionMultiplePlayers("", serverEvent.getNicknameInvolved());
            selectionMultiplePlayers.setNamesOfPlayers(selectedNames);
            mainFrame.getRemoteView().notify(selectionMultiplePlayers);
            dialog.setVisible(false);

        }
    }
}
