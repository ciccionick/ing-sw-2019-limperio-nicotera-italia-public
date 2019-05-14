package it.polimi.se2019.limperio.nicotera.italia.view.gui;

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
    private PanelMap panelMap;
    private KillshotTrackPanel killshotTrackPanel;

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
        contentPane.addComponentListener(new ContentListener(this));


        panelMap = new PanelMap(this);
        contentPane.add(panelMap,BorderLayout.CENTER);

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

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    private class ContentListener implements ComponentListener {

        private MainFrame mainFrame;

         ContentListener(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

        @Override
        public void componentResized(ComponentEvent e) {
            contentPane.removeAll();
            contentPane.validate();
            contentPane.repaint();

            panelMap = new PanelMap(mainFrame);
            contentPane.add(panelMap,BorderLayout.CENTER);

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


