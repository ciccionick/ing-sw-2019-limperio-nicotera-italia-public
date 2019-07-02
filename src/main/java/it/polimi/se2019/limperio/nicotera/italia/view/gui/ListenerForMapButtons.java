package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener of the buttons concerning the choice of the type of map in the initialization phase.
 * @author Pietro L'Imperio
 */
public class ListenerForMapButtons implements ActionListener {

    /**
     * The reference of the initialization view.
     */
    private InitializationView initializationView;

    /**
     * The constructor that initialize the class field.
     */
    public ListenerForMapButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    /**
     * Creates an event with the information of the map chosen by the first player and sends it towards the network.
     */
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
