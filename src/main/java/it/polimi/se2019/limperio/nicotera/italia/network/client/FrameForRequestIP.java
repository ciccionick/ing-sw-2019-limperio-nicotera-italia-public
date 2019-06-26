package it.polimi.se2019.limperio.nicotera.italia.network.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 */
public class FrameForRequestIP {

    /**
     *
     */
    private JFrame frame;
    /**
     *
     */
    private JTextField textField;
    /**
     *
     */
    private JLabel labelText;
    /**
     *
     */
    private JPanel centralPanel;
    /**
     *
     */
    private JButton buttonOK;
    private static Logger loggerForFrameIp = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    private static Handler handlerLoggerFrameIP = new ConsoleHandler();

    /**
     *
     * @param client
     */
    public FrameForRequestIP(Client client) {
         loggerForFrameIp.addHandler(handlerLoggerFrameIP);
         JFrame frameForRequestIP = new JFrame("Adrenaline - Request IP");
         this.frame = frameForRequestIP;
         frame.addWindowListener(new ListenerForClosing(frame));
         frameForRequestIP.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));

         Dimension dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
         frameForRequestIP.setResizable(false);
         JPanel contentPanel = new JPanel(new BorderLayout());
         frameForRequestIP.getContentPane().add(contentPanel);
         int horizontalBorder = (int) (dimensionOfScreen.getHeight()/21.6);
         int verticalBorder = (int) (dimensionOfScreen.getWidth()/19.2);
         contentPanel.setBorder(new EmptyBorder(horizontalBorder,verticalBorder,horizontalBorder,verticalBorder));

         centralPanel = new JPanel(new GridBagLayout());
         contentPanel.add(centralPanel, BorderLayout.CENTER);

         labelText = new JLabel("Digit the IP address of the server do you want to connect with");
         labelText.setHorizontalAlignment(SwingConstants.CENTER);
         GridBagConstraints gbcText = new GridBagConstraints();
         gbcText.gridx = 0;
         gbcText.gridy = 0;
         centralPanel.add(labelText, gbcText);

         textField = new JTextField();
         textField.setHorizontalAlignment(SwingConstants.CENTER);
         textField.setColumns(20);
         GridBagConstraints gbcTextField = new GridBagConstraints();
         gbcTextField.gridx = 0;
         gbcTextField.gridy = 1;


         gbcTextField.insets = new Insets(horizontalBorder, 0, 0, 0);
         centralPanel.add(textField, gbcTextField);

         buttonOK = new JButton("Connect");
         buttonOK.setHorizontalAlignment(SwingConstants.CENTER);
         GridBagConstraints gbcButton = new GridBagConstraints();
         gbcButton.gridx = 0;
         gbcButton.gridy = 2;
         gbcButton.insets = new Insets(horizontalBorder, 0, 0, 0);
         centralPanel.add(buttonOK, gbcButton);
         ListenerForIPAddress listenerForIPAddress = new ListenerForIPAddress(client, this.getFrame());
         buttonOK.addActionListener(listenerForIPAddress);
         frameForRequestIP.addKeyListener(listenerForIPAddress);
         textField.addKeyListener(listenerForIPAddress);

         frameForRequestIP.pack();
         frameForRequestIP.setLocation((int) (dimensionOfScreen.getWidth() - frameForRequestIP.getWidth()) / 2,
                 (int) (dimensionOfScreen.getHeight() - frameForRequestIP.getHeight()) / 2);

         frameForRequestIP.setVisible(true);
     }


    public JFrame getFrame() {
        return frame;
    }

    /**
     *
     */
    class ListenerForIPAddress implements ActionListener, KeyListener {

        /**
         *
         */
        private JFrame frame;
        /**
         *
         */
        private Client client;

        ListenerForIPAddress(Client client, JFrame frame) {

            this.client = client;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            sendIpAddress();
        }

        /**
         *
         */
        private void sendIpAddress(){
            buttonOK.setEnabled(false);
            client.setIpAddress(textField.getText());
            frame.setVisible(false);
            try {
                client.handleConnectionWithServer();
            } catch (IOException e1) {
                loggerForFrameIp.log(Level.ALL, "error");
            }
        }


        @Override
        public void keyTyped(KeyEvent e) {
            //not implemented
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendIpAddress();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //not implemented
        }
    }

    /**
     *
     */
    class ListenerForClosing implements WindowListener {

         private Frame frame;

         ListenerForClosing(Frame frame) {
            this.frame = frame;
        }

        @Override
        public void windowOpened(WindowEvent e) {
            //not implemented
        }

        @Override
        public void windowClosing(WindowEvent e) {
            frame.setVisible(false);
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
