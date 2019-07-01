package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardAsAmmo;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.DiscardPowerUpCardToSpawnEvent;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.RequestToDiscardPowerUpCard;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class ListenerForDiscardPowerUp implements  ActionListener{
        private MainFrame mainFrame;
        private ServerEvent.AliasCard card;
        private ServerEvent event;
        private PopupForDiscardPowerUp popup;

        ListenerForDiscardPowerUp(MainFrame mainFrame, ServerEvent.AliasCard aliasCard, ServerEvent receivedEvent, PopupForDiscardPowerUp popup) {
            this.mainFrame = mainFrame;
            this.card = aliasCard;
            this.event = receivedEvent;
            this.popup = popup;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(event.isRequestToDiscardPowerUpCardToSpawnEvent()) {
                DiscardPowerUpCardToSpawnEvent newEvent = new DiscardPowerUpCardToSpawnEvent("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                newEvent.setPowerUpCard(card);
                popup.getDialog().setVisible(false);
                mainFrame.getRemoteView().notify(newEvent);
            }
            if(event.isRequestToDiscardPowerUpCardToPay()) {
                DiscardPowerUpCardAsAmmo newEvent = new DiscardPowerUpCardAsAmmo("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
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

