package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.RequestToUseEffect;
import it.polimi.se2019.limperio.nicotera.italia.events.events_by_server.ServerEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class ListenerForChooseEffect implements ActionListener{
        MainFrame mainFrame;
        ServerEvent.AliasCard weaponCard;
        PopupForChooseEffect popup;

        ListenerForChooseEffect(MainFrame mainFrame, ServerEvent.AliasCard weaponCard, PopupForChooseEffect popup) {
            this.mainFrame = mainFrame;
            this.weaponCard = weaponCard;
            this.popup = popup;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int numOfEffect = Integer.parseInt(e.getActionCommand());
            if(e.getActionCommand().equals("0") || popup.getEffectButtons().get(numOfEffect-1).isEnabled()){
                RequestToUseEffect requestToUseEffect = new RequestToUseEffect("", mainFrame.getRemoteView().getMyPlayerBoardView().getNicknameOfPlayer());
                requestToUseEffect.setNumOfEffect(numOfEffect);
                popup.getDialog().setVisible(false);
                mainFrame.getRemoteView().notify(requestToUseEffect);


            }

        }
    }

