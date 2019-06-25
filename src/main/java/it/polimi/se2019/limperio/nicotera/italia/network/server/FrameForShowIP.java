package it.polimi.se2019.limperio.nicotera.italia.network.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 */
class FrameForShowIP  {
    /**
     *
     */
    private JFrame frame;

    /**
     *
     * @param ip
     */
     FrameForShowIP(String ip) {
        this.frame = new JFrame("Adrenaline (Server) - IP address");
        frame.setResizable(false);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //not implemented
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //not implemented
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //not implemented
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //not implemented
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //not implemented
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //not implemented
            }
        });
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel centralPane = new JPanel(new GridBagLayout());
        int widhtOfScreen = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heightOfScreen = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int horizontalBorder = (int) (heightOfScreen/21.6);
        int verticalBorder = (int) (widhtOfScreen/12.8);
        contentPane.add(centralPane, BorderLayout.CENTER);
        frame.getContentPane().add(contentPane);
        contentPane.setBorder(new EmptyBorder(horizontalBorder, verticalBorder, horizontalBorder, verticalBorder));
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        int top = heightOfScreen/108;
        int leftRight = widhtOfScreen/96;
        int bottom = heightOfScreen/216;
        gbc.insets = new Insets(top , leftRight, bottom, leftRight);

        centralPane.add(new JLabel("Let's connect to this ip address to play!"),gbc);
        gbc.gridy++;
        gbc.insets.top = heightOfScreen/216;
        gbc.insets.bottom = heightOfScreen/216;
        centralPane.add(new JLabel("(Don't close this window until you want to interrupt the match)"),gbc);
        gbc.gridy++;
        gbc.insets.bottom = widhtOfScreen/96;
        gbc.insets.top = heightOfScreen/36;
        centralPane.add(new JLabel(ip),gbc);

        frame.pack();
        Dimension dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (dimensionOfScreen.getWidth() - frame.getWidth()) / 2,
                (int) (dimensionOfScreen.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);

    }
}
