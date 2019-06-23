package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PanelOfPlayers extends JPanel {

    private MainFrame mainFrame;
    private JButton buttonMSelection;
    private JButton buttonDisableSelection;

    PanelOfPlayers(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);
        int insetTopForText;
        int insetTopForButtons;
        int insetRightForLabel = (int) (mainFrame.getFrame().getSize().getWidth()/64);
        int yForButtonOfMap = 7;
        ArrayList<Color> colorsOfPlayers = new ArrayList<>();

         for(int i = 0 ;i<mainFrame.getRemoteView().getListOfPlayerBoardViews().size(); i++){
             switch (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(i).getColorOfPlayer()){
                 case BLUE:
                     colorsOfPlayers.add(Color.BLUE);
                     break;
                 case PURPLE:
                     colorsOfPlayers.add(Color.MAGENTA);
                     break;
                 case GREY:
                     colorsOfPlayers.add(Color.GRAY);
                     break;
                 case GREEN:
                     colorsOfPlayers.add(Color.GREEN);
                     break;
                     default:
                         colorsOfPlayers.add(Color.YELLOW);
             }
         }

        insetTopForText = (int) (mainFrame.getFrame().getSize().getHeight()/108);
        insetTopForButtons = 2*insetTopForText;

        JLabel text = new JLabel("Players in game (in order) :");
        text.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.fill = GridBagConstraints.BOTH;
        gbcText.insets = new Insets(0, 0, insetTopForText, 0);
        gbcText.gridwidth = 3;
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        add(text, gbcText);

        JLabel labelNickname = new JLabel("Nickname: ");
        labelNickname.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelNickname.setForeground(Color.WHITE);

        GridBagConstraints gbcLabelNickname = new GridBagConstraints();
        gbcLabelNickname.insets = new Insets(insetTopForButtons, 0, 0, insetRightForLabel );
        gbcLabelNickname.gridx = 0;
        gbcLabelNickname.gridy = 1;
        gbcLabelNickname.anchor = GridBagConstraints.CENTER;
        add(labelNickname, gbcLabelNickname);

        JLabel labelPB = new JLabel("PLAYER BOARD: ");
        labelPB.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPB.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPB = new GridBagConstraints();
        gbcLabelPB.insets = new Insets(insetTopForButtons, 0, 0, insetRightForLabel);
        gbcLabelPB.gridx = 1;
        gbcLabelPB.gridy = 1;
        gbcLabelPB.gridwidth = 3;
        gbcLabelPB.anchor = GridBagConstraints.CENTER;
        add(labelPB, gbcLabelPB);


        JLabel labelPlayer1 = new JLabel();
         if (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(0).getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())) {
             labelPlayer1.setText("ME");
         }
         else
             labelPlayer1.setText(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(0).getNicknameOfPlayer());
        labelPlayer1.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPlayer1.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPlayer1 = new GridBagConstraints();
        gbcLabelPlayer1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelPlayer1.anchor = GridBagConstraints.WEST;
        gbcLabelPlayer1.gridx = 0;
        gbcLabelPlayer1.gridy = 2;
        add(labelPlayer1, gbcLabelPlayer1);

        JLabel labelPlayer2 = new JLabel();
         if (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(1).getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())) {
             labelPlayer2.setText("ME");
         }
         else
             labelPlayer2.setText(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(1).getNicknameOfPlayer());
        labelPlayer2.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPlayer2.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPlayer2 = new GridBagConstraints();
        gbcLabelPlayer2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelPlayer2.anchor = GridBagConstraints.WEST;
        gbcLabelPlayer2.gridx = 0;
        gbcLabelPlayer2.gridy = 3;
        add(labelPlayer2, gbcLabelPlayer2);

        JLabel labelPlayer3 = new JLabel();
         if (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(2).getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())) {
             labelPlayer3.setText("ME");
         }
         else
             labelPlayer3.setText(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(2).getNicknameOfPlayer());
        labelPlayer3.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPlayer3.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPlayer3 = new GridBagConstraints();
        gbcLabelPlayer3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelPlayer3.anchor = GridBagConstraints.WEST;
        gbcLabelPlayer3.gridx = 0;
        gbcLabelPlayer3.gridy = 4;
        add(labelPlayer3, gbcLabelPlayer3);

        JLabel labelPlayer4 = null;
        JLabel labelPlayer5 = null;

        if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>3) {
           labelPlayer4 = new JLabel();
            if (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(3).getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())) {
                labelPlayer4.setText("ME");
            }
            else
                labelPlayer4.setText(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(3).getNicknameOfPlayer());
           labelPlayer4.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
           labelPlayer4.setForeground(Color.WHITE);
           GridBagConstraints gbcLabelPlayer4 = new GridBagConstraints();
           gbcLabelPlayer4.insets = new Insets(insetTopForButtons, 0, 0, 0);
           gbcLabelPlayer4.anchor = GridBagConstraints.WEST;
           gbcLabelPlayer4.gridx = 0;
           gbcLabelPlayer4.gridy = 5;
           add(labelPlayer4, gbcLabelPlayer4);

           if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>4) {
              labelPlayer5 = new JLabel();
               if (mainFrame.getRemoteView().getListOfPlayerBoardViews().get(4).getNicknameOfPlayer().equals(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer())) {
                   labelPlayer5.setText("ME");
               }
               else
                   labelPlayer5.setText(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(4).getNicknameOfPlayer());
              labelPlayer5.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
              labelPlayer5.setForeground(Color.WHITE);
              GridBagConstraints gbcLabelPlayer5 = new GridBagConstraints();
              gbcLabelPlayer5.insets = new Insets(insetTopForButtons, 0, 0, 0);
              gbcLabelPlayer5.anchor = GridBagConstraints.WEST;
              gbcLabelPlayer5.gridx = 0;
              gbcLabelPlayer5.gridy = 6;
              add(labelPlayer5, gbcLabelPlayer5);
           }
           else
              yForButtonOfMap=6;
        }
        else
           yForButtonOfMap = 5;

        ButtonPBListener buttonPBListener = new ButtonPBListener(mainFrame);

        JToggleButton buttonPB1 = new JToggleButton(" ");

        buttonPB1.setBackground(colorsOfPlayers.get(0));
        buttonPB1.setActionCommand(labelPlayer1.getText());
        buttonPB1.addActionListener(buttonPBListener);
        GridBagConstraints gbcButtonPB1 = new GridBagConstraints();
        gbcButtonPB1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB1.anchor = GridBagConstraints.CENTER;
        gbcButtonPB1.gridx = 1;
        gbcButtonPB1.gridy = 2;
        if(mainFrame.getLeftPanel().getPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(0).getNicknameOfPlayer()))
           buttonPB1.setSelected(true);
        add(buttonPB1, gbcButtonPB1);

        JToggleButton buttonPB2 = new JToggleButton(" ");
        GridBagConstraints gbcButtonPB2 = new GridBagConstraints();
        buttonPB2.setBackground(colorsOfPlayers.get(1));
        buttonPB2.addActionListener(buttonPBListener);
        buttonPB2.setActionCommand(labelPlayer2.getText());
        gbcButtonPB2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB2.anchor = GridBagConstraints.CENTER;
        gbcButtonPB2.gridx = 1;
        gbcButtonPB2.gridy = 3;
        if(mainFrame.getLeftPanel().getPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(1).getNicknameOfPlayer()))
           buttonPB2.setSelected(true);
        add(buttonPB2, gbcButtonPB2);

        JToggleButton buttonPB3 = new JToggleButton(" ");
        GridBagConstraints gbcButtonPB3 = new GridBagConstraints();
        buttonPB3.setBackground(colorsOfPlayers.get(2));
        buttonPB3.addActionListener(buttonPBListener);
        buttonPB3.setActionCommand(labelPlayer3.getText());
        gbcButtonPB3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB3.anchor = GridBagConstraints.CENTER;
        gbcButtonPB3.gridx = 1;
        gbcButtonPB3.gridy = 4;
        if(mainFrame.getLeftPanel().getPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(2).getNicknameOfPlayer()))
           buttonPB3.setSelected(true);
        add(buttonPB3, gbcButtonPB3);

        JToggleButton buttonPB4=null;
        JToggleButton buttonPB5=null;

        if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>3) {
           buttonPB4 = new JToggleButton(" ");
           GridBagConstraints gbcButtonPB4 = new GridBagConstraints();
           buttonPB4.setBackground(colorsOfPlayers.get(3));
           buttonPB4.addActionListener(buttonPBListener);
           if(labelPlayer4!=null)
            buttonPB4.setActionCommand(labelPlayer4.getText());
           gbcButtonPB4.insets = new Insets(insetTopForButtons, 0, 0, 0);
           gbcButtonPB4.anchor = GridBagConstraints.CENTER;
           gbcButtonPB4.gridx = 1;
           gbcButtonPB4.gridy = 5;
            if(mainFrame.getLeftPanel().getPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(3).getNicknameOfPlayer()))
              buttonPB4.setSelected(true);
           add(buttonPB4, gbcButtonPB4);

           if (mainFrame.getRemoteView().getListOfPlayerBoardViews().size() > 4) {

              buttonPB5 = new JToggleButton(" ");
              GridBagConstraints gbcButtonPB5 = new GridBagConstraints();
              buttonPB5.setBackground(colorsOfPlayers.get(4));
              buttonPB5.addActionListener(buttonPBListener);
              if (labelPlayer5!=null)
               buttonPB5.setActionCommand(labelPlayer5.getText());
              gbcButtonPB5.insets = new Insets(insetTopForButtons, 0, 0, 0);
              gbcButtonPB5.anchor = GridBagConstraints.CENTER;
              gbcButtonPB5.gridx = 1;
              gbcButtonPB5.gridy = 6;
               if(mainFrame.getLeftPanel().getPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(4).getNicknameOfPlayer()))
                 buttonPB5.setSelected(true);
              add(buttonPB5, gbcButtonPB5);
           }
        }


        ButtonGroup pbButtonGroup = new ButtonGroup();
        pbButtonGroup.add(buttonPB1);
        pbButtonGroup.add(buttonPB2);
        pbButtonGroup.add(buttonPB3);
        if(buttonPB4!=null)
           pbButtonGroup.add(buttonPB4);
        if(buttonPB5!=null)
           pbButtonGroup.add(buttonPB5);

      ButtonMListener buttonMListener = new ButtonMListener(mainFrame, mainFrame.getMapPanel());

         buttonMSelection = new JButton("Return to selection");
         buttonMSelection.setSelected(false);
         buttonMSelection.setEnabled(false);
         buttonMSelection.addActionListener(buttonMListener);
         buttonMSelection.setActionCommand("Selection");
         GridBagConstraints gbcButtonSelection = new GridBagConstraints();
         gbcButtonSelection.insets = new Insets(insetTopForButtons, 0, 0, 0);
         gbcButtonSelection.gridx = 1;
         gbcButtonSelection.gridy = yForButtonOfMap;
         add(buttonMSelection, gbcButtonSelection);

         buttonDisableSelection = new JButton("Return to normal view");
         buttonDisableSelection.setEnabled(mainFrame.getRemoteView().getMapView().isHasToChooseASquare());
         buttonDisableSelection.addActionListener(buttonMListener);
         buttonDisableSelection.setActionCommand("Disable");
        GridBagConstraints gbcButtonDisable = new GridBagConstraints();
        gbcButtonDisable.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonDisable.gridx = 1;
        gbcButtonDisable.gridy = yForButtonOfMap+1;
        add(buttonDisableSelection, gbcButtonDisable);

    }

      private JButton getButtonMSelection() {
        return buttonMSelection;
    }


    class ButtonPBListener implements ActionListener{

        private MainFrame mainFrame;

        ButtonPBListener(MainFrame mainFrame) {
          this.mainFrame = mainFrame;
       }

       @Override
       public void actionPerformed(ActionEvent e) {

          mainFrame.getFrame().getContentPane().remove(mainFrame.getLeftPanel());
          mainFrame.setLeftPanel(new LeftPanel(mainFrame, mainFrame.getRemoteView().getPlayerBoardViewOfThisPlayer(e.getActionCommand())));
          mainFrame.getFrame().getContentPane().add(mainFrame.getLeftPanel(), BorderLayout.WEST);

          mainFrame.getFrame().getContentPane().repaint();
          mainFrame.getFrame().getContentPane().validate();
       }
    }

    class ButtonMListener implements ActionListener {

        private MainFrame mainFrame;
        private MapPanel mapPanel;

        ButtonMListener(MainFrame mainFrame, MapPanel mapPanel) {
            this.mainFrame = mainFrame;
            this.mapPanel = mapPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Disable")) {
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(false);
                for (JLabel label : mainFrame.getMapPanel().getHashMapForCell().values()) {
                    label.setEnabled(true);
                }
                buttonMSelection.setEnabled(true);
                buttonDisableSelection.setEnabled(false);
            } else {
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(true);
                mainFrame.updateEnableSquares(mainFrame.getRemoteView().getMapView().getReachableSquares());
                buttonMSelection.setEnabled(false);
                buttonDisableSelection.setEnabled(true);
            }
        }
    }


}
