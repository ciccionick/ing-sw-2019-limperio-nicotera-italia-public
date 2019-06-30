package it.polimi.se2019.limperio.nicotera.italia.network;

import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.FrameForRequestIP;
import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public  class Main {

    public static void main(String[] argv){
        new StartingFrame();
    }



    static class StartingFrame {
        private JFrame frame;

        StartingFrame() {
            frame = new JFrame("Adrenaline - Choose your side");
            ImageIcon iconForFavicon = new ImageIcon(getClass().getResource("/favicon.jpg"));
            Image favicon = iconForFavicon.getImage();
            frame.setIconImage(favicon);
            frame.setResizable(false);
            JPanel contentPanel = new JPanel(new GridBagLayout());
            frame.getContentPane().add(contentPanel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            int widthOfImage = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 38.4);
            int heightOfImage = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 21.6);
            int topBottomBorder = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10.8);
            int leftRightBorder = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 19.2);
            contentPanel.setBorder(new EmptyBorder(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));
            GridBagConstraints gbc = new GridBagConstraints();

            JTextArea text = new JTextArea("What do you want to be in your life?");
            text.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            text.setEditable(false);
            text.setBackground(SystemColor.menu);
            text.setLineWrap(false);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            int topInset = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 108);
            int bottomInset = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 59);
            int leftRightInset = frame.getWidth() / 192;
            gbc.insets = new Insets(topInset, leftRightInset, bottomInset, leftRightInset);
            gbc.fill = GridBagConstraints.BOTH;

            contentPanel.add(text, gbc);


            ImageIcon icon = new ImageIcon(getClass().getResource("/server.jpg"));
            Image image = icon.getImage().getScaledInstance(widthOfImage, heightOfImage, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            GridBagConstraints gbc1 = new GridBagConstraints();
            topInset = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 108);
            gbc1.insets = new Insets(topInset, leftRightInset, topInset, leftRightInset);
            JButton buttonServer = new JButton(icon);
            buttonServer.setActionCommand("Server");
            buttonServer.addActionListener(new ListenerForServerPlayerButton());
            gbc1.gridy = 1;
            gbc1.gridx = 0;
            gbc1.ipadx = 80;
            contentPanel.add(buttonServer, gbc1);

            gbc1.insets = new Insets(topInset, leftRightInset * 2, topInset, leftRightInset * 2);
            JLabel or = new JLabel("or");
            gbc1.gridx = 1;
            gbc1.ipadx = 1;
            contentPanel.add(or, gbc1);

            icon = new ImageIcon(getClass().getResource("/player.png"));
            image = icon.getImage().getScaledInstance(widthOfImage, heightOfImage, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            gbc1.insets = new Insets(topInset, leftRightInset, topInset, leftRightInset);

            JButton buttonPlayer = new JButton(icon);
            buttonPlayer.setActionCommand("Player");
            buttonPlayer.addActionListener(new ListenerForServerPlayerButton());
            gbc1.gridx = 2;
            gbc1.ipadx = 80;
            contentPanel.add(buttonPlayer, gbc1);

            frame.pack();
            Dimension dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation((int) (dimensionOfScreen.getWidth() - frame.getWidth()) / 2,
                    (int) (dimensionOfScreen.getHeight() - frame.getHeight()) / 2);


            frame.setVisible(true);
        }


        private class ListenerForServerPlayerButton implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Timer timer = new Timer();
                timer.schedule(new TaskToStart(e.getActionCommand().equalsIgnoreCase("Server")), 1000);
            }

            private class TaskToStart extends TimerTask {

                private boolean forServer;

                TaskToStart(boolean forServer) {
                    this.forServer = forServer;
                }

                @Override
                public void run() {
                    if (forServer)
                        new Server();
                    else {
                        Client client;
                        client = new Client();
                        new FrameForRequestIP(client);
                    }
                }
            }
        }
    }
}



