package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private JFrame frame;
    private JPanel contentPane;
    private RemoteView remoteView;

    public MainFrame(RemoteView remoteView) {
        this.remoteView = remoteView;
        frame = new JFrame("Adrenaline");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();

        frame.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));


        frame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) );
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        //frame.setAlwaysOnTop(true);


        PanelMap panelMap = new PanelMap(this);

        contentPane.add(panelMap,BorderLayout.CENTER);

        PanelKillshotTrack panelKillshotTrack = new PanelKillshotTrack(frame);
        contentPane.add(panelKillshotTrack, BorderLayout.NORTH);

        LeftPanel leftPanel = new LeftPanel(this);
        contentPane.add(leftPanel, BorderLayout.WEST);

        RightPanel rightPanel = new RightPanel(frame);
        contentPane.add(rightPanel, BorderLayout.EAST);

        frame.setVisible(true);

    }

    public RemoteView getRemoteView() {
        return remoteView;
    }

    public JFrame getFrame() {
        return frame;
    }
}
