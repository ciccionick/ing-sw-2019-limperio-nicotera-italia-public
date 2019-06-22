package it.polimi.se2019.limperio.nicotera.italia.network.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class FrameForShowIP  {
    private JFrame frame;

     FrameForShowIP(String ip) {
        this.frame = new JFrame("Adrenaline (Server) - IP address");
        frame.setResizable(false);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel centralPane = new JPanel(new GridBagLayout());
        contentPane.add(centralPane, BorderLayout.CENTER);
        frame.getContentPane().add(contentPane);
        contentPane.setBorder(new EmptyBorder(50, 150, 50, 150));
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 5, 20);

        centralPane.add(new JLabel("Let's connect to this ip address to play!"),gbc);
        gbc.gridy++;
        gbc.insets.top =5;
        gbc.insets.bottom =5;
        centralPane.add(new JLabel("(Don't close this window until you want to interrupt the match)"),gbc);
        gbc.gridy++;
        gbc.insets.bottom =20;
        gbc.insets.top =30;
        centralPane.add(new JLabel(ip),gbc);

        frame.pack();
        Dimension dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (dimensionOfScreen.getWidth() - frame.getWidth()) / 2,
                (int) (dimensionOfScreen.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);

    }
}
