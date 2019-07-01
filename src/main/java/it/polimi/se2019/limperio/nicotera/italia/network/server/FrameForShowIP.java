package it.polimi.se2019.limperio.nicotera.italia.network.server;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Handles the creation of a frame that show the IP of the Server to get clients able to connect with it.
 */
class FrameForShowIP  {
    /**
     * The JFrame that shows the IP address of the server.
     */
    private JFrame frame;

    /**
     * Constructor of the frame. Closing this frame it will be stopped the process where the server run.
     * @param ip IP address to show.
     */
     FrameForShowIP(String ip) {
        this.frame = new JFrame("Adrenaline (Server) - IP address");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/favicon.jpg")));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel(new BorderLayout());
        JPanel centralPane = new JPanel(new GridBagLayout());
        int widhtOfScreen = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int heightOfScreen = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int horizontalBorder = (int) (heightOfScreen/21.6);
        int verticalBorder = (int) (widhtOfScreen/12.8);
        contentPane.add(centralPane, BorderLayout.CENTER);
        frame.getContentPane().add(contentPane);
        contentPane.setBorder(new EmptyBorder(horizontalBorder, verticalBorder, horizontalBorder, verticalBorder));

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
