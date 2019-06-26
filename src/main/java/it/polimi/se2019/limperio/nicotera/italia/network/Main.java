package it.polimi.se2019.limperio.nicotera.italia.network;

import it.polimi.se2019.limperio.nicotera.italia.network.client.Client;
import it.polimi.se2019.limperio.nicotera.italia.network.client.FrameForRequestIP;
import it.polimi.se2019.limperio.nicotera.italia.network.server.Server;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));
            frame.setResizable(false);
            JPanel contentPanel = new JPanel(new GridBagLayout());
            frame.getContentPane().add(contentPanel);
            frame.addWindowListener(new InitialFrameListener());

            contentPanel.setBorder(new EmptyBorder(100, 100, 100, 100));
            GridBagConstraints gbc = new GridBagConstraints();

            JTextArea text = new JTextArea("What do you want to be in your life?");
            text.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
            text.setEditable(false);
            text.setBackground(SystemColor.menu);
            text.setLineWrap(false);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.insets = new Insets(10, 10, 20, 10);

            gbc.fill = GridBagConstraints.BOTH;
            contentPanel.add(text,gbc);

            ImageIcon icon = new ImageIcon("resources/server.jpg");
            Image image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            GridBagConstraints gbc1 = new GridBagConstraints();
            gbc1.insets = new Insets(10, 10, 10, 10);
            JButton buttonServer = new JButton(icon);
            buttonServer.setActionCommand("Server");
            buttonServer.addActionListener(new ListenerForServerPlayerButton());
            gbc1.gridy = 1;
            gbc1.gridx = 0;
            gbc1.ipadx = 100;
            contentPanel.add(buttonServer, gbc1);

            JLabel or = new JLabel("or");
            gbc1.gridx=1;
            gbc1.ipadx = 1;
            contentPanel.add(or,gbc1);

            gbc1.insets = new Insets(0, 0, 0, 0);
             icon = new ImageIcon("resources/player.png");
             image = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);

            JButton buttonPlayer = new JButton(icon);
            buttonPlayer.setActionCommand("Player");
            buttonPlayer.addActionListener(new ListenerForServerPlayerButton());
            gbc1.gridx=2;
            gbc1.ipadx = 100;
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
                     if(forServer)
                        new Server();
                     else {
                         Client client;
                         client = new Client();
                         new FrameForRequestIP(client);
                     }
                }
            }
        }

        private class InitialFrameListener implements WindowListener {
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
        }
    }
}



