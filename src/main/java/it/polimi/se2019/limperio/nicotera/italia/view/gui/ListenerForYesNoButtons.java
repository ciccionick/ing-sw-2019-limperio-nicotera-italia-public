package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the buttons concerning the choice to have terminator and frenzy mode during the game.
 * @author Pietro L'Imperio
 */
public class ListenerForYesNoButtons implements ActionListener {
    /**
     * The reference of initialization view.
     */
    private InitializationView initializationView;

    /**
     * Constructor where the class field is initialized.
     */
     ListenerForYesNoButtons(InitializationView initializationView) {
        this.initializationView = initializationView;
    }

    /**
     * Creates an event to communicate to the server if the first player decides if he wants terminator or frenzy mode during the game.
     * Sends this event generated towards the network.
     */
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
