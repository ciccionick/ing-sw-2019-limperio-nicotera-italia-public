package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.model.Player;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.MapView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class PanelOfPlayers extends JPanel {

    private MainFrame mainFrame;

     PanelOfPlayers(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);
        int insetTopForText;
        int insetTopForButtons;
        int insetRightForLabel = (int) (mainFrame.getFrame().getSize().getWidth()/64);
        int indexForLabellAll = 7;

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

        JLabel labelPB = new JLabel("PB: ");
        labelPB.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPB.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPB = new GridBagConstraints();
        gbcLabelPB.insets = new Insets(insetTopForButtons, 0, 0, insetRightForLabel);
        gbcLabelPB.gridx = 1;
        gbcLabelPB.gridy = 1;
        gbcLabelPB.anchor = GridBagConstraints.CENTER;
        add(labelPB, gbcLabelPB);

        JLabel labelMap = new JLabel("MAP: ");
        labelMap.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelMap.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelMap = new GridBagConstraints();
        gbcLabelMap.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelMap.gridx = 2;
        gbcLabelMap.gridy = 1;
        gbcLabelMap.anchor = GridBagConstraints.CENTER;
        add(labelMap, gbcLabelMap);


        JLabel labelPlayer1 = new JLabel(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(0).getNicknameOfPlayer());
        labelPlayer1.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPlayer1.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPlayer1 = new GridBagConstraints();
        gbcLabelPlayer1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelPlayer1.anchor = GridBagConstraints.WEST;
        gbcLabelPlayer1.gridx = 0;
        gbcLabelPlayer1.gridy = 2;
        add(labelPlayer1, gbcLabelPlayer1);

        JLabel labelPlayer2 = new JLabel(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(1).getNicknameOfPlayer());
        labelPlayer2.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        labelPlayer2.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelPlayer2 = new GridBagConstraints();
        gbcLabelPlayer2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelPlayer2.anchor = GridBagConstraints.WEST;
        gbcLabelPlayer2.gridx = 0;
        gbcLabelPlayer2.gridy = 3;
        add(labelPlayer2, gbcLabelPlayer2);

        JLabel labelPlayer3 = new JLabel(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(2).getNicknameOfPlayer());
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
           labelPlayer4 = new JLabel(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(3).getNicknameOfPlayer());
           labelPlayer4.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
           labelPlayer4.setForeground(Color.WHITE);
           GridBagConstraints gbcLabelPlayer4 = new GridBagConstraints();
           gbcLabelPlayer4.insets = new Insets(insetTopForButtons, 0, 0, 0);
           gbcLabelPlayer4.anchor = GridBagConstraints.WEST;
           gbcLabelPlayer4.gridx = 0;
           gbcLabelPlayer4.gridy = 5;
           add(labelPlayer4, gbcLabelPlayer4);

           if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>4) {
              labelPlayer5 = new JLabel(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(4).getNicknameOfPlayer());
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
              indexForLabellAll=6;
        }
        else
           indexForLabellAll = 5;

        JLabel labelAll = new JLabel("All");
        labelAll.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        labelAll.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelAll = new GridBagConstraints();
        gbcLabelAll.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelAll.anchor = GridBagConstraints.WEST;
        gbcLabelAll.gridx = 0;
        gbcLabelAll.gridy = indexForLabellAll;
        add(labelAll, gbcLabelAll);

        JLabel labelNone = new JLabel("None");
        labelNone.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        labelNone.setForeground(Color.WHITE);
        GridBagConstraints gbcLabelNone = new GridBagConstraints();
        gbcLabelNone.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcLabelNone.anchor = GridBagConstraints.WEST;
        gbcLabelNone.gridx = 0;
        gbcLabelNone.gridy = indexForLabellAll+1;
        add(labelNone, gbcLabelNone);

        ButtonPBListener buttonPBListener = new ButtonPBListener(mainFrame);

        JToggleButton buttonPB1 = new JToggleButton(" ");

        buttonPB1.setBackground(Color.BLACK);
        buttonPB1.setActionCommand(labelPlayer1.getText());
        buttonPB1.addActionListener(buttonPBListener);
        GridBagConstraints gbcButtonPB1 = new GridBagConstraints();
        gbcButtonPB1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB1.anchor = GridBagConstraints.WEST;
        gbcButtonPB1.gridx = 1;
        gbcButtonPB1.gridy = 2;
        if(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(0).getNicknameOfPlayer()))
           buttonPB1.setSelected(true);
        add(buttonPB1, gbcButtonPB1);

        JToggleButton buttonPB2 = new JToggleButton(" ");
        GridBagConstraints gbcButtonPB2 = new GridBagConstraints();
        buttonPB2.setBackground(Color.BLACK);
        buttonPB2.addActionListener(buttonPBListener);
        buttonPB2.setActionCommand(labelPlayer2.getText());
        gbcButtonPB2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB2.anchor = GridBagConstraints.WEST;
        gbcButtonPB2.gridx = 1;
        gbcButtonPB2.gridy = 3;
        if(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(1).getNicknameOfPlayer()))
           buttonPB2.setSelected(true);
        add(buttonPB2, gbcButtonPB2);

        JToggleButton buttonPB3 = new JToggleButton(" ");
        GridBagConstraints gbcButtonPB3 = new GridBagConstraints();
        buttonPB3.setBackground(Color.BLACK);
        buttonPB3.addActionListener(buttonPBListener);
        buttonPB3.setActionCommand(labelPlayer3.getText());
        gbcButtonPB3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonPB3.anchor = GridBagConstraints.WEST;
        gbcButtonPB3.gridx = 1;
        gbcButtonPB3.gridy = 4;
        if(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(2).getNicknameOfPlayer()))
           buttonPB3.setSelected(true);
        add(buttonPB3, gbcButtonPB3);

        JToggleButton buttonPB4=null;
        JToggleButton buttonPB5=null;

        if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>3) {
           buttonPB4 = new JToggleButton(" ");
           GridBagConstraints gbcButtonPB4 = new GridBagConstraints();
           buttonPB4.setBackground(Color.BLACK);
           buttonPB4.addActionListener(buttonPBListener);
           if(labelPlayer4!=null)
            buttonPB4.setActionCommand(labelPlayer4.getText());
           gbcButtonPB4.insets = new Insets(insetTopForButtons, 0, 0, 0);
           gbcButtonPB4.anchor = GridBagConstraints.WEST;
           gbcButtonPB4.gridx = 1;
           gbcButtonPB4.gridy = 5;
           if(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(3).getNicknameOfPlayer()))
              buttonPB4.setSelected(true);
           add(buttonPB4, gbcButtonPB4);

           if (mainFrame.getRemoteView().getListOfPlayerBoardViews().size() > 4) {

              buttonPB5 = new JToggleButton(" ");
              GridBagConstraints gbcButtonPB5 = new GridBagConstraints();
              buttonPB5.setBackground(Color.BLACK);
              buttonPB5.addActionListener(buttonPBListener);
              if (labelPlayer5!=null)
               buttonPB5.setActionCommand(labelPlayer5.getText());
              gbcButtonPB5.insets = new Insets(insetTopForButtons, 0, 0, 0);
              gbcButtonPB5.anchor = GridBagConstraints.WEST;
              gbcButtonPB5.gridx = 1;
              gbcButtonPB5.gridy = 6;
              if(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer().equals(mainFrame.getRemoteView().getListOfPlayerBoardViews().get(4).getNicknameOfPlayer()))
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

      ButtonMListener buttonMListener = new ButtonMListener(mainFrame.getRemoteView().getMapView(), mainFrame.getMapPanel());


        JToggleButton buttonM1 = new JToggleButton(" ");
        GridBagConstraints gbcButtonM1 = new GridBagConstraints();
        buttonM1.setBackground(Color.BLACK);
        buttonM1.addActionListener(buttonMListener);
        buttonM1.setActionCommand(labelPlayer1.getText());
        gbcButtonM1.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonM1.gridx = 2;
        gbcButtonM1.gridy = 2;
        add(buttonM1, gbcButtonM1);

        JToggleButton buttonM2 = new JToggleButton(" ");
        GridBagConstraints gbcButtonM2 = new GridBagConstraints();
        buttonM2.setBackground(Color.BLACK);
        buttonM2.addActionListener(buttonMListener);
        buttonM2.setActionCommand(labelPlayer2.getText());
        gbcButtonM2.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonM2.gridx = 2;
        gbcButtonM2.gridy = 3;
        add(buttonM2, gbcButtonM2);

        JToggleButton buttonM3 = new JToggleButton(" ");
        GridBagConstraints gbcButtonM3 = new GridBagConstraints();
        buttonM3.setBackground(Color.BLACK);
        buttonM3.addActionListener(buttonMListener);
        buttonM3.setActionCommand(labelPlayer3.getText());
        gbcButtonM3.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonM3.gridx = 2;
        gbcButtonM3.gridy = 4;
        add(buttonM3, gbcButtonM3);

        JToggleButton buttonM4 = null;
        JToggleButton buttonM5 = null;

        if(mainFrame.getRemoteView().getListOfPlayerBoardViews().size()>3) {
           buttonM4 = new JToggleButton(" ");
           GridBagConstraints gbcButtonM4 = new GridBagConstraints();
           buttonM4.setBackground(Color.BLACK);
           buttonM4.addActionListener(buttonMListener);
           if(labelPlayer4!=null)
            buttonM4.setActionCommand(labelPlayer4.getText());
           gbcButtonM4.insets = new Insets(insetTopForButtons, 0, 0, 0);
           gbcButtonM4.gridx = 2;
           gbcButtonM4.gridy = 5;
           add(buttonM4, gbcButtonM4);

           if (mainFrame.getRemoteView().getListOfPlayerBoardViews().size() > 4) {
              buttonM5 = new JToggleButton(" ");
              buttonM5.setBackground(Color.BLACK);
              buttonM5.addActionListener(buttonMListener);
              if(labelPlayer5!=null)
                 buttonM5.setActionCommand(labelPlayer5.getText());
              GridBagConstraints gbcButtonM5 = new GridBagConstraints();
              gbcButtonM5.insets = new Insets(insetTopForButtons, 0, 0, 0);
              gbcButtonM5.gridx = 2;
              gbcButtonM5.gridy = 6;
              add(buttonM5, gbcButtonM5);

           }
        }

        JToggleButton buttonMAll = new JToggleButton(" ");
        GridBagConstraints gbcButtonMAll = new GridBagConstraints();
        buttonMAll.setBackground(Color.BLACK);
        buttonMAll.addActionListener(buttonMListener);
        buttonMAll.setActionCommand("All");
        gbcButtonMAll.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonMAll.gridx = 2;
        gbcButtonMAll.gridy = indexForLabellAll;
        add(buttonMAll, gbcButtonMAll);

        JToggleButton buttonMNone = new JToggleButton(" ");
        buttonMNone.setSelected(true);
        buttonMNone.setBackground(Color.BLACK);
        buttonMNone.addActionListener(buttonMListener);
        buttonMNone.setActionCommand("None");
        GridBagConstraints gbcButtonMNone = new GridBagConstraints();
        gbcButtonMNone.insets = new Insets(insetTopForButtons, 0, 0, 0);
        gbcButtonMNone.gridx = 2;
        gbcButtonMNone.gridy = indexForLabellAll+1;
        add(buttonMNone, gbcButtonMNone);

        ButtonGroup mapButtonGroup = new ButtonGroup();
        mapButtonGroup.add(buttonM1);
        mapButtonGroup.add(buttonM2);
        mapButtonGroup.add(buttonM3);
        if(buttonM4!=null)
         mapButtonGroup.add(buttonM4);
        if(buttonM5!=null)
         mapButtonGroup.add(buttonM5);
        mapButtonGroup.add(buttonMAll);
        mapButtonGroup.add(buttonMNone);
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

    class ButtonMListener implements ActionListener{

        private MapView mapView;
        private MapPanel mapPanel;

        ButtonMListener(MapView mapView, MapPanel mapPanel) {
          this.mapView = mapView;
          this.mapPanel = mapPanel;
       }

       @Override
       public void actionPerformed(ActionEvent e) {
           String nameForHashMap;
           String actionCommand = e.getActionCommand();
           Square[][] matrix = mapView.getMap();
           if(!(actionCommand.equals("All")||actionCommand.equals("None"))){
               for (int i = 0 ; i<matrix.length;i++){
                  for(int j=0; j<matrix[i].length; j++){
                     nameForHashMap = "cell";
                     nameForHashMap = nameForHashMap.concat(String.valueOf(i)).concat(String.valueOf(j));
                     if(matrix[i][j]==null || !(matrix[i][j].getNicknamesOfPlayersOnThisSquare().contains(actionCommand))){
                        mapPanel.getHashMapForCell().get(nameForHashMap).setEnabled(false);
                     }
                     else{
                        mapPanel.getHashMapForCell().get(nameForHashMap).setEnabled(true);
                     }
                  }
               }
               return;
           }

           if(actionCommand.equals("None")){
              for (int i = 0 ; i<matrix.length;i++){
                 for(int j=0; j<matrix[i].length; j++){
                       nameForHashMap = "cell";
                       nameForHashMap = nameForHashMap.concat(String.valueOf(i)).concat(String.valueOf(j));
                       mapPanel.getHashMapForCell().get(nameForHashMap).setEnabled(true);
                 }
              }
              return;
           }

              for (int i = 0 ; i<matrix.length;i++){
                 for(int j=0; j<matrix[i].length; j++){
                    nameForHashMap = "cell";
                    nameForHashMap = nameForHashMap.concat(String.valueOf(i)).concat(String.valueOf(j));
                    if(matrix[i][j] == null || matrix[i][j].getNicknamesOfPlayersOnThisSquare().isEmpty())
                      mapPanel.getHashMapForCell().get(nameForHashMap).setEnabled(false);
                    else
                       mapPanel.getHashMapForCell().get(nameForHashMap).setEnabled(true);
                 }
              }

       }


    }
}
