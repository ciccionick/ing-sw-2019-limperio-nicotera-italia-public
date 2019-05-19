package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;


 public class MainFrame {

    private JFrame frame;
    private JPanel contentPane;
    private RemoteView remoteView;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private MapPanel mapPanel;
    private KillshotTrackPanel killshotTrackPanel;
    private DialogForMessage dialogForMessage;
    private boolean hasToChoosePowerUpCardForSpawn = false;
    private boolean canUseNewton = false;
    private boolean canUseTeleporter = false;
    private boolean canUseTagbackGranade = false;
    private boolean hasToChooseAWeapon = false;

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



    public void handleRequestForDrawTwoPowerUpCard(ServerEvent receivedEvent) {
        dialogForMessage = new DialogForMessage(this, receivedEvent);
    }



     public void handleRequestToDiscardPowerUpCard(ServerEvent receivedEvent) {
        setHasToChoosePowerUpCardForSpawn(true);
        if(getLeftPanel().getPlayerBoardPanel().getPlayerBoardViewed().getNicknameOfPlayer().equals(receivedEvent.getNicknameInvolved())){
            contentPane.remove(leftPanel);
            leftPanel = new LeftPanel(this,remoteView.getMyPlayerBoardView());
            contentPane.add(leftPanel,BorderLayout.WEST);
            contentPane.validate();
            contentPane.repaint();
            dialogForMessage = new DialogForMessage(this, receivedEvent);
        }

    }

    boolean hasToChoosePowerUpCard() {
         return hasToChoosePowerUpCardForSpawn;
     }

     void setHasToChoosePowerUpCardForSpawn(boolean hasToChoosePowerUpCardForSpawn) {
         this.hasToChoosePowerUpCardForSpawn = hasToChoosePowerUpCardForSpawn;
     }

      boolean isCanUseNewton() {
         return canUseNewton;
     }

      boolean isCanUseTeleporter() {
         return canUseTeleporter;
     }


      boolean isCanUseTagbackGranade() {
         return canUseTagbackGranade;
     }


     public boolean isHasToChooseAWeapon() {
         return hasToChooseAWeapon;
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

            leftPanel = new LeftPanel(mainFrame,remoteView.getMyPlayerBoardView());
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


