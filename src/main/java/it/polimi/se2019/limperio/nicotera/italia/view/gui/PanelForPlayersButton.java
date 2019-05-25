package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.TerminatorShootEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PanelForPlayersButton {

    private JPanel panelButtons;
    private ArrayList<JButton> buttons = new ArrayList<>();

     PanelForPlayersButton(JDialog dialog, MainFrame mainFrame, ArrayList<String> nicknamesOfPlayers) {
        panelButtons = new JPanel();
        panelButtons.setLayout(new GridBagLayout());
        ListenerForPlayerButtons listenerForPlayerButtons = new ListenerForPlayerButtons(mainFrame, dialog);
        while(!(nicknamesOfPlayers.isEmpty())){
            buttons.add(new JButton(nicknamesOfPlayers.remove(0)));
            buttons.get(buttons.size()-1).setActionCommand(buttons.get(buttons.size()-1).getText());
            buttons.get(buttons.size()-1).addActionListener(listenerForPlayerButtons);
        }
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(50, 100, 100, 100);
        gbc.gridx = 0;
        gbc.gridy = 0;
         panelButtons.add(buttons.get(0),gbc);
         gbc.insets.top=10;
         gbc.insets.bottom = 0;
         for(int i=1; i<buttons.size();i++){
             gbc.gridy++;
             panelButtons.add(buttons.get(i),gbc);
         }
    }

     JPanel getPanelButtons() {
        return panelButtons;
    }

    class ListenerForPlayerButtons implements ActionListener{

         private MainFrame mainFrame;
         private JDialog dialog;

         ListenerForPlayerButtons(MainFrame mainFrame, JDialog dialog) {
            this.mainFrame = mainFrame;
            this.dialog = dialog;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dialog.setVisible(false);
            TerminatorShootEvent terminatorShootEvent = new TerminatorShootEvent("" , mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
            terminatorShootEvent.setNicknamePlayerToAttack(e.getActionCommand());
            mainFrame.getRemoteView().notify(terminatorShootEvent);
        }
    }
}
