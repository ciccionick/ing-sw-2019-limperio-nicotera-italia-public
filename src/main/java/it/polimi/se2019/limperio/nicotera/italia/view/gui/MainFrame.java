package it.polimi.se2019.limperio.nicotera.italia.view.gui;


import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.*;
import it.polimi.se2019.limperio.nicotera.italia.model.Square;
import it.polimi.se2019.limperio.nicotera.italia.view.RemoteView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Handles every aspect about GUI. Creates the panel that compose the main panel setted with a border layout.
 * @author Pietro L'Imperio
 */
public class MainFrame {

    /**
     * The principal frame of the game
     */
    private JFrame frame;
    /**
     * The main panel of the frame.
     */
    private JPanel contentPane;
    /**
     * The reference of remote view.
     */
    private RemoteView remoteView;
    /**
     * The left panel in the border layout.
     */
    private LeftPanel leftPanel;
    /**
     * The right panel in the border layout.
     */
    private RightPanel rightPanel;
    /**
     * The central panel in the border layout. The panel with the representation of the map.
     */
    private MapPanel mapPanel;
    /**
     * The upper panel in the border layout.
     */
    private KillshotTrackPanel killshotTrackPanel;
    /**
     * The reference of the class that create dialog to choose a weapon card.
     */
    private PopupForChooseWeaponCard popupForChooseWeaponCard;

    /**
     * Constructor that creates the frame of the game calling the constructor of sinlge panels.
     * @param remoteView The reference of remote view.
     */
    public MainFrame(RemoteView remoteView) {
        this.remoteView = remoteView;
        frame = new JFrame("Adrenaline");
        frame.setIconImage(getResource("/favicon.jpg"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        contentPane = new JPanel();
        frame.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
       /* contentPane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateFrame();
            }
        });*/
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                    updateFrame();
                }});

        mapPanel = new MapPanel(this);
        contentPane.add(mapPanel, BorderLayout.CENTER);

        killshotTrackPanel = new KillshotTrackPanel(this);
        contentPane.add(killshotTrackPanel, BorderLayout.NORTH);


        leftPanel = new LeftPanel(this, remoteView.getMyPlayerBoardView());
        contentPane.add(leftPanel, BorderLayout.WEST);

        rightPanel = new RightPanel(this);
        contentPane.add(rightPanel.getPanel(), BorderLayout.EAST);
        frame.setVisible(true);
    }

    /**
     * Creates a dialog to show the messages contained in the events received by the server
     * @param receivedEvent The event received by the server
     */
    public void showMessage(ServerEvent receivedEvent) {
        new DialogForMessage(this, receivedEvent);
    }

    /**
     * Handles the request to discard a power up card calling the constructor of the class that makes visible a dialog that permits the choice.
     * @param receivedEvent The event received by server.
     */
    public void handleRequestToDiscardPowerUpCard(ServerEvent receivedEvent) {
        new PopupForDiscardPowerUp(this, receivedEvent);
    }


    /**
     * Restores the left panel when the player is viewing the player board of the nickname passed by parameter.
     * @param nicknameOfThePlayerInvolvedInTheUpdate The nickname of the player whose the player board has been updated.
     */
    public void updateLeftPanelForWhoIsViewing(String nicknameOfThePlayerInvolvedInTheUpdate) {
        if (leftPanel.getPlayerBoardPanel().getPlayerBoardViewed().getNicknameOfPlayer().equals(nicknameOfThePlayerInvolvedInTheUpdate)) {
            if (leftPanel.getPlayerBoardPanel().getDialogForDamage() != null)
                leftPanel.getPlayerBoardPanel().getDialogForDamage().setVisible(false);
            if (leftPanel.getPlayerBoardPanel().getDialogForMarks() != null)
                leftPanel.getPlayerBoardPanel().getDialogForMarks().setVisible(false);
            LeftPanel newLeftPanel = new LeftPanel(this, remoteView.getPlayerBoardViewOfThisPlayer(nicknameOfThePlayerInvolvedInTheUpdate));
            contentPane.remove(leftPanel);
            contentPane.add(newLeftPanel, BorderLayout.WEST);
            leftPanel = newLeftPanel;
            contentPane.validate();
            contentPane.repaint();
            leftPanel.getPlayerBoardPanel().addDialogForDamage();
            leftPanel.getPlayerBoardPanel().addDialogForMarks();
        }
    }

    /**
     * Restores the panel of action in the right panel.
     */
    public void updatePanelOfAction() {
        rightPanel.getPanelOfActions().updateStateOfButton();
    }

    /**
     * Makes enable only the JLabel in the map panel that represents square selectable.
     * @param squareSelectable The list of squares selectable.
     */
    public void updateEnableSquares(ArrayList<Square> squareSelectable) {
        mapPanel.updateEnableSquares(squareSelectable);
    }

    /**
     * Calls the constructor of the class that creates a dialog to choose a weapon.
     * @param receivedEvent The event received by server
     */
    public void showPopupForChooseWeapon(ServerEvent receivedEvent) {
        if (receivedEvent.isRequestForChooseAWeaponToCatch())
            popupForChooseWeaponCard = new PopupForChooseWeaponCard(receivedEvent, this);
        if (receivedEvent.isRequestToDiscardWeaponCard())
            popupForChooseWeaponCard = new PopupForChooseWeaponCard(receivedEvent, this);
        if (receivedEvent.isRequestSelectionWeaponToReload())
            popupForChooseWeaponCard = new PopupForChooseWeaponCard(receivedEvent, this);
    }

    /**
     * Hides the dialog created with showPopupForChooseWeapon().
     */
    public void hidePopup() {
        if (popupForChooseWeaponCard != null)
            popupForChooseWeaponCard.getPopupForChooseW().setVisible(false);
    }

    /**
     * Adds the figures of the players on the map.
     */
    public void updateFigureOnMap() {
        mapPanel.addFigureOnSquare(this);
    }

    /**
     * Restores the north panel adding the dialogs that show the situation on the killshot track.
     */
    public void updateNorthPanel() {
        contentPane.remove(killshotTrackPanel);
        if (killshotTrackPanel.getDialogForNormalSkull() != null) {
            killshotTrackPanel.getDialogForNormalSkull().setVisible(false);
            killshotTrackPanel.setNullDialogForNormalSkull();
        }

        if (killshotTrackPanel.getDialogForFrenzySkull() != null) {
            killshotTrackPanel.getDialogForFrenzySkull().setVisible(false);
            killshotTrackPanel.setNullDialogForFrenzySkull();
        }

        killshotTrackPanel = new KillshotTrackPanel(this);
        contentPane.add(killshotTrackPanel, BorderLayout.NORTH);
        contentPane.validate();
        contentPane.repaint();

        killshotTrackPanel.addDialogForNormalKillshot();
        killshotTrackPanel.addDialogForFrenzyKillshot();
    }

    /**
     * Calls the constructor of the class that creates the dialog to let the player choose an effect to shoot.
     * @param receivedEvent The event received by server.
     */
    public void handleRequestToChooseAnEffect(ServerEvent receivedEvent) {
        PopupForChooseEffect popupForChooseEffect = new PopupForChooseEffect(this, (RequestToChooseAnEffect) receivedEvent);
        popupForChooseEffect.getDialog().setVisible(true);
    }

    /**
     * Restores the panel of players in the right panel.
     */
    public void updatePanelOfPlayers() {
        rightPanel.getPanel().remove(rightPanel.getPanelOfPlayers());
        rightPanel.setPanelOfPlayers(new PanelOfPlayers(this));
        rightPanel.getPanel().add(rightPanel.getPanelOfPlayers(), rightPanel.getGbcPanelOfPlayers());
        contentPane.validate();
        contentPane.repaint();

    }

    /**
     * Calls the constructor of the class that creates the dialog to choose ammo or power up cards to pay.
     * @param receivedEvent The event received by server.
     */
    public void handleRequestToPayWithAmmoOrPUC(ServerEvent receivedEvent) {
        new PopupToPayWithAmmoOrPowerUpCard(this, receivedEvent);
    }

    /**
     * Calls the constructor of the class that creates the dialog to choose a player.
     * @param receivedEvent The event received by server.
     */
    public void handleRequestToChooseAPlayer(ServerEvent receivedEvent) {
        new PopupToChooseAPlayer(this, receivedEvent);
    }

    /**
     * Calls the constructor of the class that creates the dialog to choose more than a  player.
     * @param receivedEvent The event received by server.
     */
    public void handleRequestToChooseMultiplePlayers(ServerEvent receivedEvent) {
        new PopupToChooseMultiplePlayers(receivedEvent, this);
    }


    /**
     * Get the number of scale in function of the dimension of the frame.
     * @param height It's true if the measure is related a height, otherwise false.
     * @param originalSize The original value of the measure.
     * @return The number of scale to divide the dimension of the frame.
     */
    int resizeInFunctionOfFrame(boolean height, int originalSize){
        int sizeOfReference;
        if(height)
            sizeOfReference = frame.getHeight();
        else
            sizeOfReference = frame.getWidth();
        return sizeOfReference/originalSize;
    }

    /**
     * Get the image read by file.
     * @param path The path where go to read the image
     * @return An Image object related with the read file.
     */
    Image getResource(String path){
        return Toolkit.getDefaultToolkit().getImage(getClass().getResource(path));
    }

     private void updateFrame() {
        for (JDialog dialog : mapPanel.getDialogForFigure()) {
            dialog.setVisible(false);
        }
        if (killshotTrackPanel.getDialogForNormalSkull() != null) {
            killshotTrackPanel.getDialogForNormalSkull().setVisible(false);
            killshotTrackPanel.setNullDialogForNormalSkull();
        }
        if (killshotTrackPanel.getDialogForFrenzySkull() != null) {
            killshotTrackPanel.getDialogForFrenzySkull().setVisible(false);
            killshotTrackPanel.setNullDialogForFrenzySkull();
        }

        if (leftPanel.getPlayerBoardPanel().getDialogForDamage() != null)
            leftPanel.getPlayerBoardPanel().getDialogForDamage().setVisible(false);
        if (leftPanel.getPlayerBoardPanel().getDialogForMarks() != null)
            leftPanel.getPlayerBoardPanel().getDialogForMarks().setVisible(false);
        contentPane.removeAll();

        mapPanel = new MapPanel(this);
        contentPane.add(mapPanel, BorderLayout.CENTER);

        killshotTrackPanel = new KillshotTrackPanel(this);
        contentPane.add(killshotTrackPanel, BorderLayout.NORTH);

        leftPanel = new LeftPanel(this, leftPanel.getPlayerBoardView());
        contentPane.add(leftPanel, BorderLayout.WEST);

        rightPanel = new RightPanel(this);
        contentPane.add(rightPanel.getPanel(), BorderLayout.EAST);

        contentPane.validate();
        contentPane.repaint();

        mapPanel.addFigureOnSquare(this);
        killshotTrackPanel.addDialogForNormalKillshot();
        killshotTrackPanel.addDialogForFrenzyKillshot();
        leftPanel.getPlayerBoardPanel().addDialogForDamage();
        leftPanel.getPlayerBoardPanel().addDialogForMarks();
    }

    KillshotTrackPanel getKillshotTrackPanel() {
        return killshotTrackPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    RemoteView getRemoteView() {
        return remoteView;
    }

    JFrame getFrame() {
        return frame;
    }

    void setLeftPanel(LeftPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    LeftPanel getLeftPanel() {
        return leftPanel;
    }

    MapPanel getMapPanel() {
        return mapPanel;
    }



}


