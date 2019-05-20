package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToCatchByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToRunByPlayer;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToShootByPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelOfActions extends JPanel {

    private MainFrame mainFrame;
    private JButton buttonRun;
    private JButton buttonCatch;
    private JButton buttonShoot;
    private JButton buttonReload;
    private JButton buttonCancel;

     PanelOfActions(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        GridBagLayout gridBagLayout = new GridBagLayout();
        setLayout(gridBagLayout);
        this.setBackground(Color.DARK_GRAY);
        ActionButtonListener actionButtonListener = new ActionButtonListener();

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
        buttonShoot.addActionListener(actionButtonListener);
        add(buttonShoot, gbcButtonShoot);

        buttonReload = new JButton("Reload");
        buttonReload.setActionCommand(buttonReload.getText());
        buttonReload.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanReload());
        buttonReload.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonReload.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonReload = new GridBagConstraints();
        gbcButtonReload.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonReload.gridx = 2;
        gbcButtonReload.gridy = 4;
        buttonReload.addActionListener(actionButtonListener);
        add(buttonReload, gbcButtonReload);

        buttonCancel = new JButton("Cancel");
        buttonCancel.setActionCommand(buttonCancel.getText());
        buttonCancel.setEnabled(false);
        buttonCancel.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        buttonCancel.setForeground(new Color(0, 0, 0));
        GridBagConstraints gbcButtonCancel = new GridBagConstraints();
        gbcButtonCancel.insets = new Insets(insetTop, 0, insetBottom, 0);
        gbcButtonCancel.gridx = 2;
        gbcButtonCancel.gridy = 5;
        buttonCancel.addActionListener(actionButtonListener);
        add(buttonCancel, gbcButtonCancel);

    }



    class ActionButtonListener implements ActionListener{

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
             case "Reload":
                System.out.println("Non ancora implementato");
                break;
             case "Cancel":
                buttonRun.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanRun());
                buttonCatch.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanCatch());
                buttonShoot.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanShoot());
                buttonShoot.setEnabled(mainFrame.getRemoteView().getMyPlayerBoardView().isCanReload());
                buttonCancel.setEnabled(false);
                for(JLabel label : mainFrame.getMapPanel().getHashMapForCell().values()){
                   label.setEnabled(true);
                }
                break;
             default: throw new IllegalArgumentException();
          }
          if(!(e.getActionCommand().equals("Cancel"))) {
             disableAllButtonsAndCards();
             mainFrame.updateLeftPanelForWhoIsViewing(mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
             buttonCatch.setEnabled(false);
             buttonRun.setEnabled(false);
             buttonShoot.setEnabled(false);
             buttonReload.setEnabled(false);
             buttonCancel.setEnabled(true);
          }
       }

       private void disableAllButtonsAndCards(){
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon1(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon2(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanChooseWeapon3(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanUseNewton(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanUseTeleporter(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanUseTargetingScope(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setCanUseTagbackGranade(false);
          mainFrame.getRemoteView().getMyPlayerBoardView().setHasToChoosePowerUpCardForSpawn(false);

       }
    }
}
