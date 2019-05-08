package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForYesNoButtons implements ActionListener {
    private InitializationView initializationView;

     ListenerForYesNoButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AnswerInitializationEvent ans = null;
        if(e.getActionCommand().equals("Y"))
            ans = new AnswerInitializationEvent(null, null, 0, true, false);
        if(e.getActionCommand().equals("N"))
            ans = new AnswerInitializationEvent(null, null, 0, false, false);
        if(e.getActionCommand().equals("YT")){
            ans = new AnswerInitializationEvent(null, null, 0, false, true);
        }
        if(e.getActionCommand().equals("NT")){
            ans = new AnswerInitializationEvent(null, null, 0, false, false);
        }
        initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
    }
}
