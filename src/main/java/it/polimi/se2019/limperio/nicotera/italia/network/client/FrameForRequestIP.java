package it.polimi.se2019.limperio.nicotera.italia.network.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

class FrameForRequestIP {

    private JFrame frame;
    private Client client;
    private JTextField textField;
    private JLabel labelText;
    private JPanel centralPanel;
    private JButton buttonOK;

     FrameForRequestIP(Client client) {
        this.client = client;
        JFrame frameForRequestIP = new JFrame("Adrenaline - Request IP");
        this.frame = frameForRequestIP;
        frame.addWindowListener(new ListenerForClosing(client, frame));
        frameForRequestIP.setIconImage(Toolkit.getDefaultToolkit().getImage("resources/favicon.jpg"));

        Dimension dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
        frameForRequestIP.setSize((int)(dimensionOfScreen.getWidth()/3.2), (int)(dimensionOfScreen.getHeight()/3.6) );
        frameForRequestIP.setLocation((int) (dimensionOfScreen.getWidth() - frameForRequestIP.getWidth()) / 2,
                (int) (dimensionOfScreen.getHeight() - frameForRequestIP.getHeight()) / 2);
        frameForRequestIP.setResizable(false);
        frameForRequestIP.setContentPane(new JPanel(new BorderLayout()));
        centralPanel = new JPanel(new GridBagLayout());
        frameForRequestIP.getContentPane().add(centralPanel, BorderLayout.CENTER);

        labelText = new JLabel("Digit the IP address of the server do you want to connect with");
        labelText.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.gridx=0;
        gbcText.gridy=0;
        centralPanel.add(labelText,gbcText);

        textField = new JTextField();
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setColumns(20);
        GridBagConstraints gbcTextField = new GridBagConstraints();
        gbcTextField.gridx=0;
        gbcTextField.gridy=1;

        gbcTextField.insets = new Insets(50, 0, 0, 0);
        centralPanel.add(textField,gbcTextField);

        buttonOK = new JButton("Connect");
        buttonOK.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx=0;
        gbcButton.gridy=2;
        gbcButton.insets = new Insets(50, 0, 0, 0);
        centralPanel.add(buttonOK,gbcButton);
        buttonOK.addActionListener(new ListenerForIPAddress(client, this, frameForRequestIP));

        frameForRequestIP.setVisible(true);
    }

    public JPanel getCentralPanel() {
        return centralPanel;
    }

    JTextField getTextField() {
         return textField;
     }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getLabelText() {
        return labelText;
    }

    public JButton getButtonOK() {
        return buttonOK;
    }

    class ListenerForIPAddress implements ActionListener {

        private JFrame frame;
        private Client client;
        private FrameForRequestIP frameForRequestIP;

        ListenerForIPAddress(Client client, FrameForRequestIP frameForRequestIP, JFrame frame) {

            this.client = client;
            this.frame = frame;
            this.frameForRequestIP = frameForRequestIP;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            buttonOK.setEnabled(false);
            client.setIpAddress(frameForRequestIP.getTextField().getText());
            frame.setVisible(false);
            try {
                client.handleConnectionWithServer();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }




    }

    class ListenerForClosing implements WindowListener {

         private Client client;
         private Frame frame;

         ListenerForClosing(Client client, Frame frame) {
            this.client = client;
            this.frame = frame;
        }

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {
            frame.setVisible(false);
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
    }


}
