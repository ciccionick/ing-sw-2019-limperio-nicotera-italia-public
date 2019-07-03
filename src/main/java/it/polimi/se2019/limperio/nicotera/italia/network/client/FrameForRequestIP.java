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
 * This class handles the creation of a JFrame that ask to client the IP address of the server he wants to connect with.
 */
public class FrameForRequestIP {

    /**
     * The JFrame where type the IP address.
     */
    private JFrame frame;
    /**
     * The JTextField where inserted the string.
     */
    private JTextField textField;
    /**
     * The JLabel that explain to the client what he has to do.
     */
    private JLabel labelText;
    /**
     * The principal JPanel that compose the JFrame.
     */
    private JPanel centralPanel;
    /**
     * The JButton to press to send the IP to the Client class to try the connection with the server.
     */
    private JButton buttonOK;
    /**
     * The logger of the class to track possibly exception.
     */
    private static Logger loggerForFrameIp = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    /**
     * The handler of the logger.
     */
    private static Handler handlerLoggerFrameIP = new ConsoleHandler();

    /**
     * The constructor of the class that creates the JFrame.
     * @param client Client that has to receive the IP address typed on the textField.
     */
    public FrameForRequestIP(Client client) {
         loggerForFrameIp.addHandler(handlerLoggerFrameIP);
         JFrame frameForRequestIP = new JFrame("Adrenaline - Request IP");
         this.frame = frameForRequestIP;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/favicon.jpg")));

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
         buttonOK.addActionListener(e -> sendIpAddress(client));
         textField.addKeyListener(new KeyAdapter() {
             /**
              * The Key listener that permits to avoid to press the button just press the key ENTER.
              */
             @Override
             public void keyPressed(KeyEvent e) {
                 if (e.getKeyCode() == KeyEvent.VK_ENTER)
                     sendIpAddress(client);
             }});
         frameForRequestIP.pack();
         frameForRequestIP.setLocation((int) (dimensionOfScreen.getWidth() - frameForRequestIP.getWidth()) / 2,
                 (int) (dimensionOfScreen.getHeight() - frameForRequestIP.getHeight()) / 2);

         frameForRequestIP.setVisible(true);
     }

    /**
     * Set in the field of the client the IP address typed and calls the method of the Client to try the connection.
     * @param client Client that is trying to connect with the server.
     */
    private void sendIpAddress(Client client){
        buttonOK.setEnabled(false);
        client.setIpAddress(textField.getText());
        frame.setVisible(false);
        try {
            client.handleConnectionWithServer();
        } catch (IOException e1) {
            System.out.println("errore nella connessione");
            loggerForFrameIp.log(Level.ALL, "error");
        }
    }


    public JFrame getFrame() {
        return frame;
    }

}
