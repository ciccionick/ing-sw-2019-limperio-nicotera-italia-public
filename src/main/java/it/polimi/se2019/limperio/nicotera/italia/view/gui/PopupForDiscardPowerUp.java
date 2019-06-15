package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardAsAmmo;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCardToPay;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

class PopupForDiscardPowerUp {

    private JDialog dialog;
    private MainFrame mainFrame;
    private java.util.Timer timer;
    private int delay = 30000;
    private TaskForTagbackTimer task;

     PopupForDiscardPowerUp(MainFrame mainFrame, ServerEvent receivedEvent) {
        this.mainFrame = mainFrame;
        dialog = new JDialog(mainFrame.getFrame());
        dialog.setModal(false);
        int width = (int) (mainFrame.getFrame().getWidth() / 2.08);
        int height = (int) (mainFrame.getFrame().getHeight() / 1.56);
        dialog.setSize(width, height);
        dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);
        dialog.setUndecorated(true);


        JPanel contentPanel = new JPanel(new GridBagLayout());
        dialog.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5,5));
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
         gbcLabelMessage.insets = new Insets(5, 5, 5, 5);
         gbcLabelMessage.gridx = 0;
         gbcLabelMessage.gridy = 0;
         gbcLabelMessage.gridwidth = listOfPowerUpCards.size();
         if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && (((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTagback()))
            gbcLabelMessage.gridwidth++;
             contentPanel.add(message, gbcLabelMessage);

         contentPanel.add(message,gbcLabelMessage);

         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 1;
         gbc.insets = new Insets(10, 10, 10, 10);

         ImageIcon icon;
         Image image;
         String folderPath = "resources/powerupcards/";

        while(!listOfPowerUpCards.isEmpty()){
            JLabel card = new JLabel();
            String nameOfCard = listOfPowerUpCards.get(0).getName();
            String color = listOfPowerUpCards.get(0).getColor().toString();
            icon = new ImageIcon(folderPath.concat(nameOfCard+ " ").concat(color+".png"));
            image = icon.getImage().getScaledInstance(widthCard, heightCard, Image.SCALE_SMOOTH);
            icon = new ImageIcon(image);
            card.setIcon(icon);
            contentPanel.add(card,gbc);
            card.addMouseListener(new ListenerForPowerUpCard(card, mainFrame, gbc.gridx+1));

            gbc.gridy++;
            JButton button = new JButton();
            if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && ((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTargeting())
                button.setText("Use");
            else
                button.setText("Discard");
            button.setActionCommand(nameOfCard.concat(" "+ color));
            button.addActionListener(new ListenerForDiscardPowerUp(mainFrame, listOfPowerUpCards.get(0), receivedEvent));
            contentPanel.add(button,gbc);

            gbc.gridy=1;
            gbc.gridx++;
            listOfPowerUpCards.remove(0);

        }

        if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && (((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTargeting() || ((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTagback())){
            JButton buttonToNotDiscard = new JButton("No one");
            buttonToNotDiscard.setActionCommand("No one");
            gbc.gridy=1;
            buttonToNotDiscard.addActionListener(new ListenerForDiscardPowerUp(mainFrame, null, receivedEvent));
            contentPanel.add(buttonToNotDiscard, gbc);
        }

        if(receivedEvent.isRequestToDiscardPowerUpCardToPay() && ((RequestToDiscardPowerUpCardToPay)receivedEvent).isToTagback()){
            timer = new Timer();
            task = new TaskForTagbackTimer();
            try{
                timer.schedule(task,delay);
            }
            catch (IllegalStateException er){
                er.printStackTrace();
            }
        }

        dialog.pack();
         dialog.setLocation((int) (mainFrame.getFrame().getLocation().getX() + mainFrame.getFrame().getSize().getWidth() - dialog.getWidth()) / 2,
                 (int) (mainFrame.getFrame().getLocation().getY() + mainFrame.getFrame().getSize().getHeight() - dialog.getHeight()) / 2);

         dialog.setVisible(true);


     }

     private void addPowerUpCardsToDiscard(ArrayList<ServerEvent.AliasCard> listOfPowerUpCards, ServerEvent receivedEvent) {
         ArrayList<ServerEvent.AliasCard> nameOfPowerUpCards = ((RequestToDiscardPowerUpCardToPay)receivedEvent).getPowerUpCards();
         for(ServerEvent.AliasCard powerUpCard : mainFrame.getRemoteView().getMyPlayerBoardView().getPowerUpCardsDeck()){
             for(ServerEvent.AliasCard card : nameOfPowerUpCards){
                 if(card.getName().equals(powerUpCard.getName())&&card.getColor().equals(powerUpCard.getColor())) {
                     listOfPowerUpCards.add(powerUpCard);
                     break;
                 }
             }
         }
     }

      class ListenerForDiscardPowerUp implements ActionListener {
         private MainFrame mainFrame;
         private ServerEvent.AliasCard card;
         private ServerEvent event;

          ListenerForDiscardPowerUp(MainFrame mainFrame, ServerEvent.AliasCard aliasCard, ServerEvent receivedEvent) {
                this.mainFrame = mainFrame;
                this.card = aliasCard;
                this.event = receivedEvent;
          }

         @Override
         public void actionPerformed(ActionEvent e) {
              if(event.isRequestToDiscardPowerUpCardToSpawnEvent()) {
                  DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                  newEvent.setPowerUpCard(card);
                  dialog.setVisible(false);
                  mainFrame.getRemoteView().notify(newEvent);
              }
              if(event.isRequestToDiscardPowerUpCardToPay()) {
                  DiscardPowerUpCardAsAmmo newEvent = new DiscardPowerUpCardAsAmmo("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                  newEvent.setToCatch(((RequestToDiscardPowerUpCardToPay)event).isToCatch());
                  newEvent.setToPayAnEffect(((RequestToDiscardPowerUpCardToPay)event).isToPayAnEffect());
                  newEvent.setToReload(((RequestToDiscardPowerUpCardToPay)event).isToReload());
                  newEvent.setToTargeting(((RequestToDiscardPowerUpCardToPay)event).isToTargeting());
                  newEvent.setToTagback(((RequestToDiscardPowerUpCardToPay)event).isToTagback());
                  if(!e.getActionCommand().equals("No one")){
                      newEvent.setNameOfPowerUpCard(card.getName());
                      newEvent.setColorOfCard(card.getColor());
                  }
                  mainFrame.getRemoteView().notify(newEvent);
                  dialog.setVisible(false);

              }
         }
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
