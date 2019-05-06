package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FrameForInitialization extends JFrame {

    Map<JComponent, String> mapOfComponents = new HashMap<>();
    private Dimension dimensionOfScreen;
    Font fontForTitle = new Font(Font.SERIF, Font.BOLD,20);
    Font fontForNormalText = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

    public FrameForInitialization(String title) throws HeadlessException {
        super(title);
        dimensionOfScreen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        JPanel panelForTitle = new JPanel(new FlowLayout());
        JLabel titleForTheWindow = new JLabel("Welcome to the game!");
        titleForTheWindow.setFont(fontForTitle);
        panelForTitle.add(titleForTheWindow);
        JPanel panelForText = new JPanel(new BorderLayout());
        JLabel textForRequest = new JLabel("Digit your nickname: ");
        textForRequest.setFont(fontForNormalText);
        panelForText.add(textForRequest,BorderLayout.NORTH);
        JTextField textField = new JTextField(2);
        textField.setFont(fontForNormalText);
        panelForText.add(textField, BorderLayout.CENTER);
        JLabel instructionAboutNextButton = new JLabel("Press 'Next' to set your nickname");
        instructionAboutNextButton.setFont(fontForNormalText);
        panelForText.add(instructionAboutNextButton,BorderLayout.SOUTH);
        JPanel panelForButton = new JPanel(new FlowLayout());
        JButton buttonForNext = new JButton("Next");
        panelForButton.add(buttonForNext);
        this.getContentPane().add(panelForTitle, BorderLayout.NORTH);
        this.getContentPane().add(panelForText, BorderLayout.CENTER);
        this.getContentPane().add(panelForButton, BorderLayout.SOUTH);
        setLocation((int)(dimensionOfScreen.getWidth()-this.getWidth())/2,
                (int)(dimensionOfScreen.getHeight()-this.getHeight())/2);
        setVisible((true));
    }
}
