package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles the creation of the dialog to discard a power up card.
 * @author Pietro L'Imperio.
 */
class PopupForDiscardPowerUp {

    /**
     * Dialog created in the constructor of the class.
     */
    private JDialog dialog;
    /**
     * Reference of main frame.
     */
    private MainFrame mainFrame;
    /**
     * Timer for the choose to use Tagback grande.
     */
    private java.util.Timer timer;
    /**
     * Delay after that start the task relative to the timer.
     */
    private int delay = 20000;
    /**
     * Task that start at the end of the timer.
     */
    private TaskForTagbackTimer task;
    /**
     * Logger of the class to track possibly exception.
     */
    private static Logger loggerPopupForDiscardPUCard = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    /**
     * Handler of the logger.
     */
    private static Handler handlerLoggerPopupForDiscardPUCard = new ConsoleHandler();

    /**
     * Constructor of the class where the dialog is created.
     * @param mainFrame Reference of main frame
     * @param receivedEvent Event received by server.
     */
     PopupForDiscardPowerUp(MainFrame mainFrame, ServerEvent receivedEvent) {
         loggerPopupForDiscardPUCard.addHandler(handlerLoggerPopupForDiscardPUCard);
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setAutoRequestFocus(true);
        dialog.setUndecorated(true);

        int topBottomBorder = mainFrame.getFrame().getHeight()/mainFrame.resizeInFunctionOfFrame(true, 10);
        int leftRightBorder = mainFrame.getFrame().getWidth()/mainFrame.resizeInFunctionOfFrame(false, 10);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder));
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        dialog.getContentPane().add(contentPanel);

        ArrayList<ServerEvent.AliasCard> listOfPowerUpCards = new ArrayList<>();
        if(receivedEvent.isRequestToDiscardPowerUpCardToSpawnEvent())
            listOfPowerUpCards.addAll(mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck());
        else
            addPowerUpCardsToDiscard(listOfPowerUpCards,receivedEvent);

         int widthCard;
         int heightCard;

         if (mainFrame.getFrame().getSize().getWidth() / mainFrame.getFrame().getSize().getHeight() < 1.80) {
             widthCard = (int) (mainFrame.getFrame().getSize().getWidth() / 15);
             heightCard = (int) (widthCard * 1.70);
         } else {
             heightCard = (int) (mainFrame.getFrame().getSize().getHeight() / 5);
             widthCard = (int) (heightCard * 0.59);
         }



        JTextArea message = new JTextArea(receivedEvent.getMessageForInvolved());
         message.setBackground(SystemColor.menu);
         message.setEditable(false);
         GridBagConstraints gbcLabelMessage = new GridBagConstraints();
         gbcLabelMessage.insets = new Insets(topBottomBorder/2, leftRightBorder/2, topBottomBorder/2, leftRightBorder/2);
         gbcLabelMessage.gridx = 0;
         gbcLabelMessage.gridy = 0;
         gbcLabelMessage.gridwidth = listOfPowerUpCards.size();
         if(receivedEvent.isRequestToDiscardPowerUpCard() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()))
            gbcLabelMessage.gridwidth++;
             contentPanel.add(message, gbcLabelMessage);

         contentPanel.add(message,gbcLabelMessage);

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.insets = new Insets(topBottomBorder, leftRightBorder, topBottomBorder, leftRightBorder);

         ImageIcon icon;
         Image image;
         String folderPath = "/powerupcards/";

        while(!listOfPowerUpCards.isEmpty()){
            JLabel card = new JLabel();
            String nameOfCard = listOfPowerUpCards.get(0).getName();
            String color = listOfPowerUpCards.get(0).getColor().toString().toLowerCase();
            String path = folderPath.concat(nameOfCard+" ").concat(color+".png");
            icon = new ImageIcon(mainFrame.getResource(path));
            image = icon.getImage().getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            card.setIcon(icon);
            contentPanel.add(card,gbc);

            gbc.gridy++;
            JButton button = new JButton();
            if(receivedEvent.isRequestToDiscardPowerUpCard() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting()|| ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()))
                button.setText("Use");
            else
                button.setText("Discard");
            button.setActionCommand(nameOfCard.concat(" "+ color));
            button.addActionListener(new ListenerForDiscardPowerUp(mainFrame, listOfPowerUpCards.get(0), receivedEvent,this));
            contentPanel.add(button,gbc);

            gbc.gridy=1;
            gbc.gridx++;
            listOfPowerUpCards.remove(0);
        }

        if(receivedEvent.isRequestToDiscardPowerUpCard() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback())){
            JButton buttonToNotDiscard = new JButton("No one");
            buttonToNotDiscard.setActionCommand("No one");
            gbc.gridy=2;
            buttonToNotDiscard.addActionListener(new ListenerForDiscardPowerUp(mainFrame, null, receivedEvent,this));
            contentPanel.add(buttonToNotDiscard, gbc);
        }

        dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

         dialog.setVisible(true);

         if(receivedEvent.isRequestToDiscardPowerUpCard() && ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()){
             timer = new Timer();
             task = new TaskForTagbackTimer();
             try{
                 timer.schedule(task,delay);
             }
             catch (IllegalStateException er){
                 loggerPopupForDiscardPUCard.log(Level.ALL, "error");
             }
         }
     }


    /**
     * Adds to a list the power up cards present in the event.
     * @param listOfPowerUpCards List of alias card among which the player can choose to discard
     * @param receivedEvent Event received by the server to add to the list alias cards.
     */
     private void addPowerUpCardsToDiscard(ArrayList<ServerEvent.AliasCard> listOfPowerUpCards, ServerEvent receivedEvent) {
         ArrayList<ServerEvent.AliasCard> nameOfPowerUpCards = ((RequestToDiscardPowerUpCard)receivedEvent).getPowerUpCards();
         for(ServerEvent.AliasCard powerUpCard : mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck()){
             for(ServerEvent.AliasCard card : nameOfPowerUpCards){
                 if(card.getName().equals(powerUpCard.getName())&&card.getColor().equals(powerUpCard.getColor())) {
                     listOfPowerUpCards.add(powerUpCard);
                     break;
                 }
             }
         }
     }

    public JDialog getDialog() {
        return dialog;
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * Task that starts after the end of the timer that sends to the server side an automatically message with the will to not use Tagback granade.
     */
    private class TaskForTagbackTimer extends TimerTask {
         @Override
         public void run() {
             DiscardPowerUpCard newEvent = new DiscardPowerUpCard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
             newEvent.setToTagback(true);
             mainFrame.getRemoteView().notify(newEvent);
             dialog.setVisible(false);
         }
     }
 }
