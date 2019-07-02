package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for the next button in the frame of initialization.
 * @author Pietro L'Imperio
 */
public class ListenerForNextButton implements ActionListener {

    /**
     * The reference of the frame for initialization.
     */
    private FrameForInitialization frameForInitialization;
    /**
     * The reference of the initialization view.
     */
    private InitializationView initializationView;

    /**
     * Constructor where class fields are initialized.
     */
    public ListenerForNextButton(FrameForInitialization frameForInitialization, InitializationView initializationView) {
        this.frameForInitialization = frameForInitialization;
        this.initializationView = initializationView;
    }

    /**
     * Creates an event that permits to go on with initialization and sends it towards the network.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname;
        JTextField textField = (JTextField) this.frameForInitialization.getMapOfComponents().get("TextField");
        nickname = textField.getText();
        AnswerInitializationEvent ans = new AnswerInitializationEvent(nickname,null,0,false,false);
        initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
    }
}
