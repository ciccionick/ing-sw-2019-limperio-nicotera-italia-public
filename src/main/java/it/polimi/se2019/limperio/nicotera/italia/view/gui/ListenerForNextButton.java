package it.polimi.se2019.limperio.nicotera.italia.view.gui;

import it.polimi.se2019.limperio.nicotera.italia.events.events_by_client.AnswerInitializationEvent;
import it.polimi.se2019.limperio.nicotera.italia.view.InitializationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerForNextButton implements ActionListener {

    private FrameForInitialization frameForInitialization;
    private InitializationView initializationView;

    public ListenerForNextButton(FrameForInitialization frameForInitialization, InitializationView initializationView) {
        this.frameForInitialization = frameForInitialization;
        this.initializationView = initializationView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname;
        JTextField textField = (JTextField) this.frameForInitialization.getMapOfComponents().get("TextField");
        nickname = textField.getText();
        AnswerInitializationEvent ans = new AnswerInitializationEvent(nickname,null,0,false,false);
        initializationView.getRemoteView().getNetworkHandler().replyToRequestOfInitialization(ans);
    }
}
