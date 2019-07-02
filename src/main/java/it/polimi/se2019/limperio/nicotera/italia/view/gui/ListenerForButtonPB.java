package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons to choose to view a player board of a specific player.
 * @author Pietro L'Imperio
 */
    class ListenerForButtonPB implements ActionListener {

    /**
     * Reference of the main frame.
     */
    private MainFrame mainFrame;

    /**
     * Constructor of the class with initialization of main frame class field.
     * @param mainFrame The reference of main frame.
     */
        ListenerForButtonPB(MainFrame mainFrame) {
            this.mainFrame = mainFrame;
        }

    /**
     * Recreates the left panel with the player board panel containing information about the player board of the player chosen.
     */
    @Override
        public void actionPerformed(ActionEvent e) {
            if(mainFrame.getLeftPanel().getPlayerBoardPanel().getDialogForMarks()!=null)
                mainFrame.getLeftPanel().getPlayerBoardPanel().getDialogForMarks().setVisible(false);
            if(mainFrame.getLeftPanel().getPlayerBoardPanel().getDialogForDamage()!=null)
                mainFrame.getLeftPanel().getPlayerBoardPanel().getDialogForDamage().setVisible(false);
            mainFrame.getFrame().getContentPane().remove(mainFrame.getLeftPanel());
            mainFrame.setLeftPanel(new LeftPanel(mainFrame, mainFrame.getRemoteView().getPlayerBoardViewOfThisPlayer(e.getActionCommand())));
            mainFrame.getFrame().getContentPane().add(mainFrame.getLeftPanel(), BorderLayout.WEST);
            mainFrame.getFrame().getContentPane().repaint();
            mainFrame.getFrame().getContentPane().validate();
            mainFrame.getLeftPanel().getPlayerBoardPanel().addDialogForMarks();
            mainFrame.getLeftPanel().getPlayerBoardPanel().addDialogForDamage();
        }
    }

