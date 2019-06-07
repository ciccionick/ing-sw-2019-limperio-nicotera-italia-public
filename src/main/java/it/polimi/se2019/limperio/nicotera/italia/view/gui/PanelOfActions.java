package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToRunByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToShootByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestTerminatorActionByPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOfActions extends JPanel {

    private MainFrame mainFrame;
    private JButton buttonRun;
    private JButton buttonCatch;
    private JButton buttonShoot;
    private JButton buttonTerminator = new JButton();
    private JButton buttonCancel;
    private ActionButtonListener actionButtonListener;

     PanelOfActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);
        actionButtonListener = new ActionButtonListener();

        JLabel text = new JLabel("Choose one action:");
        text.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.fill = GridBagConstraints.BOTH;
        gbcText.insets = new Insets(0, 0, (int)(mainFrame.getFrame().getSize().getHeight()/54), 0);
        gbcText.gridwidth = 3;
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        add(text, gbcText);

        int insetTop = (int)(mainFrame.getFrame().getSize().getHeight()/54);
        int insetBottom = insetTop/4;

        buttonRun = new JButton("Run");
        buttonRun.setActionCommand(buttonRun.getText());
        buttonRun.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanRun());
        buttonRun.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonRun.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonRun = new GridBagConstraints();
        gbcButtonRun.insets = new Insets(insetTop/2, 0, insetBottom, 0);
        gbcButtonRun.gridx = 2;
        gbcButtonRun.gridy = 1;
        gbcButtonRun.fill = GridBagConstraints.BOTH;
        buttonRun.addActionListener(actionButtonListener);
        add(buttonRun, gbcButtonRun);

        buttonCatch = new JButton("Catch");
        buttonCatch.setActionCommand(buttonCatch.getText());
        buttonCatch.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanCatch());
        buttonCatch.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonCatch.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonCatch = new GridBagConstraints();
        gbcButtonCatch.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonCatch.gridx = 2;
        gbcButtonCatch.gridy = 2;
        gbcButtonCatch.fill = GridBagConstraints.BOTH;
        buttonCatch.addActionListener(actionButtonListener);
        add(buttonCatch, gbcButtonCatch);

        buttonShoot = new JButton("Shoot");
        buttonShoot.setActionCommand(buttonShoot.getText());
        buttonShoot.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanShoot());
        buttonShoot.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonShoot.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonShoot = new GridBagConstraints();
        gbcButtonShoot.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonShoot.gridx = 2;
        gbcButtonShoot.gridy = 3;
        gbcButtonShoot.fill = GridBagConstraints.BOTH;
        buttonShoot.addActionListener(actionButtonListener);
        add(buttonShoot, gbcButtonShoot);


        if(mainFrame.getRemoteView().isTerminatorMode()) {
        buttonTerminator = new JButton("Terminator");
        buttonTerminator.setActionCommand(buttonTerminator.getText());
        buttonTerminator.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isHasToDoTerminatorAction());
        buttonTerminator.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonTerminator.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonTerminator = new GridBagConstraints();
        gbcButtonTerminator.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonTerminator.gridx = 2;
        gbcButtonTerminator.gridy = 4;
        gbcButtonTerminator.fill = GridBagConstraints.BOTH;
        buttonTerminator.addActionListener(actionButtonListener);
        add(buttonTerminator, gbcButtonTerminator);
        }

        buttonCancel = new JButton("Cancel");
        buttonCancel.setActionCommand(buttonCancel.getText());
        buttonCancel.setEnabled(false);
        buttonCancel.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonCancel.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonCancel = new GridBagConstraints();
        gbcButtonCancel.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonCancel.gridx = 2;
        if(mainFrame.getRemoteView().isTerminatorMode())
            gbcButtonCancel.gridy = 5;
        else
           gbcButtonCancel.gridy = 4;
        gbcButtonCancel.fill = GridBagConstraints.BOTH;
        buttonCancel.addActionListener(actionButtonListener);
        add(buttonCancel, gbcButtonCancel);

    }

   public ActionButtonListener getActionButtonListener() {
      return actionButtonListener;
   }

   void updateStateOfButton(){
       buttonRun.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanRun());
       buttonCatch.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanCatch());
       buttonShoot.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanShoot());
       buttonTerminator.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isHasToDoTerminatorAction());
       buttonCancel.setEnabled(false);
    }



   public JButton getButtonCancel() {
      return buttonCancel;
   }

   public class ActionButtonListener implements ActionListener{

       @Override
       public void actionPerformed(ActionEvent e) {
          String nickname = mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer();
          switch(e.getActionCommand()){
             case "Run":
                mainFrame.getRemoteView().notify(new RequestToRunByPlayer("", nickname));
                break;
             case "Catch":
                mainFrame.getRemoteView().notify(new RequestToCatchByPlayer("", nickname));
                break;
             case "Shoot":
                mainFrame.getRemoteView().notify(new RequestToShootByPlayer("", nickname));
                break;
             case "Terminator":
                mainFrame.getRemoteView().notify(new RequestTerminatorActionByPlayer("",  mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer()));
                break;
             case "Cancel":
                updateStateOfButton();
                mainFrame.getRemoteView().getMyPlayerBoardView().disableWeaponsButton();
                mainFrame.getRemoteView().getMapView().setHasToChooseASquare(false);
                mainFrame.updateLeftPanelForWhoIsViewing(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                for(JLabel label : mainFrame.getMapPanel().getHashMapForCell().values()){
                   label.setEnabled(true);
                }
                break;
             default: throw new IllegalArgumentException();
          }
          if(!(e.getActionCommand().equals("Cancel"))) {
             disablePowerUpCards();
             buttonCatch.setEnabled(false);
             buttonRun.setEnabled(false);
             buttonShoot.setEnabled(false);
             buttonTerminator.setEnabled(false);
             buttonCancel.setEnabled(true);
          }
       }

      public void disablePowerUpCards(){
          mainFrame.getLeftPanel().getButtonPC1().setEnabled(false);
          mainFrame.getLeftPanel().getButtonPC2().setEnabled(false);
          mainFrame.getLeftPanel().getButtonPC3().setEnabled(false);
       }
    }
}
