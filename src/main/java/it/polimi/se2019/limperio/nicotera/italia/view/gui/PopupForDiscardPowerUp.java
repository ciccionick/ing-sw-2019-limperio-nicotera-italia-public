package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardAsAmmo;
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

class PopupForDiscardPowerUp {

    private JDialog dialog;
    private MainFrame mainFrame;
    private java.util.Timer timer;
    private int delay = 20000;
    private TaskForTagbackTimer task;
    private static Logger loggerPopupForDiscardPUCard = Logger.getLogger("it.limperio.nicotera.italia.progettoINGSFTWPolimi");
    private static Handler handlerLoggerPopupForDiscardPUCard = new ConsoleHandler();

     PopupForDiscardPowerUp(MainFrame mainFrame, ServerEvent receivedEvent) {
         loggerPopupForDiscardPUCard.addHandler(handlerLoggerPopupForDiscardPUCard);
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());

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
         if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()))
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
            if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting()|| ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()))
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

        if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && (((RequestToDiscardPowerUpCard)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback())){
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

         if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && ((RequestToDiscardPowerUpCard)receivedEvent).isToTagback()){
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

    private class TaskForTagbackTimer extends TimerTask {
         @Override
         public void run() {
             DiscardPowerUpCardAsAmmo newEvent = new DiscardPowerUpCardAsAmmo("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
             newEvent.setToTagback(true);
             mainFrame.getRemoteView().notify(newEvent);
             dialog.setVisible(false);
         }
     }
 }
