package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForColorButtons implements ActionListener {
    private InitializationView initializationView;

     ListenerForColorButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AnswerInitializationEvent ans;
         String color;
         color = e.getActionCommand();
         ans = new AnswerInitializationEvent(null, color, 0, false, false);
         initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);

    }
}
