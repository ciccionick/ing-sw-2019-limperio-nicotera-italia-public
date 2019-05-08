package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForMapButtons implements ActionListener {

    private InitializationView initializationView;

    public ListenerForMapButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int typeMap;
        AnswerInitializationEvent ans;
        switch (e.getActionCommand()){
            case "1":
                typeMap=1;
                break;
            case "2":
                typeMap=2;
                break;
            case "3":
                typeMap=3;
                break;
            case "4":
                typeMap=4;
                break;
                default:
                    typeMap=0;
        }
        ans = new AnswerInitializationEvent(null, null, typeMap,false, false);
        initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
    }
}
