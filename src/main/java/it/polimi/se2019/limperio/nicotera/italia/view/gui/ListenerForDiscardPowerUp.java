package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the choice of power up cards in two different occasion.
 * The first one is when the player has to discard a power up card to spawn.
 * The second one is when the player has to discard a power up card to pay.
 */
 class ListenerForDiscardPowerUp implements  ActionListener{
    /**
     * The reference of the main frame.
     */
    private MainFrame mainFrame;
    /**
     * The reference to the alias card representing the power up card linked with an instance of this listener.
     */
        private ServerEvent.AliasCard card;
    /**
     * The event of request to discard a power up card received by server side.
     */
    private ServerEvent event;
    /**
     * The reference of the class where the dialog where happens the choice is created.
     */
        private PopupForDiscardPowerUp popup;

    /**
     * The constructor where class fields are initialized.
     */
        ListenerForDiscardPowerUp(MainFrame mainFrame, ServerEvent.AliasCard aliasCard, ServerEvent receivedEvent, PopupForDiscardPowerUp popup) {
            this.mainFrame = mainFrame;
            this.card = aliasCard;
            this.event = receivedEvent;
            this.popup = popup;
        }

    /**
     * Creates an event between RequestToDiscardPowerUpCardToSpawnEvent and RequestToDiscardPowerUpCardToPay calling the notify of the remote view.
     */
    @Override
        public void actionPerformed(ActionEvent e) {
            if(event.isRequestToDiscardPowerUpCardToSpawnEvent()) {
                DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                newEvent.setPowerUpCard(card);
                popup.getDialog().setVisible(false);
                mainFrame.getRemoteView().notify(newEvent);
            }
            if(event.isRequestToDiscardPowerUpCard()) {
                DiscardPowerUpCard newEvent = new DiscardPowerUpCard("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                newEvent.setToCatch(((RequestToDiscardPowerUpCard)event).isToCatch());
                newEvent.setToPayAnEffect(((RequestToDiscardPowerUpCard)event).isToPayAnEffect());
                newEvent.setToReload(((RequestToDiscardPowerUpCard)event).isToReload());
                newEvent.setToTargeting(((RequestToDiscardPowerUpCard)event).isToTargeting());
                newEvent.setToTagback(((RequestToDiscardPowerUpCard)event).isToTagback());
                newEvent.setToReload(((RequestToDiscardPowerUpCard)event).isToReload());
                if(!e.getActionCommand().equals("No one")){
                    newEvent.setNameOfPowerUpCard(card.getName());
                    newEvent.setColorOfCard(card.getColor());
                }
                if(popup.getTimer()!=null)
                    popup.getTimer().cancel();
                mainFrame.getRemoteView().notify(newEvent);
                popup.getDialog().setVisible(false);

            }
        }
    }

