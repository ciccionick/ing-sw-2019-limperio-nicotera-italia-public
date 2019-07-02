package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons concerning the choice of the color of the figure in the initialization phase.
 * @author Pietro L'Imperio
 */
public class ListenerForColorButtons implements ActionListener {
    /**
     * The reference of the initialization view.
     */
    private InitializationView initializationView;

    /**
     * Constructor where class field is initialized
     * @param initializationView The reference of the initialization view.
     */
     ListenerForColorButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    /**
     * Creates an event AnswerInitializationEvent for the choice of the color and sends it towards the network.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AnswerInitializationEvent ans;
         String color;
         color = e.getActionCommand();
         ans = new AnswerInitializationEvent(null, color, 0, false, false);
         initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);

    }
}
