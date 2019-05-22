package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestForChooseAWeaponToCatch;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardWeaponCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;


public class MainFrame {

    private JFrame frame;
    private JPanel contentPane;
    private RemoteView remoteView;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private MapPanel mapPanel;
    private KillshotTrackPanel killshotTrackPanel;
    private DialogForMessage dialogForMessage;
    private PopupForChooseWeaponCard popupForChooseWeaponCardToCatch;


    public MainFrame(RemoteView remoteView) {
        this.remoteView = remoteView;
        frame = new JFrame("Adrenaline");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(new Dimension(1470,900) );


        contentPane = new JPanel();
        frame.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.addComponentListener(new FrameListener(this));


        mapPanel = new MapPanel(this);
        contentPane.add(mapPanel,BorderLayout.CENTER);

        killshotTrackPanel = new KillshotTrackPanel(frame);
        contentPane.add(killshotTrackPanel, BorderLayout.NORTH);

        leftPanel = new LeftPanel(this,remoteView.getMyPlayerBoardView());
        contentPane.add(leftPanel, BorderLayout.WEST);

        rightPanel = new RightPanel(this);
        contentPane.add(rightPanel, BorderLayout.EAST);
        frame.setVisible(true);
    }

    RemoteView getRemoteView() {
        return remoteView;
    }
    JFrame getFrame() {
        return frame;
    }
    void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }
    LeftPanel getLeftPanel() {
        return leftPanel;
    }
    MapPanel getMapPanel() {
        return mapPanel;
    }



    public void showMessage(ServerEvent receivedEvent) {
        dialogForMessage = new DialogForMessage(this, receivedEvent);
    }

     public void handleRequestToDiscardPowerUpCard(ServerEvent receivedEvent) {
        dialogForMessage = new DialogForMessage(this, receivedEvent);
    }


     public void updateLeftPanelForWhoIsViewing(String nicknameOfThePlayerInvolvedInTheUpdate) {
        if(leftPanel.getPlayerBoardPanel().getPlayerBoardViewed().getNicknameOfPlayer().equals(nicknameOfThePlayerInvolvedInTheUpdate)){
            LeftPanel newLeftPanel = new LeftPanel(this, remoteView.getPlayerBoardViewOfThisPlayer(nicknameOfThePlayerInvolvedInTheUpdate));
            contentPane.remove(leftPanel);
            contentPane.add(newLeftPanel,BorderLayout.WEST);
            leftPanel= newLeftPanel;
            contentPane.validate();
            contentPane.repaint();
        }
     }

     public void updatePanelOfAction() {
        rightPanel.getPanelOfActions().updateStateOfButton();
     }

     public void updateEnableSquares(ArrayList<Square> squaresReachableWithRunAction) {
        mapPanel.updateEnableSquares(squaresReachableWithRunAction);
     }

    public void showPopupForChooseWeapon(ServerEvent receivedEvent) {
        if(receivedEvent.isRequestForChooseAWeaponToCatch())
            popupForChooseWeaponCardToCatch = new PopupForChooseWeaponCard(((RequestForChooseAWeaponToCatch)receivedEvent), null,this);
        if(receivedEvent.isRequestToDiscardWeaponCard())
            popupForChooseWeaponCardToCatch = new PopupForChooseWeaponCard(null, ((RequestToDiscardWeaponCard)receivedEvent), this);
    }






    private class FrameListener implements ComponentListener {

        private MainFrame mainFrame;

         FrameListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void componentResized(ComponentEvent e) {
            contentPane.removeAll();

            mapPanel = new MapPanel(mainFrame);
            contentPane.add(mapPanel,BorderLayout.CENTER);

            killshotTrackPanel = new KillshotTrackPanel(frame);
            contentPane.add(killshotTrackPanel, BorderLayout.NORTH);

            leftPanel = new LeftPanel(mainFrame,leftPanel.getPlayerBoardView());
            contentPane.add(leftPanel, BorderLayout.WEST);

            rightPanel = new RightPanel(mainFrame);
            contentPane.add(rightPanel, BorderLayout.EAST);

            contentPane.validate();
            contentPane.repaint();
        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }

        @Override
        public void componentHidden(ComponentEvent e) {

        }
    }
}


