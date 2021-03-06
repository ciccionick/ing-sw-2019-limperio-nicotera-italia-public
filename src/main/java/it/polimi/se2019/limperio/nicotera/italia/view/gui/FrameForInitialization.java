package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Handle the creation and update of the frame in the initial phase to store the information about connecting player
 * @author Pietro L'Imperio
 */
public class FrameForInitialization {

    /**
     * The frame created by the class.
     */
    private JFrame frame = new JFrame();
    /**
     * The hash map that contains all of the component stored in the frame with a String key useful to remove them when change the request by server side.
     */
    private  Map<String, Component> mapOfComponents = new HashMap<>();
    /**
     * The dimension of the screen of the player terminal.
     */
    private Dimension dimensionOfScreen;
    /**
     * The font used for the title.
     */
    private Font fontForTitle = new Font(Font.SANS_SERIF, Font.BOLD | Font.ITALIC, 20);
    /**
     * The font used for normal text.
     */
    private Font fontForNormalText = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
    /**
     * The reference of initialization view where the information useful in this phase are stored.
     */
    private InitializationView initializationView;
    /**
     * The main panel of the frame.
     */
    private JPanel contentPane;


    /**
     * The constructor of the class where the frame is created.
     * @param title The title of the frame.
     * @param initializationView The reference of initialization view.
     */
    public FrameForInitialization(String title, InitializationView initializationView) {
        frame.setTitle(title);
        this.initializationView = initializationView;
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/favicon.jpg")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setResizable(false);
        contentPane = new JPanel();
        int topBottom = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/10.8);
        int leftRight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/12.8);
        contentPane.setBorder(new EmptyBorder(topBottom,leftRight, topBottom, leftRight));
        frame.setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 1));
        JPanel panelForTitle = new JPanel(new GridLayout());
        JLabel titleForTheWindow = new JLabel("Welcome to the game!");
        JPanel panelForLabel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel textForRequest = new JLabel("Digit your nickname: ");
        JPanel panelForTextField = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField textField = new JTextField(15);
        JPanel panelForButton = new JPanel();
        JButton buttonForNext = new JButton("Next");
        this.mapOfComponents.put("TitlePanel", panelForTitle);
        this.mapOfComponents.put("TitleText", titleForTheWindow);
        this.mapOfComponents.put("PanelForLabel", panelForLabel);
        this.mapOfComponents.put("LabelTextForRequest", textForRequest);
        this.mapOfComponents.put("PanelForTextField", panelForTextField);
        this.mapOfComponents.put("TextField", textField);
        this.mapOfComponents.put("PanelForButton", panelForButton);
        this.mapOfComponents.put("ButtonForNext", buttonForNext);

        titleForTheWindow.setFont(fontForTitle);
        titleForTheWindow.setHorizontalAlignment(SwingConstants.CENTER);
        panelForTitle.add(titleForTheWindow);

        textForRequest.setFont(fontForNormalText);
        panelForLabel.add(textForRequest);

        textField.setFont(fontForNormalText);
        panelForTextField.add(textField);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    String nickname;
                    JTextField textField = (JTextField)mapOfComponents.get("TextField");
                    nickname = textField.getText();
                    if(nickname.length()!=0){
                        AnswerInitializationEvent ans = new AnswerInitializationEvent(nickname,null,0,false,false);
                        initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
                    }
                }

            }
        });
        buttonForNext.addActionListener(new ListenerForNextButton(this, initializationView));
        panelForButton.add(buttonForNext);

        contentPane.add(panelForTitle);
        contentPane.add(panelForLabel);
        contentPane.add(panelForTextField);
        contentPane.add(panelForButton);
        frame.pack();
        frame.setLocation((int) (dimensionOfScreen.getWidth() - frame.getWidth()) / 2,
                (int) (dimensionOfScreen.getHeight() - frame.getHeight()) / 2);

        frame.setVisible((true));
    }

    /**
     * Handles the retake of the nickname when the one chosen by player is already used or not valid.
     */
    public void handleRetakeForNickname() {
        JLabel labelText;
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        labelText.setText("Nickname invalid or already used! Try again: ");
        frame.pack();
    }

    /**
     * Handles the retake of the choose of the color of figure when the one chosen is already used by someone else.
     */
    public void handleRetakeForColor() {
        JLabel labelText;
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        labelText.setText("Color already used! Try again: ");
        frame.pack();
    }

    /**
     * Handles the choose of the color.
     */
    public void setColor() {
        JLabel labelText;
        JPanel panelForColorButtons;
        JPanel panelForNextButton;
        JButton nextButton;
        JTextField textField;
        panelForNextButton = (JPanel) mapOfComponents.get("PanelForButton");
        panelForColorButtons = (JPanel) mapOfComponents.get("PanelForTextField");
        nextButton = (JButton) mapOfComponents.get("ButtonForNext");
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        textField = (JTextField) mapOfComponents.get("TextField");
        labelText.setText("Choose your color: ");
        panelForNextButton.remove(nextButton);
        mapOfComponents.remove("ButtonForNext");
        frame.getContentPane().remove(panelForNextButton);
        mapOfComponents.remove("PanelForButton");
        frame.getContentPane().revalidate();
        frame.repaint();
        JButton buttonYellow = new JButton("Yellow");
        JButton buttonPurple = new JButton("Purple");
        JButton buttonGreen = new JButton("Green");
        JButton buttonGrey = new JButton("Grey");
        JButton buttonBlue = new JButton("Blue");
        panelForColorButtons.remove(textField);
        mapOfComponents.remove("TextField");
        panelForColorButtons.add(buttonBlue);
        mapOfComponents.put("Blue", buttonBlue);
        panelForColorButtons.add(buttonGreen);
        mapOfComponents.put("Green", buttonGreen);
        panelForColorButtons.add(buttonGrey);
        mapOfComponents.put("Grey", buttonGrey);
        panelForColorButtons.add(buttonPurple);
        mapOfComponents.put("Purple", buttonPurple);
        panelForColorButtons.add(buttonYellow);
        mapOfComponents.put("Yellow", buttonYellow);
        ListenerForColorButtons listenerForColorButtons = new ListenerForColorButtons(initializationView);
        for(Component button : panelForColorButtons.getComponents()){
            ((JButton)button).setActionCommand(((JButton) button).getText());
            ((JButton)button).addActionListener(listenerForColorButtons);
        }
        makeVisibleFrame();
    }

    /**
     * Handles the choose about play the game with or without frenzy mode.
     */
    public void setFrenzy() {
        JLabel labelText;
        JPanel panelForButtons;
        JButton yesButton = new JButton("YES");
        JButton noButton = new JButton("NO");
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        labelText.setText("Do you want to have frenzy mode during the last round?");
        panelForButtons = (JPanel) mapOfComponents.get("PanelForTextField");
        panelForButtons.remove(mapOfComponents.remove("Blue"));
        panelForButtons.remove(mapOfComponents.remove("Purple"));
        panelForButtons.remove(mapOfComponents.remove("Yellow"));
        panelForButtons.remove(mapOfComponents.remove("Green"));
        panelForButtons.remove(mapOfComponents.remove("Grey"));
        frame.repaint();
        yesButton.setActionCommand("Y");
        noButton.setActionCommand("N");
        ListenerForYesNoButtons listenerForYesNoButtons = new ListenerForYesNoButtons(initializationView);
        yesButton.addActionListener(listenerForYesNoButtons);
        noButton.addActionListener(listenerForYesNoButtons);
        panelForButtons.add(yesButton);
        panelForButtons.add(noButton);
        mapOfComponents.put("Y", yesButton);
        mapOfComponents.put("N", noButton);
        makeVisibleFrame();
    }


    /**
     * Handles the choose of the type of map to play with.
     */
    public void setMap() {
        JLabel labelText;
        JPanel panelForButtons;
        ListenerForMapButtons listenerForMapButtons  = new ListenerForMapButtons(initializationView);
        JButton buttonMap1 = new JButton("");
        buttonMap1.setActionCommand("1");
        ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/board/maps/map1/onlymatrix1.png")));
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(icon.getIconWidth()/15, icon.getIconHeight()/15, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        buttonMap1.setIcon(icon);
        buttonMap1.addActionListener(listenerForMapButtons);
        JButton buttonMap2 = new JButton("");
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/board/maps/map2/onlymatrix2.png")));
        image = icon.getImage();
        newImg = image.getScaledInstance(icon.getIconWidth()/15, icon.getIconHeight()/15, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        buttonMap2.setIcon(icon);
        buttonMap2.setActionCommand("2");
        buttonMap2.addActionListener(listenerForMapButtons);
        JButton buttonMap3 = new JButton("");
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/board/maps/map3/onlymatrix3.png")));
        image = icon.getImage();
        newImg = image.getScaledInstance(icon.getIconWidth()/15, icon.getIconHeight()/15, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        buttonMap3.setIcon(icon);
        buttonMap3.addActionListener(listenerForMapButtons);
        buttonMap3.setActionCommand("3");
        JButton buttonMap4 = new JButton("");
        icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/board/maps/map4/onlymatrix4.png")));
        image = icon.getImage();
        newImg = image.getScaledInstance(icon.getIconWidth()/15, icon.getIconHeight()/15, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        buttonMap4.setIcon(icon);
        buttonMap4.addActionListener(listenerForMapButtons);
        buttonMap4.setActionCommand("4");
        JButton dontCareButton = new JButton("Don't Care");
        dontCareButton.setContentAreaFilled(true);
        dontCareButton.addActionListener(listenerForMapButtons);
        dontCareButton.setActionCommand("0");
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        labelText.setText("<html>Which map do you want to play with? <br> (If you choose 'Don't Care' the game will choose for you) </html> ");
        panelForButtons = (JPanel) mapOfComponents.get("PanelForTextField");
        panelForButtons.remove(mapOfComponents.remove("Y"));
        panelForButtons.remove(mapOfComponents.remove("N"));
        frame.repaint();
        panelForButtons.add(buttonMap1);
        panelForButtons.add(buttonMap2);
        panelForButtons.add(buttonMap3);
        panelForButtons.add(buttonMap4);
        panelForButtons.add(dontCareButton);
        mapOfComponents.put("Map1", buttonMap1);
        mapOfComponents.put("Map2", buttonMap2);
        mapOfComponents.put("Map3", buttonMap3);
        mapOfComponents.put("Map4", buttonMap4);
        mapOfComponents.put("Don't Care", dontCareButton);
        makeVisibleFrame();
    }

    /**
     * Handles the choose to player with or without terminator.
     */
    public void setTerminator() {
        JLabel labelText;
        JPanel panelForButtons;
        JButton yesButton = new JButton("YES");
        yesButton.setActionCommand("YT");
        JButton noButton = new JButton("NO");
        noButton.setActionCommand("NT");
        ListenerForYesNoButtons listenerForYesNoButtons = new ListenerForYesNoButtons(initializationView);
        labelText = (JLabel) mapOfComponents.get("LabelTextForRequest");
        labelText.setText("Would you like play with terminator if the game will start with three/four players? ");
        panelForButtons = (JPanel) mapOfComponents.get("PanelForTextField");
        panelForButtons.remove(mapOfComponents.remove("Map1"));
        panelForButtons.remove(mapOfComponents.remove("Map2"));
        panelForButtons.remove(mapOfComponents.remove("Map3"));
        panelForButtons.remove(mapOfComponents.remove("Map4"));
        panelForButtons.remove(mapOfComponents.remove("Don't Care"));
        frame.repaint();
        panelForButtons.add(yesButton);
        panelForButtons.add(noButton);
        mapOfComponents.put("Y", yesButton);
        mapOfComponents.put("N", noButton);
        yesButton.addActionListener(listenerForYesNoButtons);
        noButton.addActionListener(listenerForYesNoButtons);
        makeVisibleFrame();
    }

    /**
     * Makes the frame visible after call pack on it and locates it in middle of the screen.
     */
    private void makeVisibleFrame() {
        frame.pack();
        frame.setLocation((int) (dimensionOfScreen.getWidth() - frame.getWidth()) / 2,
                (int) (dimensionOfScreen.getHeight() - frame.getHeight()) / 2);

        frame.setVisible(true);
    }

    /**
     * Handles the last phase of the initialization phase asking to remain in wait for the start of the game.
     */
    public void remainInListeningForTheStartGame() {
        frame.getContentPane().removeAll();
        mapOfComponents.clear();
        frame.repaint();
        contentPane.setLayout(new GridBagLayout());
        int topBottom = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/21.6);
        int leftRight = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/38.4);
        contentPane.setBorder(new EmptyBorder(topBottom, leftRight, topBottom, leftRight));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel gif = new JLabel("");
        gif.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/loading.gif"))));
        JLabel labelToAskForWait = new JLabel("Setup completed! Wait for the start of the game...");
        labelToAskForWait.setFont(fontForTitle);
        contentPane.add(labelToAskForWait,gbc);
        gbc.gridy++;
        gbc.insets = new Insets(frame.getHeight()/20, 0, 0, 0);
        contentPane.add(gif,gbc);
        makeVisibleFrame();
    }

    Map<String, Component> getMapOfComponents() {
        return mapOfComponents;
    }

    public JFrame getFrame() {
        return frame;
    }
}
